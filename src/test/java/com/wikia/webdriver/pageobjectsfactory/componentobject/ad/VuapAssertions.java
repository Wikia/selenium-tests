package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class VuapAssertions {

  private static final long MAX_AUTOPLAY_MOVIE_START_DELAY = 5L;
  private static final int PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD = 28;
  private static final int EXPECTED_PERCENTAGE_DIFFERENCE_IN_VIDEO_AD_HEIGHT = 40;

  private VuapAssertions() {
    throw new IllegalAccessError("Utility class");
  }

  public static void verifyVideoUnmuteAndMute(final AutoplayVuap vuap) throws InterruptedException {
    vuap.play();
    TimeUnit.SECONDS.sleep(1);
    vuap.togglePause();

    vuap.mute();
    vuap.play();
    TimeUnit.SECONDS.sleep(1);
    vuap.togglePause();
    Assert.assertTrue(vuap.isMuted(), "Video should be muted.");

    vuap.unmute();
    vuap.play();
    TimeUnit.SECONDS.sleep(1);
    vuap.togglePause();
    Assert.assertTrue(vuap.isUnmuted(), "Video should be unmuted.");

    vuap.mute();
    vuap.play();
    TimeUnit.SECONDS.sleep(1);
    vuap.togglePause();
    Assert.assertTrue(vuap.isMuted(), "Video should be muted.");
  }

  public static void verifyVideoTimeIsProgressing(final AutoplayVuap vuap) throws InterruptedException {
    vuap.togglePause();
    final double startProgressBarWidth = vuap.getProgressBarWidth();
    vuap.play();
    TimeUnit.SECONDS.sleep(3);
    vuap.togglePause();

    Assert.assertTrue(startProgressBarWidth < vuap.getProgressBarWidth(), "Video time indicator should move.");
  }

  public static void verifyVideoPlay(final AutoplayVuap vuap) {
    Assert.assertTrue(vuap.isPauseLayerVisible(), "VUAP did not automatically played when page was opened.");
    Assert.assertTrue(vuap.isColourVuapVideoAdVisible(), "VUAP video ad has wrong colour");
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

    vuap.closeWithJS();
    Assert.assertTrue(vuap.isPauseLayerNotVisible(), "Pause layer hidden after closing");
  }

  public static void verifyVideoAdSize(AutoplayVuap vuap, double videoAdHeight,
                                       double adSlotHeight, long maxAutoplayDuration) {
    Assert.assertTrue(vuap.isVideoAdBiggerThanImageAd(videoAdHeight, adSlotHeight));

    vuap.play();

    vuap.waitForVideoToEnd(maxAutoplayDuration);

    double adSlotHeightAfterVideoClose = vuap.getAdSlotHeight();

    Assert.assertEquals(adSlotHeight, adSlotHeightAfterVideoClose);
  }

  public static boolean isVideoAdBiggerThanImageAd(double videoHeight, double imageHeight) {
    int percentResult = (int)Math.round(100-(100/(videoHeight/imageHeight)));
    return percentResult == PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD;
  }

  public static void verifyIsResolvedStateDisplayed(double defaultVideoHeight, double resolvedVideoHeight) {
    Assert.assertEquals(
        getStatesPercentageDifference(defaultVideoHeight, resolvedVideoHeight),
        EXPECTED_PERCENTAGE_DIFFERENCE_IN_VIDEO_AD_HEIGHT,
        String.format(
            "Resolved video size should be decreased by %s%%, actual difference rate: %s%% defaultVideoHeight: %s, resolvedVideoHeight: %s",
            EXPECTED_PERCENTAGE_DIFFERENCE_IN_VIDEO_AD_HEIGHT,
            getStatesPercentageDifference(defaultVideoHeight, resolvedVideoHeight),
            defaultVideoHeight,
            resolvedVideoHeight
        )
    );
  }

  private static int getStatesPercentageDifference(double defaultVideoHeight, double resolvedVideoHeight) {
    return (int) Math.round(100 - (100 / (defaultVideoHeight / resolvedVideoHeight)));
  }
}
