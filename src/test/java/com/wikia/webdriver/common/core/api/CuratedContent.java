package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class CuratedContent extends ApiCall {

  private CuratedContent() {
  }

  private static String secret;
  private static String baseURL;

  private static void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    secret = XMLReader.getValue(configFile, "edit_controller.secret");
    baseURL =
        new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
        + "wikia.php?controller=CuratedContent&method=setData";
    URL_STRING = baseURL;
    user = User.STAFF;
  }

  /*
  * Clear Curated Content of current wiki
  * Sets empty array as value of wgWikiaCuratedContent wikifactory variable.
  */
  public static void clear() {
    if (StringUtils.isBlank(secret)) {
      init();
    }
    call();
  }
}
