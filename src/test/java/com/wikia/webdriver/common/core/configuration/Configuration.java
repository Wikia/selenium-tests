package com.wikia.webdriver.common.core.configuration;

import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.exceptions.TestEnvInitFailedException;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configuration handler. This Class should handle run configuration and global properties.
 * Configuration handling:
 * <ol>
 * <li>Look for the property key in testConfig map, if key is present, return the value</li>
 * <li>Look for the property key in system properties - return value of this property if key
 * present
 * </li>
 * <li>If no System Property is found - value is provided from configuration files
 * (config_default.yml and config.yml). Values provided in config.yml, are overriding values from
 * config_default.yml</li>
 * </ol>
 */
public class Configuration {

  public static final String DEFAULT_LANGUAGE = "en";
  private static final String DEFAULT_CONFIG_FILE_NAME = "config_default.yml";
  private static final String LOCAL_CONFIG_FILE_NAME = "config.yml";
  private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());
  private static final String SELENIUM_CONFIG_REPO_CONFIG_FILE_NAME = "config.xml";
  private static Map<String, String> defaultConfig;
  private static Map<String, String> testConfig = new HashMap<>();
  @Getter(lazy = true)
  private static final String domain = getEnvType().getDomain();

  public Configuration() {}

  private static Map<String, String> readConfiguration() {
    if (defaultConfig == null) {
      Yaml yaml = new Yaml();

      try {
        defaultConfig = (Map<String, String>) yaml.load(new FileInputStream(new File(
            DEFAULT_CONFIG_FILE_NAME)));
      } catch (FileNotFoundException e) {
        throw new TestEnvInitFailedException(String.format("CANNOT FIND DEFAULT CONFIG FILE : %s",
                                                           DEFAULT_CONFIG_FILE_NAME
        ), e);
      }

      File localConfigFile = new File(LOCAL_CONFIG_FILE_NAME);
      if (localConfigFile.exists()) {
        try {
          defaultConfig.putAll((Map<String, String>) yaml.load(new FileInputStream(localConfigFile)));
        } catch (FileNotFoundException e) {
          LOGGER.log(Level.INFO, "local config file not found", e);
        }
      } else {
        LOGGER.log(Level.INFO, "local config file does not exist");
      }
    }

    return defaultConfig;
  }

  public static String getPropertyFromFile(String propertyName) {
    return "null".equals(String.valueOf(readConfiguration().get(propertyName))) ? null
                                                                                : String.valueOf(
                                                                                    readConfiguration()
                                                                                        .get(
                                                                                            propertyName));
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

  public static String getDpr() {
    return getProp("dpr");
  }

  public static String getDefaultWikiName() {
    return getPropertyFromFile("wikiName");
  }

  public static String getEnv() {
    return getProp("env");
  }

  public static String getWikiName() {
    return getProp("wikiName");
  }

  public static String getWikiLanguage() {
    return getProp("language");
  }

  public static String getPlatform() {
    return getProp("platform");
  }

  public static String getMobileWikiVersion() { return getProp("mobileWikiVersion"); }

  public static String getCredentialsFilePath() {
    return Paths.get(getProp("seleniumConfigPath"), SELENIUM_CONFIG_REPO_CONFIG_FILE_NAME)
        .toString();
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

  public static Boolean getAdsData() { return "true".equals(getProp("adsData")); }

  public static String getMockAds() {
    return getProp("mockAds");
  }

  public static Boolean getForceHttps() {
    return "true".equalsIgnoreCase(getProp("forceHttps"));
  }

  public static Boolean isUnsafePageLoad() {
    return "true".equalsIgnoreCase(getProp("unsafePageLoad"));
  }

  public static Boolean getForceFandomDomain() {
    return "true".equalsIgnoreCase(getProp("forceFandomDomain"));
  }

  public static Boolean getForceWikiOrg() {
    return "true".equalsIgnoreCase(getProp("forceWikiaOrg"));
  }

  public static Emulator getEmulator() {
    Emulator emulatorToUse = Emulator.DEFAULT;
    if (TestContext.getCurrentTestMethod() != null && TestContext.getCurrentTestMethod()
        .getDeclaringClass()
        .isAnnotationPresent(InBrowser.class)) {
      emulatorToUse = TestContext.getCurrentTestMethod()
          .getDeclaringClass()
          .getDeclaredAnnotation(InBrowser.class)
          .emulator();
    }
    if (TestContext.getCurrentTestMethod() != null && TestContext.getCurrentTestMethod()
        .isAnnotationPresent(InBrowser.class)) {
      emulatorToUse = TestContext.getCurrentTestMethod()
          .getDeclaredAnnotation(InBrowser.class)
          .emulator();
    }
    return emulatorToUse;
  }

  public static String useMITM() {
    if (getForceHttps()) {
      return "true";
    }

    return getProp("useMITM");
  }

  public static String useZap() {
    return getProp("useZapProxy");
  }

  public static String getPageLoadStrategy() {
    return getProp("unstablePageLoadStrategy");
  }

  public static Credentials getCredentials() {
    return new Credentials();
  }

  public static EnvType getEnvType() {
    return getEnvType(getEnv());
  }

  public static EnvType getEnvType(String env) {
    String[] sandboxEnvs = new String[]{"verify", "preview", "sandbox", "stable", "adeng"};

    if (env.contains("prod")) {
      return EnvType.PROD;
    } else if (StringUtils.indexOfAny(env, sandboxEnvs) != -1) {
      return EnvType.SANDBOX;
    } else if (env.contains("dev")) {
      return EnvType.DEV;
    }
    return EnvType.PROD;
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

  public static boolean useProxy() {
    return Boolean.valueOf(getProp("useProxy")) || StringUtils.isNotBlank(getCountryCode())
           || Boolean.valueOf(getProp("useZapProxy"));
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
                             Integer.valueOf(size.split("x")[1])
        );
      }
    } else {
      throw new WebDriverException("browser size: " + size + " is not a proper value");
    }
  }

  public static String[] getExtensions() {
    String exts = getProp("extensions");

    if (StringUtils.isEmpty(exts)) {
      return new String[]{};
    }

    ArrayList<String> res = new ArrayList<>();
    for (String ext : exts.replace("[", "").replace("]", "").split(",")) {
      res.add(ext.trim());
    }

    return res.toArray(new String[res.size()]);
  }

  public static String getDisableCommunityPageSalesPitchDialog() {
    return getProp("disableCommunityPageSalesPitchDialog");
  }

  public static HttpHost getBorderProxy() {

    EnvType envType = getEnvType(getEnv());
    File configFile = new File(getCredentialsFilePath());

    if (envType.equals(EnvType.DEV)) {
      return new HttpHost(
          XMLReader.getValue(configFile, "border.poz.address"),
          Integer.parseInt(XMLReader.getValue(configFile, "border.poz.port")),
          XMLReader.getValue(configFile, "border.poz.protocol")
      );
    } else {
      return new HttpHost(
          XMLReader.getValue(configFile, "border.sjc.address"),
          Integer.parseInt(XMLReader.getValue(configFile, "border.sjc.port")),
          XMLReader.getValue(configFile, "border.sjc.protocol")
      );
    }
  }

  //TODO: Get rid of replacing wikia with fandom
  public static String getServicesUrl() {
    final String environment = Configuration.getEnvType().getKey();
    File configurationFile = new File(Configuration.getCredentialsFilePath());
    final String url = XMLReader.getValue(configurationFile, "services." + environment).replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX);
    final String properUrl = Configuration.getForceFandomDomain() ? url.replace("wikia", "fandom") : url;
    Objects.requireNonNull(url, "Please check if your configuration file contains url for service ");
    return properUrl;
  }
}
