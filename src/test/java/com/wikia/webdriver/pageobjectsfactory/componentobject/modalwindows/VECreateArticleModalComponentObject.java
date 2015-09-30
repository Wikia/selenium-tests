package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    LOG.log("createPage", "Add A Page button is clicked", LOG.Type.SUCCESS);
  }

  public void closeModal() {
    closeButton.click();
    LOG.log("closeModal", "The 'X' button is clicked", LOG.Type.SUCCESS);
  }

  public void cancel() {
    cancelButton.click();
    LOG.log("cancel", "The cancel button is clicked", LOG.Type.SUCCESS);
  }
}
