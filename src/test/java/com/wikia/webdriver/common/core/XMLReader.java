package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

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
      throw new ConfigurationRuntimeException("Cannot find a file with credentials");
    }

    try {
      Parameters params = new Parameters();
      FileBasedConfigurationBuilder<XMLConfiguration> builder =
              new FileBasedConfigurationBuilder<>(XMLConfiguration.class).configure(params.fileBased().setFile(file));
      org.apache.commons.configuration2.Configuration config = builder.getConfiguration();
      return config.getString(key);
    } catch (ConfigurationException e) {
      throw new ConfigurationRuntimeException(e);
    }
  }

  public static String getValue(String key) {
    return getValue(defaultConfigFile, key);
  }
}
