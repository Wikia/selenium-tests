package com.wikia.webdriver.TestCases.Mobile;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import org.testng.annotations.Test;


public class TopbarTests extends NewTestTemplate{

	@Test(groups={"topbarTest_001", "topbarTests", "mobile"})
	public void TopbarTests_001_topbarButtons() {
		MobileArticlePageObject topbar = new MobileArticlePageObject(driver);
		topbar.openTopbarPage(wikiURL);
		topbar.verifyAllTopbarButtons();
	}

	@Test(groups={"topbarTest_002", "topbarTests", "mobile"})
	public void TopbarTests_002_wordmark() {
		MobileArticlePageObject topbar = new MobileArticlePageObject(driver);
		topbar.openTopbarPage(wikiURL);
		topbar.clickWordmark();
		topbar.verifyMainPageOpened(wikiURL);
	}

	@Test(groups={"topbarTest_003", "topbarTests", "mobile"})
	public void TopbarTests_003_topbarPosition() {
		MobileArticlePageObject topbar = new MobileArticlePageObject(driver);
		topbar.openTopbarPage(wikiURL);
		Long startPosition = topbar.getPosition();
		topbar.triggerSearch();
		topbar.verifyCurtainOpened();
		topbar.clickOutsideSearchField();
		topbar.verifyCurtainClosed();
		topbar.verifyPositionDifferent(startPosition);
	}

	@Test(groups={"topbarTest_004", "topbarTests", "mobile"})
	public void TopbarTests_004_menuPagination() {
		MobileArticlePageObject topbar = new MobileArticlePageObject(driver);
		topbar.openTopbarPage(wikiURL);
		topbar.verifyMenuPagination();
	}

}
