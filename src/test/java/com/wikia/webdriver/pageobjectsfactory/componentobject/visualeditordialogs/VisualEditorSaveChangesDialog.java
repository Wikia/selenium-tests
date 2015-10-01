package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorSaveChangesDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-processDialog-actions-primary .oo-ui-labelElement-label")
  private WebElement publishButton;
  @FindBy(css = "#recaptcha_area")
  private WebElement recaptchaArea;
  @FindBy(css = ".ve-ui-mwSaveDialog-captcha iframe")
  private WebElement recaptchaContainer;
  @FindBy(css = ".oo-ui-processDialog-actions-other .oo-ui-labelElement-label")
  private WebElement reviewChangesButton;
  @FindBy(css = ".oo-ui-window-body")
  private WebElement saveDialogBody;
  @FindBy(css = ".ve-ui-mwSaveDialog-summary textarea")
  private WebElement editSummary;
  @FindBy(css = "#wpMinoredit")
  private WebElement minorEdit;
  @FindBy(css = ".ve-ui-mwSaveDialog-savePanel")
  private WebElement savePanel;

  private By recaptchaImageBy = By.cssSelector("#recaptcha_challenge_image");

  public VisualEditorSaveChangesDialog(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject savePage() {
    wait.forElementInViewPort(editSummary);
    wait.forElementInViewPort(reviewChangesButton);
    wait.forElementVisible(savePanel);
    publishButton.click();
    return new ArticlePageObject(driver);
  }

  public void verifyRecaptchaIsVisible() {
    wait.forElementVisible(recaptchaContainer);
    driver.switchTo().defaultContent();
    LOG.success("verifyRecaptchaIsVisible", "ReCAPTCHA is showing on the dialog", true);
  }

  public String getRecaptchaImageSrc() {
    wait.forElementVisible(recaptchaContainer);
    String imageSrc = recaptchaContainer.getAttribute("src");
    LOG.success("getRecaptchaImageSrc", "RECAPTCHA img source is: " + imageSrc, true);
    driver.switchTo().defaultContent();
    return imageSrc;
  }

  public VisualEditorSaveChangesDialog clickSaveWithRecaptcha() {
    wait.forElementClickable(publishButton);
    if (isElementOnPage(recaptchaArea)) {
      recaptchaContainer = saveDialogBody.findElement(recaptchaImageBy);
      wait.forElementVisible(recaptchaContainer);
    }
    publishButton.click();
    return new VisualEditorSaveChangesDialog(driver);
  }

  public void verifyIsNewRecaptcha(String target) {
    Assertion.assertNotEquals(getRecaptchaImageSrc(), target);
    LOG.success("verifyIsNewRecaptcha", "A new ReCAPTCHA appeared");
  }

  public VisualEditorReviewChangesDialog clickReviewYourChanges() {
    wait.forElementVisible(reviewChangesButton);
    wait.forElementClickable(reviewChangesButton);
    reviewChangesButton.click();
    return new VisualEditorReviewChangesDialog(driver);
  }

  public void verifyRecaptchaImageSrc() {
    Assertion.assertNotEquals("", getRecaptchaImageSrc(),
        "Verify RECAPTCHA image source is not empty");
  }

  public void typeEditSummary(String text) {
    wait.forElementVisible(editSummary);
    editSummary.sendKeys(text);
    waitForValueToBePresentInElementsAttributeByElement(editSummary, "value", text);
  }

  public void clickMinorEdit() {
    wait.forElementClickable(minorEdit);
    minorEdit.click();
  }
}
