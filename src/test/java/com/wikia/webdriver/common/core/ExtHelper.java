package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/*
 Browser extensions helper
 */
public class ExtHelper {

  private static ImmutableMap<Object, Object> ext = ImmutableMap.builder()
      .put("CHROME", ".crx")
      .put("CHROMEMOBILEMERCURY", ".crx")
      .put("FF", ".xpi")
      .build();
  private final FirefoxProfile firefoxProfile;
  private final ChromeOptions chromeOptions;

  public ExtHelper(FirefoxProfile firefoxProfile, ChromeOptions chromeOptions) {
    this.firefoxProfile = firefoxProfile;
    this.chromeOptions = chromeOptions;
  }

  public void addExtension(String name) {
    if (ext.containsKey(Configuration.getBrowser())) {
      name = name + ext.get(Configuration.getBrowser());
    }

    File extension = findExtension(name);

    try {

      if ("FF".equalsIgnoreCase(Configuration.getBrowser())) {
        firefoxProfile.addExtension(extension);
      }

      if (Configuration.getBrowser().contains("CHROME")) {
        chromeOptions.addExtensions(extension);
      }

    } catch (IOException e) {
      PageObjectLogging.log("Error with adding extension", e, false);
    }
  }

  public void addExtensions(String[] names) {
    for (String name : names) {
      addExtension(name);
    }
  }

  private File findExtension(final String name) {
    File extensions = new File("." + File.separator +
                               "src" + File.separator +
                               "test" + File.separator +
                               "resources" + File.separator +
                               "extensions" + File.separator);
    Iterator it = FileUtils.iterateFiles(extensions, null, true);
    if (it.hasNext()) {
      return (File) it.next();
    }
    throw new WebDriverException(String.format("Can't find %s extension", name));
  }
}
