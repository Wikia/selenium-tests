/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorPageObject extends BasePageObject{

	/**
	 * @param driver
	 */
	public VisualEditorPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="")
	private WebElement undoButton;
	@FindBy(css="")
	private WebElement redoButton;
	@FindBy(css="")
	private WebElement paragraphDropdown;
	@FindBy(css=".ve-ui-icon-bold-b")
	private WebElement boldButton;
	@FindBy(css=".ve-ui-icon-italic-i")
	private WebElement italicButton;
	@FindBy(css=".ve-ui-icon-link")
	private WebElement linkButton;
	@FindBy(css=".ve-ui-frame")
	private WebElement linkIframe;
	@FindBy(css=".ve-ui-linkTargetInputWidget > input")
	private WebElement linkInputField;
	@FindBy(css=".ve-ui-icon-code")
	private WebElement codeButton;
	@FindBy(css=".ve-ui-icon-clear")
	private WebElement clearButton;
	@FindBy(css=".ve-ui-icon-number-list")
	private WebElement numListButton;
	@FindBy(css=".ve-ui-icon-bullet-list")
	private WebElement bulletListButton;
	@FindBy(css=".ve-ce-documentNode")
	private WebElement editArea;
	@FindBy(css="p.ve-ce-branchNode")
	private WebElement textLine;
	@FindBy(css="b.ve-ce-TextStyleAnnotation.ve-ce-TextStyleBoldAnnotation")
	private WebElement boldLine;
	@FindBy(css="i.ve-ce-TextStyleAnnotation.ve-ce-TextStyleItalicAnnotation")
	private WebElement italicLine;
	@FindBy(css=".ve-ce-LinkAnnotation")
	private WebElement linkLine;
	@FindBy(css="code.ve-ce-TextStyleCodeAnnotation")
	private WebElement codeLine;
	@FindBy(css="ol.ve-ce-branchNode > li")
	private List<WebElement> numList;
	@FindBy(css="ul.ve-ce-branchNode > li")
	private List<WebElement> bullList;

	public void openVisualEditor() {
		getUrl("http://public.inez.wikia-dev.com/VisualEditor/demos/ve/");
	}

	public void write(String text) {
		editArea.sendKeys(text);
	}

	public void write(Keys key) {
		editArea.sendKeys(key);
	}

	public void clear() {
		editArea.clear();
	}

	public void clickBoldButton() {
		boldButton.click();
	}

	public void clickItalicButton() {
		italicButton.click();
	}

	public void clickLinkButton() {
		linkButton.click();
	}

	public void clickCodeButton() {
		codeButton.click();
	}

	public void clickClearButton() {
		clearButton.click();
	}

	public void clickNumListButton() {
		numListButton.click();
	}

	public void clickBullListButton() {
		bulletListButton.click();
	}

	public void typeHyperlink(String hyperlink) {
		waitForElementByElement(linkIframe);
		driver.switchTo().frame(linkIframe);
		linkInputField.sendKeys(hyperlink);
		linkInputField.sendKeys(Keys.ENTER);
		driver.switchTo().defaultContent();
	}

	public void verifyLink(String text, String hyperlink) {
		waitForElementByElement(linkLine);
		Assertion.assertEquals(hyperlink, linkLine.getAttribute("href"));
		Assertion.assertEquals(text, linkLine.getText());
	}

	public void verifyTextBold(String text) {
		waitForElementByElement(boldLine);
		Assertion.assertEquals(text, boldLine.getText());
	}

	public void verifyTextItalic(String text) {
		waitForElementByElement(italicLine);
		Assertion.assertEquals(text, italicLine.getText());
	}

	public void verifyCodeText(String text) {
		waitForElementByElement(codeLine);
		Assertion.assertEquals(text, codeLine.getText());
	}

	public void selectBoldText() {
		waitForElementByElement(boldLine);
		Actions actions = new Actions(driver);
		actions.doubleClick(boldLine).build().perform();
	}

	public void selectItalicText() {
		waitForElementByElement(italicLine);
		Actions actions = new Actions(driver);
		actions.doubleClick(italicLine).build().perform();
	}

	public void selectCodeText() {
		waitForElementByElement(codeLine);
		Actions actions = new Actions(driver);
		actions.doubleClick(codeLine).build().perform();
	}

	public void verifyTextNotFormatted(String text) {
		waitForElementByElement(textLine);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String currentText = js.executeScript("return $(arguments[0]).html()", textLine).toString();
		Assertion.assertEquals(text, currentText);
	}

	public void verifyNumList(List<String> elements) {
		for (int i=0; i<elements.size(); i++) {
			Assertion.assertEquals(elements.get(i), numList.get(i).getText());
		}
	}

	public void verifyBullList(List<String> elements) {
		for (int i=0; i<elements.size(); i++) {
			Assertion.assertEquals(elements.get(i), bullList.get(i).getText());
		}
	}




}
