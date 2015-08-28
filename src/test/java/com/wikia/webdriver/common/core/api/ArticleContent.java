package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class ArticleContent extends ApiCall {

  private static String secret;
  private static String baseURL;

  public ArticleContent() {
  }

  @Override protected String getURL() {
    return null;
  }

  @Override protected User getUser() {
    return null;
  }

  @Override protected List<NameValuePair> getParams() {
    return null;
  }

  private void init() {

  }

  public void push(String text, String articleTitle) {
    if (StringUtils.isBlank(secret)) {
      init();
    }

    URL_STRING = baseURL + articleTitle;

//    nvps = new ArrayList<>();
//    nvps.add(new BasicNameValuePair("text", text));
//    nvps.add(new BasicNameValuePair("summary", "SUMMARY_QM"));
//    nvps.add(new BasicNameValuePair("secret", secret));

    call();
  }

  public  void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public  void clear(String articleTitle) {
    push("", articleTitle);
  }

  public  void clear() {
    push("", TestContext.getCurrentMethodName());
  }
}
