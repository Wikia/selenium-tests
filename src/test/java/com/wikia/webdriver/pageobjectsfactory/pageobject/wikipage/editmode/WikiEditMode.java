package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class WikiEditMode extends WikiBasePageObject {

  @FindBy(css = "#wpSave")
  private WebElement publishButtonGeneral;

  /**
   * Click on Publish button
   */
  public WikiArticleEditMode clickOnPublishButton() {
    wait.forElementVisible(publishButtonGeneral);
    wait.forElementClickable(publishButtonGeneral);
    publishButtonGeneral.click();
    wait.forElementPresent(editButtonBy);

    PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true);
    return new WikiArticleEditMode();
  }
}
