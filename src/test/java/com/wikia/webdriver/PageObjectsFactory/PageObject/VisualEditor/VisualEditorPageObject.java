package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorPageObject extends VisualEditorMenu {

	public VisualEditorPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ve-ui-linkTargetInputWidget > input")
	private WebElement linkInputField;
	@FindBy(css=".ve-ce-documentNode")
	private WebElement editArea;
	@FindBy(css="ol.ve-ce-branchNode > li")
	private List<WebElement> numList;
	@FindBy(css="ul.ve-ce-branchNode > li")
	private List<WebElement> bullList;
	@FindBy(css=".ve-init-mw-viewPageTarget-surface")
	private WebElement veEditorSurface;

	public void typeTextArea(String text) {
		waitForElementVisibleByElement(editArea);
		editArea.sendKeys(text);
		PageObjectLogging.log("write", "text " + text + "written", true);
	}

	public void press(Keys key) {
		editArea.sendKeys(key);
		PageObjectLogging.log("press", "key " + key.toString() + "pressed", true);
	}

	public void selectText(int from, int to) {
		String showSelectiontJS = "ve.instances[0].model.change( null, new ve.Range( " + from + ", " + to + " ) );";
		((JavascriptExecutor) driver).executeScript(showSelectiontJS);
	}

	public void selectText(String text) {
		String textDump = editArea.getText();
		int from = textDump.indexOf(text) + 1; //+1 because index is counted differently in selectText() method
		int to = from  +text.length() + 1;
		selectText(from, to);
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

	public void verifyFormatting(Formatting format, String text) {
		Assertion.assertEquals(text, editArea.findElement(format.getTag()).getText());
	}

	public void verifyStyle(Style style, String text) {
		Assertion.assertEquals(text, editArea.findElement(style.getTag()).getText());
	}

	public void verifyEditorSurfacePresent() {
		waitForElementVisibleByElement(veMode);
		waitForElementVisibleByElement(veEditorSurface);
		PageObjectLogging.log("verifyEditorSurface", "VE editor surface is displayed", true, driver);
	}

	public ArticlePageObject clickVEEditAndPublish(String wikiURL, String articleName, String content) {
		typeTextArea(content);
		VisualEditorSaveChangesDialog save = clickPublishButton();
		save.savePage();
		return new ArticlePageObject(driver);
	}
}
