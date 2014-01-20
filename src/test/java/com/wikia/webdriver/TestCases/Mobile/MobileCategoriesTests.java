package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileCategoryPageObject;

/**
 * @author PMG
 *
 * Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE browser
 * 1. Verify that user is able to see category exhibition buttons grid
 * 2. Verify that category with more than 25 entries for one letter have pagination of entries
 */
public class MobileCategoriesTests extends NewTestTemplate {

	@Test(groups={"MobileCategory_001", "MobileCategories", "Mobile"})
	public void MobileCategory_001_checkCategoryExhibitionButtons() {
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory(wikiURL);
		mobile.verifyCategoryExhibition();
	}

	@Test(groups={"MobileCategory_002", "MobileCategories", "Mobile"})
	public void MobileCategory_002_checkPagination() {
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
