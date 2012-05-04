package com.handstandtech.foursquare.v2.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.handstandtech.foursquare.shared.model.v2.FoursquareCheckin;
import com.handstandtech.foursquare.shared.model.v2.FoursquareMeta;
import com.handstandtech.foursquare.shared.model.v2.FoursquareUser;
import com.handstandtech.foursquare.v2.FoursquareNot200Exception;
import com.handstandtech.restclient.server.RESTClient;
import com.handstandtech.restclient.server.impl.RESTClientAppEngineURLFetchImpl;
import com.handstandtech.restclient.shared.model.RESTResult;
import com.handstandtech.restclient.shared.model.RequestMethod;
import com.handstandtech.restclient.shared.util.RESTURLUtil;

public class FoursquareUtils {

	private static Logger log = LoggerFactory.getLogger(FoursquareUtils.class);
	private static Gson gson = new Gson();

	public static void checkMetaFromResult(RESTResult result)
			throws FoursquareNot200Exception {
		if (result != null) {
			try {
				JSONObject entireResponse = getObjectFromResult(result);
				JSONObject metaJsonObj = entireResponse.getJSONObject("meta");
				FoursquareMeta meta = FoursquareUtils
						.createFoursquareMeta(metaJsonObj);
				if (meta.getCode() != 200) {
					// ERROR
					throw new FoursquareNot200Exception(meta);
				}
			} catch (JSONException e) {
				log.error("There was an error parsing the result: \n" + result);
			}

		} else {
			log.error("RESTResult was NULL: " + result);
		}
	}

	public static FoursquareUser getFoursquareUserFromResult(RESTResult result) {
		if (result != null) {
			try {
				JSONObject entireResponse = getObjectFromResult(result);
				JSONObject jsonUserObject = entireResponse.getJSONObject(
						"response").getJSONObject("user");
				if (jsonUserObject != null) {
					FoursquareUser user = getFoursquareUserFromJson(jsonUserObject);
					return user;
				}
			} catch (JSONException e) {
				log.error("There was an error parsing the result: \n" + result);
			}

		} else {
			log.error("RESTResult was NULL: " + result);
		}
		return null;
	}

	private static JSONObject getObjectFromResult(RESTResult result)
			throws JSONException {
		String body = result.getResponseBody();
		if (body != null) {
			JSONObject entireResponse = new JSONObject(body);
			return entireResponse;
		} else {
			log.error("Body was null: " + result);
			return null;
		}
	}

	private static FoursquareMeta createFoursquareMeta(JSONObject metaJsonObj) {
		return new Gson()
				.fromJson(metaJsonObj.toString(), FoursquareMeta.class);
	}

	private static FoursquareUser getFoursquareUserFromJson(
			JSONObject jsonUserObject) {
		return getFoursquareUserFromJson(jsonUserObject.toString());
	}

	public static FoursquareUser getFoursquareUserFromJson(String json) {
		return gson.fromJson(json, FoursquareUser.class);
	}

	public static FoursquareCheckin getFoursquareCheckinFromJson(String json) {
		return gson.fromJson(json, FoursquareCheckin.class);
	}

	public static Integer getTotalFriendCountFromResult(RESTResult result) {
		try {
			JSONObject responseObject = getObjectFromResult(result);
			return responseObject.getJSONObject("response")
					.getJSONObject("friends").getInt("count");
		} catch (JSONException e) {
			return 0;
		}
	}

	public static List<FoursquareUser> getFriendListFromResult(RESTResult result) {
		List<FoursquareUser> friends = new ArrayList<FoursquareUser>();
		try {
			JSONObject responseObject = getObjectFromResult(result);
			JSONArray jsonUserObjects = responseObject
					.getJSONObject("response").getJSONObject("friends")
					.getJSONArray("items");
			for (int i = 0; i < jsonUserObjects.length(); i++) {
				JSONObject jsonObj = jsonUserObjects.getJSONObject(i);
				String json = jsonObj.toString();
				FoursquareUser user = getFoursquareUserFromJson(json);
				friends.add(user);
			}
		} catch (JSONException e) {
			log.error("There was an error parsing the result: \n" + result);
		}
		return friends;
	}

	public static String getAccessToken(HttpServletRequest request,
			String code, String clientId, String clientSecret,
			String callbackUri) {
		String baseUrl = "https://foursquare.com/oauth2/access_token";
		Map<String, String> params = new HashMap<String, String>();
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("grant_type", "authorization_code");
		params.put("redirect_uri", callbackUri);
		params.put("code", code);
		String fullUrl = RESTURLUtil.createFullUrl(baseUrl, params);

		RESTClient client = new RESTClientAppEngineURLFetchImpl();
		RESTResult result = client.request(RequestMethod.GET, fullUrl);

		try {
			JSONObject jsonObj = new JSONObject(result.getResponseBody());
			return jsonObj.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Collection<FoursquareCheckin> getCheckinListFromResult(
			RESTResult result) {
		List<FoursquareCheckin> checkins = new ArrayList<FoursquareCheckin>();
		try {
			JSONObject responseObject = getObjectFromResult(result);
			JSONArray jsonCheckinObjects = responseObject.getJSONObject(
					"response").getJSONArray("recent");
			for (int i = 0; i < jsonCheckinObjects.length(); i++) {
				JSONObject jsonCheckinObj = jsonCheckinObjects.getJSONObject(i);
				String json = jsonCheckinObj.toString();
				FoursquareCheckin user = getFoursquareCheckinFromJson(json);
				checkins.add(user);
			}
		} catch (JSONException e) {
			log.error("There was an error parsing the result: \n" + result);
		}
		return checkins;
	}

	public static Collection<FoursquareCheckin> getSelfCheckinListFromResult(
			RESTResult result) {
		List<FoursquareCheckin> checkins = new ArrayList<FoursquareCheckin>();
		try {
			JSONObject responseObject = getObjectFromResult(result);
			JSONArray jsonCheckinObjects = responseObject
					.getJSONObject("response").getJSONObject("checkins")
					.getJSONArray("items");
			for (int i = 0; i < jsonCheckinObjects.length(); i++) {
				JSONObject jsonCheckinObj = jsonCheckinObjects.getJSONObject(i);
				String json = jsonCheckinObj.toString();
				FoursquareCheckin user = getFoursquareCheckinFromJson(json);
				checkins.add(user);
			}
		} catch (JSONException e) {
			log.error("There was an error parsing the result: \n" + result);
		}
		return checkins;
	}

}
