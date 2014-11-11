package com.wikia.webdriver.common.core.configuration;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ConfigurationFactory {

	public static AbstractConfiguration getConfig() {

		if (System.getProperty("run_mvn") == null) {
			return new ManualConfiguration();
		} else {
			return new POMConfiguration();
		}
	}
}
