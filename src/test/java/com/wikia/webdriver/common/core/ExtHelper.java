package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/*
 Browser extensions helper
 */
public class ExtHelper {

  private static ImmutableMap<String, String> ext = new ImmutableMap.Builder<String, String>()
      .put("CHROME", "crx")
      .put("CHROMEMOBILEMERCURY", "crx")
      .put("FF", "xpi")
      .build();

  public static void addExtension(String name) {
    String br = Configuration.getBrowser();

    File extension = findExtension(name, ext.get(br));

    try {

      if ("FF".equalsIgnoreCase(br)) {
        NewDriverProvider.getFirefoxProfile().addExtension(extension);
      }

      if (br.contains("CHROME")) {
        NewDriverProvider.getChromeOptions().addExtensions(extension);
      }

    } catch (IOException e) {
      PageObjectLogging.log("Error occurred during adding extension", e, false);
    }
  }

  public static void addExtensions(String[] names) {
    for (String name : names) {
      addExtension(name);
    }
  }

  private static File findExtension(String name, String suffix) {
    File extensions = new File("." + File.separator +
                               "src" + File.separator +
                               "test" + File.separator +
                               "resources" + File.separator +
                               "extensions" + File.separator);
    name = name + "." + suffix;
    Collection<File> files = FileUtils.listFiles(extensions, new String[]{suffix}, true);
    for (File file : files) {
      if (file.getName().equals(name)) {
        return file;
      }
    }
    throw new WebDriverException(String.format("Can't find '%s' extension", name));
  }
}
