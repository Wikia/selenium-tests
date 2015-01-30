package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author Karol Kujawiak
 */

public class CreateNewWikiPageObjectStep1 extends WikiBasePageObject {

  @FindBy(name = "wiki-name")
  private WebElement wikiName;
  @FindBy(name = "wiki-domain")
  private WebElement wikiDomain;
  @FindBy(css = "span.domain-status-icon img[src*='check.png']")
  private WebElement successIcon;
  @FindBy(css = ".next.enabled")
  private WebElement submitButton;
  @FindBy(css = "select[name='wiki-language']")
  private WebElement languageSelector;
  @FindBy(css = "#ChangeLang")
  private WebElement languageSelectorTrigger;
  @FindBy(css = ".domain-country")
  private WebElement languageSelectedIndicator;
  @FindBy(css = ".wiki-domain-error.error-msg")
  private WebElement wikiDomainErrorMessage;
  ;

  private String wikiNameString;

  public CreateNewWikiPageObjectStep1(WebDriver driver) {
    super(driver);
  }

  public String getWikiName() {
    wikiNameString = CreateWikiMessages.WIKINAME_PREFIX + getRandomDigits(3) + getRandomString(5);
    return this.wikiNameString;
  }


  public void selectLanguage(String lang) {
    scrollAndClick(languageSelectorTrigger);
    waitForElementByElement(languageSelector);
    Select language = new Select(languageSelector);
    List<WebElement> langList = language.getOptions();
    for (int i = 0; i < langList.size(); i++) {
      String langDropElement = langList.get(i).getText();
      if (langDropElement.contains(lang + ":")) {
        language.selectByIndex(i);
        Assertion.assertEquals(lang + ".", languageSelectedIndicator.getText());
        break;
      }
    }
  }

  public void typeInWikiName(String name) {
    wikiName.sendKeys(name);
    PageObjectLogging.log("typeInWikiName ", "Typed wiki name" + name, true);
  }

  public void typeInWikiDomain(String domain) {
    wikiDomain.clear();
    wikiDomain.sendKeys(domain);
    PageObjectLogging.log("typeInWikiDomain ", "Typed wiki domain " + domain, true);
  }

  /**
   * @author Karol Kujawiak
   */
  public void verifySuccessIcon() {
    waitForElementByElement(successIcon);
    waitForElementByElement(submitButton);
    PageObjectLogging.log("waitForSuccessIcon", "Success icon found", true, driver);
  }

  public void verifyOccupiedWikiAddress(String wikiName) {
    waitForTextToBePresentInElementByElement(wikiDomainErrorMessage, wikiName.toLowerCase());
    PageObjectLogging.log("verifyOccupiedWikiAddress", "Verified occupied wiki address", true);
  }

  public void verifyIncorrectWikiName() {
    waitForTextToBePresentInElementByElement(wikiDomainErrorMessage,
                                             CreateWikiMessages.WIKINAME_VIOLATES_POLICY);
    PageObjectLogging.log("verifyIncorrectWikiName",
                          "Verified wiki name violates naming policy", true);
  }

  public CreateNewWikiPageObjectStep2 submit() {
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "Submit button clicked", true, driver);
    return new CreateNewWikiPageObjectStep2(driver);
  }

  public CreateNewWikiLogInSignUpPageObject submitToLogInSignUp() {
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "Submit button clicked", true, driver);
    return new CreateNewWikiLogInSignUpPageObject(driver);
  }

  public void verifyWikiName(String expectedWikiName) {
    Assertion.assertEquals(expectedWikiName, wikiName.getAttribute("value"));
    PageObjectLogging.log("verifyWikiName", "verified wiki name equals: " + expectedWikiName, true);
  }

}
