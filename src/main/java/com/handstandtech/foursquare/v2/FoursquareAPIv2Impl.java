package com.handstandtech.foursquare.v2;

import java.net.HttpURLConnection;
import com.handstandtech.restclient.server.util.RESTUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handstandtech.foursquare.shared.model.v2.FoursquareCheckin;
import com.handstandtech.foursquare.shared.model.v2.FoursquareUser;
import com.handstandtech.foursquare.v2.util.FoursquareUtils;
import com.handstandtech.restclient.server.RESTClient;
import com.handstandtech.restclient.server.impl.RESTClientAppEngineURLFetchImpl;
import com.handstandtech.restclient.shared.model.RESTResult;
import com.handstandtech.restclient.shared.model.RequestMethod;
import com.handstandtech.restclient.shared.util.RESTURLUtil;

/**
 * Talks to Foursquare.
 * 
 * @author Sam Edwards
 * @version 2010.07.09 - Uses {@link HttpURLConnection} now so we can
 *          disconnect()
 */
public class FoursquareAPIv2Impl implements FoursquareAPIv2 {
	protected static final String V2_BASE_URL = "https://api.foursquare.com/v2";

	protected static Logger log = LoggerFactory
			.getLogger(FoursquareAPIv2Impl.class.getName());

	protected String oauth_token;

	public FoursquareAPIv2Impl() {
	}

