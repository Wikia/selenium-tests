package com.wikia.webdriver.pageobjectsfactory.pageobject.actions;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RenamePageObject extends ArticlePageObject {

  @FindBy(css = "#wpNewTitleMain")
  private WebElement newNameInput;
  @FindBy(css = ".mw-submit [name='wpMove']")
  private WebElement submitRename;
  @FindBy(css = "input#wpLeaveRedirect")
  private WebElement leaveRedirectCheckbox;

  public RenamePageObject(WebDriver driver) {
    super();
  }

  public ArticlePageObject rename(String newName, boolean leaveRedirect) {
    wait.forElementClickable(newNameInput);
    jsActions.scrollElementIntoViewPort(newNameInput);
    newNameInput.clear();
    newNameInput.sendKeys(newName);
    if (leaveRedirect) {
      scrollAndClick(leaveRedirectCheckbox);
    }
    scrollAndClick(submitRename);
    Log.log("ArticleRenamed", "Article renamed", true);
    return new ArticlePageObject();
  }
}
