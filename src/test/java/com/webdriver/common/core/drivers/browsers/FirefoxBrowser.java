package com.webdriver.common.core.drivers.browsers;

import java.io.File;
import java.io.IOException;

import com.webdriver.common.core.ExtHelper;
import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.drivers.BrowserAbstract;
import com.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowser extends BrowserAbstract {

  private FirefoxProfile firefoxProfile;

  @Override
  public void setOptions() {
    // Windows 8 requires to set webdriver.firefox.bin system variable
    // to path where executive file of FF is placed
    if ("WINDOWS 8".equalsIgnoreCase(System.getProperty("os.name"))) {
      System.setProperty("webdriver.firefox.bin", "c:" + File.separator + "Program Files (x86)"
          + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");
    }

    // Check if user who is running tests have write access in ~/.mozilla dir and home dir
    if ("LINUX".equalsIgnoreCase(System.getProperty("os.name"))) {
      File homePath = new File(System.getenv("HOME") + File.separator);
      File mozillaPath = new File(homePath + File.separator + ".mozilla");
      File tmpFile;
      if (mozillaPath.exists()) {
        try {
          tmpFile = File.createTempFile("webdriver", null, mozillaPath);
        } catch (IOException ex) {
          PageObjectLogging.log("Can't create file", ex, false);
          throw new WebDriverException(
              "Can't create file in path: %s".replace("%s", mozillaPath.getAbsolutePath()));
        }
      } else {
        try {
          tmpFile = File.createTempFile("webdriver", null, homePath);
        } catch (IOException ex) {
          PageObjectLogging.log("Can't create file", ex, false);
          throw new WebDriverException(
              "Can't create file in path: %s".replace("%s", homePath.getAbsolutePath()));
        }
      }
      tmpFile.delete();
    }

    firefoxProfile = new FirefoxProfile(
        new File(ClassLoader.getSystemResource("FirefoxProfiles/Default").getPath()));

    if ("true".equals(Configuration.getPageLoadStrategy())) {
      firefoxProfile.setPreference("webdriver.load.strategy", "unstable");
    }

    if ("true".equals(Configuration.getDisableFlash())) {
      firefoxProfile.setPreference("plugin.state.flash", 0);
    }
  }

  @Override
  public PageWebDriver create() {
    caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
    caps.setCapability("marionette", true);

    return new PageWebDriver(new FirefoxDriver(caps), server, false);
  }

  @Override
  public void addExtension(String extensionName) {
    try {
      firefoxProfile.addExtension(ExtHelper.findExtension(extensionName, "xpi"));
    } catch (IOException e) {
      PageObjectLogging.logError("Can't initialize extensions", e);
    }
  }
}
