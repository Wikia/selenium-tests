package com.wikia.webdriver.common.core.configuration;


import java.io.File;

/**
 * @author Bogna 'bognix' Knychała
 */
public class POMConfiguration extends AbstractConfiguration {

  private String browser;
  private String env;
  private String wikiName;
  private String captchaPath;
  private String credentialsFilePath;

  public POMConfiguration() {
    browser = System.getProperty("browser");
    if (browser == null || browser.isEmpty()) {
      browser = "FF"; //Set default value to Firefox;
    }

    env = System.getProperty("env");
    if (env == null || env.isEmpty()) {
      env = "prod"; //Set default value to production
    }

    wikiName = System.getProperty("wikiName");
    if (wikiName == null || wikiName.isEmpty()) {
      wikiName = "mediawiki119"; //Set default value to mediawiki119
    }

    credentialsFilePath = System.getProperty("config");
    captchaPath = System.getProperty("captcha");
  }

  @Override
  public String getBrowser() {
    return this.browser;
  }

  @Override
  public String getEnv() {
    return this.env;
  }

  @Override
  public String getWikiName() {
    return this.wikiName;
  }

  @Override
  public File getCaptchaFile() {
    return new File(captchaPath);
  }

  @Override
  public String getPlatformVersion() {
    return System.getProperty("platformVersion");
  }

  @Override
  public String getPlatform() {
    return System.getProperty("platform");
  }

  @Override
  public String getDeviceId() {
    return System.getProperty("deviceId");
  }

  @Override
  public String geMobileConfig() {
    return System.getProperty("mobileConfig");
  }

  @Override
  public String getCredentialsFilePath() {
    return this.credentialsFilePath;
  }

  @Override
  public String getQS() {
    return System.getProperty("qs");
  }

  @Override
  public String getAppiumIp() {
    return System.getProperty("appiumIp");
  }

  @Override
  public String getDeviceName() {
    return System.getProperty("deviceName");
  }

  @Override
  public String getDisableFlash() {
    return System.getProperty("disableFlash");
  }
}
