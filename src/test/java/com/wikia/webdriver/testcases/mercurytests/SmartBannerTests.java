package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SmartBannerComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**l
 * @authors: Rodrigo Gomez, Łukasz Nowak
 * @ownership: Mobile Web
 */

public class SmartBannerTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	
	private static final String URL_TO_TEST_SMART_BANNER = "http://glee.wikia.com/";
	
	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, URL_TO_TEST_SMART_BANNER);
	}

	//SBT01
	@Test(groups = {"MercurySmartBannerTest_001", "MercurySmartBannerTests", "Mercury"})
	public void MercurySmartBannerTest_001_UserCanCloseSmartBanner() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryWiki("glee");
		SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
		banner.clickCloseButton();
		banner.verifySmartBannerWasClosed();
	}
	
	//SBT02
	@Test(groups = {"MercurySmartBannerTest_002", "MercurySmartBannerTests", "Mercury"})
	public void MercurySmartBannerTest_002_FixPositionOfSmartBanner() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryWiki("glee");
		SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		banner.verifyFixPositionOfSmartBanner(touchAction);
	}
	
	//SBT03
	@Test(groups = {"MercurySmartBannerTest_003", "MercurySmartBannerTests", "Mercury"})
	public void MercurySmartBannerTest_003_AndroidUserSeeInstallButton() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryWiki("glee");
		SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
		banner.verifyButtonName(SmartBannerComponentObject.BUTTON_NAME_FOR_ANDROID);
	}
	
	//SBT04
	@Test(groups = {"MercurySmartBannerTest_004", "MercurySmartBannerTests", "Mercury"})
	public void MercurySmartBannerTest_004_IOSUserSeeGETButton() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryWiki("glee");
		SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
		banner.verifyButtonName(SmartBannerComponentObject.BUTTON_NAME_FOR_IOS);
	}
}
