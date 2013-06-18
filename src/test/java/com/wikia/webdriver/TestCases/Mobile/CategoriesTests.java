package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileCategoryPageObject;

public class CategoriesTests extends TestTemplate {

	@Test(groups={"mobile"})
	public void categoryTest_001(){
		MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
		mobile.openCategory();
		mobile.verifyShowAll();
		mobile.verifyChevronClosed();
		mobile.clickShowAllButton();
		mobile.verifyChevronOpened();
		mobile.verifyHideAll();
		mobile.clickHideAllButton();
		mobile.verifyShowAll();
		mobile.verifyChevronClosed();
	}

}
