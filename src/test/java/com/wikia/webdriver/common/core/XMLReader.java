package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.logging.LOG;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;

public class XMLReader {

  private XMLReader() {

  }

  /**
   * method used to get credentials from configuration xml
   *
   * @author Karol Kujawiak
   */
  public static String getValue(File file, String key) {

    try {
      XMLConfiguration xml = new XMLConfiguration(file);
      return xml.getString(key);
    } catch (ConfigurationException e) {
      LOG.error("Error while reading XML config", e);

      return e.getMessage();
    }
  }
}
