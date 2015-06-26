package com.wikia.webdriver.common.core.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Created by Ludwik on 2015-06-26.
 */
public class Configuration extends AbstractConfiguration {

  private Map<String, String> config;

  public Configuration() {
    Yaml yaml = new Yaml();
    InputStream input = null;
    try {
      input = new FileInputStream(new File("config.yml"));
    } catch (FileNotFoundException ex) {
      try {
        input = new FileInputStream(new File("config_sample.yml"));
      } catch (FileNotFoundException ex2) {
        System.out.println("CAN'T LOCATE CONFIG FILE");
      }
    }
    config = (Map<String, String>) yaml.load(input);
  }

  private String getProp(String propertyName) {
    String value =
        System.getProperty(propertyName) != null ? System.getProperty(propertyName) : config
            .get(propertyName);
    return value;
  }

  @Override
  public String getBrowser() {
    return getProp("browser");
  }

  @Override
  public String getEnv() {
    return getProp("env");
  }

  @Override
  public String getWikiName() {
    return getProp("wikiName");
  }

  @Override
  public File getCaptchaFile() {
    return new File(getProp("captchaPath"));
  }

  @Override
  public String getPlatformVersion() {
    return getProp("platformVersion");
  }

  @Override
  public String getPlatform() {
    return getProp("platform");
  }

  @Override
  public String getDeviceId() {
    return getProp("deviceId");
  }

  @Override
  public String geMobileConfig() {
    return getProp("mobileConfig");
  }

  @Override
  public String getCredentialsFilePath() {
    return getProp("credentialsPath");
  }

  @Override
  public String getQS() {
    return getProp("qs");
  }

  @Override
  public String getAppiumIp() {
    return getProp("appiumIp");
  }

  @Override
  public String getDeviceName() {
    return getProp("deviceName");
  }

  @Override
  public String getDisableFlash() {
    return String.valueOf(getProp("disableFlash"));
  }
}
