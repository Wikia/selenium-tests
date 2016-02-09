package com.wikia.webdriver.common.core.drivers.Browsers;

import com.wikia.webdriver.common.core.ExtHelper;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FirefoxBrowser extends BrowserAbstract {

  private static FirefoxProfile firefoxProfile;

  @Override
  public WikiaWebDriver setInstance(){
    // Windows 8 requires to set webdriver.firefox.bin system variable
    // to path where executive file of FF is placed
    if ("WINDOWS 8".equalsIgnoreCase(System.getProperty("os.name"))) {
      System.setProperty("webdriver.firefox.bin", "c:" + File.separator + "Program Files (x86)"
                                                  + File.separator + "Mozilla Firefox"
                                                  + File.separator + "Firefox.exe");
    }

    // Check if user who is running tests have write access in ~/.mozilla dir and home dir
    if ("LINUX".equals(System.getProperty("os.name").toUpperCase())) {
      File homePath = new File(System.getenv("HOME") + File.separator);
      File mozillaPath = new File(homePath + File.separator + ".mozilla");
      File tmpFile;
      if (mozillaPath.exists()) {
        try {
          tmpFile = File.createTempFile("webdriver", null, mozillaPath);
        } catch (IOException ex) {
          PageObjectLogging.log("Can't create file", ex, false);
          throw new WebDriverException("Can't create file in path: %s".replace("%s",
                                                                               mozillaPath
                                                                                   .getAbsolutePath()));
        }
      } else {
        try {
          tmpFile = File.createTempFile("webdriver", null, homePath);
        } catch (IOException ex) {
          PageObjectLogging.log("Can't create file", ex, false);
          throw new WebDriverException("Can't create file in path: %s".replace("%s",
                                                                               homePath
                                                                                   .getAbsolutePath()));
        }
      }
      tmpFile.delete();
    }

    firefoxProfile =
        new FirefoxProfile(
            new File(ClassLoader.getSystemResource("FirefoxProfiles/Default").getPath()));

    if ("true".equals(Configuration.getPageLoadStrategy())) {
      firefoxProfile.setPreference("webdriver.load.strategy", "unstable");
    }

    if ("true".equals(Configuration.getDisableFlash())) {
      firefoxProfile.setPreference("plugin.state.flash", 0);
    }

    caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

    // Adding console logging for FF browser
    setBrowserLogging(Level.SEVERE);

    ExtHelper.addExtensions(Configuration.getExtensions());

    return new WikiaWebDriver(new FirefoxDriver(caps), false);
  }

  public static FirefoxProfile getProfile(){
    return firefoxProfile;
  }
}
