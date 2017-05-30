package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class EditActivity extends Activity {

  By diffButtonSelector = By.cssSelector("a.activityfeed-diff");
  //By changeDescription = By.cssSelector("tr.summary");

  WebElement diffLink;
  WebElement changeDescriptionTextBox;

  public EditActivity(WebDriver driver, WebElement parentElement) {
    super(driver, parentElement);

    diffLink = parentElement.findElement(diffButtonSelector);
    //changeDescriptionTextBox = parentElement.findElement(changeDescription);

  }

  public DiffPagePageObject showChanges() {
    scrollAndClick(diffLink);
    return new DiffPagePageObject(driver);
  }

//  public String getChangeDescription(){
//    return changeDescriptionTextBox.getText();
//  }
}
