package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationRuntimeException;
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

    if (!file.exists() || file.isDirectory()) {
      throw new ConfigurationRuntimeException("Cannot find a file with credentials");;
    }

    try {
      XMLConfiguration xml = new XMLConfiguration(file);
      return xml.getString(key);
    } catch (ConfigurationException e) {
      throw new ConfigurationRuntimeException("Error while reading XML config");
    }
  }

  public static String getValue(String key) {
    return getValue(defaultConfigFile, key);
  }
}
