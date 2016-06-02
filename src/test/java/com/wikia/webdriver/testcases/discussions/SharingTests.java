package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class SharingTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";
  private static final String[] expected_networks_for_english_language =
      new String[]{"Facebook", "Twitter", "Reddit", "Tumblr"};

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanSeeSocialNetworkIcons")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeeSocialNetworkIcons() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanSeeSocialNetworkIconsInPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeSocialNetworkIconsInPost() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);}

  @Test(groups = "discussions-anonUserOnDesktopCanSeeSocialNetworkIcons", enabled = false)
  @RelatedIssue(issueID = "SOC-2567")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeSocialNetworkIcons() {
    socialNetworkIconsAreDisplayed(expected_networks_for_english_language);}

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSeeSocialNetworkIcons")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeeSocialNetworkIcons() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeeSocialNetworkIconsInPost", enabled = false)
  @RelatedIssue(issueID = "SOC-2567")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIconsInPost() {
    toggleShareIconClickDisplaysSocialNetworkIcons(expected_networks_for_english_language);
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeeSocialNetworkIcons", enabled = false)
  @RelatedIssue(issueID = "SOC-2567")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIcons() {
    socialNetworkIconsAreDisplayed(expected_networks_for_english_language);
  }

  /**
   * TESTING METHODS SECTION
   */

  private void toggleShareIconClickDisplaysSocialNetworkIcons(String[] expectedSocialNetworks) {
    Post post = new PostsListPage().open().getPost();
    int postIndex = 0;
    post.clickShareIcon(postIndex);
    String[] currentSocialNetworks = post.getSocialNetworkIconClasses(postIndex);

    for (int i = 0; i < expectedSocialNetworks.length; i++) {
      String currentSocialNetwork = currentSocialNetworks[i];
      String expectedSocialNetwork = expectedSocialNetworks[i];

      Assertion.assertEquals(
          currentSocialNetwork.toLowerCase(), expectedSocialNetwork.toLowerCase(),
          "Expected network not found on its position. Note that the order of social buttons "
          + "matters, as defined in requirements. Missing network:" + expectedSocialNetwork
      );
    }
  }

  private void socialNetworkIconsAreDisplayed(String[] expectedSocialNetworks) {
    PostDetailsPage postDetails = new PostDetailsPage().open();
    String[] currentSocialNetworks = postDetails.getPost().getSocialNetworkIconsClasses();

    for (int i = 0; i < expectedSocialNetworks.length; i++) {
      String currentSocialNetwork = currentSocialNetworks[i];
      String expectedSocialNetwork = expectedSocialNetworks[i];

      Assertion.assertEquals(
          currentSocialNetwork.toLowerCase(), expectedSocialNetwork.toLowerCase(),
          "Expected network not found on its position. Note that the order of social buttons "
          + "matters, as defined in requirements. Missing network:" + expectedSocialNetwork
      );
    }
  }
}
