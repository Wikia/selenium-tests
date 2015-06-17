package com.wikia.webdriver.common.core.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author: Bogna 'bognix' Knychała
 */

public class ManualConfiguration extends AbstractConfiguration {

  private Map<String, String> config;

  public ManualConfiguration() {
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

  @Override
  public String getBrowser() {
    return config.get("browser");
  }

  @Override
  public String getEnv() {
    return config.get("env");
  }

  @Override
  public String getWikiName() {
    return config.get("wikiName");
  }

  @Override
  public File getCaptchaFile() {
    return new File(config.get("captchaPath"));
  }

  @Override
  public String getPlatformVersion() {
    return config.get("platformVersion");
  }

  @Override
  public String getPlatform() {
    return config.get("platform");
  }

  @Override
  public String getDeviceId() {
    return config.get("deviceId");
  }

  @Override
  public String geMobileConfig() {
    return config.get("mobileConfig");
  }

  @Override
  public String getCredentialsFilePath() {
    return config.get("credentialsPath");
  }

  @Override
  public String getQS() {
    return config.get("qs");
  }

  @Override
  public String getAppiumIp() {
    return config.get("appiumIp");
  }

  @Override
  public String getDeviceName() {
    return config.get("deviceName");
  }

  @Override
  public String getDisableFlash() {
    return String.valueOf(config.get("disableFlash"));
  }
}
