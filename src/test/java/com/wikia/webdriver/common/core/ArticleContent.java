package com.wikia.webdriver.common.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriverException;
import org.seleniumhq.jetty7.util.URIUtil;

import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class ArticleContent {
  private static String secret;
  private static String baseURL;

  private static void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    secret = XMLReader.getValue(configFile, "edit_controller.secret");
    baseURL =
        new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
            + "wikia.php?controller=Wikia\\Helios\\SampleController&method=edit&title=";
  }

  public static void push(String text, String articleTitle) {
    if (StringUtils.isBlank(secret)) {
      init();
    }

    try {
      CloseableHttpClient httpClient = HttpClients.createDefault();

      HttpPost httpPost = new HttpPost(URIUtil.encodePath(baseURL + articleTitle));
      List<NameValuePair> nvps = new ArrayList<>();

      httpPost.addHeader("Authorization", "Bearer " + Helios.getAccessToken(User.STAFF));

      nvps.add(new BasicNameValuePair("text", text));
      nvps.add(new BasicNameValuePair("summary", "SUMMARY_QM"));
      nvps.add(new BasicNameValuePair("secret", secret));

      httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
      httpClient.execute(httpPost);

      PageObjectLogging.log("CONTENT PUSH", "Content pushed to article: " + baseURL + articleTitle,
          true);
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", e.toString(), false);
      throw new WebDriverException("Problem with content pushing");
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", e.toString(), false);
      throw new WebDriverException("Problem with content pushing");
    }
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
