package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;

public class SectionsTests extends NewTestTemplate{

	@Test(groups={"sectionTest_001", "mobile"})
	public void Sections_001_chevronTest(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections(wikiURL);
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickSection(1);
		article.verifySectionInvisibility();
	}

	@Test(groups={"sectionTest_002", "mobile"})
	public void Sections_002_hideTest(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections(wikiURL);
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickHideButton();
		article.verifySectionInvisibility();
	}


}
