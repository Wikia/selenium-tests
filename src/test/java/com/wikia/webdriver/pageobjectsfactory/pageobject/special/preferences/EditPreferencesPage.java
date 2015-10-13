package com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class EditPreferencesPage extends PreferencesPageObject {

  @FindBy(css = "select#mw-input-wpeditor")
  private WebElement preferredEditorDropdown;

  @FindBy(css = "input#mw-input-wpemailaddress")
  private WebElement emailAddressInput;

  public EditPreferencesPage(WebDriver driver) {
    super(driver);
  }

  public EditPreferencesPage openEditingSection(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_EDITING_PREFERENCES);
    return this;
  }

  public EditPreferencesPage openEditingSection() {
    return openEditingSection(urlBuilder.getUrlForWiki(Configuration.getWikiName()));
  }

  public EditPreferencesPage openEmailSection(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_EDITING_PREFERENCES_EMAIL);
    return this;
  }

  public EditPreferencesPage openEmailSection() {
    return openEmailSection(urlBuilder.getUrlForWiki(Configuration.getWikiName()));
  }

  public void selectPreferredEditor(String value) {
    wait.forElementClickable(preferredEditorDropdown);
    Select select = new Select(preferredEditorDropdown);
    select.selectByValue(value);
    PageObjectLogging.log("selectPreferredEditor", "Selected " + value + " from preference", true);
  }

  public EditPreferencesPage changeEmail(String value) {
    wait.forElementVisible(emailAddressInput);
    emailAddressInput.clear();
    emailAddressInput.sendKeys(value);

    return this;
  }

  public String getEmailAdress() {
    wait.forElementVisible(emailAddressInput);
    return emailAddressInput.getAttribute("value");
  }
}
