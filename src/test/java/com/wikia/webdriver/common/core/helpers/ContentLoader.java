package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.logging.PageObjectLogging;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Helper class to load content from external files
 */

public class ContentLoader {

  public static final String TEXT_FILE_RESOURCES_PATH =
      "." + File.separator + "src" + File.separator +
      "test" + File.separator + "resources" + File.separator +
      "TextFiles" + File.separator;

  /**
   * @param filename - name of file which content should be loaded
   * @return content of desired file
   */
  public String loadWikiTextContent(String filename) {
    StringBuilder textContent = new StringBuilder();
    String separator = System.getProperty("line.separator");
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File(TEXT_FILE_RESOURCES_PATH + filename), "UTF-8");
    } catch (FileNotFoundException e) {
      PageObjectLogging.logInfo("File was not loaded", e);
    }
    try {
      while (scanner.hasNextLine()){
        textContent.append(scanner.nextLine() + separator);
      }
    } finally {
      scanner.close();
    }

    return textContent.toString();
  }

}
