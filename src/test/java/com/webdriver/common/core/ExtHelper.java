package com.webdriver.common.core;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

/*
 * Browser extensions helper
 */
public class ExtHelper {

  private ExtHelper() {}

  public static File findExtension(String name, String suffix) {
    File extensions = new File("." + File.separator + "src" + File.separator + "test"
        + File.separator + "resources" + File.separator + "extensions");
    String fullName = name + "." + suffix;
    Collection<File> extFiles = FileUtils.listFiles(extensions, new String[] {suffix}, true);

    for (File extFile : extFiles) {
      if (extFile.getName().equals(fullName)) {
        return extFile;
      }
    }

    throw new WebDriverException(
        String.format("Can't find '%s' extension in '%s'", fullName, extensions.getPath()));
  }
}
