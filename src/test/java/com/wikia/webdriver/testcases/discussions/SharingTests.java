package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ShareDialog;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
@Test(groups = {"discussions-sharing"})
public class SharingTests extends NewTestTemplate {

  private static final List<String> EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE =
      Arrays.asList("facebook", "twitter", "reddit", "tumblr");

  private PostEntity.Data existingPost;

  @BeforeSuite
  private void setUp() {
    String siteId = Utils.excractSiteIdFromWikiName(MercuryWikis.DISCUSSIONS_5);
    existingPost = DiscussionsClient
      .using(User.USER_4, driver)
      .createPostWithUniqueData(siteId);
  }

  /**
   * ANONS ON MOBILE SECTION
   */

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeeSocialNetworkIconsOnPostListPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeeSocialNetworkIconsOnPostDetailsPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSeeSocialNetworkIconsOnPostListPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSeeSocialNetworkIconsOnPostDetailsPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSeeSocialNetworkIconsOnPostListPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSeeSocialNetworkIconsOnPostDetailsPage() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostDetailsPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void loggedInUserOnDesktopCanSeeSocialNetworkIconsOnPostList() {
    List<String> socialNetworkNames = findSocialNetworksNamesForFirstPostOnPostListPage();

    Assertion.assertEquals(socialNetworkNames, EXPECTED_SOCIAL_NETWORKS_FOR_ENGLISH_LANGUAGE,
        "Displayed social networks are different than expected.");
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
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
    PostDetailsPage page = new PostDetailsPage().open(existingPost.getId());
    return findSocialNetworksNamesForFirstPostOn(page);
  }
}
