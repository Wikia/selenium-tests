package com.wikia.webdriver.Common.Core;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class Configuration {

	private static HashMap<String, Object> config;

	private static void setConfigurationManually() {
		String configuration_file_path = "c:" + File.separator
				+ "selenium-config " + File.separator
				+ "config.xml";
		String captcha_file_path = "c:" + File.separator
				+"selenium-config" + File.separator
				+ "captcha.txt";

		config.put("BROWSER", "FF");
		config.put("ENV", "prod");
		config.put("QS", "?");
		config.put("WIKI_NAME", "mediawiki119");
		config.put("CONFIGURATION_FILE", new File(configuration_file_path));
		config.put("CAPTCHA_FILE", new File(captcha_file_path));
		config.put("LOG_VERBOSE", 2);
		config.put("LOG_ENABLED", true);

		if (((String) config.get("ENV")).contains("dev")) {
			config.put("LOGIN_BY_COOKIE", false);
		} else {
			config.put("LOGIN_BY_COOKIE", true);
		}
	}

	private static void setConfiguartionBasedOnPom() {
		config.put("BROWSER", System.getProperty("browser"));
		config.put("ENV", System.getProperty("env"));
		config.put("QS", System.getProperty("qs"));
		config.put("WIKI_NAME", System.getProperty("wiki-name"));
		config.put("CONFIGURATION_FILE", new File(System.getProperty("config")));
		config.put("CAPTCHA_FILE", new File(System.getProperty("captcha")));
		config.put("LOG_VERBOSE", 2);
		config.put("LOG_ENABLED", true);

		if (((String) config.get("ENV")).contains("dev")) {
			Global.LOGIN_BY_COOKIE = false;
		} else {
			config.put("LOGIN_BY_COOKIE", true);
		}
	}

	private static void setDefaultValues() {
		try {
			if (((String) config.get("ENV")).isEmpty()) {
				config.put("ENV", "prod");
			}
		} catch (NullPointerException ex) {
			config.put("ENV", "prod");
		}

		try {
			if (((String) config.get("BROWSER")).isEmpty()) {
				config.put("BROWSER", "FF");
			}
		} catch (NullPointerException ex) {
			config.put("BROWSER", "FF");
		}
	}

	public static HashMap<String, Object> getConfiguration() {
		config = new HashMap<String, Object>();
		try {
			if (System.getProperty("run_mvn").equals("true")) {
				setConfiguartionBasedOnPom();
			} else {
				setConfigurationManually();
			}
		} catch (NullPointerException ex) {
			setDefaultValues();
		}
		return config;
	}

}
