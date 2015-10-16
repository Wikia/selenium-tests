package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

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

  public static void addExtension(String name) {
    String br = Configuration.getBrowser();

    if (ext.containsKey(br)) {
      name = name + ext.get(br);
    }

    File extension = findExtension(name);

    try {

      if ("FF".equalsIgnoreCase(br)) {
        NewDriverProvider.getFirefoxProfile().addExtension(extension);
      }

      if (br.contains("CHROME")) {
        NewDriverProvider.getChromeOptions().addExtensions(extension);
      }

    } catch (IOException e) {
      PageObjectLogging.log("Error with adding extension", e, false);
    }
  }

  public static void addExtensions(String[] names) {
    for (String name : names) {
      addExtension(name);
    }
  }

  private static File findExtension(final String name) {
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
