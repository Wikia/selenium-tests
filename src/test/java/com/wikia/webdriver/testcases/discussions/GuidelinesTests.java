package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.GuidelinesPage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_2)
@Test(groups = {"discussions-guidelines"})
public class GuidelinesTests extends NewTestTemplate {

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isDiscussionsFilterDisplayed());
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotSeeEditGuidelines")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertFalse(guidelinesPage.isEditButtonDisplayed());
  }

  @Test(groups = "discussions-anonUserOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSeeGuidelinesImageArea() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesImageAreaDisplayed());
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   * User_6 was chosen because, I take the last available user form the list of users
   */
  @Test(groups = "discussions-staffUserOnDesktopCanSeeEditGuidelines")
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffOnDesktopCanClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isModalGuidelinesDisplayed());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanSeeEditGuidelines")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isEditButtonDisplayed());
  }

  @Test(groups = "discussions-regularUserOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.USER_6)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void regularUserOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isDiscussionsFilterDisplayed());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isDiscussionsFilterDisplayed());
  }

  @Test(groups = "discussions-staffOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isDiscussionsFilterDisplayed());
  }

  @Test(groups = "discussions-regularUserOnDesktopCanNotSeeEditGuidelines")
  @Execute(asUser = User.USER_6)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void regularUserOnDesktopCanNotClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertFalse(guidelinesPage.isEditButtonDisplayed());
  }

  @Test(groups = "discussions-regularUserOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.USER_6)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void regularUserOnDesktopCanSeeGuidelinesImageArea() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesImageAreaDisplayed());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanSeeGuidelinesImageArea() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesImageAreaDisplayed());
  }

  @Test(groups = "discussions-staffOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffOnDesktopCanSeeGuidelinesImageArea() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesImageAreaDisplayed());
  }
  @Test(groups = "discussions-staffOnDesktopCanAddTextToGuidelines")
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffOnDesktopCanAddTextToGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.canAddNewTextToGuidelines());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanAddTextToGuidelines")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanAddTextToGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.canAddNewTextToGuidelines());
  }
}
