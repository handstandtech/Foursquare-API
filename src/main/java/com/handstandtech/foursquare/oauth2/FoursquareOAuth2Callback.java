package com.handstandtech.foursquare.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handstandtech.foursquare.v2.util.FoursquareUtils;

/**
 * An OAuth2 callback handler.
 * 
 * @author Sam Edwards
 */
@SuppressWarnings("serial")
public abstract class FoursquareOAuth2Callback extends HttpServlet {

	protected final Logger log = LoggerFactory.getLogger(getClass().getName());

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	/**
	 * Exchange an OAuth request token for an access token, and store the latter
	 * in cookies.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		this.request = request;
		this.response = response;

		String code = request.getParameter("code");
		String clientId = getClientId(request);
		String clientSecret = getClientSecret(request);
		String callbackUri = getCallbackUrl(request);
		String accessToken = FoursquareUtils.getAccessToken(request, code, clientId, clientSecret, callbackUri);
		log.info("access_token -> " + accessToken);

		handleAccessTokenFound(accessToken);
		redirectToFoursquareApp();
	}

	protected abstract String getCallbackUrl(HttpServletRequest request);

	protected abstract String getClientId(HttpServletRequest request);

	protected abstract String getClientSecret(HttpServletRequest request);

	protected abstract void handleAccessTokenFound(String accessToken);

	protected abstract void redirectToFoursquareApp();

}
