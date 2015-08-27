package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class ArticleContent extends ApiCall {

  private static String secret;
  private static String baseURL;

  private static void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    secret = XMLReader.getValue(configFile, "edit_controller.secret");
    baseURL =
        new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
        + "wikia.php?controller=Wikia\\Helios\\SampleController&method=edit&title=";
    user = User.STAFF;
  }

  public static void push(String text, String articleTitle) {
    if (StringUtils.isBlank(secret)) {
      init();
    }

    URL_STRING = baseURL + articleTitle;

    nvps = new ArrayList<>();
    nvps.add(new BasicNameValuePair("text", text));
    nvps.add(new BasicNameValuePair("summary", "SUMMARY_QM"));
    nvps.add(new BasicNameValuePair("secret", secret));

    call();
  }

  public static void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public static void clear(String articleTitle) {
    push("", articleTitle);
  }

  public static void clear() {
    push("", TestContext.getCurrentMethodName());
  }
}
