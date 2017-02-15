package com.wikia.webdriver.common.core.helpers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Helper class to load content from external files
 */

public class ContentLoader {

  private static final String TEXT_FILES_PATH_FORMAT = "TextFiles/%s";

  private ContentLoader() {}

  public static class ContentLoaderException extends RuntimeException {
    public ContentLoaderException(String reason, Exception ex) {
      super(reason, ex);
    }
  }

  /**
   * @param filename - name of file which content should be loaded
   * @return content of desired file
   */
  public static String loadWikiTextContent(String filename) {
    StringBuilder textContent = new StringBuilder();
    String separator = System.getProperty("line.separator");
    String path = String.format(TEXT_FILES_PATH_FORMAT, filename);
    try (Scanner scanner = new Scanner(new File(ClassLoader.getSystemResource(path).getPath()),
      "UTF-8")) {
      while (scanner.hasNextLine()) {
        textContent.append(scanner.nextLine()).append(separator);
      }
    } catch (NullPointerException | FileNotFoundException ex) {
      throw new ContentLoaderException("Error when loading file!", ex);
    }
    return textContent.toString();
  }

}
