package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileCategoryPageObject;

public class CategoriesTests extends NewTestTemplate {

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
		mobile.openArticle(2);
		mobile.verifyArticlesCount(25);
		String firstArticle = mobile.getFirstArticleName();
		String lastArticle = mobile.getLastArticleName();
		mobile.showNextArticles();
		String firstArticle2 = mobile.getFirstArticleName();
		mobile.verifyArticlesNotEquals(firstArticle, firstArticle2);
		mobile.showPreviousArticles();
		mobile.verifyArticlesCount(25);
		String firstArticle3 = mobile.getFirstArticleName();
		String lastArticle3 = mobile.getLastArticleName();
		mobile.verifyArticlesEquals(firstArticle, firstArticle3);
		mobile.verifyArticlesEquals(lastArticle, lastArticle3);
	}

}
