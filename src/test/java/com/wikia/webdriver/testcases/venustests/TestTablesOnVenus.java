package com.wikia.webdriver.testcases.venustests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.venus.VenusArticlePageObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 *
 * @ownership Consumer
 */
public class TestTablesOnVenus extends NewTestTemplate {

	private final static String WIKI_NAME = "venustest";
	private final static Dimension BIG_RESOLUTION = new Dimension(1920, 1024);
	private final static Dimension SMALL_RESOLUTION = new Dimension(768, 1024);

	@Test(groups = {"TestTablesOnVenus_001", "TestTablesOnVenus"})
	public void TestTablesOnVenus_001_scrollableTablePresent() {
		String url = urlBuilder.getUrlForPath(WIKI_NAME, "Tables/ScrollableTable");
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		Assertion.assertTrue(venusArticle.isScrollableTablePresent(), "Scrollable table should be present");
	}

	@Test(groups = {"TestTablesOnVenus_002", "TestTablesOnVenus"})
	public void TestTablesOnVenus_002_scrollableTablePresentOnSmallResolution() {
		String url = urlBuilder.getUrlForPath(WIKI_NAME, "Tables/ScrollableTableOnSmallRes");
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		venusArticle.resizeWindow(BIG_RESOLUTION);
		Assertion.assertFalse(venusArticle.isScrollableTablePresent(), "Scrollable table shouldn't be present");
		venusArticle.resizeWindow(SMALL_RESOLUTION);
		Assertion.assertTrue(venusArticle.isScrollableTablePresent(), "Scrollable table should be present");
	}

	@Test(groups = {"TestTablesOnVenus_003", "TestTablesOnVenus"})
	public void TestTablesOnVenus_003_scrollableTableNotPresent() {
		String url = urlBuilder.getUrlForPath(WIKI_NAME, "Tables/NotScrollableTable");
		VenusArticlePageObject venusArticle = new VenusArticlePageObject(driver);
		venusArticle.getUrl(url);
		Assertion.assertTrue(venusArticle.isTablePresent(), "Table should be present");
		Assertion.assertFalse(venusArticle.isScrollableTablePresent(), "Scrollable table shouldn't be present");
	}
}
