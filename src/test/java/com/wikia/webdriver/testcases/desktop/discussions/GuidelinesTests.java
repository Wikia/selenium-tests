package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.GuidelinesPage;

import org.testng.annotations.Test;

@Execute(onWikia = "qadiscussions", language = "de")
@Test(groups = {"discussions-guidelines"})
public class GuidelinesTests extends NewTestTemplate {

  private static final String DESKTOP = "discussions-guidelines-desktop";

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanOpenGuidelinesInReadOnlyMode() {
    GuidelinesPage guidelinesPage = new GuidelinesPage().open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesImageAreaDisplayed());
    Assertion.assertFalse(guidelinesPage.isEditButtonDisplayed());
    Assertion.assertTrue(guidelinesPage.clickBackToDiscussions().isDiscussionsFilterDisplayed());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_6)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void regularUserOnDesktopCanOpenGuidelinesInReadOnlyMode() {
    GuidelinesPage guidelinesPage = new GuidelinesPage().open();

    Assertion.assertTrue(guidelinesPage.isGuidelinesImageAreaDisplayed());
    Assertion.assertFalse(guidelinesPage.isEditButtonDisplayed());
    Assertion.assertTrue(guidelinesPage.clickBackToDiscussions().isDiscussionsFilterDisplayed());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffOnDesktopCanAddTextToGuidelines() {
    String text = TextGenerator.createUniqueText();
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open();
    guidelinesPage.clearAndAddUniqueText(text);

    Assertion.assertTrue(guidelinesPage.doesGuidelinesContainsText(text));
    Assertion.assertTrue(guidelinesPage.clickBackToDiscussions().isDiscussionsFilterDisplayed());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanAddTextToGuidelines() {
    String text = TextGenerator.createUniqueText();
    GuidelinesPage guidelinesPage = new GuidelinesPage();
    guidelinesPage.open().clearAndAddUniqueText(text);

    Assertion.assertTrue(guidelinesPage.doesGuidelinesContainsText(text));
    Assertion.assertTrue(guidelinesPage.clickBackToDiscussions().isDiscussionsFilterDisplayed());
  }
}
