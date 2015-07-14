package com.wikia.webdriver.common.core;

import java.io.File;

import org.apache.commons.configuration.XMLConfiguration;

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
    } catch (Exception e) {
      return e.toString();
    }
  }
}
