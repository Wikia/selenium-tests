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
	By contentWrapper = By.cssSelector("#mw-content-text");
	By publishButton = By.cssSelector("#publish");
	By tableOfContents = By.cssSelector("#toc");
	By tableOfContentsOrderedList = By.cssSelector("#toc ol");

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
		PageObjectLogging.log(
				"verifyVideoAlignment",
				"video alignment is as exepected " + positions.toString(),
				true
		);
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

	public void verifyTextContent(String desiredText) {
		Assertion.assertEquals(desiredText, previewModal.findElement(contentWrapper).getText());
	}

	public void publish() {
		previewModal.findElement(publishButton).click();
	}

	public void verifyTOCpresentOnPreview() {
		waitForElementByElement(previewModal.findElement(tableOfContents));
		PageObjectLogging.log("verifyTOCpresentOnPreview", "TOC is present on preview", true);
	}

	public void verifyTOCexpandedOnPreview() {
		waitForElementByElement(previewModal.findElement(tableOfContentsOrderedList));
		PageObjectLogging.log("verifyTOCexpandedOnPreview", "TOC is expanded on preview", true);
	}

	public void verifyTOCcollapsedOnPreview() {
		waitForElementNotVisibleByElement(previewModal.findElement(tableOfContentsOrderedList));
		PageObjectLogging.log("verifyTOCcollapsedOnPreview", "TOC is collapsed on preview", true);
	}

}
