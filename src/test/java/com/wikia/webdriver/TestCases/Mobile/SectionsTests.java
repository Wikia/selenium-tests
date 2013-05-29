package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;

public class SectionsTests extends TestTemplate{

	@Test(groups={"mobile"})
	public void Sections_001_chevronTest(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections();
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickSection(1);
		article.verifySectionInvisibility();
	}

	@Test(groups={"mobile"})
	public void Sections_002_hideTest(){
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections();
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickHideButton();
		article.verifySectionInvisibility();
	}


}
