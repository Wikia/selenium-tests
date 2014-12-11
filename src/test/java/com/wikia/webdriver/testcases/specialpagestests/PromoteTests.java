package com.wikia.webdriver.testcases.specialpagestests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPromotePageObject;

/**
 * @author Michal 'justnpT' Nowierski
 *         1. Upload image, put description and put deadline for a Wiki
 */
public class PromoteTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"PromoteTests_001", "PromoteTests"})
	public void PromoteTests_001_changePromoteElements() {
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