package com.wikia.webdriver.common.driverprovider;

import java.util.HashMap;

/**
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
			"Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) "
				+ "AppleWebKit/536.26 (KHTML, like Gecko) "
				+ "Version/8.0 Mobile/10A5376e Safari/8536.25"
		);
	}

	public String getUserAgent(String userAgent) {
		return userAgentRegistry.get(userAgent);
	}
}
