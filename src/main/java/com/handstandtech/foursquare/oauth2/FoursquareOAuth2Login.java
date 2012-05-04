package com.handstandtech.foursquare.oauth2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handstandtech.restclient.shared.util.RESTURLUtil;

/**
 * You Must Extend this class and implement the methods:
 * 
 * getClientId() and getClientUrl()
 * 
 * @author Sam Edwards
 */
@SuppressWarnings("serial")
public abstract class FoursquareOAuth2Login extends HttpServlet {

	protected final Logger log = LoggerFactory.getLogger(getClass().getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callbackUrl = getCallbackUrl(request);
		String clientId = getClientId(request);
		Map<String, String> params = new HashMap<String, String>();
		params.put("client_id", clientId);
		params.put("response_type", "code");
		params.put("redirect_uri", callbackUrl);
		String fullUrl = RESTURLUtil.createFullUrl("https://foursquare.com/oauth2/authenticate", params);
		log.info("Redirecting to Foursquare Login: " + fullUrl);
		response.sendRedirect(fullUrl);
	}

	protected abstract String getClientId(HttpServletRequest request);

	protected abstract String getCallbackUrl(HttpServletRequest request);
}
