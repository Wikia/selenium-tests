package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CuratedContent {

  private CuratedContent() {
  }

  private static String secret;
  private static String baseURL;
  private static String errorMessage = "Problem with content clearing";

  private static void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    secret = XMLReader.getValue(configFile, "edit_controller.secret");
    baseURL =
        new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
        + "wikia.php?controller=CuratedContent&method=setData";
  }

  /*
  * Clear Curated Content of current wiki
  * Sets empty array as value of wgWikiaCuratedContent wikifactory variable.
  */
  public static void clear() {
    if (StringUtils.isBlank(secret)) {
      init();
    }

    try {
      CloseableHttpClient httpClient = HttpClients.createDefault();

      URL url = new URL(baseURL);

      HttpPost httpPost =
          new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
                               url.getPath(), url.getQuery(), url.getRef()));
      List<NameValuePair> nvps = new ArrayList<>();

      httpPost.addHeader("Authorization", "Bearer " + Helios.getAccessToken(User.STAFF));

      httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
      httpClient.execute(httpPost);

      PageObjectLogging.log("CONTENT CLEAR", "Curated Content cleared on: ", true);
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", e.toString(), false);
      throw new WebDriverException(errorMessage);
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", e.toString(), false);
      throw new WebDriverException(errorMessage);
    } catch (URISyntaxException e) {
      PageObjectLogging.log("URI_SYNTAX EXCEPTION", e.toString(), false);
      throw new WebDriverException(errorMessage);
    }
  }
}
