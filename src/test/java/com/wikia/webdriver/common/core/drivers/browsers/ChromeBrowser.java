package com.wikia.webdriver.common.core.drivers.browsers;

import com.wikia.webdriver.common.core.ExtHelper;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.driverprovider.UserAgentsRegistry;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChromeBrowser extends BrowserAbstract {

  private static final String CHROMEDRIVER_PATH_FORMAT = "ChromeDriver/chromedriver_%s";
  private static final String CHROMEDRIVER_PATH_MAC =
      String.format(CHROMEDRIVER_PATH_FORMAT, "mac64/chromedriver");
  private static final String CHROMEDRIVER_PATH_LINUX =
      String.format(CHROMEDRIVER_PATH_FORMAT, "linux64/chromedriver");
  private static final String CHROMEDRIVER_PATH_WINDOWS =
      String.format(CHROMEDRIVER_PATH_FORMAT, "win32/chromedriver.exe");
  private ChromeOptions chromeOptions = new ChromeOptions();
  private boolean useMobile = "CHROMEMOBILEMERCURY".equals(Configuration.getBrowser());

  @Override
  public void setOptions() {
    String chromeBinaryPath = "";
    String osName = System.getProperty("os.name").toUpperCase();
    Emulator emulator = Configuration.getEmulator();

    if (osName.contains("WINDOWS")) {
      chromeBinaryPath = CHROMEDRIVER_PATH_WINDOWS;
    } else if (osName.contains("MAC")) {
      chromeBinaryPath = CHROMEDRIVER_PATH_MAC;
    } else if (osName.contains("LINUX")) {
      chromeBinaryPath = CHROMEDRIVER_PATH_LINUX;
    }

    File chromedriver = new File(ClassLoader.getSystemResource(chromeBinaryPath).getPath());

    // set application user permissions to 455
    chromedriver.setExecutable(true);

    System.setProperty("webdriver.chrome.driver", chromedriver.getPath());
    PageObjectLogging.logInfo("Using chromedriver: ", chromedriver.getPath());

    chromeOptions.addArguments("start-maximized");
    chromeOptions.addArguments("disable-notifications");
    chromeOptions.addArguments("process-per-site");
    chromeOptions.addArguments("dns-prefetch-disable");
    chromeOptions.addArguments("allow-running-insecure-content");
    chromeOptions.addArguments("ignore-certificate-errors");

    if ("true".equals(Configuration.getDisableFlash())) {
      chromeOptions.addArguments("disable-bundled-ppapi-flash");
    }

    if (useMobile) {
      chromeOptions.addArguments("--user-agent=" + UserAgentsRegistry.IPHONE.getUserAgent());
    }

    if (!emulator.equals(Emulator.DEFAULT)) {
      Map<String, Object> mobileEmulation = new HashMap<>();
      if(StringUtils.isNotBlank(emulator.getUserAgent())){
        mobileEmulation.put("userAgent", emulator.getUserAgent());
      }
      if (StringUtils.isNotBlank(emulator.getDeviceName())) {
        mobileEmulation.put("deviceName", emulator.getDeviceName());
      } else {
        mobileEmulation.put("deviceMetrics", emulator.getDeviceMetrics());
      }

      chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
    }
  }

  @Override
  public WikiaWebDriver create() {
    caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

    return new WikiaWebDriver(new ChromeDriver(caps), server, useMobile);
  }

  @Override
  public void addExtension(String extensionName) {
    chromeOptions.addExtensions(ExtHelper.findExtension(extensionName, "crx"));
  }
}
