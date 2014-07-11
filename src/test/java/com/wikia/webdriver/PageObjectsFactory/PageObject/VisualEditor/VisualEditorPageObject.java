package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Indentation;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertList;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
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
	@FindBy(css=".image.video.video-thumbnail.medium")
	private List<WebElement> mediaNodes;
	@FindBy(css="figure.ve-ce-branchNode a")
	private WebElement mediaNode;

	public void selectMediaAndDelete() {
		waitForElementByElement(editArea);
		editArea.click();
		waitForElementByElement(mediaNode);
		mediaNode.click();
		Actions actions2 = new Actions(driver);
		actions2.sendKeys(Keys.DELETE).build().perform();
	}

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

	public int[] getTextIndex(String text) {
		String textDump = editArea.getText();
		int[] indexes = new int[2];
		//+1 because index is counted differently in selectText() method
		indexes[0] = textDump.indexOf(text) + 1;
		indexes[1] = indexes[0] +text.length();
		if (indexes[0] == 0) {
			throw new NoSuchElementException("String: " + text + " is not found");
		}
		return indexes;
	}

	public void removeText(String text) {
		int[] indexes = getTextIndex(text);
		String script = "ve.instances[0].model.change("
			+"ve.dm.Transaction.newFromRemoval(ve.instances[0].model.documentModel,"
			+"new ve.Range(arguments[0],arguments[1])));";
		((JavascriptExecutor) driver).executeScript(script, indexes[0], indexes[1]);
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

	public ArticlePageObject clickVEEditAndPublish(String content) {
		typeTextArea(content);
		VisualEditorSaveChangesDialog save = clickPublishButton();
		save.savePage();
		return new ArticlePageObject(driver);
	}

	public void verifyVideo() {
		waitForElementByElement(mediaNode);
		waitForElementVisibleByElement(mediaNode);
		PageObjectLogging.log("verifyVideo", "VE video is displayed", true);
	}

	public void verifyVideos(int expected) {
		waitForElementByElement(mediaNode);
		waitForElementVisibleByElement(mediaNode);
		Assertion.assertNumber(expected, mediaNodes.size(), "Checking the correct number of video nodes added");
		PageObjectLogging.log("verifyVideos", mediaNodes.size() + " videos displayed", true);
	}

	public void typeReturn() {
		waitForElementVisibleByElement(editArea);
		editArea.sendKeys(Keys.RETURN);
	}

	public void typeTextInAllFormat(String text) {
		for (Formatting format : Formatting.values()){
			PageObjectLogging.log("Formatting selection", format.toString() + " selected", true);
			selectFormatting(format);
			typeTextArea(text);
			typeReturn();
			if (format.name().equals("PREFORMATTED")) {
				selectFormatting(Formatting.PARAGRAPH);
			}
		}
	}

	public void typeTextInAllStyle(String text) {
		for (Style style : Style.values()) {
			PageObjectLogging.log("Style selection", style.toString() + " selected", true);
			selectStyle(style);
			typeTextArea(text);
			typeReturn();
		}
	}

	public void typeTextInAllList(String text) {
		typeReturn();
		typeTextArea(text);
		insertList(InsertList.BULLET_LIST);
		typeReturn();
		selectIndentation(Indentation.DECREASE);
		typeTextArea(text);
		insertList(InsertList.NUMBERED_LIST);
		typeReturn();
		selectIndentation(Indentation.DECREASE);
	}

	public void copyAndPaste() {
		waitForElementClickableByElement(editArea);
		editArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		editArea.sendKeys(Keys.chord(Keys.CONTROL, "c"));
		editArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		editArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		PageObjectLogging.log("copyAndPaste", editArea.getText(), true, driver);
	}
}
