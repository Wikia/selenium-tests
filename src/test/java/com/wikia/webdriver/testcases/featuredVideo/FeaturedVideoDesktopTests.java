package com.wikia.webdriver.testcases.featuredVideo;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo.FeaturedVideoComponentObject;

import org.testng.annotations.Test;

@Test(groups = {"FeaturedVideoDesktop"})
@Execute(onWikia = "featuredvideo", asUser = User.ANONYMOUS)
public class FeaturedVideoDesktopTests extends NewTestTemplate {

  @Test
  public void videoIsPresentOnArticle() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isFeaturedVideo());
  }

  @Test
  public void videoTitleIsVisible() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(video.getTitle(), "Why Jon Snow Should Rule Westeros");
  }

  @Test
  public void videoSubtitleIsVisible() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertEquals(
        video.getSubtitle(),
        "Jon Snow is the leader Westeros needs. This is why he should rule the Seven Kingdoms."
    );
  }

  @Test
  public void videoIsPlaying() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay();

    Assertion.assertTrue(video.isVideoPlaying());
  }

  @Test
  public void videoIsPaused() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .clickPause();

    Assertion.assertFalse(video.isVideoPaused());
  }

  @Test
  public void videoIsAutoplayed() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(true)
        .openWikiArticle("FeaturedVideo")
        .openSettingsMenu();

    Assertion.assertTrue(video.isAutoplayOn());
  }

  @Test
  public void videoIsNotAutoplayed() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo")
        .clickPlay()
        .openSettingsMenu();

    Assertion.assertFalse(video.isAutoplayOn());
  }

}
