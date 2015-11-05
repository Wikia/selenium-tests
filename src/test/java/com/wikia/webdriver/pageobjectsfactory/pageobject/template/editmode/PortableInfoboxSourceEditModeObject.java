package com.wikia.webdriver.pageobjectsfactory.pageobject.template.editmode;


import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.EditMode;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @ownshership: Content West-Wing
 */

public class PortableInfoboxSourceEditModeObject extends EditMode {

  @FindBy(css = "#editarea .ace_scroller")
  private WebElement textArea;

  public PortableInfoboxSourceEditModeObject(WebDriver driver) {
    super(driver);
  }

  public String copyContent() {
    wait.forElementVisible(textArea);
    return textArea.getText();
  }

  public PortableInfoboxSourceEditModeObject focusTextArea() {
    jsActions.focus("textAreaScroller");
    return this;
  }

  public PortableInfoboxSourceEditModeObject addContentToInfoboxTemplateInSourceMode(String text) {
    wait.forElementVisible(textArea);
    new Actions(driver)
        .click()
        .sendKeys(text)
        .build()
        .perform();
    return this;
  }

  public PortableInfoboxSourceEditModeObject strokeKey(Keys keyName, int characterCount) {
    wait.forElementVisible(textArea);
    Actions action = new Actions(driver);
    action
        .moveToElement(textArea)
        .click();

    for (int i = 0; i < characterCount; i++) {
      action.sendKeys(keyName);
    }
    action
        .build()
        .perform();
    return this;
  }


}
