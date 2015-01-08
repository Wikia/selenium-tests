package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SmartBannerComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**l
 * @authors: Rodrigo Gomez, Łukasz Nowak
 * @ownership: Mobile Web
 */

public class SmartBannerTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	@Test(groups = {"MercurySmartBannerTest_001", "MercurySmartBannerTests", "Mercury"})
	public void MercurySmartBannerTest_001_VerifyUserCanCloseSmartBanner() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryWiki(URLsContent.SMART_BANNER_GLEE);
		SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
		banner.clickCloseButton();
		banner.verifySmartBannerWasClosed();
	}

//	@Test(groups = {"MercurySmartBannerTest_002", "MercurySmartBannerTests", "Mercury"})
//	public void MercurySmartBannerTest_002_ClickInstallButton() {
//		MercuryBasePageObject base = new MercuryBasePageObject(driver);
//		base.openMercuryWiki(wikiURL, URLsContent.SMART_BANNER_GLEE);
//		SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
//		banner.clickInstallButton();
//		//TO DO
//		/*
//		* On our devices for tests phone should remember how to open links like these (with chrome)
//		* and verify link Google Play/App store
//		* */
//	}
}
