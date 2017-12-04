package com.webdriver.common.core.drivers.browsers;

import java.net.MalformedURLException;
import java.net.URL;

import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.drivers.BrowserAbstract;
import com.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AndroidBrowser extends BrowserAbstract {

  private static AndroidDriver mobileDriver;

  public static AndroidDriver getMobileDriver() {
    return mobileDriver;
  }

  @Override
  public void setOptions() {
    DesiredCapabilities destCaps = new DesiredCapabilities();
    destCaps.setCapability("deviceName", Configuration.getDeviceName());
    URL url = null;
    try {
      url = new URL("http://" + Configuration.getAppiumIp() + "/wd/hub");
    } catch (MalformedURLException e) {
      PageObjectLogging.log("getAndroindInstance", e, false);
    }
    mobileDriver = new AndroidDriver(url, destCaps);

  }

  @Override
  public PageWebDriver create() {
    return new PageWebDriver(mobileDriver, true);
  }

  @Override
  public void addExtension(String extensionName) {
    // No extensions are applied to android
  }
}
