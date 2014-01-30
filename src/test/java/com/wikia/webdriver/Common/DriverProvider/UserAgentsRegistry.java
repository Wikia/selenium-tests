package com.wikia.webdriver.Common.DriverProvider;

import java.util.HashMap;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class UserAgentsRegistry {

	private HashMap<String, String> userAgentRegistry;

	public UserAgentsRegistry() {
		userAgentRegistry = new HashMap();
		userAgentRegistry.put(
			"sony_tvs",
			"Mozilla/5.0(X11; sony_tvs; Linux x86_64; rv:24.0)"
			+ "Gecko/20100101 Firefox/24.0"
		);
		userAgentRegistry.put(
			"iPhone",
			"Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en)"
			+ "AppleWebKit/420+ (KHTML, like Gecko)"
			+ "Version/3.0 Mobile/1A543a Safari/419.3"
		);
	}

	public String getUserAgent(String userAgent) {
		return userAgentRegistry.get(userAgent);
	}
}
