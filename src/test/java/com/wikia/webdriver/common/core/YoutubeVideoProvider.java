package com.wikia.webdriver.common.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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
 * Created by Ludwik on 2015-02-12.
 */
public class YoutubeVideoProvider {

  public static YoutubeVideo getLatestVideoForQuery(String searchQuerry) {
    HttpClient httpclient =
        HttpClientBuilder.create().setConnectionBackoffStrategy(new DefaultBackoffStrategy())
            .disableAutomaticRetries().build();

    List<NameValuePair> nvps = new ArrayList<NameValuePair>();

    nvps.add(new BasicNameValuePair("key", "AIzaSyDmRJPcPgPMvxHU2xqjj9xIAsM2nY8PPTw"));
    nvps.add(new BasicNameValuePair("part", "snippet"));
    nvps.add(new BasicNameValuePair("order", "date"));
    nvps.add(new BasicNameValuePair("maxResults", "10"));
    nvps.add(new BasicNameValuePair("q", searchQuerry));
    nvps.add(new BasicNameValuePair("publishedAfter", DateTime.now(DateTimeZone.forID("UTC"))
        .minusMinutes(20).toString()));

    HttpGet httpPost =
        new HttpGet("https://www.googleapis.com/youtube/v3/search?"
            + URLEncodedUtils.format(nvps, "utf-8"));

    HttpResponse response = null;

    ReadContext responseValue = null;

    String videoId = null;
    String title = null;
    try {
      response = httpclient.execute(httpPost);


      HttpEntity entity = response.getEntity();

      responseValue = JsonPath.parse(EntityUtils.toString(entity));

      title = responseValue.read("$.items[0].snippet.title");
      videoId = responseValue.read("$.items[0].id.videoId");

    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    PageObjectLogging.log("LOGIN HEADERS: ", response.toString(), true);
    PageObjectLogging.log("LOGIN RESPONSE: ", responseValue.toString(), true);

    return new YoutubeVideo(title, "https://www.youtube.com/watch?v=" + videoId);
  }
}
