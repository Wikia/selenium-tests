package com.wikia.webdriver.testcases.oasismainpage;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.curatedContentToolModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.testng.annotations.Test;

/**
 * @ownership Content
 */
@Test(groups = "CuratedContent")
public class CuratedContentTool extends NewTestTemplate {

  @Test
  @Execute(asUser = User.STAFF)
  @CreationTicket(ticketID = "CONCF-1073")
  public void verifyModal() {
    MainPage main = new MainPage(driver).open();
    curatedContentToolModal modal = main.clickCuratedContentToolButton();
    modal.verifyModal();
  }
}
