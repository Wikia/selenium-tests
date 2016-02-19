package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.Diff;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiffPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Diff diff = new Diff();

  @FindBy(css = ".diff-page_undo")
  private WebElement undoButton;

  private By undoButtonBy = By.cssSelector(".diff-page_undo");

  public DiffPage() {
    super();
  }

  public void submitUndo() {
    wait.forElementClickable(undoButton);
    PageObjectLogging.logInfo("Undo button is clickable");
    undoButton.click();
    wait.forElementNotVisible(undoButtonBy);
  }
}
