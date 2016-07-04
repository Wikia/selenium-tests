package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplateComponentObject extends BasePageObject {

  @FindBy(css = "input[name='map-title']")
  private WebElement mapTitleField;
  @FindBy(css = "input[name='tile-set-title']")
  private WebElement nameTemplateField;
  @FindBy(css = "#intMapNext")
  private WebElement nextButton;
  @FindBy(css = "#intMapBack")
  private WebElement backButton;
  @FindBy(css = ".intMapPreviewImage")
  private WebElement templateImagePreview;
  @FindBy(css = "#intMapError")
  private WebElement mapError;

  public TemplateComponentObject(WebDriver driver) {
    super();
  }

  public CreatePinTypesComponentObject clickNext() {
    wait.forElementClickable(nextButton);
    nextButton.click();
    PageObjectLogging.log("clickNext", "clicked next button in naming map modal", true, driver);
    return new CreatePinTypesComponentObject(driver);
  }

  public CreateACustomMapComponentObject clickBack() {
    wait.forElementClickable(backButton);
    backButton.click();
    PageObjectLogging.log("clickBack", "clicked back button in naming map modal", true, driver);
    return new CreateACustomMapComponentObject(driver);
  }

  public void typeMapName(String mapName) {
    wait.forElementVisible(mapTitleField);
    mapTitleField.sendKeys(mapName);
    PageObjectLogging.log("typeMapName", mapName + " title for map typed in", true, driver);
  }

  public void typeTemplateName(String templateName) {
    wait.forElementVisible(nameTemplateField);
    nameTemplateField.sendKeys(templateName);
    PageObjectLogging.log("typeTemplateName", templateName + " title for template typed in", true,
        driver);
  }

  public void verifyTemplateImagePreview() {
    wait.forElementVisible(templateImagePreview);
  }

  public void verifyTemplateImage(String selectedTemplateName) {
    wait.forElementVisible(templateImagePreview);
    Assertion.assertTrue(templateImagePreview.getAttribute("src").endsWith(selectedTemplateName));
  }

  public void verifyErrorExists() {
    wait.forElementVisible(mapError);
    Assertion.assertEquals(isElementOnPage(mapError), true);
  }
}
