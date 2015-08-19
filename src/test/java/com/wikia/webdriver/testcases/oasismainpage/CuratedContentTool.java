package com.wikia.webdriver.testcases.oasismainpage;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CuratedContentToolModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCuratedContentPageObject;

import org.testng.annotations.Test;
import com.wikia.webdriver.common.core.annotations.User;

/**
 * @ownership Content
 */
@Test(groups = {"CuratedContent"})
public class CuratedContentTool extends NewTestTemplate {

  private static final String CATEGORY = URLsContent.CATEGORY_HELP;
  private static final String LABEL = PageContent.LOREM_IPSUM_SHORT;

  Credentials credentials = Configuration.getCredentials();


  @Test
  @Execute(asUser = User.STAFF)
  @CreationTicket(ticketID = "CONCF-1073")
  public void verifyModal() {
    MainPage main = (MainPage) new MainPage(driver).open();
    CuratedContentToolModal modal = main.clickCuratedContentToolButton();
    modal.verify();
  }
}
