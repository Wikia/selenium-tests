package com.webdriver.common.core;

import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;

public class XMLReader {

  private static final File defaultConfigFile = new File(Configuration.getCredentialsFilePath());

  private XMLReader() {

  }

  /**
   * method used to get credentials from configuration xml
   */
  public static String getValue(File file, String key) {

    try {
      XMLConfiguration xml = new XMLConfiguration(file);
      return xml.getString(key);
    } catch (ConfigurationException e) {
      PageObjectLogging.log("Error while reading XML config", e, false);

      return e.getMessage();
    }
  }

  public static String getValue(String key) {
    return getValue(defaultConfigFile, key);
  }
}
