package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Lukasz Jedrzejczak
 */

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
    super(driver);
  }

  public CreatePinTypesComponentObject clickNext() {
    wait.forElementClickable(nextButton);
    nextButton.click();
    LOG.success("clickNext", "clicked next button in naming map modal", true);
    return new CreatePinTypesComponentObject(driver);
  }

  public CreateACustomMapComponentObject clickBack() {
    wait.forElementClickable(backButton);
    backButton.click();
    LOG.success("clickBack", "clicked back button in naming map modal", true);
    return new CreateACustomMapComponentObject(driver);
  }

  public void typeMapName(String mapName) {
    wait.forElementVisible(mapTitleField);
    mapTitleField.sendKeys(mapName);
    LOG.success("typeMapName", mapName + " title for map typed in", true);
  }

  public void typeTemplateName(String templateName) {
    wait.forElementVisible(nameTemplateField);
    nameTemplateField.sendKeys(templateName);
    LOG.success("typeTemplateName", templateName + " title for template typed in",true);
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
