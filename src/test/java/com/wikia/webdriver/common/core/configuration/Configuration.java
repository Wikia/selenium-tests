package com.wikia.webdriver.common.core.configuration;

import com.wikia.webdriver.common.core.exceptions.TestEnvInitFailedException;
import com.wikia.webdriver.common.properties.Credentials;

import org.apache.commons.lang.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration handler. This Class should handle run configuration and global properties.
 * Configuration handling: 1. If there is a System Property specified - return value of this
 * property 2. If no System Property is found - value is provided from configuration files
 * (config_sample.yml if no config.yml provided) NOTE: system property should override ANY property
 * specified in config files
 */
public class Configuration {

  private static Map<String, String> config;
  private static Map<String, String> testConfig = new HashMap<>();

  private Configuration() {
  }

  private static Map<String, String> readConfiguration() {
    if (config == null) {
      Yaml yaml = new Yaml();
      InputStream input = null;
      try {
        input = new FileInputStream(new File("config.yml"));
      } catch (FileNotFoundException ex) {
        try {
          input = new FileInputStream(new File("config_sample.yml"));
        } catch (FileNotFoundException ex2) {
          throw new TestEnvInitFailedException("CAN'T LOCATE CONFIG FILE");
        }
      }
      config = (Map<String, String>) yaml.load(input);
    }
    return config;
  }

  private static String getPropertyFromFile(String propertyName) {
    return "null".equals(String.valueOf(readConfiguration().get(propertyName))) ? null : String
        .valueOf(readConfiguration().get(propertyName));
  }

  private static String getProp(String propertyName) {
    if (testConfig.get(propertyName) == null) {
      return System.getProperty(propertyName) != null ? System.getProperty(propertyName)
                                                      : getPropertyFromFile(propertyName);
    } else {
      return testConfig.get(propertyName);
    }
  }

  public static String getBrowser() {
    return getProp("browser");
  }

  public static String getEnv() {
    return getProp("env");
  }

  public static String getWikiName() {
    return getProp("wikiName");
  }

  public static String getPlatform() {
    return getProp("platform");
  }

  public static String getCredentialsFilePath() {
    return getProp("credentialsPath");
  }

  public static String getQS() {
    return getProp("qs");
  }

  public static String getAppiumIp() {
    return getProp("appiumIp");
  }

  public static String getDeviceName() {
    return getProp("deviceName");
  }

  public static String getDisableFlash() {
    return getProp("disableFlash");
  }

  public static String getJSErrorsEnabled() {
    return getProp("jsErrorsEnabled");
  }

  public static String getLogEnabled() {
    return getProp("logEnabled");
  }

  public static Credentials getCredentials() {
    return new Credentials();
  }

  public static String getEnvType() {
    if (getEnv().contains("prod")) {
      return "prod";
    } else if (getEnv().contains("verify")) {
      return "verify";
    } else if (getEnv().contains("preview")) {
      return "preview";
    } else if (getEnv().contains("sandbox")) {
      return "sandbox";
    } else if (getEnv().contains("dev")) {
      return "dev";
    }
    return "";
  }

  public static void setTestValue(String key, String value) {
    testConfig.put(key, value);
  }

  public static void clearCustomTestProperties() {
    testConfig.clear();
  }

  public static String getCountryCode() {
    return getProp("countryCode");
  }

  public static String[] getExtensions() {
    return toArray(getProp("extensions"));
  }

  private static String[] toArray(String str) {
    if (StringUtils.isEmpty(str)) {
      return new String[]{};
    }
    return str.replace("[", "").replace("]", "").split(",");
  }
}
