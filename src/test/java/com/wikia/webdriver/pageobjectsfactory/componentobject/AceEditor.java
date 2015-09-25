package com.wikia.webdriver.pageobjectsfactory.componentobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class AceEditor extends WikiBasePageObject {

  @FindBy(css = ".ace_text-layer > .ace_line")
  private WebElement aceLayerTextArea;

  @FindBy(css = "textarea.ace_text-input")
  private WebElement aceInputTextArea;

  public AceEditor(WebDriver driver) {
    super(driver);
  }

  public AceEditor clearContent() {
    wait.forElementVisible(aceLayerTextArea);
    jsActions.execute("ace.edit('editarea').setValue('');");
    PageObjectLogging.log("clearCssText", "ace editor was cleared", true, driver);

    return this;
  }

  public AceEditor insertContent(String cssText) {
    wait.forElementVisible(aceLayerTextArea);
    jsActions.execute("ace.edit('editarea').navigateFileEnd();");
    sendContent(cssText);
    PageObjectLogging.log("sendAceCssText",
        "the following text was send to ace editor: " + cssText, true);

    return this;
  }

  private AceEditor sendContent(String cssText) {
    wait.forElementVisible(aceLayerTextArea);
    for(char character : cssText.toCharArray()){
      aceInputTextArea.sendKeys((String.valueOf(character)));
    }
    PageObjectLogging.log("sendCssText", "the following text was send to ace editor: " + cssText,
        true);

    return this;
  }
}
