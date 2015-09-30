package com.wikia.webdriver.common.core.geoedge;

import java.io.File;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

public class GeoEdgeProxy {

  private static final File CONFIG_FILE = new File(Configuration.getCredentialsFilePath());

  public static String getProxyAddress(String countryCode) {
    return XMLReader.getValue(CONFIG_FILE, "ci.geoedge." + countryCode);
  }
}
