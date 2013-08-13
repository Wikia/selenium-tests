package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileCategoryPageObject;

public class CategoriesTests extends NewTestTemplate {

	@Test(groups={"categoryTest_001", "categoriesTests", "mobile"})
	public void categoryTest_001() {
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory(wikiURL);
		mobile.verifyShowAll();
		mobile.verifyChevronClosed();
		mobile.clickShowAllButton();
		mobile.verifyChevronOpened();
		mobile.verifyHideAll();
		mobile.clickHideAllButton();
		mobile.verifyShowAll();
		mobile.verifyChevronClosed();
	}

	@Test(groups={"categoryTest_002", "categoriesTests", "mobile"})
	public void categoryTest_002_checkCategoryExhibitionButtons() {
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory(wikiURL);
		mobile.verifyCategoryExhibition();
	}

	@Test(groups={"categoryTest_003", "categoriesTests", "mobile"})
	public void categoryTest_003_checkPagination() {
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory(wikiURL);
		mobile.openArticlesWithPagination();
		mobile.verifyPagination();
	}

}
