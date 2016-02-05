package com.wikia.webdriver.common.core.drivers.Browsers;

import com.wikia.webdriver.common.core.drivers.BrowserAbstract;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

public class GhostBrowser extends BrowserAbstract {

  @Override
  public EventFiringWebDriver getInstance() {
    String phantomJSBinaryName;
    String OSName = System.getProperty("os.name").toUpperCase();

    if (OSName.contains("WINDOWS")) {
      phantomJSBinaryName = "phantomjs.exe";

      File phantomJSBinary =
          new File("." + File.separator + "src" + File.separator + "test" + File.separator
                   + "resources" + File.separator + "PhantomJS" + File.separator
                   + phantomJSBinaryName);

      caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                         phantomJSBinary.getAbsolutePath());
    }

    return new EventFiringWebDriver(new PhantomJSDriver(caps));
  }
}
