package com.wikia.webdriver.elements.oasis.templateclassificiation;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TemplateModal {

  @FindBy(css = ".button.normal.secondary")
  private WebElement cancelButton;

  @FindBy(css = ".button.normal.primary")
  private WebElement saveButton;

  @FindBy(css = "#template-classification-infobox")
  private WebElement typeInputInfobox;

  @FindBy(css = "#template-classification-quote")
  private WebElement typeInputQuote;

  private Wait wait;

  public TemplateModal(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public TemplateModal closeTemplateClassificationModal() {
    wait.forElementClickable(cancelButton);
    cancelButton.click();

    PageObjectLogging.logInfo("Template classification modal was closed");

    return this;
  }

  public TemplateModal saveTemplateType() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    PageObjectLogging.logInfo("Template type was saved");

    return this;
  }

  public TemplateModal selectInfoboxTemplate() {
    wait.forElementVisible(typeInputInfobox);
    typeInputInfobox.click();

    PageObjectLogging.logInfo("Infobox template was chosen");

    return this;
  }
  public TemplateModal selectQuoteTemplate() {
    wait.forElementVisible(typeInputQuote);
    typeInputQuote.click();

    PageObjectLogging.logInfo("Quote template was chosen");

    return this;
  }

  public TemplateModal selectTemplateType(String templateName) {
    if (!templateName.equals("Infobox")) {
      selectInfoboxTemplate();
    } else {
      selectQuoteTemplate();
    }

    return this;
  }
}
