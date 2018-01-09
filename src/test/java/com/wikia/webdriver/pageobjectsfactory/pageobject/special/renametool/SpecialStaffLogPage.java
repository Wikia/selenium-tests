package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialStaffLogPage extends SpecialPageObject {

  @FindBy(css = "")
  private WebElement usernameTextBox;
  @FindBy(css = "")
  private WebElement typeComboBox;
  @FindBy(css = "")
  private WebElement applyFilterButton;

  public SpecialStaffLogPage(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }
}
