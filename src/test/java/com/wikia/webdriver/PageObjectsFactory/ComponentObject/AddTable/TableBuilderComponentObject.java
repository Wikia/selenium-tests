package com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddTable;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * @author llukaszj
 */
public class TableBuilderComponentObject extends WikiBasePageObject {

	@FindBy(css=".cke_dialog_ui_button.wikia-button")
	private WebElement submitLightboxButton;
	@FindBy(css=".cke_dialog_body")
	private WebElement addTableLightbox;
	@FindBy(css="input.cke_dialog_ui_input_text")
	private List<WebElement> tablePropertiesInputs;
	@FindBy(css=".cke_dialog_ui_input_select")
	private List<WebElement> tablePropertiesDropdownOptions;

	public TableBuilderComponentObject(WebDriver driver) {
		super(driver);
	}

	public void verifyAddTableLightbox() {
		waitForElementByElement(addTableLightbox);
	}

	public void typeAmountOfRows(int rows) {
		tablePropertiesInputs.get(0).clear();
		tablePropertiesInputs.get(0).sendKeys(Integer.toString(rows));
		PageObjectLogging.log(
			"typeAmountOfRows",
			"amount of rows was typed: " + rows,
			true
		);
	}

	public void typeAmountOfColumns(int columns) {
		tablePropertiesInputs.get(1).clear();
		tablePropertiesInputs.get(1).sendKeys(Integer.toString(columns));
		PageObjectLogging.log(
			"typeAmountOfColumns",
			"amount of columns was typed: " + columns,
			true
		);
	}

	public void typeBorderSize(int border) {
		tablePropertiesInputs.get(2).clear();
		tablePropertiesInputs.get(2).sendKeys(Integer.toString(border));
		PageObjectLogging.log("typeBorderSize", "border size was typed: " + border, true);
	}

	public void typeWidth(int width) {
		tablePropertiesInputs.get(3).clear();
		tablePropertiesInputs.get(3).sendKeys(Integer.toString(width));
		PageObjectLogging.log("typeWidth", "width was typed:" + width, true);
	}

	public void typeHeight(int height) {
		tablePropertiesInputs.get(4).clear();
		tablePropertiesInputs.get(4).sendKeys(Integer.toString(height));
		PageObjectLogging.log("typeHeight", "height was typed: " + height, true);
	}

	public void typeCellSpacing(int cellSpacing) {
		tablePropertiesInputs.get(5).clear();
		tablePropertiesInputs.get(5).sendKeys(Integer.toString(cellSpacing));
		PageObjectLogging.log(
			"typeCellSpacing",
			"cell spacing was typed: " + cellSpacing,
			true
		);
	}

	public void typeCellPadding(int cellPadding) {
		tablePropertiesInputs.get(6).clear();
		tablePropertiesInputs.get(6).sendKeys(Integer.toString(cellPadding));
		PageObjectLogging.log(
			"typeCellPadding",
			"cell padding was typed: " + cellPadding,
			true, driver
		);
	}

	public enum Headers {
		NONE, FIRSTROW, FIRSTCOLUMN, BOTH
	}

	public void selectHeader(Headers header) {
		waitForElementByElement(tablePropertiesDropdownOptions.get(0));
		Select headerDropdown = new Select(tablePropertiesDropdownOptions.get(0));
		switch(header) {
		case NONE:
			headerDropdown.selectByIndex(header.ordinal());
			break;
		case FIRSTROW:
			headerDropdown.selectByIndex(header.ordinal());
			break;
		case FIRSTCOLUMN:
			headerDropdown.selectByIndex(header.ordinal());
			break;
		case BOTH:
			headerDropdown.selectByIndex(header.ordinal());
			break;
		}
		PageObjectLogging.log("selectHeader", header.toString() + " header selected", true, driver);
	}

	public enum Alignment {
		LEFT, CENTER, RIGHT;

		private final String label;

		Alignment() {
			this.label = StringUtils.capitalize(this.toString().toLowerCase());
		}

		public String getAlignment() {
			return this.label;
		}
	}

	public void selectAlignment(Alignment position) {
		waitForElementByElement(tablePropertiesDropdownOptions.get(1));
		Select positionDropdown = new Select(tablePropertiesDropdownOptions.get(1));
		switch(position) {
		case LEFT:
			positionDropdown.selectByVisibleText(position.getAlignment());
			break;
		case CENTER:
			positionDropdown.selectByVisibleText(position.getAlignment());
			break;
		case RIGHT:
			positionDropdown.selectByVisibleText(position.getAlignment());
			break;
		}
		PageObjectLogging.log("selectPosition", position.getAlignment() + " position selected", true, driver);
	}

	public void submitTable() {
		waitForElementByElement(submitLightboxButton);
		submitLightboxButton.click();
		PageObjectLogging.log("submitButton", "Table submited", true);
	}

}
