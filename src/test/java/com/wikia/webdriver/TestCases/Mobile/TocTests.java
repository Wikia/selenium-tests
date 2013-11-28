package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Mobile.TableOfContentsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;

public class TocTests extends NewTestTemplate{

	@Test(groups={"tocTest_001", "tocTests", "mobile"})
	public void TocTests_001_tocTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.verifyTocClosed();
		TableOfContentsComponentObject toc = article.openToc();
		toc.verifyTocElements();
		toc.closeToc();
		article.verifyTocClosed();
	}

	@Test(groups={"tocTest_002", "tocTests", "mobile"})
	public void TocTests_002_tocToSectionLevel1Test() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		TableOfContentsComponentObject toc = article.openToc();
		Long positionBeforeClick = toc.getPosition();
		String clickedItem = toc.clickOnLevel1Section(5, wikiURL);
		article.verifyPositionsNotEquals(positionBeforeClick);
		//TODO: fix the verification method, so it verifies that view was scrolled to the header.
		article.verifySectionHeaderOpened(clickedItem);
	}

	@Test(groups={"tocTest_003", "tocTests", "mobile"})
	public void Sections_003_tocToSectionLevel2Test() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.openToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel2SectionInToc(0, wikiURL);
		article.verifySectionOpened(clickedItem,3);
		article.verifyPositionsNotEquals(positionBeforeClick);
	}

	@Test(groups={"tocTest_004", "tocTests", "mobile"})
	public void Sections_004_tocToSectionLevel3Test() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.openToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel3SectionInToc(0, wikiURL);
		article.verifySectionOpened(clickedItem,4);
		article.verifyPositionsNotEquals(positionBeforeClick);
	}

	@Test(groups={"tocTest_005", "tocTests", "mobile"})
	public void Sections_005_tocToSectionLevel4Test() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.openToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel4SectionInToc(0, wikiURL);
		article.verifySectionOpened(clickedItem,5);
		article.verifyPositionsNotEquals(positionBeforeClick);
	}

	@Test(groups={"tocTest_006", "tocTests", "mobile"})
	public void Sections_006_tocClickOutsideOfToc() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.openToc();
		article.verifyCurtainOpened();
		article.clickOnWikiaTopPageLogo();
		article.verifyCurtainClosed();
	}

}
