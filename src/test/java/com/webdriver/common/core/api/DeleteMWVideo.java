package com.webdriver.common.core.api;

import java.util.ArrayList;

import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.helpers.User;
import com.webdriver.common.core.url.UrlBuilder;
import org.apache.http.message.BasicNameValuePair;

public class DeleteMWVideo extends ApiCall {

  private String videoTitle;

  public DeleteMWVideo(String videoTitile) {
    this.videoTitle = videoTitile;
  }

  @Override
  protected String getURL() {
    return new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
        + "/wikia.php?controller=VideoHandler&method=removeVideo&format=json";
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
}
