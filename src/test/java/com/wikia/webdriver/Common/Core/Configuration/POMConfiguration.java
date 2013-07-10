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
		try {
			if (browser.isEmpty()) {
				browser = "FF"; //Set default value to Firefox;
			}
		} catch (NullPointerException ex) {
			browser = "FF";
		}

		env = System.getProperty("env");
		try {
			if (env.isEmpty()) {
				env = "prod"; //Set default value to production
			}
		} catch (NullPointerException ex) {
			env = "prod";
		}

		wikiName = System.getProperty("wiki-name");
		try {
			if (wikiName.isEmpty()) {
				wikiName = "mediawiki119"; //Set default value to mediawiki119
			}
		} catch (NullPointerException ex) {
			wikiName = "mediawiki119";
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
		return !env.contains("dev");
	}

	@Override
	public String getCredentialsFilePath() {
		return this.credentialsFilePath;
	}
}
