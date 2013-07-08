package com.wikia.webdriver.Common.Core.Configuration;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ConfigurationFactory {

	public static ConfigurationInterface getConfig() {
		if (System.getProperty("run_mvn").equals("true")) {
			return new POMConfiguration();
		} else {
			return new ManualConfiguration();
		}
	}
}
