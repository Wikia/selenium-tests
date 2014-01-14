package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileCategoryPageObject;

public class MobileCategoriesTests extends NewTestTemplate {

	@Test(groups={"categoryTest_001", "categoriesTests", "mobile"})
	public void categoryTest_001_checkCategoryExhibitionButtons() {
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory(wikiURL);
		mobile.verifyCategoryExhibition();
	}

	@Test(groups={"categoryTest_002", "categoriesTests", "mobile"})
	public void categoryTest_002_checkPagination() {
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory(wikiURL);
		mobile.verifyArticlesCount("P", 25);
		String firstArticle = mobile.getFirstArticleName("P");
		String lastArticle = mobile.getLastArticleName("P");
		mobile.showNextArticles("P");
		String firstArticle2 = mobile.getFirstArticleName("P");
		mobile.verifyArticlesNotEquals(firstArticle, firstArticle2);
		mobile.showPreviousArticles("P");
		mobile.verifyArticlesCount("P", 25);
		String firstArticle3 = mobile.getFirstArticleName("P");
		String lastArticle3 = mobile.getLastArticleName("P");
		mobile.verifyArticlesEquals(firstArticle, firstArticle3);
		mobile.verifyArticlesEquals(lastArticle, lastArticle3);
	}

}
