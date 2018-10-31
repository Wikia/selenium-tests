package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class DeleteMWVideo extends ApiCall {

  private String videoTitle;

  public DeleteMWVideo(String videoTitile) {
    this.videoTitle = videoTitile;
  }

  @Override
  protected String getURL() {
    return UrlBuilder.stripUrlFromEnvSpecificPartAndDowngrade(
        UrlBuilder.createUrlBuilder().getUrl()
        + "/wikia.php?controller=VideoHandler&method=removeVideo&format=json");
  }

  @Override
  protected User getUser() {
    return User.SUS_CHAT_STAFF2;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {
    ArrayList<BasicNameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("title", videoTitle));
    return params;
  }

  @Override
  protected String getUserName() {
    return null;
  }
}
