package com.wikia.webdriver.common.core.helpers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * Helper class to load content from external files
 */

public class ContentLoader {

  private static final String TEXT_FILES_PATH_FORMAT = "TextFiles/%s";

  private ContentLoader() {}

  /**
   * @param filename - name of file which content should be loaded
   * @return content of desired file
   */
  public static String loadWikiTextContent(String filename) {
    StringBuilder textContent = new StringBuilder();
    String separator = System.getProperty("line.separator");
    Scanner scanner = null;
    String path = String.format(TEXT_FILES_PATH_FORMAT, filename);
    try {
      scanner = new Scanner(new File(ClassLoader.getSystemResource(path).getPath()), "UTF-8");
    } catch (FileNotFoundException e) {
      PageObjectLogging.logError("File was not loaded", e);
    }
    if (scanner != null) {
      try {
        while (scanner.hasNextLine()) {
          textContent.append(scanner.nextLine()).append(separator);
        }
      } finally {
        scanner.close();
      }
    } else {
      throw new NullPointerException("Could not initialize scanner!");
    }
    return textContent.toString();
  }

}
