package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCuratedContentPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content
 */
public class CuratedContentTests extends NewTestTemplate {

  String category = URLsContent.CATEGORY_HELP;
  String label = PageContent.LOREM_IPSUM_SHORT;

  Credentials credentials = config.getCredentials();

  @CreationTicket(ticketID = "CONCF-767")
  @Test(groups = {"CuratedContent001", "CuratedContent"})
  public void curatedContent001_saveWithoutImage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);
    // add new element without image
    cc.addNewElement(category, label);
    // verify error message and save button not clickable
    cc.verifyImageErrorInLastElement();
    cc.verifySaveNotClickable();
  }

  @CreationTicket(ticketID = "CONCF-767")
  @Test(groups = {"CuratedContent002", "CuratedContent"})
  public void curatedContent002_saveWithImage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialCuratedContentPageObject cc = base.openSpecialCuratedContent(wikiURL);
    // add new element and add image to that element
    cc.addNewElement(category, label);
    PhotoAddComponentObject addPhotoModal = cc.clickImageOnLastElement();
    PhotoOptionsComponentObject photoOptionsModal = addPhotoModal.clickAddThisPhoto(1);
    cc.verifyImageInLastElement();
    cc.clickSave();
    cc.verifySuccesfulSave();
    //clean the added element
    cc.removeLastElement(label);
    cc.clickSave();
    cc.verifySuccesfulSave();
  }
}
