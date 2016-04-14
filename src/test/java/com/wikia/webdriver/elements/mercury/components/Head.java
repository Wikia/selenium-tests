package com.wikia.webdriver.elements.mercury.components;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Head {

  private final WebDriver driver;

  @FindBy(css = "head title")
  private WebElement documentTitle;

  public Head(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public String getDocumentTitle() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return (String) js.executeScript("return arguments[0].innerText", documentTitle);
  }
}
