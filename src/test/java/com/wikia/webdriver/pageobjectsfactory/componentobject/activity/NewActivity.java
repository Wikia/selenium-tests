package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewActivity extends Activity {

  @FindBy(css = "tbody tr:first-child")
  private WebElement activityDescription;

  public String getActivityDescription() {
    return activityDescription.getText();
  }
}
