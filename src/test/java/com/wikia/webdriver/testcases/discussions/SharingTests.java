package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ShareDialog;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = {"discussions-sharing"})
public class SharingTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";
  private static final List<String> EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE =
      Arrays.asList("facebook", "twitter", "reddit", "tumblr");

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanSeeSocialNetworkIcons")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeeSocialNetworkIconsOnPostListPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Test(groups = "discussions-anonUserOnMobileCanSeeSocialNetworkIcons")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeeSocialNetworkIconsOnPostDetailsPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanSeeSocialNetworkIcons")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeSocialNetworkIconsOnPostListPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Test(groups = "discussions-anonUserOnDesktopCanSeeSocialNetworkIcons")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeSocialNetworkIconsOnPostDetailsPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSeeSocialNetworkIcons")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeeSocialNetworkIconsOnPostListPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanSeeSocialNetworkIcons")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeeSocialNetworkIconsOnPostDetailsPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeeSocialNetworkIconsOnPostList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIconsOnPostList() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeeSocialNetworkIconsOnPostDetails")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIconsOnPostDetails() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * TESTING METHODS SECTION
   */

  private List<String> findSocialNetworksNamesForFirstPostOnPostListPage() {
    return findSocialNetworksNamesForFirstPostOn(new PostsListPage().open());
  }

  private List<String> findSocialNetworksNamesForFirstPostOn(final PageWithPosts page) {
    return page.getPost()
        .findNewestPost()
        .clickMoreOptions()
        .clickSharePostOption()
        .getSocialIcons()
        .stream()
        .map(ShareDialog.SocialIcon::getSocialNetworkName)
        .collect(toList());
  }

  private List<String> findSocialNetworksNamesForFirstPostOnPostDetailsPage() {
    PostDetailsPage page = new PostDetailsPage().openDefaultPost();
    return findSocialNetworksNamesForFirstPostOn(page);
  }
}
