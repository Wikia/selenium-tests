package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

@Test(groups = {"Discussions", "Sharing"})
public class Sharing extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";
  private static final String[] expected_networks_for_english_language =
      new String[]{"Facebook", "Twitter", "Reddit", "Tumblr"};

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = {"Sharing_001"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeeSocialNetworkIcons() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = {"Sharing_002"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeSocialNetworkIconsInPost() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);}

  @Test(groups = {"Sharing_003"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeSocialNetworkIcons() {
    socialNetworkIconsAreDisplayed(expected_networks_for_english_language);}

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = {"Sharing_004"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeeSocialNetworkIcons() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = {"Sharing_005"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIconsInPost() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);
  }

  @Test(groups = {"Sharing_006"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIcons() {
    socialNetworkIconsAreDisplayed(expected_networks_for_english_language);
  }


  /**
   * TESTING METHODS SECTION
   */

  public void toggleShareIconClickDisplaysSocialNetworkIcons(String[] expectedSocialNetworks) {
    PostsListPage postList = new PostsListPage(driver).open();
    int postIndex = 0;
    postList.clickShareIcon(postIndex);
    String[] currentSocialNetworks = postList.getSocialNetworkIconClasses(postIndex);
    for (int i = 0; i < expectedSocialNetworks.length; i++) {
      String currentSocialNetwork = currentSocialNetworks[i];
      String expectedSocialNetwork = expectedSocialNetworks[i];
      Assertion.assertEqualsIgnoreCase(
          currentSocialNetwork, expectedSocialNetwork,
          "Expected network not found on its position. Note that the order of social buttons "
          + "matters, as defined in requirements. Missing network:" + expectedSocialNetwork
      );
    }
  }

  public void socialNetworkIconsAreDisplayed(String[] expectedSocialNetworks) {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    String[] currentSocialNetworks = postDetails.getSocialNetworkIconsClasses();
    for (int i = 0; i < expectedSocialNetworks.length; i++) {
      String currentSocialNetwork = currentSocialNetworks[i];
      String expectedSocialNetwork = expectedSocialNetworks[i];
      Assertion.assertEqualsIgnoreCase(
          currentSocialNetwork, expectedSocialNetwork,
          "Expected network not found on its position. Note that the order of social buttons "
          + "matters, as defined in requirements. Missing network:" + expectedSocialNetwork
      );
    }
  }
}
