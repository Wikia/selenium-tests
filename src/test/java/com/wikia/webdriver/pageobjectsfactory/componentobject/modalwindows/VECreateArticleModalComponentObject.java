package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VECreateArticleModalComponentObject extends WikiBasePageObject {

  @FindBy(css = ".close")
  private WebElement closeButton;
  @FindBy(css = ".normal.secondary")
  private WebElement cancelButton;
  @FindBy(css = ".normal.primary")
  private WebElement addAPageButton;
  @FindBy(css = "#CreatePageModalDialog")
  private WebElement modal;

  public VECreateArticleModalComponentObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public void createPage() {
    wait.forElementVisible(modal);
    wait.forElementClickable(addAPageButton);
    addAPageButton.click();
    Log.log("createPage", "Add A Page button is clicked", true);
  }

  public void closeModal() {
    closeButton.click();
    Log.log("closeModal", "The 'X' button is clicked", true);
  }

  public void cancel() {
    cancelButton.click();
    Log.log("cancel", "The cancel button is clicked", true);
  }
}
