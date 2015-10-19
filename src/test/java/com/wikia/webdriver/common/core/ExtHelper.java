package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/*
 Browser extensions helper
 */
public class ExtHelper {

  private ExtHelper() {
  }

  public static void addExtension(String name) {
    String br = Configuration.getBrowser();

    try {

      if ("FF".equalsIgnoreCase(br)) {
        NewDriverProvider.getFirefoxProfile().addExtension(findExtension(name, "xpi"));
      } else if (br.contains("CHROME")) {
        NewDriverProvider.getChromeOptions().addExtensions(findExtension(name, "crx"));
      } else {
        throw new WebDriverException(
            String.format("'%s' browser doesn't support adding of extensions", br));
      }

    } catch (IOException e) {
      PageObjectLogging.log("Error occurred during adding '" + name + "' extension", e, false);
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
                               "extensions");
    String fullName = name + "." + suffix;
    Collection<File> extFiles = FileUtils.listFiles(extensions, new String[]{suffix}, true);
    for (File extFile : extFiles) {
      if (extFile.getName().equals(fullName)) {
        return extFile;
      }
    }
    throw new WebDriverException(
        String.format("Can't find '%s' extension in '%s'", fullName, extensions.getPath()));
  }
}
