package com.wikia.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilePageObject extends WikiBasePageObject {

  @FindBy(css = ".file-usage")
  private WebElement snippetSection;

  @FindBy(css = ".wikia-card__title")
  private WebElement snippetTitle;

  @FindBy(css = ".wikia-card__snippet")
  private WebElement snippetText;

  public String snippetText() {
    return snippetText.getText();
  }

  public boolean doesSnippetContainXSS() {
    wait.forElementVisible(snippetSection);
    scrollTo(snippetTitle);
    return snippetText().contains("alert('xss')");
  }
}
