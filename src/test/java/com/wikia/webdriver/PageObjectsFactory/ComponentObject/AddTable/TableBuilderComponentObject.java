package com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddTable;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author llukaszj
 */
public class TableBuilderComponentObject extends BasePageObject {

	@FindBy(css=".cke_dialog_ui_button.wikia-button")
	private WebElement okLightboxButton;
	@FindBy(css=".cke_dialog_body")
	private WebElement addTableLightbox;
	@FindBy(css="input.cke_dialog_ui_input_text")
	private List<WebElement> tablePropertiesInputs;
	@FindBy(css=".cke_dialog_ui_input_select > option")
	private List<WebElement> selectingInputs;

	public TableBuilderComponentObject(WebDriver driver) {
		super(driver);
	}

	public void verifyAddTableLightbox() {
		waitForElementByElement(addTableLightbox);
	}

	public void typeAmountOfRows(int rows) {
		tablePropertiesInputs.get(0).clear();
		tablePropertiesInputs.get(0).sendKeys(Integer.toString(rows));
		PageObjectLogging.log("typeAmountOfRows", "amount of rows was typed", true, driver);
	}

	public void typeAmountOfColumns(int columns) {
		tablePropertiesInputs.get(1).clear();
		tablePropertiesInputs.get(1).sendKeys(Integer.toString(columns));
		PageObjectLogging.log("typeAmountOfColumns", "amount of columns was typed", true, driver);
	}

	public void typeBorderSize(int border) {
		tablePropertiesInputs.get(2).clear();
		tablePropertiesInputs.get(2).sendKeys(Integer.toString(border));
		PageObjectLogging.log("typeBorderSize", "border size was typed", true, driver);
	}

	public void typeWidth(int width) {
		tablePropertiesInputs.get(3).clear();
		tablePropertiesInputs.get(3).sendKeys(Integer.toString(width));
		PageObjectLogging.log("typeWidth", "width was typed", true, driver);
	}

	public void typeHeight(int height) {
		tablePropertiesInputs.get(4).clear();
		tablePropertiesInputs.get(4).sendKeys(Integer.toString(height));
		PageObjectLogging.log("typeHeight", "height was typed", true, driver);
	}

	public void typeCellSpacing(int cellSpacing) {
		tablePropertiesInputs.get(5).clear();
		tablePropertiesInputs.get(5).sendKeys(Integer.toString(cellSpacing));
		PageObjectLogging.log("typeCellSpacing", "cell spacing was typed", true, driver);
	}

	public void typeCellPadding(int cellPadding) {
		tablePropertiesInputs.get(6).clear();
		tablePropertiesInputs.get(6).sendKeys(Integer.toString(cellPadding));
		PageObjectLogging.log("typeCellPadding", "cell padding was typed", true, driver);
	}

	public enum Headers {
		None, FirstRow, FirstColumn, Both
	}

	public void selectHeader(Headers head) {
		waitForElementByElement(selectingInputs.get(0));
		switch(head) {
		case None:
			selectingInputs.get(0).click();
			break;
		case FirstRow:
			selectingInputs.get(1).click();
			break;
		case FirstColumn:
			selectingInputs.get(2).click();
			break;
		case Both:
			selectingInputs.get(3).click();
			break;
		}
		PageObjectLogging.log("selectHeader", head.toString()+" header selected", true, driver);
	}

	public enum Alignment {
		Left, Center, Right
	}

	public void selectAlignment(Alignment position) {
		waitForElementByElement(selectingInputs.get(4));
		switch(position) {
		case Left:
			selectingInputs.get(5).click();
			break;
		case Center:
			selectingInputs.get(6).click();
			break;
		case Right:
			selectingInputs.get(7).click();
			break;
		}
		PageObjectLogging.log("selectPosition", position.toString() + " position selected", true, driver);
	}

	public void clickOKButton() {
		waitForElementByElement(okLightboxButton);
		okLightboxButton.click();
		PageObjectLogging.log("clickOKButton", "OK button clicked", true);
	}

}
