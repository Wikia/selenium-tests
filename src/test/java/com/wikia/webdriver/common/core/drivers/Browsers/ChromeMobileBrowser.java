package com.wikia.webdriver.common.core.drivers.Browsers;

import com.wikia.webdriver.common.core.ExtHelper;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.driverprovider.UserAgentsRegistry;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ChromeMobileBrowser extends BrowserAbstract{

  private static ChromeOptions chromeOptions = new ChromeOptions();

  @Override
  public WikiaWebDriver setInstance() {
    String chromeBinaryPath = "";
    String osName = System.getProperty("os.name").toUpperCase();
    String emulator = Configuration.getEmulator();

    if (osName.contains("WINDOWS")) {
      chromeBinaryPath = "/chromedriver_win32/chromedriver.exe";
    } else if (osName.contains("MAC")) {
      chromeBinaryPath = "/chromedriver_mac32/chromedriver";

      File chromedriver =
          new File(ClassLoader.getSystemResource("ChromeDriver" + chromeBinaryPath).getPath());

      // set application user permissions to 455
      chromedriver.setExecutable(true);
    } else if (osName.contains("LINUX")) {
      chromeBinaryPath = "/chromedriver_linux64/chromedriver";

      File chromedriver =
          new File(ClassLoader.getSystemResource("ChromeDriver" + chromeBinaryPath).getPath());

      // set application user permissions to 455
      chromedriver.setExecutable(true);
    }

    System.setProperty("webdriver.chrome.driver",
                       new File(ClassLoader.getSystemResource("ChromeDriver" + chromeBinaryPath)
                                    .getPath())
                           .getPath());

    // TODO change mobile tests to use @UserAgent annotation
    if (Browser.CHROME_MOBILE_MERCURY.equals(Configuration.getBrowser())) {
      chromeOptions
          .addArguments("--user-agent=" + UserAgentsRegistry.IPHONE.getUserAgent());
    }

    if ("true".equals(Configuration.getDisableFlash())) {
      chromeOptions.addArguments("disable-bundled-ppapi-flash");
      chromeOptions.addArguments("process-per-site");
      chromeOptions.addArguments("start-maximized");
      chromeOptions.addArguments("disable-notifications");
    }

    if (!"null".equals(emulator)) {
      Map<String, String> mobileEmulation = new HashMap<>();
      mobileEmulation.put("deviceName", emulator);
      chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
    }

    caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

    setBrowserLogging(Level.SEVERE);

    ExtHelper.addExtensions(Configuration.getExtensions());

    return new WikiaWebDriver(new ChromeDriver(caps), true);
  }

  public static ChromeOptions getChromeOptions() {
    return chromeOptions;
  }
}
