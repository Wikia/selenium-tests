package com.wikia.webdriver.common.core.drivers.Browsers;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidBrowser extends BrowserAbstract {

  private static AndroidDriver mobileDriver;

  @Override
  public EventFiringWebDriver getInstance() {
    DesiredCapabilities destCaps = new DesiredCapabilities();
    destCaps.setCapability("deviceName", Configuration.getDeviceName());
    URL url = null;
    try {
      url = new URL("http://" + Configuration.getAppiumIp() + "/wd/hub");
    } catch (MalformedURLException e) {
      PageObjectLogging.log("getAndroindInstance", e, false);
    }
    mobileDriver = new AndroidDriver(url, destCaps);

    return new EventFiringWebDriver(mobileDriver);
  }

  public static AndroidDriver getMobileDriver() {
    return mobileDriver;
  }
}
