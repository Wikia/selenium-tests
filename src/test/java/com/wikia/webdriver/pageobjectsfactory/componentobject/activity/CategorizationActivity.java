package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by szymonczerwinski on 10.03.2017.
 */
public class CategorizationActivity extends Activity {

  By diffButtonSelector = By.cssSelector("a.activityfeed-diff");
  By articleDescription = By.cssSelector("tr[data-type=new-page]");

  WebElement diffLink;
  WebElement articleDescriptionTextBox;

  public CategorizationActivity(WebDriver driver, WebElement parentElement) {
    super(driver, parentElement);

    diffLink = parentElement.findElement(diffButtonSelector);
    articleDescriptionTextBox = parentElement.findElement(articleDescription);

  }

  public void showChanges() {
    diffLink.click();
  }

  public String getArticleDescription(){
    return articleDescriptionTextBox.getText();
  }
}
