/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class PreviewEditModePageObject extends EditMode {

	@FindBy(css=".modalWrapper.preview")
	private WebElement previewModal;

	By closeButton = By.cssSelector(".close.wikia-chiclet-button > img");
	By videoWidthSelector = By.cssSelector("img.thumbimage");
	By videoCaptionSelector = By.cssSelector(".thumbcaption");
	By videoNoCaptionSelector = By.cssSelector(".Wikia-video-thumb:not(.thumbimage)");

	String videoPostionSelector = "figure.t%position%";

	public PreviewEditModePageObject(WebDriver driver) {
		super(driver);
		waitForElementByElement(previewModal);
	}

	public void verifyVideoAlignment(PositionsVideo positions) {
		String position;

		switch(positions) {
			case left:
				position = "left";
				break;
			case right:
				position = "right";
				break;
			case center:
				position = "none";
				break;
			default:
				position = "position not provided";
				break;
		}
		previewModal.findElement(
				By.cssSelector(
						videoPostionSelector.replace("%position%", position)
						)
				);
		PageObjectLogging.log("verifyVideoAlignment", "video alignment is as exepected " + positions.toString(), true);
	}

	public void verifyVideoWidth(int desiredWidth) {
		int width = Integer.parseInt(previewModal.findElement(
				videoWidthSelector
				).getAttribute("width"));
		Assertion.assertNumber(desiredWidth, width, "width should be " + desiredWidth + " but is "+width);
	}

	public void verifyVideoCaption(String desiredCaption) {
		String caption = previewModal.findElement(videoCaptionSelector).getText();
		Assertion.assertStringContains(caption, desiredCaption);
	}

	public void verifyVideoNoCaption() {
		previewModal.findElement(
				videoNoCaptionSelector
		);
		PageObjectLogging.log("verifyNoVideoCaption", "video has no caption as expected", true);
	}

	public void closePreviewModal() {
		previewModal.findElement(closeButton).click();
		waitForElementNotPresent(closeButton);
		PageObjectLogging.log("closePreviewModal", "preview modal closed", true);
	}
}
