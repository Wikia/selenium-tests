package com.wikia.webdriver.common.core.configuration;

/**
 * @author Bogna 'bognix' Knychala
 */
public class ConfigurationFactory {

	private ConfigurationFactory() {

	}

	public static AbstractConfiguration getConfig() {

		if (System.getProperty("run_mvn") == null || "false".equals(System.getProperty("run_mvn"))) {
			return new ManualConfiguration();
		} else {
			return new POMConfiguration();
		}
	}
}
