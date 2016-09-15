package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;

public class TemplateContent extends ApiCall {

  private static String secret;
  private static String baseURL;
  private static String URL_STRING;
  private static ArrayList<BasicNameValuePair> PARAMS;

  public TemplateContent() {
    baseURL = new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
              + "/wikia.php?controller=Wikia\\Helios\\SampleController&method=edit&title=";

    File configFile = new File(Configuration.getCredentialsFilePath());
    if (StringUtils.isBlank(secret)) {
      secret = XMLReader.getValue(configFile, "edit_controller.secret");
    }
    PARAMS = new ArrayList<BasicNameValuePair>();
    PARAMS.add(new BasicNameValuePair("summary", "SUMMARY_QM"));
    PARAMS.add(new BasicNameValuePair("secret", secret));
  }

  @Override protected String getURL() {
    return URL_STRING;
  }

  @Override protected User getUser() {
    return User.STAFF;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {

    return PARAMS;
  }

  public void push(String text, String templateTitle) {
    URL_STRING = String.format("%s%s:%s", baseURL, PageContent.TEMPLATE_NAMESPACE, templateTitle);
    PARAMS.add(new BasicNameValuePair("text", text));
    call();
  }

  public void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public void push() {
    push(PageContent.ARTICLE_TEXT, TestContext.getCurrentMethodName());
  }

  public TemplateContent clear(String templateTitle) {
    push("", templateTitle);

    return this;
  }

  public  void clear() {
    push("", TestContext.getCurrentMethodName());
  }
}
