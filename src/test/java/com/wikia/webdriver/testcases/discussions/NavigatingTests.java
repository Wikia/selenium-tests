package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import org.testng.annotations.Test;


@Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
@Test(groups = {"discussions-navigation"})
public class NavigatingTests extends NewTestTemplate {

  /**
   * ANONS ON MOBILE SECTION
   */

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanClickOnPostAuthorUsername() {
    clickingOnPostAuthorUsernameLoadsUserPage();
  }


  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNavigateToPostDetailsPageByClickingOnPost() {
    clickingPostContentRedirectsToPostDetailsPage();
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanClickOnPostAuthorAvatar() {
    clickingOnPostAuthorAvatarLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickOnPostAuthorAvatar() {
    clickingOnPostAuthorAvatarLoadsUserPage();
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickOnPostAuthorUsername() {
    clickingOnPostAuthorUsernameLoadsUserPage();
  }


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
