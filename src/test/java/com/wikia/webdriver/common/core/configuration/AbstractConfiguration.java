package com.wikia.webdriver.common.core.configuration;

import com.wikia.webdriver.common.properties.Credentials;

import java.io.File;

/**
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

	public abstract String geMobileConfig();

	public abstract String getCredentialsFilePath();

	public Credentials getCredentials() {
		return new Credentials(new File(this.getCredentialsFilePath()));
	}
}
