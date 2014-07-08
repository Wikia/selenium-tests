package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorSaveChangesDialog extends WikiBasePageObject {

	@FindBy(
		css=
		".oo-ui-widget.oo-ui-flaggableElement-constructive" +
		".oo-ui-buttonWidget .oo-ui-labeledElement-label"
	)
	private WebElement publishButton;
	@FindBy(css=".oo-ui-frame")
	private WebElement saveDialogIFrame;
	@FindBy(css="#recaptcha_area")
	private WebElement recaptchaArea;
	@FindBy(css="#recaptcha_challenge_image")
	private WebElement recaptchaImage;
	@FindBy(css=".secondary .oo-ui-labeledElement-label")
	private WebElement reviewChangesButton;

	public VisualEditorSaveChangesDialog(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject savePage() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementByElement(publishButton);
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		PageObjectLogging.log("savePage", "The 2nd Publish Button is clicked", true);
		waitForElementNotVisibleByElement(saveDialogIFrame);
		driver.switchTo().defaultContent();
		return new ArticlePageObject(driver);
	}

	private void verifyRecaptchaIsVisible() {
		waitForElementVisibleByElement(recaptchaArea);
		PageObjectLogging.log("verifyRecaptchaIsVisible", "ReCAPTCHA is showing on the dialog", true, driver);
	}

	public void verifyRecaptchaIsNotVisible() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementNotVisibleByElement(recaptchaArea);
		PageObjectLogging.log("verifyRecaptchaIsNotVisible", "ReCAPTCHA is not showing on the dialog", true, driver);
		driver.switchTo().defaultContent();
	}

	public String getRecaptchaImageSrc() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		verifyRecaptchaIsVisible();
		waitForElementByElement(recaptchaImage);
		String imageSrc = recaptchaImage.getAttribute("src");
		PageObjectLogging.log("getRecaptchaImageSrc", "RECAPTCHA img source is: " + imageSrc, true, driver);
		driver.switchTo().defaultContent();
		return imageSrc;
	}

	public void clickSaveWithRecaptcha() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickSaveWithRecaptcha", "The 2nd Publish Button is clicked", true);
	}

	public void verifyIsNewRecaptcha(String target) {
		String current = getRecaptchaImageSrc();
		Assertion.assertNotEquals(target, current);
		PageObjectLogging.log("verifyIsNewRecaptcha", "A new ReCAPTCHA appeared", true);
	}

	public VisualEditorReviewChangesDialog clickReviewYourChanges() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementClickableByElement(reviewChangesButton);
		reviewChangesButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickReviewYourChanges", "Review Your Changes Button is clicked", true);
		return new VisualEditorReviewChangesDialog(driver);
	}
}
