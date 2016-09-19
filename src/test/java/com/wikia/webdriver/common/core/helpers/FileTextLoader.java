package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.contentpatterns.PageContent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTextLoader {

  public String loadFileTextContent(String filename) {
    StringBuilder textContent = new StringBuilder();
    String NL = System.getProperty("line.separator");
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File(PageContent.TEXT_FILE_RESOURCES_PATH + filename), "UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      while (scanner.hasNextLine()){
        textContent.append(scanner.nextLine() + NL);
      }
    }
    finally{
      scanner.close();
    }

    return textContent.toString();
  }

}
