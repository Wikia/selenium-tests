package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

public class VuapVideos {

  public static final String PORVATA_VAST = "porvata_vast";

  public static final String VAST_VIDEO = "', '<?xml version=\"1.0\" encoding=\"UTF-8\"?><VAST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance" +
      "\" xsi:noNamespaceSchemaLocation=\"vast_2.0.1-creativeView.xsd\" version=\"2.0\"><Ad id=\"wikia\"><InLine>" +
      "<AdSystem>Wikia</AdSystem><AdTitle>Wikia Red Test Video</AdTitle><Impression></Impression><Creatives>" +
      "<Creative><Linear><Duration>00:00:03</Duration><MediaFiles><MediaFile type=\"video/webm\" height=\"360\" width=\"640\" delivery=\"progressive\">" +
      "<![CDATA[http://public.adeng.wikia-dev.pl/porvata/red.webm]]></MediaFile></MediaFiles></Linear></Creative></Creatives></InLine></Ad></VAST>'";

  public static final String VAST_VIDEO_WITH_TRACKING_EVENTS = "', '<?xml version=\"1.0\" encoding=\"UTF-8\"?><VAST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance" +
      "\" xsi:noNamespaceSchemaLocation=\"vast_2.0.1-creativeView.xsd\" version=\"2.0\"><Ad id=\"wikia\"><InLine>" +
      "<AdSystem>Wikia</AdSystem><AdTitle>Wikia Red Test Video</AdTitle><Impression></Impression><Creatives>" +
      "<Creative><Linear><Duration>00:00:03</Duration>" +
      "<TrackingEvents><Tracking event=\"start\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_STARTS&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"firstQuartile\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_25&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"midpoint\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_50&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"thirdQuartile\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_75&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"complete\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_COMPLETES&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"pause\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_PAUSE&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"resume\"><![CDATA[https://track.loopme.me/sj/tr?et=VIDEO_RESUME&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0]]>" +
      "</Tracking><Tracking event=\"close\"><![CDATA[https://track.loopme.me/sj/tr?et=AD_CLOSE&id=58943f98cbf0b260f4fa1ff0&meta=MjcxMDk6NjMxNzoxMjM0&playtime=[CONTENTPLAYHEAD]]]>" +
      "</Tracking></TrackingEvents> " +
      "<MediaFiles><MediaFile type=\"video/webm\" height=\"360\" width=\"640\" delivery=\"progressive\">" +
      "<![CDATA[http://public.adeng.wikia-dev.pl/porvata/red.webm]]></MediaFile></MediaFiles></Linear></Creative></Creatives></InLine></Ad></VAST>'";

  private VuapVideos() {
    throw new IllegalAccessError("Utility class");
  }
}