package com.wikia.webdriver.pageobjectsfactory.componentobject.addtable;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TableBuilderComponentObject extends WikiBasePageObject {

  @FindBy(css = ".cke_dialog_ui_button.wikia-button")
  private WebElement submitLightboxButton;
  @FindBy(css = ".cke_dialog_body")
  private WebElement addTableLightbox;
  @FindBy(css = "input.cke_dialog_ui_input_text")
  private List<WebElement> tablePropertiesInputs;
  @FindBy(css = "select.cke_dialog_ui_input_select")
  private List<WebElement> tablePropertiesDropdownOptions;

  public TableBuilderComponentObject(WebDriver driver) {
    super();
  }

  public void verifyAddTableLightbox() {
    wait.forElementVisible(addTableLightbox);
  }

  public void typeAmountOfRows(int rows) {
    tablePropertiesInputs.get(0).clear();
    tablePropertiesInputs.get(0).sendKeys(Integer.toString(rows));
    Log.log("typeAmountOfRows", "amount of rows was typed: " + rows, true);
  }

  public void typeAmountOfColumns(int columns) {
    tablePropertiesInputs.get(1).clear();
    tablePropertiesInputs.get(1).sendKeys(Integer.toString(columns));
    Log.log("typeAmountOfColumns", "amount of columns was typed: " + columns, true);
  }

  public void typeBorderSize(int border) {
    tablePropertiesInputs.get(2).clear();
    tablePropertiesInputs.get(2).sendKeys(Integer.toString(border));
    Log.log("typeBorderSize", "border size was typed: " + border, true);
  }

  public void typeWidth(int width) {
    tablePropertiesInputs.get(3).clear();
    tablePropertiesInputs.get(3).sendKeys(Integer.toString(width));
    Log.log("typeWidth", "width was typed:" + width, true);
  }

  public void typeHeight(int height) {
    tablePropertiesInputs.get(4).clear();
    tablePropertiesInputs.get(4).sendKeys(Integer.toString(height));
    Log.log("typeHeight", "height was typed: " + height, true);
  }

  public void typeCellSpacing(int cellSpacing) {
    tablePropertiesInputs.get(5).clear();
    tablePropertiesInputs.get(5).sendKeys(Integer.toString(cellSpacing));
    Log.log("typeCellSpacing", "cell spacing was typed: " + cellSpacing, true);
  }

  public void typeCellPadding(int cellPadding) {
    tablePropertiesInputs.get(6).clear();
    tablePropertiesInputs.get(6).sendKeys(Integer.toString(cellPadding));
    Log.log("typeCellPadding", "cell padding was typed: " + cellPadding, true, driver);
  }

  public void selectHeader(Headers header) {
    wait.forElementVisible(tablePropertiesDropdownOptions.get(0));
    Select headerDropdown = new Select(tablePropertiesDropdownOptions.get(0));
    switch (header) {
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
      default:
        throw new NoSuchElementException("Non-existing header selected");
    }
    Log.log("selectHeader", header.toString() + " header selected", true, driver);
  }

  public void selectAlignment(Alignment position) {
    wait.forElementVisible(tablePropertiesDropdownOptions.get(1));
    Select positionDropdown = new Select(tablePropertiesDropdownOptions.get(1));
    switch (position) {
      case LEFT:
        positionDropdown.selectByVisibleText(position.getAlignment());
        break;
      case CENTER:
        positionDropdown.selectByVisibleText(position.getAlignment());
        break;
      case RIGHT:
        positionDropdown.selectByVisibleText(position.getAlignment());
        break;
      default:
        throw new NoSuchElementException("Non-existing alignment selected");
    }
    Log.log("selectPosition", position.getAlignment() + " position selected", true, driver);
  }

  public void submitTable() {
    wait.forElementVisible(submitLightboxButton);
    submitLightboxButton.click();
    Log.log("submitButton", "Table submited", true);
  }

  public enum Headers {
    NONE, FIRSTROW, FIRSTCOLUMN, BOTH
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
}
