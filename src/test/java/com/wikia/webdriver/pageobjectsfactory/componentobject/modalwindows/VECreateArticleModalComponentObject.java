package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Robert 'rochan' Chan
 */
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
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void createPage() {
    wait.forElementVisible(modal);
    wait.forElementClickable(addAPageButton);
    addAPageButton.click();
    LOG.success("createPage", "Add A Page button is clicked");
  }

  public void closeModal() {
    closeButton.click();
    LOG.success("closeModal", "The 'X' button is clicked");
  }

  public void cancel() {
    cancelButton.click();
    LOG.success("cancel", "The cancel button is clicked");
  }
}
