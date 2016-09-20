package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTextLoader {

  public String loadFileTextContent(String filename) {
    StringBuilder textContent = new StringBuilder();
    String separator = System.getProperty("line.separator");
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File(PageContent.TEXT_FILE_RESOURCES_PATH + filename), "UTF-8");
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
