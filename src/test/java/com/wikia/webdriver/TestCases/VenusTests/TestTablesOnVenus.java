package com.wikia.webdriver.TestCases.VenusTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Venus.VenusArticlePageObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 *
 * @ownership Consumer
 */
public class TestTablesOnVenus extends NewTestTemplate {

	private final static String wikiName = "mediawiki119";
	private final static Dimension bigResolution = new Dimension(1920, 900);
	private final static Dimension smallResolution = new Dimension(768, 1024);

	@Test(groups = {"TestTablesOnVenus_001", "TestTablesOnVenus"})
	public void TestTablesOnVenus_001_scrollableTablePresent() {
		String url = urlBuilder.getUrlForPath(wikiName, "Tables/ScrollableTable");
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		Assertion.assertTrue(venusArticle.isScrollableTablePresent(), "Scrollable table should be present");
	}

	@Test(groups = {"TestTablesOnVenus_002", "TestTablesOnVenus"})
	public void TestTablesOnVenus_002_scrollableTablePresentOnSmallResolution() {
		String url = urlBuilder.getUrlForPath(wikiName, "Tables/ScrollableTableOnSmallRes");
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		venusArticle.resizeWindow(bigResolution);
		Assertion.assertFalse(venusArticle.isScrollableTablePresent(), "Scrollable table shouldn't be present");
		venusArticle.resizeWindow(smallResolution);
		Assertion.assertTrue(venusArticle.isScrollableTablePresent(), "Scrollable table should be present");
	}

	@Test(groups = {"TestTablesOnVenus_003", "TestTablesOnVenus"})
	public void TestTablesOnVenus_002_scrollableTableNotPresent() {
		String url = urlBuilder.getUrlForPath(wikiName, "Tables/NotScrollableTable");
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		Assertion.assertFalse(venusArticle.isScrollableTablePresent(), "Scrollable table shouldn't be present");
	}
}
