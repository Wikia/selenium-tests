package com.wikia.webdriver.elements.oasis.components.templateclassificiation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class TemplateClassification extends WikiBasePageObject {

  @FindBy(css = ".template-classification-type-label")
  private WebElement entryPointLink;

  @FindBy(css = "#template-classification-infobox")
  private WebElement typeInputInfobox;

  @FindBy(css = "#template-classification-quote")
  private WebElement typeInputQuote;

  @FindBy(css = ".button.normal.primary")
  private WebElement saveButton;

  @FindBy(css = ".template-classification-edit-modal .close")
  private WebElement closeButton;

  private By modalSelector = By.cssSelector(".template-classification-edit-modal");

  private String templateName = "";

  public TemplateClassification(WebDriver driver) {
    super(driver);
  }

  public TemplateClassification open() {
    wait.forElementClickable(entryPointLink);
    this.templateName = entryPointLink.getText();
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
    String oldTemplateName = this.templateName;
    wait.forElementClickable(saveButton);
    saveButton.click();

    wait.forElementVisible(entryPointLink);
    String currentTemplateName = entryPointLink.getText();
    Assertion.assertFalse(currentTemplateName.equals(oldTemplateName),
        "Template type did not change");
    PageObjectLogging.logInfo(
        "Template type changed from: '" + oldTemplateName + "', to: '" + currentTemplateName + "'");

    return this;
  }

  public TemplateClassification selectTemplateType() {
    if (!this.templateName.equals("Infobox")) {
      selectInfoboxTemplate();
    } else {
      selectQuoteTemplate();
    }

    return this;
  }

  private TemplateClassification selectInfoboxTemplate() {
    wait.forElementVisible(typeInputInfobox);
    typeInputInfobox.click();

    PageObjectLogging.logInfo("Infobox template was chosen");

    return this;
  }

  private TemplateClassification selectQuoteTemplate() {
    wait.forElementVisible(typeInputQuote);
    typeInputQuote.click();

    PageObjectLogging.logInfo("Quote template was chosen");

    return this;
  }
}
