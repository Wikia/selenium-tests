package com.wikia.webdriver.TestCases.Mobile;

import org.testng.Assert;
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
 * 1. Verify that user in edit mode of a new article would redirect to 404 after selecting cancel
 * 2. Verify that user is 
 * 3. Verify that user is able to login successfully using FB login
 */
public class MobileEditTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"MobileEdit_001", "MobileEdit", "Mobile"})
	public void MobileEdit_001_Edit() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.login(credentials.userName, credentials.password);
		//go to a random page with ?action=history suffix
		String randomPage = wikiURL + URLsContent.wikiDir 
				+ PageContent.articleNamePrefix + mobile.getTimeStamp();
		String tmp = urlBuilder.appendQueryStringToURL(
				randomPage, URLsContent.mobileHistoryPage);
		MobileHistoryPageObject mobileHistory = mobile.goToRandomMobileHistoryPage(tmp);
		//make sure there is no history
		String tmp2 = urlBuilder.appendQueryStringToURL(
				randomPage, URLsContent.mobileEditPage);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit = mobileHistory.goToNewPageWithEdit(tmp2);
		//expect 404 redirect page here.
		MobileArticlePageObject mobileArticle = mobileEdit.clickCancel();
		mobileArticle.verifyUrl(tmp);	
	}
	
	@Test(groups={"MobileEdit_002", "MobileEdit", "Mobile"})
	public void MobileEdit_002_Edit() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.login(credentials.userName, credentials.password);
		//go to a random page with ?action=history suffix
		String randomPage = wikiURL + URLsContent.wikiDir 
				+ PageContent.articleNamePrefix + mobile.getTimeStamp();
		String tmp = urlBuilder.appendQueryStringToURL(
				randomPage, URLsContent.mobileHistoryPage);
		MobileHistoryPageObject mobileHistory = mobile.goToRandomMobileHistoryPage(tmp);
		//make sure there is no history
		String tmp2 = urlBuilder.appendQueryStringToURL(
				randomPage, URLsContent.mobileEditPage);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit = mobileHistory.goToNewPageWithEdit(tmp2);
		mobileEdit.verifyModeName();
		mobileEdit.verifyEditArticleName();
	}
	
	@Test(groups={"MobileEdit_003", "MobileEdit", "Mobile"})
	public void MobileEdit_003_Edit() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.login(credentials.userName, credentials.password);
		//go to a random page with ?action=history suffix
		String randomPage = wikiURL + URLsContent.wikiDir + PageContent.articleNamePrefix + mobile.getTimeStamp();
		String tmp = urlBuilder.appendQueryStringToURL(randomPage, URLsContent.mobileHistoryPage);
		MobileHistoryPageObject mobileHistory = mobile.goToRandomMobileHistoryPage(tmp);
		//make sure there is no history
		String tmp2 = urlBuilder.appendQueryStringToURL(randomPage, URLsContent.mobileEditPage);
		//go into edit mode ?action=edit
		MobileEditModePageObject mobileEdit = mobileHistory.goToNewPageWithEdit(tmp2);
		mobileEdit.enterText("Test123");
		MobileEditPreviewPageObject mobilePreview = mobileEdit.clickPreview();
	}
}
