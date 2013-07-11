package com.wikia.webdriver.Common.Core.Configuration;

import com.wikia.webdriver.Common.Properties.Credentials;

import java.io.File;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class POMConfiguration extends AbstractConfiguration {

	private String browser;
	private String env;
	private String wikiName;
	private String captchaPath;
	private String credentialsFilePath;

	public POMConfiguration() {
		browser = System.getProperty("browser");
		if (browser == null) {
			browser = "FF"; //Set default value to Firefox;
		}

		env = System.getProperty("env");
		if (env == null) {
			env = "prod"; //Set default value to production
		}

		wikiName = System.getProperty("wiki-name");
		if (wikiName == null) {
			wikiName = "mediawiki119"; //Set default value to mediawiki119
		}

		credentialsFilePath = System.getProperty("config");
		captchaPath = System.getProperty("captcha");
	}

	@Override
	public String getBrowser() {
		return this.browser;
	}

	@Override
	public String getEnv() {
		return this.env;
	}

	@Override
	public String getWikiName() {
		return this.wikiName;
	}

	@Override
	public File getCaptchaFile() {
		return new File(captchaPath);
	}

	@Override
	public boolean loginCookieAvailable() {
		return !(env.contains("dev") || (env.contains("sandbox")));
	}

	@Override
	public String getCredentialsFilePath() {
		return this.credentialsFilePath;
	}
}
