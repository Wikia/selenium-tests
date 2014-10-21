package com.wikia.webdriver.TestCases.VenusTests;

import com.wikia.webdriver.Common.DataProvider.Venus.VenusArticleDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Venus.VenusArticlePageObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 * @ownership Consumer
 */
public class TestTablesOnVenus extends NewTestTemplate {

	@Test(
			dataProvider = "getArticleWithScrollableTable",
			dataProviderClass = VenusArticleDataProvider.class,
			groups = {"TestTablesOnVenus_001", "TestTablesOnVenus"}
	)
	public void TestTablesOnVenus_001_scrollableTablePresent(String wikiName, String article) {
		String url = urlBuilder.getUrlForPath(wikiName, article);
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		venusArticle.verifyScrollableTablePresent();
	}

	@Test(
			dataProvider = "getArticleWithScrollableTableOnSmallResoultion",
			dataProviderClass = VenusArticleDataProvider.class,
			groups = {"TestTablesOnVenus_002", "TestTablesOnVenus"}
	)
	public void TestTablesOnVenus_002_scrollableTablePresentOnSmallResolution(String wikiName, String article, Dimension bigResolution, Dimension smallResolution) {
		String url = urlBuilder.getUrlForPath(wikiName, article);
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		venusArticle.resizeWindow(bigResolution);
		venusArticle.verifyScrollableTableNotPresent();
		venusArticle.resizeWindow(smallResolution);
		venusArticle.verifyScrollableTablePresent();
	}

	@Test(
			dataProvider = "getArticleWithNotScrollableTable",
			dataProviderClass = VenusArticleDataProvider.class,
			groups = {"TestTablesOnVenus_003", "TestTablesOnVenus"}
	)
	public void TestTablesOnVenus_002_scrollableTableNotPresent(String wikiName, String article) {
		String url = urlBuilder.getUrlForPath(wikiName, article);
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		venusArticle.verifyScrollableTableNotPresent();
	}
}
