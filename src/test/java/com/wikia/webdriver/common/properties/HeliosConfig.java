package com.wikia.webdriver.common.properties;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.wikia.webdriver.common.core.XMLFunctions;
import com.wikia.webdriver.common.core.configuration.ConfigurationFactory;

/**
 * Created by Ludwik on 2015-04-24.
 */
public class HeliosConfig {

  private static String clientSecret;
  private static String clientId;
  private static String baseURL;

  private static void init() {
    File configFile = new File(ConfigurationFactory.getConfig().getCredentialsFilePath());
    clientId = XMLFunctions.getXMLConfiguration(configFile, "helios.client_id");
    clientSecret = XMLFunctions.getXMLConfiguration(configFile, "helios.client_secret");
    baseURL = XMLFunctions.getXMLConfiguration(configFile, "helios.base_url");
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
