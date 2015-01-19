package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorSaveChangesDialog extends VisualEditorDialog {

	@FindBy(
		css =
			".oo-ui-window-foot .oo-ui-flaggableElement-constructive .oo-ui-labeledElement-label"
	)
	private WebElement publishButton;
	@FindBy(css = "#recaptcha_area")
	private WebElement recaptchaArea;
	@FindBy(css = "#recaptcha_challenge_image")
	private WebElement recaptchaImage;
	@FindBy(css = ".secondary .oo-ui-labeledElement-label")
	private WebElement reviewChangesButton;
	@FindBy(css = ".oo-ui-window-body")
	private WebElement saveDialogBody;
	@FindBy(css = ".ve-ui-mwSaveDialog-summary textarea")
	private WebElement editSummary;
	@FindBy(css = "#wpMinoredit")
	private WebElement minorEdit;

	private By recaptchaImageBy = By.cssSelector("#recaptcha_challenge_image");

	public VisualEditorSaveChangesDialog(WebDriver driver) {
		super(driver);
	}

	@Override
	public void switchToIFrame() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			PageObjectLogging.log("switchToIFrame", e.getMessage(), false);
		}
		super.switchToIFrame();
	}

	public ArticlePageObject savePage() {
		switchToIFrame();
		waitForElementInViewPort(editSummary);
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		PageObjectLogging.log("savePage", "The 2nd Publish Button is clicked", true);
		switchOutOfIFrame();
		return new ArticlePageObject(driver);
	}

	public void verifyRecaptchaIsVisible() {
		switchToIFrame();
		waitForElementVisibleByElement(recaptchaArea);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyRecaptchaIsVisible", "ReCAPTCHA is showing on the dialog", true, driver);
	}

	public String getRecaptchaImageSrc() {
		switchToIFrame();
		waitForElementVisibleByElement(recaptchaImage);
		String imageSrc = recaptchaImage.getAttribute("src");
		PageObjectLogging.log("getRecaptchaImageSrc", "RECAPTCHA img source is: " + imageSrc, true, driver);
		driver.switchTo().defaultContent();
		return imageSrc;
	}

	public VisualEditorSaveChangesDialog clickSaveWithRecaptcha() {
		switchToIFrame();
		waitForElementClickableByElement(publishButton);
		if (checkIfElementOnPage(recaptchaArea)) {
			WebElement recaptchaImage = saveDialogBody.findElement(recaptchaImageBy);
			waitForElementVisibleByElement(recaptchaImage);
		}
		publishButton.click();
		PageObjectLogging.log("clickSaveWithRecaptcha", "The 2nd Publish Button is clicked", true);
		switchOutOfIFrame();
		return new VisualEditorSaveChangesDialog(driver);
	}

	public void verifyIsNewRecaptcha(String target) {
		String current = getRecaptchaImageSrc();
		Assertion.assertNotEquals(target, current);
		PageObjectLogging.log("verifyIsNewRecaptcha", "A new ReCAPTCHA appeared", true);
	}

	public VisualEditorReviewChangesDialog clickReviewYourChanges() {
		switchToIFrame();
		waitForElementVisibleByElement(reviewChangesButton);
		waitForElementClickableByElement(reviewChangesButton);
		reviewChangesButton.click();
		PageObjectLogging.log("clickReviewYourChanges", "Review Your Changes Button is clicked", true);
		switchOutOfIFrame();
		return new VisualEditorReviewChangesDialog(driver);
	}

	public void verifyRecaptchaImageSrc() {
		Assertion.assertNotEquals("", getRecaptchaImageSrc(), "Verify RECAPTCHA image source is not empty");
	}

	public void typeEditSummary(String text) {
		switchToIFrame();
		waitForElementVisibleByElement(editSummary);
		editSummary.sendKeys(text);
		waitForValueToBePresentInElementsAttributeByElement(editSummary, "value", text);
		switchOutOfIFrame();
	}

	public void clickMinorEdit() {
		switchToIFrame();
		waitForElementClickableByElement(minorEdit);
		minorEdit.click();
		switchOutOfIFrame();
	}
}
