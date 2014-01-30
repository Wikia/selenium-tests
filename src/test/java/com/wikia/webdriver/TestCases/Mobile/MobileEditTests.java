package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.MobilePageContent;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileEditPreviewPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileHistoryPageObject;

/**
 * @author Robert "rochan" Chan
 *
 * Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE browser
 * 1. Verify that user can go to edit mode.
 * 2. Verify that edit text and summary text are retained when switching between preview mode and edit mode
 * 3. Verify that edit on a mobile browser would show the '(Mobile)' tag on the edit history page
 * 4. Cancel from edit mode goes back to article
 * 5. Editing the correct section from article.
 * 6. Non signed in user do not have edit option on article
 * 7. Preview is non-interactive, only scrolling
 * 8. Opening license to see if opens in new tab
 * 9. check for the new article banner can be dismissed
 */
public class MobileEditTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"MobileEdit_001", "MobileEdit", "Mobile"})
	public void MobileEdit_001_signedInCanEdit() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit =
				mobile.goToNewPageWithEdit(wikiURL);
		mobileEdit.verifyModeName();
		mobileEdit.verifyEditArticleName();
	}

	@Test(groups={"MobileEdit_002", "MobileEdit", "Mobile"})
	public void MobileEdit_002_editAndSummaryRetain() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit =
				mobile.goToNewPageWithEdit(wikiURL);
		mobileEdit.enterEditText(PageContent.articleText);
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
		mobilePreview.verifyEditModeContent(PageContent.articleText);
		mobilePreview.verifyPreviewPageHeader(MobilePageContent.previewHeader);
		mobilePreview.enterSummaryText(MobilePageContent.summaryText);
		MobileEditModePageObject continueMobileEdit = mobilePreview.clickKeepEditing();
		continueMobileEdit.verifyEditText(PageContent.articleText);
		MobileEditPreviewPageObject continueMobilePreview = continueMobileEdit.clickPreview();
		continueMobilePreview.verifySummaryText(MobilePageContent.summaryText);
	}
	@Test(groups={"MobileEdit_003", "MobileEdit", "Mobile"})
	public void MobileEdit_003_publishHistory() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit =
				mobile.goToNewPageWithEdit(wikiURL);
		String articleName = mobile.getCurrentUrl();
		mobileEdit.enterEditText(PageContent.articleText);
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
		mobilePreview.verifyEditModeContent(PageContent.articleText);
		mobilePreview.verifyPreviewPageHeader(MobilePageContent.previewHeader);
		mobilePreview.enterSummaryText(MobilePageContent.summaryText);
		MobileArticlePageObject mobileArticle = mobilePreview.clickPublish();
		MobileHistoryPageObject mobileArticleHistory =
				mobileArticle.goToCurrentArticleHistoryPage();
		mobileArticleHistory.verifyHistoryPageHeader(MobilePageContent.historyHeader);
		mobileArticleHistory.verifyArticleName(articleName);
		mobileArticleHistory.verifyLastEditHistorySummary(MobilePageContent.historySummaryText);
		mobileArticleHistory.verifyLastEditHistoryDevice(MobilePageContent.mobileEditTag);
	}
}
