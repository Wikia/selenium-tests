package com.wikia.webdriver.common.core.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.yaml.snakeyaml.Yaml;

import com.wikia.webdriver.common.core.exceptions.TestEnvInitFailedException;
import com.wikia.webdriver.common.properties.Credentials;

/**
 * Configuration handler. This Class should handle run configuration and global properties.
 * Configuration handling:
 * <ol>
 * <li>Look for the property key in testConfig map, if key is present, return the value</li>
 * <li>Look for the property key in system properties - return value of this property if key present
 * </li>
 * <li>If no System Property is found - value is provided from configuration files
 * (config_default.yml and config.yml). Values provided in config.yml, are overriding values from
 * config_default.yml</li>
 * </ol>
 */
public class Configuration {

  private static final String DEFAULT_CONFIG_FILE_NAME = "config_default.yml";
  private static final String LOCAL_CONFIG_FILE_NAME = "config.yml";
  private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

  private static Map<String, String> defaultConfig;
  private static Map<String, String> testConfig = new HashMap<>();

  private Configuration() {}

  private static Map<String, String> readConfiguration() {
    if (defaultConfig == null) {
      Yaml yaml = new Yaml();

      try {
        defaultConfig = (Map<String, String>) yaml
            .load(new FileInputStream(new File(DEFAULT_CONFIG_FILE_NAME)));
      } catch (FileNotFoundException e) {
        throw new TestEnvInitFailedException(
            String.format("CANNOT FIND DEFAULT CONFIG FILE : %s", DEFAULT_CONFIG_FILE_NAME), e);
      }

      File localConfigFile = new File(LOCAL_CONFIG_FILE_NAME);
      if (localConfigFile.exists()) {
        try {
          defaultConfig
              .putAll((Map<String, String>) yaml.load(new FileInputStream(localConfigFile)));
        } catch (FileNotFoundException e) {
          LOGGER.log(Level.INFO, "local config file not found", e);
        }
      } else {
        LOGGER.log(Level.INFO, "local config file does not exist");
      }
    }

    return defaultConfig;
  }

  private static String getPropertyFromFile(String propertyName) {
    return "null".equals(String.valueOf(readConfiguration().get(propertyName))) ? null
        : String.valueOf(readConfiguration().get(propertyName));
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

  /**
   * @return null if window is supposed to be maximised, Dimension if any other size is demanded
   */
  public static Dimension getBrowserSize() {
    String size = getProp("browserSize");

    if (StringUtils.isNotBlank(size) || "maximised".equals(size) || size.split("x").length == 2) {
      if ("maximised".equals(size)) {
        return null;
      } else {
        return new Dimension(Integer.valueOf(size.split("x")[0]),
            Integer.valueOf(size.split("x")[1]));
      }
    } else {
      throw new WebDriverException("browser size: " + size + " is not a proper value");
    }
  }

  public static String[] getExtensions() {
    String exts = getProp("extensions");

    if (StringUtils.isEmpty(exts)) {
      return new String[] {};
    }

    ArrayList<String> res = new ArrayList<>();
    for (String ext : exts.replace("[", "").replace("]", "").split(",")) {
      res.add(ext.trim());
    }

    return res.toArray(new String[res.size()]);
  }
}
