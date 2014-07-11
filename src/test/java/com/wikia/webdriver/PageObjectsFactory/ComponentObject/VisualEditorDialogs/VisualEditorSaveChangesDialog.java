package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.By;
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
		".oo-ui-window-foot .oo-ui-flaggableElement-constructive .oo-ui-labeledElement-label"
	)
	private WebElement publishButton;
	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement saveDialogIFrame;
	@FindBy(css="#recaptcha_area")
	private WebElement recaptchaArea;
	@FindBy(css="#recaptcha_challenge_image")
	private WebElement recaptchaImage;
	@FindBy(css=".secondary .oo-ui-labeledElement-label")
	private WebElement reviewChangesButton;
	@FindBy(css=".oo-ui-window-body")
	private WebElement saveDialogBody;

	private By recaptchaImageBy = By.cssSelector("#recaptcha_challenge_image");

	public VisualEditorSaveChangesDialog(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject savePage() {
		waitForElementByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementByElement(publishButton);
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		PageObjectLogging.log("savePage", "The 2nd Publish Button is clicked", true);
		waitForElementNotVisibleByElement(saveDialogIFrame);
		driver.switchTo().defaultContent();
		return new ArticlePageObject(driver);
	}

	public void verifyRecaptchaIsVisible() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementVisibleByElement(recaptchaArea);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyRecaptchaIsVisible", "ReCAPTCHA is showing on the dialog", true, driver);
	}

	public String getRecaptchaImageSrc() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementVisibleByElement(recaptchaImage);
		String imageSrc = recaptchaImage.getAttribute("src");
		PageObjectLogging.log("getRecaptchaImageSrc", "RECAPTCHA img source is: " + imageSrc, true, driver);
		driver.switchTo().defaultContent();
		return imageSrc;
	}

	public VisualEditorSaveChangesDialog clickSaveWithRecaptcha() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementClickableByElement(publishButton);
		if(checkIfElementOnPage(recaptchaArea)) {
			WebElement recaptchaImage = saveDialogBody.findElement(recaptchaImageBy);
			waitForElementVisibleByElement(recaptchaImage);
		}
		publishButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickSaveWithRecaptcha", "The 2nd Publish Button is clicked", true);
		return new VisualEditorSaveChangesDialog(driver);
	}

	public void verifyIsNewRecaptcha(String target) {
		String current = getRecaptchaImageSrc();
		Assertion.assertNotEquals(target, current);
		PageObjectLogging.log("verifyIsNewRecaptcha", "A new ReCAPTCHA appeared", true);
	}

	public VisualEditorReviewChangesDialog clickReviewYourChanges() {
		waitForElementByElement(saveDialogIFrame);
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementByElement(reviewChangesButton);
		waitForElementClickableByElement(reviewChangesButton);
		reviewChangesButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickReviewYourChanges", "Review Your Changes Button is clicked", true);
		return new VisualEditorReviewChangesDialog(driver);
	}

	public void verifyRecaptchaImageSrc() {
		Assertion.assertNotEquals("", getRecaptchaImageSrc(), "Verify RECAPTCHA image source is not empty");
	}

}
