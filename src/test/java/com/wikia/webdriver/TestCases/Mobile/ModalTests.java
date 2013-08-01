package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileModalComponentObject;

public class ModalTests extends NewTestTemplate{

	// next image
	@Test(groups={"modalTest_001", "mobile"})
	public void modalTest_001(){
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToNextImage();
		Assertion.assertStringContains(current, modal.getCurrentImageUrl());
		modal.closeModal();

	}

	// previous image
	@Test(groups={"modalTest_002", "mobile"})
	public void modalTest_002(){
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToPreviousImage();
		Assertion.assertStringContains(current, modal.getCurrentImageUrl());
		modal.closeModal();

	}

	//test top bar visible/hidden
	@Test(groups={"modalTest_003", "mobile"})
	public void modalTest_003(){
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickModal();
		modal.verifyTopBarVisible();
		modal.hideTopBar();
		modal.verifyTopBarHidden();
		modal.showTopBar();
		modal.verifyTopBarVisible();
	}




}

