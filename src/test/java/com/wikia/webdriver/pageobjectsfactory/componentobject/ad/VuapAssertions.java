package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class VuapAssertions {

  private static final long MAX_AUTOPLAY_MOVIE_START_DELAY = 5L;

  private VuapAssertions() {
    throw new IllegalAccessError("Utility class");
  }

  public static void verifyVideoUnmuteAndMute(final AutoplayVuap vuap) {
    Assert.assertTrue(vuap.isMuted(), "Video should be muted.");

    vuap.unmute();
    Assert.assertTrue(vuap.isUnmuted(), "Video should be unmuted.");

    vuap.mute();
    Assert.assertTrue(vuap.isMuted(), "Video should be muted.");
  }

  public static void verifyVideoTimeIsProgressing(final AutoplayVuap vuap) {
    vuap.pause();

    final double currentTime = vuap.getCurrentTime();
    final double indicatorCurrentTime = vuap.getIndicatorCurrentTime();

    playVideoForOneSecond(vuap);

    vuap.pause();

    Assert.assertTrue(currentTime < vuap.getCurrentTime(), "Video should be played.");
    Assert.assertTrue(indicatorCurrentTime > vuap.getIndicatorCurrentTime(), "Video time indicator should move.");
  }

  public static void verifyVideoPlay(final AutoplayVuap vuap) {
    vuap.pause();

    Assert.assertTrue(vuap.hasStarted(), "VUAP did not automatically played when page was opened.");
    Assert.assertEquals(vuap.findTitle(), "Advertisement", "VUAP video title is not Advertisement.");
  }

  public static void verifyReplyButtonDisplayedAfterVideoEnds(final AutoplayVuap vuap, long maxVideoDuration) {
    vuap.waitForVideoToStart(MAX_AUTOPLAY_MOVIE_START_DELAY);
    vuap.waitForVideoToEnd(maxVideoDuration);
  }

  public static void verifyReplyButtonDisplayedAfterVideoClose(final AutoplayVuap vuap, long maxVideoDuration) {
    vuap.waitForVideoToEnd(maxVideoDuration);
  }

  private static void playVideoForOneSecond(final AutoplayVuap vuap) {
    vuap.play();
    try {
      TimeUnit.MILLISECONDS.sleep(200);
    } catch (InterruptedException x) {
      // ignore this exception
    }
  }
}
