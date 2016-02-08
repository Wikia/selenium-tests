package com.wikia.webdriver.elements.oasis.components.templateclassificiation;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EntryPoint {

  @FindBy(css = ".template-classification-type-label")
  private WebElement entryPointLink;

  private String templateName;

  private Wait wait;

  public EntryPoint(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public EntryPoint openTemplateClassificationModal() {
    setTemplateName();
    wait.forElementClickable(entryPointLink);
    entryPointLink.click();
    PageObjectLogging.logInfo("Template classification modal was opened");

    return this;
  }

  public String getTemplateName() {
    return this.templateName;
  }

  public EntryPoint setTemplateName() {
    wait.forElementVisible(entryPointLink);
    this.templateName = entryPointLink.getText();

    return this;
  }

  public EntryPoint checkTemplateType() {
    wait.forElementVisible(entryPointLink);

    Assertion.assertFalse(entryPointLink.getText().equals(this.templateName), "Template did not change");
    PageObjectLogging.logInfo("Template changed");

    return this;
  }
}
