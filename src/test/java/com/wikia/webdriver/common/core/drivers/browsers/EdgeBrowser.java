package com.wikia.webdriver.common.core.drivers.browsers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;

public class EdgeBrowser extends BrowserAbstract {

  private EdgeOptions edgeOptions = new EdgeOptions();

  private static final String EDGEDRIVER_PATH = "EdgeDriver/MicrosoftEdgeDriver.exe";

  @Override
  public void setOptions() {

    File edgedriver = new File(ClassLoader.getSystemResource(EDGEDRIVER_PATH).getPath());
    edgedriver.setExecutable(true);
    System.setProperty("webdriver.edge.driver", edgedriver.getPath());
  }

  @Override
  public WikiaWebDriver create() {
    caps.setCapability(EdgeOptions.CAPABILITY, edgeOptions);

    return new WikiaWebDriver(new EdgeDriver(caps), server, false);
  }

  @Override
  public void addExtension(String extensionName) {

  }

}