	public FoursquareAPIv2Impl(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	protected static Long getCreatedTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yy HH:mm:ss Z");
		try {
			return format.parse(time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws FoursquareNot200Exception
	 * @see FoursquareAPIv2#friendRequest(java .lang.String)
	 */
	@Override
	public FoursquareUser friendRequest(String id)
			throws FoursquareNot200Exception {
		Map<String, String> params = createBaseParams();
		String url = RESTURLUtil.createFullUrl(V2_BASE_URL + getUserURI(id)
				+ "/request", params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.POST, url);

		FoursquareUtils.checkMetaFromResult(result);
		return FoursquareUtils.getFoursquareUserFromResult(result);
	}

	private Map<String, String> createBaseParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("v", "20120501"); // Last Updated May 1st
		params.put("oauth_token", oauth_token);
		return params;
	}

	private RESTClient getRestClientImpl() {
		return new RESTClientAppEngineURLFetchImpl();
		// return new RESTClientJavaNetImpl();
	}

	public static String getUserURI(String id) {
		return "/users/" + id;
	}

	/**
	 * @throws FoursquareNot200Exception
	 * @see FoursquareAPIv2#unFriendRequest(java .lang.String)
	 */
	@Override
	public void unFriendRequest(String id) throws FoursquareNot200Exception {
		Map<String, String> params = createBaseParams();
		String url = RESTURLUtil.createFullUrl(V2_BASE_URL + "/users/" + id
				+ "/unfriend", params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.POST, url);

		FoursquareUtils.checkMetaFromResult(result);
	}

	/**
	 * @see FoursquareAPIv2#getFriends(java.lang .String, java.lang.Integer)
	 */
	@Override
	public RESTResult getFriends(String id, Integer offset) {
		Map<String, String> params = createBaseParams();
		params.put("limit", "500");
		if (offset != null) {
			params.put("offset", offset.toString());
		}
		String url = RESTURLUtil.createFullUrl(V2_BASE_URL + "/users/" + id
				+ "/friends", params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url);
		log.debug(result.toString());
		return result;
	}

	/**
	 * @throws FoursquareNot200Exception
	 * @see FoursquareAPIv2#getUserInfo(java. lang.String)
	 */
	@Override
	public FoursquareUser getUserInfo(String id)
			throws FoursquareNot200Exception {
		Map<String, String> params = createBaseParams();
		String url = RESTURLUtil.createFullUrl(V2_BASE_URL + "/users/" + id,
				params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url);
		return FoursquareUtils.getFoursquareUserFromResult(result);
	}

	public static String createCSVLine(List<String> requestURIs) {
		StringBuffer sb = new StringBuffer();
		for (String s : requestURIs) {
			sb.append(s + ",");
		}
		if (requestURIs.size() > 0) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	/**
	 * @see FoursquareAPIv2#getMultiResult(java .lang.String)
	 */
	@Override
	public RESTResult getMultiResult(String multi) {
		Map<String, String> params = createBaseParams();
		params.put("requests", multi);
		String endpoint = "https://api.foursquare.com/v2/multi";

		String url = RESTURLUtil.createFullUrl(endpoint, params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url);
		return result;
	}

	public static String getUserInfoURI(String encodedBrandId) {

		return null;
	}

	public static boolean isProduction(String string) {
		if (string.toLowerCase().contains("localhost")) {
			return false;
		}

		if (string.toLowerCase().contains("127.0.0.1")) {
			return false;
		}

		return true;
	}

	/**
	 * @see FoursquareAPIv2#getUserInfosForIds (Collection)
	 */
	@Override
	public Collection<FoursquareUser> getUserInfosForIds(
			Collection<String> allIds) {
		Collection<FoursquareUser> users = new HashSet<FoursquareUser>();
		int count = 0;
		List<String> userURIsToGet = new ArrayList<String>();
		for (String id : allIds) {
			String encodedUserId = RESTUtil.encode(id);
			userURIsToGet.add(FoursquareAPIv2Impl.getUserURI(encodedUserId));
			count++;

			if (count == 5) {
				users.addAll(getFoursquareUsersForURIs(userURIsToGet));
				count = 0;
				userURIsToGet.clear();
			}
		}

		if (userURIsToGet.size() > 0) {
			users.addAll(getFoursquareUsersForURIs(userURIsToGet));
		}

		return users;
	}

	protected List<FoursquareUser> getFoursquareUsersForURIs(List<String> uris) {
		String userUris = FoursquareAPIv2Impl.createCSVLine(uris);
		RESTResult result = getMultiResult(userUris);

		List<FoursquareUser> users = new ArrayList<FoursquareUser>();
		try {
			JSONObject obj = new JSONObject(result.getResponseBody());
			JSONArray responses = obj.getJSONObject("response").getJSONArray(
					"responses");
			for (int i = 0; i < responses.length(); i++) {
				JSONObject firstResponse = responses.getJSONObject(i);
				JSONObject jsonResponse = firstResponse
						.getJSONObject("response");
				JSONObject userJson = jsonResponse.getJSONObject("user");
				FoursquareUser user = FoursquareUtils
						.getFoursquareUserFromJson(userJson.toString());
				users.add(user);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		log.info("Found Users: " + users.size());
		return users;
	}

	@Override
	public HashSet<FoursquareUser> getAllFriends() {
		HashSet<FoursquareUser> friends = new HashSet<FoursquareUser>();
		// Boolean keepGoing = true;
		int offset = 0;
		String userId = "self";
		RESTResult result = getFriends(userId, offset);
		Integer friendCount = FoursquareUtils
				.getTotalFriendCountFromResult(result);
		log.debug("Total Friend Count: " + friendCount);
		if (friendCount > 0) {
			while (offset < friendCount) {
				List<FoursquareUser> friendResponseList = null;
				if (offset == 0) {
					friendResponseList = FoursquareUtils
							.getFriendListFromResult(result);
				} else {
					result = getFriends(userId, offset);
					friendResponseList = FoursquareUtils
							.getFriendListFromResult(result);
				}
				friends.addAll(friendResponseList);
				offset = offset + 500;
			}
		}
		return friends;
	}

	@Override
	public void logout() {

	}

	@Override
	public Collection<FoursquareCheckin> getRecentCheckins() {
		return getRecentCheckins(null, null, null);
	}

	@Override
	public Collection<FoursquareCheckin> getRecentCheckins(Integer limit,
			Integer afterTimestamp, Integer beforeTimestamp) {
		Map<String, String> params = createBaseParams();
		if (limit != null) {
			params.put("limit", limit.toString());
		}
		if (afterTimestamp != null) {
			params.put("afterTimestamp", afterTimestamp.toString());
		}
		if (beforeTimestamp != null) {
			params.put("beforeTimestamp", beforeTimestamp.toString());
		}
		String url = RESTURLUtil.createFullUrl(
				V2_BASE_URL + "/checkins/recent", params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url);
		return FoursquareUtils.getCheckinListFromResult(result);
	}

	@Override
	public Collection<FoursquareCheckin> getSelfCheckinHistory(Integer limit,
			Integer offset) {
		Map<String, String> params = createBaseParams();

		if (limit != null) {
			params.put("limit", limit.toString());
		}
		if (offset != null) {
			params.put("offset", offset.toString());
		}

		String url = RESTURLUtil.createFullUrl(V2_BASE_URL
				+ "/users/self/checkins", params);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url);
		return FoursquareUtils.getSelfCheckinListFromResult(result);

	}

}
