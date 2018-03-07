package com.wikia.webdriver.testcases.featuredvideo;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo.FeaturedVideoMobileComponentObject;

import org.testng.annotations.Test;

@Test(groups = {"FeaturedVideoMobile"})
@Execute(onWikia = "featuredvideo", asUser = User.ANONYMOUS)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
public class FeaturedVideoMobileTests extends NewTestTemplate {

  @Test
  public void videoIsPresentOnArticle() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isFeaturedVideoDisplayed());
  }
  @Test
  public void videoTitleIsVisible() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(video.getTitle(), "Papuga atakuje!");
  }

  @Test
  public void videoIsPlaying() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isVideoPlaying());
  }

  @Test
  public void videoIsPaused() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay()
        .activatePlayerOptions()
        .clickPause();

    Assertion.assertTrue(video.isVideoPaused());
  }

  @Test
  public void autoplayToggleIsOn() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .openWikiArticle("FeaturedVideo")
        .setAutoplayCookie(true)
        .clickPause()
        .openSettingsMenu();

    Assertion.assertTrue(video.isAutoplayOn());
  }

  @Test
  public void autoplayToggleIsOff() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay()
        .activatePlayerOptions()
        .clickPause()
        .openSettingsMenu();

    Assertion.assertFalse(video.isAutoplayOn());
  }

  @Test
  public void videoMutedWhenAutoplayed() {
    FeaturedVideoMobileComponentObject video = new FeaturedVideoMobileComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .activatePlayerOptions()
        .clickPause()
        .showControlBar();

    Assertion.assertTrue(video.isVolumeMuted());
  }

}
