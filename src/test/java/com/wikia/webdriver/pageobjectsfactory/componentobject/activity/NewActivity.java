package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class NewActivity extends Activity {

  private WebElement activityDescriptionTextBox;

  public NewActivity(WebDriver driver, WebElement parentElement) {
    super(driver, parentElement);

    By activityDescriptionTextBoxBy = By.cssSelector("tbody tr:first-child");
    activityDescriptionTextBox = parentElement.findElement(activityDescriptionTextBoxBy);
  }

  public String getActivityDescription(){
    return activityDescriptionTextBox.getText();
  }
}
