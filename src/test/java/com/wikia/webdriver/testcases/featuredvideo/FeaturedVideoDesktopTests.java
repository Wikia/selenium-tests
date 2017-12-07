package com.wikia.webdriver.testcases.featuredvideo;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo.FeaturedVideoDesktopComponentObject;

import org.testng.annotations.Test;

@Test(groups = {"FeaturedVideoDesktop"})
@Execute(onWikia = "featuredvideo", asUser = User.ANONYMOUS)
@InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
public class FeaturedVideoDesktopTests extends NewTestTemplate {


  @Test
  public void videoIsPresentOnArticle() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isFeaturedVideoDisplayed());
  }

  @Test
  public void videoTitleIsVisible() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(video.getTitle(), "Papuga atakuje!");
  }

  @Test
  public void videoSubtitleIsVisible() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(
        video.getSubtitle(),
        "Czyli jak wyprowadzic papuge z rownowagi"
    );
  }

  @Test
  public void feedbackIsVisibleWhenVideoPlayed() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay();

    Assertion.assertTrue(video.isVideoFeedbackDisplayed());
  }

  @Test
  public void videoIsPlaying() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isVideoPlaying());
  }

  @Test
  public void videoIsPaused() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause();

    Assertion.assertTrue(video.isVideoPaused());
  }

  @Test
  public void feedbackIsNotVisibleWhenVideoPaused() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause();

    Assertion.assertFalse(video.isVideoFeedbackNotDisplayed());
  }

  @Test
  public void autoplayToggleIsOn() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .openSettingsMenu();

    Assertion.assertTrue(video.isAutoplayOn());
  }

  @Test
  public void autoplayToggleIsOff() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay()
        .clickPause()
        .openSettingsMenu();

    Assertion.assertFalse(video.isAutoplayOn());
  }

  @Test
  public void videoMutedWhenAutoplayed() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .showControlBar();

    Assertion.assertTrue(video.isVolumeMuted());
  }

  @Test
  public void videoQualityCanBeChanged() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .showControlBar()
        .openSettingsMenu()
        .openQualityMenu();

    Assertion.assertTrue(video.isQualityAvailable());

  }

  @Test
  public void videoCaptionsCanBeChanged() {
    FeaturedVideoDesktopComponentObject video = new FeaturedVideoDesktopComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .showControlBar()
        .openSettingsMenu()
        .openCaptionsMenu();

    Assertion.assertTrue(video.areCaptionsAvailable());

  }


}
