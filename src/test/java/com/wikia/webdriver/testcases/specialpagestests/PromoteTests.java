package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPromotePageObject;

import org.testng.annotations.Test;

public class PromoteTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"PromoteTests_001", "PromoteTests"})
  public void PromoteTests_001_changePromoteElements() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialPromotePageObject promote = base.openSpecialPromotePage(wikiURL);
    promote.typeIntoHeadline(PageContent.LOREM_IPSUM_SHORT);
    promote.typeIntoDescription(PageContent.LOREM_IPSUM_LONG);
    promote.uploadThumbnailImage(PageContent.FILEPNG);
    promote.verifyUploadedImage(PageContent.FILEPNG);
    promote.modifyThumnailImage(PageContent.FILE2PNG);
    promote.verifyUploadedImage(PageContent.FILE2PNG);
  }
}
