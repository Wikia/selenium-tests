package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.ContentPatterns.WikiFactoryVariablesProvider.WikiFactoryVariables;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFactoryPageObject;
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
			WikiFactoryVariables.wgHighValueCountries
		);
		String wgHVC_JSConsole = instantGlobalsPrefix + WikiFactoryVariables.wgHighValueCountries.toString();
		Object[] wgHVC_valueInConsole = wikiFactory.getWgVariableKeysFromPage(testedWiki, wgHVC_JSConsole);

		wikiFactory.verifyWgVariableValuesTheSame(
			wgHVC_valueInConsole, variableKeysFromCommunity
		);
	}
}
