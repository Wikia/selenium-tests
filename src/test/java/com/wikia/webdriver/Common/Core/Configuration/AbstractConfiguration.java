package com.wikia.webdriver.Common.Core.Configuration;

import java.io.File;

import com.wikia.webdriver.Common.Properties.Credentials;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public abstract class AbstractConfiguration {

	public abstract String getBrowser();
	public abstract String getEnv();
	public abstract String getWikiName();
	public abstract String getQS();
	public abstract File getCaptchaFile();
	public abstract String getPlatformVersion();
	public abstract String getPlatform();
	public abstract String getDeviceId();
	public abstract boolean loginCookieAvailable();
	public abstract String getCredentialsFilePath();
	public Credentials getCredentials() {
		return new Credentials(new File(this.getCredentialsFilePath()));
	}
}
