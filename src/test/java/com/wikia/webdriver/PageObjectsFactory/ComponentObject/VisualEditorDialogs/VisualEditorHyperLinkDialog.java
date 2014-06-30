package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorHyperLinkDialog extends WikiBasePageObject {

	@FindBy(css=".oo-ui-widget .oo-ui-frame")
	private WebElement hyperLinkIFrame;
	@FindBy(css=".oo-ui-icon-previous")
	private WebElement previousButton;
	@FindBy(css=".oo-ui-icon-remove")
	private WebElement removeButton;
	@FindBy(css=".ve-ui-mwLinkTargetInputWidget input")
	private WebElement linkInput;
	@FindBy(css=".oo-ui-texture-pending")
	private WebElement inputPending;
	@FindBy(css=".oo-ui-optionWidget-selected")
	private WebElement selectedResult;
	@FindBy(css=".ve-ui-desktopContext")
	private WebElement desktopContext;
	@FindBy(css=".ve-ui-mwLinkTargetInputWidget")
	private WebElement linkInputDiv;
	@FindBy(css=".oo-ui-window-title")
	private WebElement title;
	@FindBy(css=".oo-ui-frame-html")
	private WebElement frame;



	public VisualEditorHyperLinkDialog(WebDriver driver) {
		super(driver);
	}

	public void typeInLinkInput(String text) {
		waitForElementByElement(hyperLinkIFrame);
		driver.switchTo().frame(hyperLinkIFrame);
		waitForElementByElement(linkInput);
		linkInput.sendKeys(text);
//		//Need to click away and then click on the input for the results to be updated
//		linkInputDiv.click();
//		title.click();
		driver.switchTo().defaultContent();
	}

	public void viewResults() {
		waitForElementNotVisibleByElement(inputPending);
		waitForElementByElement(selectedResult);
		WebElement linkResultMenu = desktopContext.findElement(By.cssSelector(".ve-ui-mwLinkTargetInputWidget-menu"));
		waitForElementVisibleByElement(linkResultMenu);
		List<WebElement> results = linkResultMenu.findElements(By.cssSelector(".oo-ui-menuItemWidget"));
		for (WebElement result: results) {
			String itemText = result.findElement(By.cssSelector(".oo-ui-labeledElement-label")).getAttribute("title");
			System.out.println(itemText);
		}
	}
}
