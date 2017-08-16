package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class WallPostActivity extends Activity {

  @FindBy(css = "a.activityfeed-diff")
  private WebElement diffLink;

  @FindBy(css = "tbody td:nth-child(2) p:nth-child(2)")
  private WebElement activityDescription;

  public void showChanges() {
    wait.forElementClickable(diffLink);
    diffLink.click();
  }

  public String getActivityDescription() {
    return activityDescription.getText();
  }
}
