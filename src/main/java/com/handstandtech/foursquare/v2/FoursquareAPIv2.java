package com.handstandtech.foursquare.v2;

import java.util.Collection;

import com.handstandtech.foursquare.shared.model.v2.FoursquareCheckin;
import com.handstandtech.foursquare.shared.model.v2.FoursquareUser;
import com.handstandtech.restclient.shared.model.RESTResult;

public interface FoursquareAPIv2 {

	public abstract FoursquareUser friendRequest(String id)
			throws FoursquareNot200Exception;

	public abstract void unFriendRequest(String id)
			throws FoursquareNot200Exception;

	public abstract RESTResult getFriends(String id, Integer offset);

	public abstract FoursquareUser getUserInfo(String id)
			throws FoursquareNot200Exception;

	public abstract RESTResult getMultiResult(String multi);

	public abstract Collection<FoursquareUser> getUserInfosForIds(
			Collection<String> allIds);

	public abstract Collection<FoursquareUser> getAllFriends();

	public abstract void logout();

	public abstract Collection<FoursquareCheckin> getRecentCheckins();

	/**
	 * Returns a list of recent checkins from friends.
	 * 
	 * @param limit
	 *            Default 20 - Number of results to return, up to 100.
	 * @param afterTimestamp
	 *            Seconds after which to look for checkins, e.g. for looking for
	 *            new checkins since the last fetch. If more than limit results
	 *            are new since then, this is ignored. Checkins created prior to
	 *            this timestamp will still be returned if they have new
	 *            comments or photos, making it easier to poll for all new
	 *            activity.
	 * @return
	 */
	public abstract Collection<FoursquareCheckin> getRecentCheckins(
			Integer limit, Integer afterTimestamp, Integer beforeTimestamp);

	public abstract Collection<FoursquareCheckin> getSelfCheckinHistory(
			Integer limit, Integer offset);

	// public abstract void getRecentCheckins(String uid);

}