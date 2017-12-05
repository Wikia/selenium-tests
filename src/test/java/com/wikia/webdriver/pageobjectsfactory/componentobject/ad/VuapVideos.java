package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

public class VuapVideos {
  public static final VideoAd DEFAULT =  new VideoAd("https://public.adeng.wikia-dev.pl/porvata/red.webm", 640, 480, 3);
  public static final VideoAd VIDEO_10s = new VideoAd("https://public.adeng.wikia-dev.pl/porvata/red_10s.mp4", 640, 480, 10);

  private VuapVideos() {
    throw new IllegalAccessError("Utility class");
  }
}