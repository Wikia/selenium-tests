package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class WikiEditMode extends WikiBasePageObject {

  @FindBy(css = "#wpSave")
  private WebElement publishButtonGeneral;

  public WikiEditMode(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  /**
   * Click  on Publish button
   *
   * @author Michal Nowierski
   */
  public WikiArticlePageObject clickOnPublishButton() {
    wait.forElementVisible(publishButtonGeneral);
    wait.forElementClickable(publishButtonGeneral);
    publishButtonGeneral.click();
    wait.forElementPresent(editButtonBy);

    LOG.log("ClickOnPublishButton", "Click on 'Publish' button", LOG.Type.SUCCESS);
    return new WikiArticlePageObject(driver);
  }

}
