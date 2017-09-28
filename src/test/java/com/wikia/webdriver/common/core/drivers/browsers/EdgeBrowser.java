package com.wikia.webdriver.common.core.drivers.browsers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowser extends BrowserAbstract {

  private EdgeOptions edgeOptions = new EdgeOptions();

  @Override
  public void setOptions() {
    
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
