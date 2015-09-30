package com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditingPreferencesPageObject extends PreferencesPageObject {

  @FindBy(css = "select#mw-input-wpeditor")
  private WebElement preferredEditorDropdown;

  public EditingPreferencesPageObject(WebDriver driver) {
    super(driver);
  }

  public void selectPreferredEditor(String value) {
    wait.forElementClickable(preferredEditorDropdown);
    Select select = new Select(preferredEditorDropdown);
    select.selectByValue(value);
    LOG.log("selectPreferredEditor", "Selected " + value + " from preference", LOG.Type.SUCCESS);
  }
}
