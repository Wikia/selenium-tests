package com.wikia.webdriver.Common.DriverProvider.MobileProvider;

import java.util.HashMap;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileDriversRegistry {

	private HashMap<String, String> devicesRegistry= new HashMap();

	public MobileDriversRegistry() {
		devicesRegistry.put("4.2", "0243e50a8ec7e8cb");
		devicesRegistry.put("4.4", "04d360941c8a5983");
	}

	public String getDeviceForAndroidVersion(String version) {
		return devicesRegistry.get(version);
	}
}
