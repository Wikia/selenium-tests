package com.wikia.webdriver.testcases.mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MobilePageContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileEditPreviewPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileHistoryPageObject;

/**
 * @author Robert "rochan" Chan
 *
 * Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE browser
 * 1. Verify that user can go to edit mode.
 * 2. Verify that edit text and summary text are retained when switching between preview mode and edit mode
 * 3. Verify that edit on a mobile browser would show the '(Mobile)' tag on the edit history page
 * 4. Cancel from edit mode goes back to article
 * 5. Verify that clicking edit on a section only shows the correct section in edit mode
 * 6. Anon user does not have edit option on article
 * 7. Verify that preview is non-interactive, only scrolling
 * 8. Opening license to see if opens in new tab
 * 9. check for the new article banner can be dismissed
 */
public class MobileEditModeTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"MobileEdit_001", "MobileEdit", "Mobile"})
	public void MobileEdit_001_signedInCanEdit() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		MobileEditModePageObject mobileEdit =
				mobile.openNewArticleEditMode(wikiURL);
		mobileEdit.verifyModeName();
	}

	@Test(groups={"MobileEdit_002", "MobileEdit", "Mobile"})
	public void MobileEdit_002_editAndSummaryRetain() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		MobileEditModePageObject mobileEdit =
				mobile.openNewArticleEditMode(wikiURL);
		mobileEdit.typIntoEditArea(PageContent.articleText);
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
		mobilePreview.verifyEditModeContent(PageContent.articleText);
		mobilePreview.verifyPreviewPageHeader(MobilePageContent.previewHeader);
		mobilePreview.typeSummaryText(MobilePageContent.summaryText);
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
		MobileEditModePageObject mobileEdit =
				mobile.openNewArticleEditMode(wikiURL);
		String articleName = mobile.getCurrentUrl();
		mobileEdit.typIntoEditArea(PageContent.articleText);
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
		mobilePreview.verifyEditModeContent(PageContent.articleText);
		mobilePreview.verifyPreviewPageHeader(MobilePageContent.previewHeader);
		mobilePreview.typeSummaryText(MobilePageContent.summaryText);
		MobileArticlePageObject mobileArticle = mobilePreview.clickPublish();
		MobileHistoryPageObject mobileArticleHistory =
				mobileArticle.goToCurrentArticleHistoryPage();
		mobileArticleHistory.verifyHistoryPageHeader(MobilePageContent.historyHeader);
		mobileArticleHistory.verifyArticleName(articleName);
		mobileArticleHistory.verifyLastEditHistorySummary(MobilePageContent.historySummaryText);
		mobileArticleHistory.verifyLastEditHistoryDevice(MobilePageContent.mobileEditTag);
	}
}
