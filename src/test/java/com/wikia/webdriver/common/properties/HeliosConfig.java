package com.wikia.webdriver.common.properties;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class HeliosConfig {

  private static String baseURL;

  private static void init() {
    File configFile = new File(Configuration.getCredentialsFilePath());
    String env = Configuration.getEnvType();
    baseURL = XMLReader.getValue(configFile, "helios." + env + ".base_url");
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
    TOKEN("token"), INFO("info"), USERS("users");

    private final String controller;

    HeliosController(String controller) {
      this.controller = controller;
    }

    public String getController() {
      return controller;
    }
  }
}
