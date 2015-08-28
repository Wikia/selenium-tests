package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.io.File;
import java.net.URL;
import java.util.List;

public class CuratedContent extends ApiCall {

  private CuratedContent() {
  }

  @Override protected String getURL() {
    return "MY AWESOME URL";
  }

  @Override protected User getUser() {
     return User.STAFF;
  }

  @Override protected List<NameValuePair> getParams() {
    return null;
  }

  private static String secret;
  private static String baseURL;

  //TODO: you don't need it
  private void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    secret = XMLReader.getValue(configFile, "edit_controller.secret");
    baseURL =
        new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
        + "wikia.php?controller=CuratedContent&method=setData";
    URL_STRING = baseURL;
  }

  /*
  * Clear Curated Content of current wiki
  * Sets empty array as value of wgWikiaCuratedContent wikifactory variable.
  */
  public void clear() {
    if (StringUtils.isBlank(secret)) {
      init();
    }
    call();
  }
}
