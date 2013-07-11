package com.wikia.webdriver.Common.Core.Configuration;

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
