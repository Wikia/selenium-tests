package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiEditMode extends WikiBasePageObject {

  @FindBy(css = "#wpSave")
  private WebElement publishButtonGeneral;

  public WikiEditMode(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  /**
   * Click  on Publish button
   */
  public WikiArticlePageObject clickOnPublishButton() {
    wait.forElementVisible(publishButtonGeneral);
    wait.forElementClickable(publishButtonGeneral);
    publishButtonGeneral.click();
    wait.forElementPresent(editButtonBy);

    PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true);
    return new WikiArticlePageObject(driver);
  }
}
