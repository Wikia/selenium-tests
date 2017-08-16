package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CategorizationActivity extends Activity {

  @FindBy(css = "a.activityfeed-diff")
  private WebElement diffLink;

  @FindBy(css = "tr[data-type=inserted-category]")
  private WebElement activityDescription;

}
