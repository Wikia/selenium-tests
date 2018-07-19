package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    Log.log("ClickOnPublishButton", "Click on 'Publish' button", true);
    return new WikiArticleEditMode();
  }
}
