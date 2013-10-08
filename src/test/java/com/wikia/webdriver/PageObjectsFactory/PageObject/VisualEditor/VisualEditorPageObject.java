/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorPageObject extends VisualEditorMenu {

	public VisualEditorMenu menu;

	public VisualEditorPageObject(WebDriver driver) {
		super(driver);
		this.menu = new VisualEditorMenu(driver);
	}

	@FindBy(css=".ve-ui-linkTargetInputWidget > input")
	private WebElement linkInputField;
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

	public void write(String text) {
		editArea.sendKeys(text);
		PageObjectLogging.log("write", "text " + text + "written", true);
	}

	public void write(Keys key) {
		editArea.sendKeys(key);
		PageObjectLogging.log("write", "text " + key.toString() + "pressed", true);
	}

	public void clear() {
		editArea.clear();
		PageObjectLogging.log("clear", "editor area cleared", true);
	}


	public void typeHyperlink(String hyperlink) {
		waitForElementByElement(linkIframe);
		driver.switchTo().frame(linkIframe);
		linkInputField.sendKeys(hyperlink);
		linkInputField.click();
		linkInputField.sendKeys(Keys.ENTER);
//		Actions a = new Actions(driver);
//		a.sendKeys(linkInputField, hyperlink);
//		a.sendKeys(linkInputField, Keys.ENTER);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].trigger(\"change\")", linkInputField);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("typeHyperlink", "hyperlink added", true);
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

	public void selectText(int from, int to) {
		String showSelectiontJS = "ve.instances[0].model.change( null, new ve.Range( " + from + ", " + to + " ) );";
		((JavascriptExecutor) driver).executeScript(showSelectiontJS);
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

	public void verifyFormatting (Formatting format, String text) {
		Assertion.assertEquals(text, editArea.findElement(format.getTag()).getText());
	}
}
