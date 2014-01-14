package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileModalComponentObject;

public class MobileModalTests extends NewTestTemplate{

	@Test(groups={"modalTest_001", "modalTests", "mobile"})
	public void modalTest_001_nextImage() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToNextImage();
		Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
		modal.closeModal();
		modal.verifyModalClosed();
	}

	@Test(groups={"modalTest_002", "modalTests", "mobile"})
	public void modalTest_002_previousImage() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToPreviousImage();
		Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
		modal.closeModal();
		modal.verifyModalClosed();
	}

	@Test(groups={"modalTest_003", "modalTests", "mobile"})
	public void modalTest_003_topBarVisibleOrNot() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickModal();
		modal.verifyTopBarVisible();
		modal.hideTopBar();
		modal.verifyTopBarHidden();
		modal.showTopBar();
		modal.verifyTopBarVisible();
	}

	@Test(groups={"modalTest_004", "modalTests", "mobile"})
	public void modalTest_004_backButton() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		MobileModalComponentObject modal = mobile.clickOpenedImage(5);
		modal.closeModalWithBackButton();
		modal.verifyModalClosed();
	}

	@Test(groups={"modalTest_005", "modalTests", "mobile"})
	public void modalTest_005_positionAfterCloseModal() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		mobile.openModals(wikiURL);
		mobile.scrollToImage(5);
		Long positionBeforeModal = mobile.getPosition();
		MobileModalComponentObject modal = mobile.clickOpenedImage(5);
		modal.closeModal();
		modal.verifyModalClosed();
		modal.verifyPositionTheSame(positionBeforeModal);
	}

}
