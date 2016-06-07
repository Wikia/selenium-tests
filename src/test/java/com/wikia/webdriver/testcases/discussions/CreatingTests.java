package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class CreatingTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)

  public void anonUserOnMobileCanNotWriteNewPost() {
    userMustBeLoggedInToUsePostCreator();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOndESKTOPCanNotWriteNewPost() {
    ;
  }
/**
 * TESTING METHODS SECTION
 */

  private void userMustBeLoggedInToUsePostCreator() {
    PostsCreatorMobile postsCreator = new PostsListPage().open().getPostsCreatorMobile();
    postsCreator.clickPostCreator();

    Assertion.assertTrue(postsCreator.isModalDialogVisible());

    postsCreator.clickOkButtonInSignInDialog().clickPostCreator();

    Assertion.assertTrue(postsCreator.isModalDialogVisible());

    postsCreator.clickSignInButtonInSignInDialog();
  }


}
