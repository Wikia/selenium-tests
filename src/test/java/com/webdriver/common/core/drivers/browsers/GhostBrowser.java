package com.webdriver.common.core.drivers.browsers;

import java.io.File;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.drivers.BrowserAbstract;

public class GhostBrowser extends BrowserAbstract {

  @Override
  public void setOptions() {
    String phantomJSBinaryName;
    String osName = System.getProperty("os.name").toUpperCase();

    if (osName.contains("WINDOWS")) {
      phantomJSBinaryName = "phantomjs.exe";

      File phantomJSBinary =
          new File("." + File.separator + "src" + File.separator + "test" + File.separator
              + "resources" + File.separator + "PhantomJS" + File.separator + phantomJSBinaryName);

      caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
          phantomJSBinary.getAbsolutePath());
    }
  }

  @Override
  public PageWebDriver create() {
    return new PageWebDriver(new PhantomJSDriver(caps), false);
  }

  @Override
  public void addExtension(String extensionName) {
    // No extensions are applied to PhantomJS browser
  }
}
