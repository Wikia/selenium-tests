package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPromotePageObject;

/**
* @author Michal 'justnpT' Nowierski
* 1. Upload image, put description and put deadline for a Wiki
*/
public class PromoteTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups={"PromoteTests_001", "PromoteTests"})
	public void PromoteTests_001_changePromoteElements(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialPromotePageObject promote = base.openSpecialPromotePage(wikiURL);
		promote.typeIntoHeadline(PageContent.WIKI_PROMOTE_HEADLINE);
		promote.typeIntoDescription(PageContent.WIKI_PROMOTE_DESCRIPTION);
		promote.uploadThumbnailImage(PageContent.FILEPNG);
		promote.verifyUploadedImage(PageContent.FILEPNG);
		promote.modifyThumnailImage(PageContent.FILE2PNG);
		promote.verifyUploadedImage(PageContent.FILE2PNG);
	}

}