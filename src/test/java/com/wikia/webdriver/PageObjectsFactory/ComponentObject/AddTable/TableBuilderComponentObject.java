package com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddTable;

import com.wikia.webdriver.Common.Core.Assertion;
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
	@FindBy(css=".cke_dialog_background_cover")
	private WebElement lightboxBackground;
	@FindBy(css="input.cke_dialog_ui_input_text")
	private List<WebElement> tablePropertiesInputs;
	@FindBy(css=".cke_dialog_ui_input_select > option")
	private List<WebElement> selectingInputs;

	private String backgroundLigthboxColor = "rgba(0, 0, 0, 1)";

	public TableBuilderComponentObject(WebDriver driver) {
		super(driver);
	}

	public void verifyAddTableLightbox() {
		waitForElementByElement(addTableLightbox);
		Assertion.assertTrue(lightboxBackground.getCssValue("background-color").equals(backgroundLigthboxColor));
	}

	public void typeAmountOfRows(Integer i) {
		tablePropertiesInputs.get(0).clear();
		tablePropertiesInputs.get(0).sendKeys(i.toString());
		PageObjectLogging.log("typeAmountOfRows", "amount of rows was typed", true, driver);
	}

	public void typeAmountOfColumns(Integer i) {
		tablePropertiesInputs.get(1).clear();
		tablePropertiesInputs.get(1).sendKeys(i.toString());
		PageObjectLogging.log("typeAmountOfColumns", "amount of columns was typed", true, driver);
	}

	public void typeBorderSize(Integer i) {
		tablePropertiesInputs.get(2).clear();
		tablePropertiesInputs.get(2).sendKeys(i.toString());
		PageObjectLogging.log("typeBorderSize", "border size was typed", true, driver);
	}

	public void typeWidth(Integer width) {
		tablePropertiesInputs.get(3).clear();
		tablePropertiesInputs.get(3).sendKeys(width.toString());
		PageObjectLogging.log("typeWidth", "width was typed", true, driver);
	}

	public void typeHeight(Integer height) {
		tablePropertiesInputs.get(4).clear();
		tablePropertiesInputs.get(4).sendKeys(height.toString());
		PageObjectLogging.log("typeHeight", "height was typed", true, driver);
	}

	public void typeCellSpacing(Integer i) {
		tablePropertiesInputs.get(5).clear();
		tablePropertiesInputs.get(5).sendKeys(i.toString());
		PageObjectLogging.log("typeCellSpacing", "cell spacing was typed", true, driver);
	}

	public void typeCellPadding(Integer i) {
		tablePropertiesInputs.get(6).clear();
		tablePropertiesInputs.get(6).sendKeys(i.toString());
		PageObjectLogging.log("typeCellPadding", "cell padding was typed", true, driver);
	}

	public enum Headers{
		None, FirstRow, FirstColumn, Both
	}

	public void selectHeader(Headers head){
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

	public enum Alignment{
		Left, Center, Right
	}

	public void selectAlignment(Alignment position){
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

	public void clickOKButton(){
		waitForElementByElement(okLightboxButton);
		okLightboxButton.click();
		PageObjectLogging.log("clickOKButton", "OK button clicked", true);
	}

}
