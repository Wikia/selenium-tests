package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;

public class TocTests extends NewTestTemplate{

	@Test(groups={"tocTest_001", "mobile"})
	public void Sections_001_tocTest(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.verifyTocClosed();
		article.clickChevronToOpenToc();
		article.verifyTocOpened();
		article.clickChevronToCloseToc();
		article.verifyTocClosed();
	}

	@Test(groups={"tocTest_002", "mobile1", "mobile"}) // TODO create assertNotEq method
	public void Sections_002_tocToSectionLevel1Test(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.clickChevronToOpenToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel1SectionInToc(5, wikiURL);
		article.verifySectionHeaderOpened(clickedItem);
		Long positionAfterClick = article.getPosition();
		Assertion.assertNotEquals(positionBeforeClick, positionAfterClick);
	}

	@Test(groups={"tocTest_003", "mobile1", "mobile"}) // TODO create assertNotEq method
	public void Sections_003_tocToSectionLevel2Test(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.clickChevronToOpenToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel2SectionInToc(0, wikiURL);
		article.verifySectionOpened(clickedItem,3);
		Long positionAfterClick = article.getPosition();
		Assertion.assertNotEquals(positionBeforeClick, positionAfterClick);
	}

	@Test(groups={"tocTest_004", "mobile1", "mobile"}) // TODO create assertNotEq method
	public void Sections_004_tocToSectionLevel3Test(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.clickChevronToOpenToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel3SectionInToc(0, wikiURL);
		article.verifySectionOpened(clickedItem,4);
		Long positionAfterClick = article.getPosition();
		Assertion.assertNotEquals(positionBeforeClick, positionAfterClick);
	}

	@Test(groups={"tocTest_005", "mobile1", "mobile"}) // TODO create assertNotEq method
	public void Sections_005_tocToSectionLevel4Test(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.clickChevronToOpenToc();
		Long positionBeforeClick = article.getPosition();
		String clickedItem = article.clickOnLevel4SectionInToc(0, wikiURL);
		article.verifySectionOpened(clickedItem,5);
		Long positionAfterClick = article.getPosition();
		Assertion.assertNotEquals(positionBeforeClick, positionAfterClick);
	}

	@Test(groups={"tocTest_006", "mobile1", "mobile"})
	public void Sections_006_tocClickOutsideOfToc(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openTOCPage(wikiURL);
		article.clickChevronToOpenToc();
		article.verifyCurtainOpened();
		article.clickOnWikiaTopPageLogo();
		article.verifyCurtainClosed();
	}

}
