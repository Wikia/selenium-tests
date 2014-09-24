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
			"Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_1 like Mac OS X) "
			+ "AppleWebKit/537.51.2 (KHTML, like Gecko) "
			+ "Version/7.0 Mobile/11D257 Safari/9537.53"
		);
	}

	public String getUserAgent(String userAgent) {
		return userAgentRegistry.get(userAgent);
	}
}
