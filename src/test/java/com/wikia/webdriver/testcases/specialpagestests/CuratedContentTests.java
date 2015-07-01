package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCuratedContentPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content
 */
public class CuratedContentTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @CreationTicket(ticketID = "CONCF-767")
  @Test(groups = {"CuratedContent001", "CuratedContent"})
  public void cureatedContent001_saveWithoutImage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);
  }

  @CreationTicket(ticketID = "CONCF-767")
  @Test(groups = {"CuratedContent002", "CuratedContent"})
  public void cureatedContent002_saveWithImage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);

  }
}
