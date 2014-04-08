package com.wikia.webdriver.Common.Templates.Mobile;

import com.wikia.webdriver.Common.DriverProvider.MobileProvider.MobileDriverProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileTestTemplate extends NewTestTemplate {

	@Override
	public void startBrowser() {
		driver = new MobileDriverProvider().getDriverInstance(config.getPlatform(), config.getVersion());
	}
}
