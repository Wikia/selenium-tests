package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.WikiFactoryVariablesProvider.WikiFactoryVariables;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFactoryPageObject;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestHighValueCountries extends NewTestTemplate {

	private Credentials credentials = config.getCredentials();
	private SpecialFactoryPageObject wikiFactory;
	private String instantGlobalsPrefix = "Wikia.InstantGlobals.";

	private void logIn() {
		SpecialUserLoginPageObject userLoginPageObject = new SpecialUserLoginPageObject(driver);
		userLoginPageObject.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
	}

	@Test(
		dataProvider="getWikisWithStandardHVC", dataProviderClass=AdsDataProvider.class,
		groups= {"HVC", "HVC_Standard"}
	)
	public void TestStandardHVC(String wikiName) {
		logIn();
		String testedWiki = urlBuilder.getUrlForWiki(wikiName);
		wikiFactory = new SpecialFactoryPageObject(driver);
		wikiFactory.openWikiFactoryPage(testedWiki);
		Object[] variableKeysFromCommunity = wikiFactory.getVariableDefaultValueKeys(
			WikiFactoryVariables.WG_HIGH_VALUE_COUNTRIES
		);
		String wgHVC_JSConsole = instantGlobalsPrefix + WikiFactoryVariables.WG_HIGH_VALUE_COUNTRIES.toString();
		Object[] wgHVC_valueInConsole = wikiFactory.getWgVariableKeysFromPage(testedWiki, wgHVC_JSConsole);

		wikiFactory.verifyWgVariableValuesTheSame(
			wgHVC_valueInConsole, variableKeysFromCommunity
		);
	}
}
