package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.GuidelinesPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_2)
@Test(groups = {"discussions-guidelines"})
public class GuidelinesTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1440x1080";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isWikiaHomeLinkDisplayed());
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotSeeEditGuidelines")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertFalse(guidelinesPage.isEditButtonDisplayed());
  }

  @Test(groups = "discussions-anonUserOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeGuidelinesHeroUnit() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesHeroUnitDisplayed());
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   * User_6 was chosen because, I take the last available user form the list of users
   */
  @Test(groups = "discussions-staffUserOnDesktopCanSeeEditGuidelines")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffOnDesktopCanClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isModalGuidelinesDisplayed());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanSeeEditGuidelines")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsModeratorOnDesktopCanClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isEditButtonDisplayed());
  }

  @Test(groups = "discussions-regularUserOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.USER_6)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void regularUserOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isWikiaHomeLinkDisplayed());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsModeratorOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isWikiaHomeLinkDisplayed());
  }

  @Test(groups = "discussions-staffOnDesktopCanClickBackToDiscussions")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffOnDesktopCanClickBackToDiscussions() {
    DiscussionsPage mainDiscussionPage = new GuidelinesPage().open().clickBackToDiscussions();

    Assertion.assertTrue(mainDiscussionPage.isWikiaHomeLinkDisplayed());
  }

  @Test(groups = "discussions-regularUserOnDesktopCanNotSeeEditGuidelines")
  @Execute(asUser = User.USER_6)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void regularUserOnDesktopCanNotClickEditGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertFalse(guidelinesPage.isEditButtonDisplayed());
  }

  @Test(groups = "discussions-regularUserOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.USER_6)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void regularUserOnDesktopCanSeeGuidelinesHeroUnit() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesHeroUnitDisplayed());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsModeratorOnDesktopCanSeeGuidelinesHeroUnit() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesHeroUnitDisplayed());
  }

  @Test(groups = "discussions-staffOnDesktopCanSeeGuidelinesHeroUnit")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffOnDesktopCanSeeGuidelinesHeroUnit() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesHeroUnitDisplayed());
  }
  @Test(groups = "discussions-staffOnDesktopCanAddTextToGuidelines")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffOnDesktopCanAddTextToGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.canAddNewTextToGuidelines());
  }

  @Test(groups = "discussions-discussionsModeratorOnDesktopCanAddTextToGuidelines")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void discussionsModeratorOnDesktopCanAddTextToGuidelines() {
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();

    Assertion.assertTrue(guidelinesPage.canAddNewTextToGuidelines());
  }
}
