package com.wikia.webdriver.Common.Core.Configuration;

import java.io.File;

/**
 * @author: Bogna 'bognix' Knychała
 */

public class ManualConfiguration extends AbstractConfiguration {

	private String browser = "FF"; //Accepted values: FF, CHROME, GHOST, IE
	private String env = "prod"; //Accepted values: prod, dev-name, preview, sandbox-number
	private String wikiName = "mediawiki119"; //Mediawiki119 is default testing wiki

	protected String credentialsFilePath = ""; //Set path to your selenium-config/config.xml file
	private String captchaPath = ""; //Set path to your selenium-config/captcha.xml file

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
