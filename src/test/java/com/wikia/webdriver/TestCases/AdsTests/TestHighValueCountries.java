package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.ContentPatterns.WikiFactoryVariables.wikiFactoryVariables;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFactoryPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestHighValueCountries extends NewTestTemplate {

	private Credentials credentials = config.getCredentials();
	private String communityHVC;
	private SpecialFactoryPageObject wikiFactory;

	@BeforeMethod(alwaysRun=true)
	public void logIn() {
		SpecialUserLoginPageObject userLoginPageObject = new SpecialUserLoginPageObject(driver);
		userLoginPageObject.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
	}

	@Test(
		dataProvider="getWikisWithNonStandardHVC", dataProviderClass=AdsDataProvider.class,
		groups= {"HVC", "HVC_nonStandard"}
	)
	public void TestNonStandardHVC(String wikiName) {
		String testedWiki = urlBuilder.getUrlForWiki(wikiName);
		wikiFactory = new SpecialFactoryPageObject(driver);
		wikiFactory.openWikiFactoryPage(testedWiki);
		wikiFactory.verifyVariableValueDifferentThenDefault(wikiFactoryVariables.wgHighValueCountries);
	}

	@Test(
		dataProvider="getWikisWithStandardHVC", dataProviderClass=AdsDataProvider.class,
		groups= {"HVC", "HVC_Standard"}
	)
	public void TestStandardHVC(String wikiName) {
		String testedWiki = urlBuilder.getUrlForWiki(wikiName);
		wikiFactory = new SpecialFactoryPageObject(driver);
		wikiFactory.openWikiFactoryPage(testedWiki);
		String valueOnCommunity = wikiFactory.getVariableDefaultValue(
			wikiFactoryVariables.wgHighValueCountries
		);
		wikiFactory.verifyWgVariableValueSameAsDefault(
			wikiFactoryVariables.wgHighValueCountries, valueOnCommunity, testedWiki
		);
	}
}
