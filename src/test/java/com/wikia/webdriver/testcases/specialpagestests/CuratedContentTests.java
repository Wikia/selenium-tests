package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCuratedContentPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content
 */
public class CuratedContentTests extends NewTestTemplate {

  private static final String CATEGORY = URLsContent.CATEGORY_HELP;
  private static final String LABEL = PageContent.LOREM_IPSUM_SHORT;

  Credentials credentials = Configuration.getCredentials();

  @CreationTicket(ticketID = "CONCF-767")
  @Test(groups = {"CuratedContent001", "CuratedContent"})
  public void curatedContent001_saveWithoutImage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);
    // add new element without image
    cc.addNewElement(CATEGORY, LABEL);
    // verify error message and save button not clickable
    cc.verifyImageErrorInLastElement();
    cc.verifySaveNotClickable();
  }

  @CreationTicket(ticketID = "CONCF-767")
  @Test(groups = {"CuratedContent002", "CuratedContent"})
  public void curatedContent002_saveWithImage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);
    // add new element and add image to that element
    cc.addNewElement(CATEGORY, LABEL);
    PhotoAddComponentObject addPhotoModal = cc.clickImageOnLastElement();
    addPhotoModal.clickAddThisPhoto(1);
    cc.verifyImageInLastElement();
    cc.clickSave();
    cc.verifySuccesfulSave();
    //clean the added element
    cc.removeLastElement();
    cc.clickSave();
    cc.verifySuccesfulSave();
  }

  @CreationTicket(ticketID = "CONCF-1073")
  @Test(groups = {"CuratedContent003", "CuratedContent"})
  public void curatedContent003_open() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);
    // add new element and add image to that element
    cc.addNewElement(CATEGORY, LABEL);
    PhotoAddComponentObject addPhotoModal = cc.clickImageOnLastElement();
    addPhotoModal.clickAddThisPhoto(1);
    cc.verifyImageInLastElement();
    cc.clickSave();
    cc.verifySuccesfulSave();
    //clean the added element
    cc.removeLastElement();
    cc.clickSave();
    cc.verifySuccesfulSave();
  }
}
