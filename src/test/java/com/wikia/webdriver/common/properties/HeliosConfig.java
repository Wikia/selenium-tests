package com.wikia.webdriver.common.properties;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * Created by Ludwik on 2015-04-24.
 */
public class HeliosConfig {

  private static String clientSecret;
  private static String clientId;
  private static String baseURL;

  private static void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    String env = Configuration.getEnvType();
    clientId = XMLReader.getValue(configFile, "helios." + env + ".client_id");
    clientSecret = XMLReader.getValue(configFile, "helios." + env + ".client_secret");
    baseURL = XMLReader.getValue(configFile, "helios." + env + ".base_url");
  }

  public static String getClientId() {
    if (StringUtils.isBlank(clientId)) {
      init();
    }
    return clientId;
  }

  public static String getClientSecret() {
    if (StringUtils.isBlank(clientSecret)) {
      init();
    }
    return clientSecret;
  }

  private static String getBaseURL() {
    if (StringUtils.isBlank(baseURL)) {
      init();
    }
    return baseURL;
  }

  public static String getUrl(HeliosController controller) {
    return getBaseURL() + controller.getController();
  }

  public enum GrantType {
    PASSWORD("password"), REFRESH_TOKEN("refresh_token");

    private final String grantType;

    GrantType(String grantType) {
      this.grantType = grantType;
    }

    public String getGrantType() {
      return grantType;
    }
  }

  public enum HeliosController {
    TOKEN("token"), INFO("info");

    private final String controller;

    HeliosController(String controller) {
      this.controller = controller;
    }

    public String getController() {
      return controller;
    }
  }
}
