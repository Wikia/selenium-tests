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
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CategoryPill;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-creating-posts")
public class CreatingPostTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanNotWriteNewPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotWriteNewPost() {
    userOnMobileMustBeLoggedInToUsePostCreator();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanNotWriteNewPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotWriteNewPost() {
    userOnDesktopMustBeLoggedInToUsePostCreator();
  }

  /**
   * LOGGED-IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUsersDesktopPosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserCanExpandPostEditorOnDesktop() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostCreatorDesktop();

    postsCreator.clickPostCreator();

    Assertion.assertTrue(postsCreator.isExpanded());
    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = "discussions-loggedInUsersDesktopPosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSaveEmptyPost() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostCreatorDesktop();

    postsCreator
            .clickPostCreator()
            .closeGuidelinesMessage();

    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = "myTest")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanAddPostWithoutTitle() {
    final long timestamp = System.nanoTime();
    final String description = "Automated test, timestamp " + timestamp;

    PostsListPage postListPage = new PostsListPage().open();
    PostsCreatorDesktop postsCreator = postListPage.getPostCreatorDesktop();

    CategoryPill categoryPill = postsCreator.clickPostCreator()
            .closeGuidelinesMessage()
            .fillPostDescriptionWith(description)
            .clickAddCategoryButton()
            .findCategoryOnPosition(0);

    categoryPill.click();

    Assertion.assertTrue(postsCreator.isPostButtonActive());

    postsCreator.clickSubmitButton()
                .waitForSpinnerToAppearAndDisappear();

    Post posts = postListPage.getPost();
    WebElement webElement = posts.waitForPostToAppearWith(description)
            .getTheNewestPost();

    if (null != webElement) {
      Assertion.assertStringContains(webElement.findElement(By.className("timestamp")).getText(), "now");
      Assertion.assertEquals(webElement.findElement(By.className("post-details-link")).getText(), description);
      Assertion.assertStringContains(webElement.findElement(By.className("post-category-name")).getText(), categoryPill.getName());
    } else {
      Assertion.fail("Post with description \"" + description + "\" was not added/found.");
    }
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userOnMobileMustBeLoggedInToUsePostCreator() {
    PostsCreatorMobile postsCreator = new PostsListPage().open().getPostsCreatorMobile();

    Assertion.assertTrue(postsCreator.clickPostCreator().isModalDialogVisible());

    postsCreator.clickOkButtonInSignInDialog();

    Assertion.assertTrue(postsCreator.clickPostCreator().isModalDialogVisible());

    postsCreator.clickSignInButtonInSignInDialog();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.JOIN_PAGE));
  }

  private void userOnDesktopMustBeLoggedInToUsePostCreator() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostCreatorDesktop();

    Assertion.assertTrue(postsCreator.clickPostCreator().isModalDialogVisible());

    postsCreator.clickOkButtonInSignInDialog();

    Assertion.assertTrue(postsCreator.clickPostCreator().isModalDialogVisible());

    postsCreator.clickSignInButtonInSignInDialog();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.REGISTER_PAGE));
  }
}
