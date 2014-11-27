package com.wikia.webdriver.TestCases.AdsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;

/**
 * @author Piotr PMG Gackowski
 * @ownership AdEngineering
 * Below test cases are prepared to check status of parameters
 * allowing targeting ads to Top 1000 WAM Wikis.
 * 1. TestTopWamWikis_GeoEdgeFree - go to two wikis and check that first of them have
 * correct parameter and second don`t have parameter.
 * 2. TestTopWamWikisWikifactory_GeoEdgeFree - go to Special:WhereIsMyExtension
 * and check how many wikis have set wgAdDriverWikiIsTop1000 variable.
 * Need in settings wikiName=community
 *
 */

public class TestTopWamWikis extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	Integer numberOfTop1kWikis = 1000;
	String extensionURL = "var=1429&searchType=bool&val=2&likeValue=true";

	public TestTopWamWikis() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@Test(
		dataProvider="topWamWikis", dataProviderClass=AdsDataProvider.class,
		groups= {"TopWamWikis"}
	)
	public void TestTopWamWikis_GeoEdgeFree(String wikiName, String article, Boolean isTop) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTop1kParamState(isTop);
	}

	@Test(groups = {"TopWamWikisWhereIsMyExtension"})
	public void TopWamWikisWhereIsMyExtension_GeoEdgeFree() {
		String whereIsExtensionUrl = urlBuilder.getUrlForPath(URLsContent.COMMUNITY_WIKI, URLsContent.SPECIAL_WHERE_IS_EXTENSION);
		whereIsExtensionUrl = urlBuilder.appendQueryStringToURL(whereIsExtensionUrl, extensionURL);
		WikiBasePageObject wikiPage = new WikiBasePageObject(driver);
		wikiPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		wikiPage.getUrl(whereIsExtensionUrl);
		wikiPage.verifyNumberOfTop1kWikis(numberOfTop1kWikis);
	}
}
