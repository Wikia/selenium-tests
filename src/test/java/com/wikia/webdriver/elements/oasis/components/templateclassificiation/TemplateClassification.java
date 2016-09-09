package com.wikia.webdriver.elements.oasis.components.templateclassificiation;

import com.wikia.webdriver.common.contentpatterns.TemplateTypes;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplateClassification extends WikiBasePageObject {

  @FindBy(css = ".template-classification-type-label")
  private WebElement entryPointLink;

  @FindBy(css = ".button.normal.primary")
  private WebElement saveButton;

  @FindBy(css = ".template-classification-edit-modal .close")
  private WebElement closeButton;

  private By modalSelector = By.cssSelector(".template-classification-edit-modal");

  public TemplateClassification() {
    super();
  }

  public TemplateClassification open() {
    wait.forElementClickable(entryPointLink);
    entryPointLink.click();

    wait.forElementVisible(modalSelector);
    PageObjectLogging.logInfo("Template classification modal was opened");

    return this;
  }

  public TemplateClassification close() {
    wait.forElementClickable(closeButton);
    closeButton.click();

    wait.forElementNotVisible(modalSelector);
    PageObjectLogging.logInfo("Template classification modal was closed");

    return this;
  }

  public TemplateClassification save() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    return this;
  }

  public TemplateClassification clickAddButton() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    return this;
  }

  public TemplateClassification changeTemplateType(TemplateTypes templateType) {
    String templateName = templateType.getType();
    WebElement typeInput = driver.findElement(By.cssSelector("#template-classification-" + templateName.toLowerCase()));

    wait.forElementClickable(typeInput);
    typeInput.click();

    PageObjectLogging.logInfo(templateName + " template was chosen");

    return this;
  }

  public TemplateClassification resetTemplateType() {
    this.changeTemplateType(TemplateTypes.UNKNOWN);

    return this;
  }

  public String getTemplateType() {
    wait.forElementVisible(entryPointLink);

    return entryPointLink.getText();
  }
}
