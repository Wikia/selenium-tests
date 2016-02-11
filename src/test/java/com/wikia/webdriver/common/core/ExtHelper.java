package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.browsers.ChromeBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.FirefoxBrowser;
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
    String browser = Configuration.getBrowser();

    try {

      if ("FF".equalsIgnoreCase(browser)) {
        FirefoxBrowser.getProfile().addExtension(findExtension(name, "xpi"));
      } else if (browser.contains("CHROME")) {
        ChromeBrowser.getChromeOptions().addExtensions(findExtension(name, "crx"));
      } else {
        throw new WebDriverException(
            String.format("'%s' browser doesn't support adding of extensions", browser));
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
