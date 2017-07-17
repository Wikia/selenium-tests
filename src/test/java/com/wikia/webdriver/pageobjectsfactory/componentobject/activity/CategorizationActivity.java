package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CategorizationActivity extends Activity {

  private WebElement diffLink;
  private WebElement activityDescriptionTextBox;

  public CategorizationActivity(WebDriver driver, WebElement parentElement) {
    super(driver, parentElement);

    By diffButtonSelector = By.cssSelector("a.activityfeed-diff");
    diffLink = parentElement.findElement(diffButtonSelector);
    By articleDescription = By.cssSelector("tr[data-type=inserted-category]");
    activityDescriptionTextBox = parentElement.findElement(articleDescription);

  }

  public void showChanges() {
    wait.forElementClickable(diffLink);
    diffLink.click();
  }

  public String getActivityDescription(){
    return activityDescriptionTextBox.getText();
  }
}
