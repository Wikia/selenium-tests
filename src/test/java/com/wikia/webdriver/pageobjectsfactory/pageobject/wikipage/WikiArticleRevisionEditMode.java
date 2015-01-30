package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WikiArticleRevisionEditMode extends WikiArticleEditMode {

  public WikiArticleRevisionEditMode(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

}
