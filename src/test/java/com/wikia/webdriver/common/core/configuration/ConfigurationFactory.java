package com.wikia.webdriver.common.core.configuration;

/**
 * @author Bogna 'bognix' Knychala
 */
public class ConfigurationFactory {

  private ConfigurationFactory() {

  }

  public static AbstractConfiguration getConfig() {
    return new Configuration();
  }
}
