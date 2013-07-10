package com.wikia.webdriver.Common.Core.Configuration;

import com.wikia.webdriver.Common.Properties.Credentials;

import java.io.File;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public interface ConfigurationInterface {

	public String getBrowser();
	public String getEnv();
	public String getWikiName();
	public File getCaptchaFile();
	public boolean loginCookieAvailable();
	public Credentials getCredentials();
}
