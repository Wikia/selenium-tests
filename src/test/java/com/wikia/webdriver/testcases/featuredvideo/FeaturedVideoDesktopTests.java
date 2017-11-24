package com.wikia.webdriver.testcases.featuredvideo;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo.FeaturedVideoComponentObject;

import org.testng.annotations.Test;

@Test(groups = {"FeaturedVideoDesktop"})
@Execute(onWikia = "featuredvideo", asUser = User.ANONYMOUS)
@InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
public class FeaturedVideoDesktopTests extends NewTestTemplate {


  @Test
  public void videoIsPresentOnArticle() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isFeaturedVideoDisplayed());
  }

  @Test
  public void videoTitleIsVisible() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(video.getTitle(), "Papuga atakuje!");
  }

  @Test
  public void videoSubtitleIsVisible() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(
        video.getSubtitle(),
        "Czyli jak wyprowadzic papuge z rownowagi"
    );
  }

  @Test
  public void feedbackIsVisibleWhenVideoPlayed() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay();

    Assertion.assertTrue(video.isVideoFeedbackDisplayed());
  }

  @Test
  public void videoIsPlaying() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isVideoPlaying());
  }

  @Test
  public void videoIsPaused() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause();

    Assertion.assertTrue(video.isVideoPaused());
  }

  @Test
  public void feedbackIsNotVisibleWhenVideoPaused() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause();

    Assertion.assertFalse(video.isVideoFeedbackNotDisplayed());
  }

  @Test
  public void autoplayToggleIsOn() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .openSettingsMenu();

    Assertion.assertTrue(video.isAutoplayOn());
  }

  @Test
  public void autoplayToggleIsOff() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay()
        .openSettingsMenu();

    Assertion.assertFalse(video.isAutoplayOn());
  }

  @Test
  public void videoMutedWhenAutoplayed() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .showControlBar();

    Assertion.assertTrue(video.isVolumeMuted());
  }

  @Test
  public void videoQualityCanBeChanged() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
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
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause()
        .showControlBar()
        .openSettingsMenu()
        .openCaptionsMenu();

    Assertion.assertTrue(video.areCaptionsAvailable());

  }


}
