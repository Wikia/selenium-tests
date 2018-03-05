package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelpPage extends WikiBasePageObject {


  private static final By HELP_PAGE_HEADER_BY = By.cssSelector(".page-header__title");
  @FindBy(css = "Help:Rename my account")
  private WebElement messageTextBox;


  public HelpPage(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public boolean isHelpPageHeaderPresent() {
    return !driver.findElements(HELP_PAGE_HEADER_BY).isEmpty();
  }
}
