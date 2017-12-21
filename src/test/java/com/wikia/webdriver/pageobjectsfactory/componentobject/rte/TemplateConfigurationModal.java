package com.wikia.webdriver.pageobjectsfactory.componentobject.rte;

import com.wikia.webdriver.elements.Frame;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The template configuration modal allows users to customize the transclusion parameters of a template
 * and offers preview functionality.
 */
public class TemplateConfigurationModal {
  @FindBy(className = "cke_wysiwyg_frame")
  private WebElement editorFrameElement;
  @FindBy(css = "#templateParameters textarea")
  private List<WebElement> templateParameters;
  @FindBy(id = "cke_82_label")
  private WebElement okButton;

  private final By templatePlaceholderBy = By.className("placeholder-double-brackets");

  private final Frame editorFrame;

  private final WebDriver webDriver;
  private final WebDriverWait webDriverWait;

  public TemplateConfigurationModal(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
    this.webDriver = webDriver;
    webDriverWait = new WebDriverWait(webDriver, 10);
    editorFrame = new Frame(webDriver, editorFrameElement);
  }

  /**
   * Set a value for one of the template's parameters
   * @param name name of the parameter to set
   * @param value value to set for the parameter
   * @throws NoSuchElementException if there is no parameter with the provided name
   */
  public void configureTemplateParameter(String name, String value) {
    templateParameters.stream()
        .filter(textArea -> textArea.getAttribute("rel").equals(name))
        .findFirst()
        .orElseThrow(NoSuchElementException::new)
        .sendKeys(value);
  }

  /**
   * Insert the configured template into the article.
   * This method blocks until the inserted template becomes visible, with a timeout of 10 seconds.
   */
  public void clickOkButton() {
    final int numberOfTemplatesOnPage = webDriver.findElements(templatePlaceholderBy).size();
    okButton.click();

    editorFrame.frameScope(() -> webDriverWait.until(ExpectedConditions.numberOfElementsToBe(templatePlaceholderBy, numberOfTemplatesOnPage + 1)));
  }
}
