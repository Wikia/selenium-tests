package com.wikia.webdriver.common.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultBackoffStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * Created by Ludwik Kaźmierczak on 2015-02-12.
 */
public class YoutubeVideoProvider {

  /**
   * This method returns latest youtube video(added no longer then hour ago) for a specified query.
   * This one is using a YouTube Data API (v3) - see for reference -
   * https://developers.google.com/youtube/v3/
   *
   * @param searchQuery
   * @return
   */
  public static YoutubeVideo getLatestVideoForQuery(String searchQuery) {
    HttpClient httpclient =
        HttpClientBuilder.create().setConnectionBackoffStrategy(new DefaultBackoffStrategy())
            .disableAutomaticRetries().build();

    List<NameValuePair> nvps = new ArrayList<>();

    nvps.add(new BasicNameValuePair("key", "AIzaSyDmRJPcPgPMvxHU2xqjj9xIAsM2nY8PPTw"));
    nvps.add(new BasicNameValuePair("part", "snippet"));
    nvps.add(new BasicNameValuePair("order", "date"));
    nvps.add(new BasicNameValuePair("maxResults", "10"));
    nvps.add(new BasicNameValuePair("q", searchQuery));
    nvps.add(new BasicNameValuePair("publishedAfter", DateTime.now(DateTimeZone.forID("UTC"))
        .minusMinutes(60).toString()));

    HttpGet httpPost =
        new HttpGet("https://www.googleapis.com/youtube/v3/search?"
            + URLEncodedUtils.format(nvps, "utf-8"));

    String videoTitle = null;
    String videoUrl = null;

    try {
      HttpResponse response = httpclient.execute(httpPost);

      HttpEntity entity = response.getEntity();

      ReadContext responseValue = JsonPath.parse(EntityUtils.toString(entity));

      videoTitle = responseValue.read("$.items[0].snippet.title");
      String videoId = responseValue.read("$.items[0].id.videoId");

      videoUrl = new URLCodec().encode("https://www.youtube.com/watch?v=" + videoId);

    } catch (IOException | EncoderException e) {
      PageObjectLogging.log("A problem occurred while receiving a YouTube video", e.getMessage(),
          false);
    }

    return new YoutubeVideo(videoTitle, videoUrl);
  }
}
