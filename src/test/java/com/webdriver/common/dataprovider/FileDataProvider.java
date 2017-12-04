package com.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class FileDataProvider {

  private FileDataProvider() {
  }

  @DataProvider
  public static final Object[][] getFileNames() {
    return new Object[][]{
        {"文件名óśłżźćńę?.jpg"},
        {"Image001.jpg"},
    };
  }
}
