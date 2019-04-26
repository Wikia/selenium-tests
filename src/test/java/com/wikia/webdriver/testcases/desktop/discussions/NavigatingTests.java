package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.Post;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import org.testng.annotations.Test;

@Test(groups = {"discussions-navigation"})
@Execute(onWikia = "qadiscussions", language = "de")
public class NavigatingTests extends NewTestTemplate {

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanClickOnPostAuthorUsername() {
    clickingOnPostAuthorUsernameLoadsUserPage();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNavigateToPostDetailsPageByClickingOnPost() {
    clickingPostContentRedirectsToPostDetailsPage();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanClickOnPostAuthorAvatar() {
    clickingOnPostAuthorAvatarLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickOnPostAuthorAvatar() {
    clickingOnPostAuthorAvatarLoadsUserPage();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickOnPostAuthorUsername() {
    clickingOnPostAuthorUsernameLoadsUserPage();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNavigateToPostDetailsPageByClickingOnPost() {
    clickingPostContentRedirectsToPostDetailsPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void clickingOnPostAuthorAvatarLoadsUserPage() {
    Post post = new PostsListPage().open().getPost();
    String username = post.getUsername();
    post.clickUserAvatar();
    Assertion.assertEquals(new UserProfilePage().getUserName(), username);
  }

  private void clickingOnPostAuthorUsernameLoadsUserPage() {
    Post post = new PostsListPage().open().getPost();
    String username = post.getUsername();
    post.clickUsernameLink();
    Assertion.assertEquals(new UserProfilePage().getUserName(), username);
  }

  private void clickingPostContentRedirectsToPostDetailsPage() {
    new PostsListPage().open().getPost().findNewestPost().click();
    PostDetailsPage postDetailsPage = new PostDetailsPage();
    postDetailsPage.waitForEmberLoad();
    Assertion.assertTrue(postDetailsPage.isDisplayed());
  }
}
