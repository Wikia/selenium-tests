package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class VideoAd {
  private static final String VAST_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><VAST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance" +
          "\" xsi:noNamespaceSchemaLocation=\"vast_2.0.1-creativeView.xsd\" version=\"2.0\"><Ad id=\"wikia\"><InLine>" +
          "<AdSystem>Wikia</AdSystem><AdTitle>%s</AdTitle><Impression></Impression><Creatives>" +
          "<Creative><Linear><Duration>%s</Duration><MediaFiles><MediaFile type=\"%s\" width=\"%d\" height=\"%d\" delivery=\"progressive\">" +
          "<![CDATA[%s]]></MediaFile></MediaFiles></Linear></Creative></Creatives></InLine></Ad></VAST>";

  private String title = "Test Video";
  private int duration = 3;
  private String type = "video/mp4";
  private String url = "http://public.adeng.wikia-dev.pl/porvata/red_10s.mp4";
  private int width = 640;
  private int height = 480;

  VideoAd(String url, int width, int height, int duration) {
    this.url = url;
    this.width = width;
    this.height = height;
    this.duration = duration;
  }

  @Override
  public String toString() {
    return getVastXML();
  }

  public String getVastXML() {
    return String.format(VAST_XML, title, parseDuration(), type, width, height, url);
  }

  private String parseDuration() {
    NumberFormat formatter = new DecimalFormat("00");
    int hours = (int) Math.floor(duration / 3600);
    int minutes = (int) Math.floor(duration / 60 % 60);
    int seconds = (int) Math.floor(duration % 60);

    return String.format("%s:%s:%s", formatter.format(hours), formatter.format(minutes), formatter.format(seconds));
  }
}
