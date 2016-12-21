package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.ReplyCreatorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = {"discussions-creating-replies"})
public class CreatingReplyTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  /**
   * ANONS ON MOBILE SECTION
   */
  @Test(groups = "discussions-anonUserOnMobileCanNotWriteNewReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)

  public void anonUserOnMobileCanNotWriteNewReply() {
    userOnMobileMustBeLoggedInToUseReplyCreator();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */
  @Test(groups = "discussions-anonUserOnDesktopCanNotWriteNewReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)

  public void anonUserOnDesktopCanNotWriteNewReply() {
    userOnDesktopMustBeLoggedInToUseReplyCreator();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userOnDesktopMustBeLoggedInToUseReplyCreator() {
    ReplyCreatorDesktop replyCreator = new PostDetailsPage().openDefaultPost().getReplyCreatorDesktop();

    Assertion.assertTrue(replyCreator.clickReplyCreator().isModalDialogVisible());

    replyCreator.clickOkButtonInSignInDialog();

    Assertion.assertTrue(replyCreator.clickReplyCreator().isModalDialogVisible());

    replyCreator.clickSignInButtonInSignInDialog();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.REGISTER_PAGE));
  }

  private void userOnMobileMustBeLoggedInToUseReplyCreator() {
    ReplyCreatorMobile replyCreator = new PostDetailsPage().openDefaultPost().getReplyCreatorMobile();

    Assertion.assertTrue(replyCreator.click().isModalDialogVisible());

    replyCreator.clickOkButtonInSignInDialog();

    Assertion.assertTrue(replyCreator.click().isModalDialogVisible());

    replyCreator.clickSignInButtonInSignInDialog();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.JOIN_PAGE));
  }

}
