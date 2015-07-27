package com.wikia.webdriver.common.core.geoedge;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

import java.io.File;

public class GeoEdgeProxy {

  private static final File CONFIG_FILE = new File(Configuration.getCredentialsFilePath());

  public static String getProxyAddress(String countryCode) {
    return XMLReader.getValue(CONFIG_FILE, "ci.geoedge." + countryCode);
  }
}
