package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class VuapAssertions {

  private static final long MAX_AUTOPLAY_MOVIE_START_DELAY = 5L;
  private static final int PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD = 28;

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
    vuap.togglePause();
    final double startProgressBarWidth = vuap.getProgressBarWidth();
    playVideoForFewSeconds(vuap);

    Assert.assertTrue(startProgressBarWidth < vuap.getProgressBarWidth(), "Video time indicator should move.");
  }

  public static void verifyVideoPlay(final AutoplayVuap vuap) {
    vuap.togglePause();

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

  public static void verifyVideoClosesAfterTapOnCloseButton(final AutoplayVuap vuap) {
    vuap.play();
    Assert.assertTrue(vuap.isPauseLayerVisible(), "Pause layer visible after clicking play");

    vuap.close();
    Assert.assertTrue(vuap.isPauseLayerNotVisible(), "Pause layer hidden after closing");
  }

  private static void playVideoForFewSeconds(final AutoplayVuap vuap) {
    vuap.play();

    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException x) {
      // ignore this exception
    }

    vuap.togglePause();
  }

  public static void verifyVideoAdSize(AutoplayVuap vuap, double videoAdHeight,
                                       double adSlotHeight, long maxAutoplayDuration) {
    Assert.assertTrue(vuap.isVideoAdBiggerThanImageAd(videoAdHeight, adSlotHeight));

    vuap.play();

    vuap.waitForVideoToEnd(maxAutoplayDuration);

    double adSlotHeightAfterVideoClose = vuap.getAdSlotHeight();

    Assert.assertEquals(adSlotHeight, adSlotHeightAfterVideoClose);
  }

  public static boolean isVideoAdBiggerThanImageAdOasis(double videoHeight, double imageHeight) {
    int percentResult = (int)Math.round(100-(100/(videoHeight/imageHeight)));
    return percentResult == PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD;
  }

  public static boolean isImageAdInCorrectSize(AutoplayVuap vuap) {
    long time = System.currentTimeMillis();
    long endTime = time+3000;
    while(time < endTime) {
      if (vuap.getAdSlotHeight() == vuap.getAdSlotHeight()){
        return true;
      }

      try {
        TimeUnit.MILLISECONDS.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      time = System.currentTimeMillis();
    }
    return false;
  }
}
