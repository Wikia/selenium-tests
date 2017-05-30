package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class EditActivity extends Activity {

  private WebElement diffLink;

  public EditActivity(WebDriver driver, WebElement parentElement) {
    super(driver, parentElement);

    By diffButtonSelector = By.cssSelector("a.activityfeed-diff");
    diffLink = parentElement.findElement(diffButtonSelector);
  }

  public DiffPagePageObject showChanges() {
    scrollAndClick(diffLink);
    return new DiffPagePageObject(driver);
  }
}
