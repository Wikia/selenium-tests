package com.wikia.webdriver.common.core.api;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;

public class ArticleContent extends ApiCall {

  private static String secret;
  private String baseURL = new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
      + "/wikia.php?controller=Wikia\\Helios\\SampleController&method=edit&title=";
  private ArrayList<BasicNameValuePair> params = new ArrayList<>();
  private User user = User.STAFF;

  public ArticleContent() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    if (StringUtils.isBlank(secret)) {
      secret = XMLReader.getValue(configFile, "edit_controller.secret");
    }
    this.params.add(new BasicNameValuePair("summary", "SUMMARY_QM"));
    this.params.add(new BasicNameValuePair("secret", secret));
  }

  /**
   * Push content, overriding a default user
   * @param user
   */
  public ArticleContent(User user) {
    File configFile = new File(Configuration.getCredentialsFilePath());
    if (StringUtils.isBlank(secret)) {
      secret = XMLReader.getValue(configFile, "edit_controller.secret");
    }
    this.params.add(new BasicNameValuePair("summary", "SUMMARY_QM"));
    this.params.add(new BasicNameValuePair("secret", secret));

    this.user = user;
  }

  @Override
  protected String getURL() {
    return URL_STRING;
  }

  @Override protected User getUser() {
    return user;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {

    return params;
  }

  public void push(String text, String articleTitle) {
    URL_STRING = baseURL + articleTitle;
    params.add(new BasicNameValuePair("text", text));
    call();
  }

  public void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public void push() {
    push(PageContent.ARTICLE_TEXT, TestContext.getCurrentMethodName());
  }

  public ArticleContent clear(String articleTitle) {
    push("", articleTitle);

    return this;
  }

  public void clear() {
    push("", TestContext.getCurrentMethodName());
  }
}
