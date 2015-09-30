package com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MiniEditorPreviewComponentObject extends WikiBasePageObject {

  By publishButton = By.cssSelector(".buttons .primary");
  By contentWrapper = By.cssSelector("#mw-content-text");

  @FindBy(css = "#WallPreviewModal")
  private WebElement previewModal;

  public MiniEditorPreviewComponentObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void verifyTextContent(String desiredText) {
    Assertion.assertEquals(previewModal.findElement(contentWrapper).getText(), desiredText);
  }

  public void publish() {
    previewModal.findElement(publishButton).click();
    LOG.log("publish", "publish button clicked", LOG.Type.SUCCESS);
  }

}
