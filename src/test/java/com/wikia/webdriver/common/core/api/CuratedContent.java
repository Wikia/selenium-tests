package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class CuratedContent extends ApiCall {

  public CuratedContent() {
  }

  @Override protected String getURL() {
    return new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
           + "/wikia.php?controller=CuratedContent&method=setCuratedContentData";
  }

  @Override protected User getUser() {
     return User.STAFF;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {
    return null;
  }

  /**
   * Clear Curated Content of current wiki Sets empty array as value of wgWikiaCuratedContent
   * wikifactory variable.
   *
   * Add to test body: new CuratedContent().clear();
   */
  public void clear() {
    call();
  }
}
