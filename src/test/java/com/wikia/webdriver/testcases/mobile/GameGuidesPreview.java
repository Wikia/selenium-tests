package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileModalComponentObject;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author PMG
 *         <p/>
 *         Below test cases are executed against Special:GameGuidesPreview
 *         1. Verify that sections are opened in article view
 *         2. Verify that hide button in section works
 *         3. Verify that next image button in modal works
 *         4. Verify that previous image button in modal works
 *         5. Verify that you are able to hide top bar in modal
 *         6. Verify that back button close modal
 *         7. Verify that when you go to modal and go back you are in the same place as previously.
 */
public class GameGuidesPreview extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"GameGuidesPreview_001", "MobileGG"})
	public void GameGuidesPreview_001_sections_chevronTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		article.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		article.appendToUrl(URLsContent.RENDER_FULL_QS);
		article.appendToUrl(URLsContent.PAGENAME + "Sections");
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickSection(1);
		article.verifySectionInvisibility();
	}

	@Test(groups = {"GameGuidesPreview_002", "MobileGG"})
	public void GameGuidesPreview_002_sections_hideTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		article.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		article.appendToUrl(URLsContent.RENDER_FULL_QS);
		article.appendToUrl(URLsContent.PAGENAME + "Sections");
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickHideButton();
		article.verifySectionInvisibility();
	}

	@Test(groups = {"GameGuidesPreview_003", "MobileGG"})
	public void GameGuidesPreview_003_modalTest_nextImage() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		mobile.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		mobile.appendToUrl(URLsContent.RENDER_FULL_QS);
		mobile.appendToUrl(URLsContent.PAGENAME + "Modal");
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToNextImage();
		Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
		modal.closeModal();
		modal.verifyModalClosed();
	}

	@Test(groups = {"GameGuidesPreview_004", "MobileGG"})
	public void GameGuidesPreview_004_modalTest_previousImage() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		mobile.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		mobile.appendToUrl(URLsContent.RENDER_FULL_QS);
		mobile.appendToUrl(URLsContent.PAGENAME + "Modal");
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToPreviousImage();
		Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
		modal.closeModal();
		modal.verifyModalClosed();
	}

	@Test(groups = {"GameGuidesPreview_005", "MobileGG"})
	public void GameGuidesPreview_005_topBarVisibleOrNot() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		mobile.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		mobile.appendToUrl(URLsContent.RENDER_FULL_QS);
		mobile.appendToUrl(URLsContent.PAGENAME + "Modal");
		MobileModalComponentObject modal = mobile.clickModal();
		modal.verifyTopBarVisible();
		modal.hideTopBar();
		modal.verifyTopBarHidden();
		modal.showTopBar();
		modal.verifyTopBarVisible();
	}

	@Test(groups = {"GameGuidesPreview_006", "MobileGG"})
	public void GameGuidesPreview_006_backButton() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		mobile.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		mobile.appendToUrl(URLsContent.RENDER_FULL_QS);
		mobile.appendToUrl(URLsContent.PAGENAME + "Modal");
		MobileModalComponentObject modal = mobile.clickOpenedImage(5);
		modal.closeModalWithBackButton();
		modal.verifyModalClosed();
	}

	@Test(groups = {"GameGuidesPreview_007", "MobileGG"})
	public void GameGuidesPreview_007_positionAfterCloseModal() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.WIKIA_PHP);
		mobile.appendToUrl(URLsContent.GAMEGUIDES_CONTROLLER_QS);
		mobile.appendToUrl(URLsContent.RENDER_FULL_QS);
		mobile.appendToUrl(URLsContent.PAGENAME + "Modal");
		MobileModalComponentObject modal = mobile.clickOpenedImage(5);
		modal.closeModal();
		modal.verifyModalClosed();
		Long positionBeforeModal = modal.getPosition();
		mobile.clickOpenedImage();
		modal.closeModal();
		modal.verifyModalClosed();
		modal.verifyPositionTheSame(positionBeforeModal);
	}
}
