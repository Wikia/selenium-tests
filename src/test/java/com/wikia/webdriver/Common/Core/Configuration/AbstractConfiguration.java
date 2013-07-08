package com.wikia.webdriver.Common.Core.Configuration;

import com.wikia.webdriver.Common.Properties.Credentials;

import java.io.File;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public abstract class AbstractConfiguration {

	public abstract String getBrowser();
	public abstract String getEnv();
	public abstract String getWikiName();
	public abstract File getCaptchaFile();
	public abstract boolean loginCookieAvailable();
	protected abstract String getCredentialsFilePath();
	public Credentials getCredentials() {
		return new Credentials(new File(this.getCredentialsFilePath()));
	}
}
