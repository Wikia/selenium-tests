package com.wikia.webdriver.common.core.helpers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTextLoader {

  public String loadFileTextContent(String filename) {
    StringBuilder textContent = new StringBuilder();
    String NL = System.getProperty("line.separator");
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("src/test/resources/TextFiles/" + filename), "UTF-8");
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
