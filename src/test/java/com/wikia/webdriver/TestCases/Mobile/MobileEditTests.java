package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
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
 */
public class MobileEditTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	
	@Test(groups={"MobileEdit_001", "MobileEdit", "Mobile"})
	public void MobileEdit_001_Edit() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		String randomPageURL = wikiURL + URLsContent.wikiDir 
				+ PageContent.articleNamePrefix + mobile.getTimeStamp();
		String pageHistoryURL = urlBuilder.appendQueryStringToURL(
				randomPageURL, URLsContent.mobileHistoryPage);
		String pageEditURL = urlBuilder.appendQueryStringToURL(
				randomPageURL, URLsContent.mobileEditPage);
		//go to a random page with ?action=history suffix
		MobileHistoryPageObject mobileHistory = 
				mobile.goToRandomMobileHistoryPage(pageHistoryURL);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit = 
				mobileHistory.goToNewPageWithEdit(pageEditURL);
		mobileEdit.verifyModeName();
		mobileEdit.verifyEditArticleName();
	}
	
	@Test(groups={"MobileEdit_002", "MobileEdit", "Mobile"})
	public void MobileEdit_002_Edit() {	
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		//go to a random page with ?action=history suffix
		String randomPageURL = wikiURL + URLsContent.wikiDir 
				+ PageContent.articleNamePrefix + mobile.getTimeStamp();
		String pageHistoryURL = urlBuilder.appendQueryStringToURL(
				randomPageURL, URLsContent.mobileHistoryPage);
		String pageEditURL = urlBuilder.appendQueryStringToURL(
				randomPageURL, URLsContent.mobileEditPage);
		MobileHistoryPageObject mobileHistory = 
				mobile.goToRandomMobileHistoryPage(pageHistoryURL);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit = 
				mobileHistory.goToNewPageWithEdit(pageEditURL);
		mobileEdit.enterEditText(PageContent.articleText);
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
		mobilePreview.verifyEditModeContent(PageContent.articleText);
		mobilePreview.verifyPreviewPageHeader(PageContent.previewHeader);
		mobilePreview.enterSummaryText(PageContent.summaryText);
		MobileEditModePageObject continueMobileEdit = mobilePreview.clickKeepEditing();
		continueMobileEdit.verifyEditText(PageContent.articleText);
		MobileEditPreviewPageObject continueMobilePreview = continueMobileEdit.clickPreview();
		continueMobilePreview.verifySummaryText(PageContent.summaryText);
	}
	@Test(groups={"MobileEdit_003", "MobileEdit", "Mobile"})
	public void MobileEdit_003_Edit() {	
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.loginDropDown(credentials.userName, credentials.password);
		//go to a random page with ?action=history suffix
		String articleName = PageContent.articleNamePrefix + mobile.getTimeStamp();
		String randomPageURL = wikiURL + URLsContent.wikiDir + articleName;
		String pageHistoryURL = urlBuilder.appendQueryStringToURL(
				randomPageURL, URLsContent.mobileHistoryPage);
		String pageEditURL = urlBuilder.appendQueryStringToURL(
				randomPageURL, URLsContent.mobileEditPage);
		MobileHistoryPageObject mobileHistory = 
				mobile.goToRandomMobileHistoryPage(pageHistoryURL);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit = 
				mobileHistory.goToNewPageWithEdit(pageEditURL);
		mobileEdit.enterEditText(PageContent.articleText);
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
		mobilePreview.verifyEditModeContent(PageContent.articleText);
		mobilePreview.verifyPreviewPageHeader(PageContent.previewHeader);
		mobilePreview.enterSummaryText(PageContent.summaryText);
		MobileArticlePageObject mobileArticle = mobilePreview.clickPublish();
		MobileHistoryPageObject mobileArticleHistory = 
				mobileArticle.goToCurrentArticleHistoryPage();
		mobileArticleHistory.verifyHistoryPageHeader(PageContent.historyHeader);
		mobileArticleHistory.verifyArticleName(articleName);
		mobileArticleHistory.verifyLastEditHistorySummary(PageContent.historySummaryText);
		mobileArticleHistory.verifyLastEditHistoryDevice(PageContent.mobileEditTag);
	}
}
