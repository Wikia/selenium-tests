package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

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
  private WebElement wikiDomainErrorMessage;;

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
    wait.forElementVisible(languageSelector);
    Select language = new Select(languageSelector);
    List<WebElement> langList = language.getOptions();
    for (int i = 0; i < langList.size(); i++) {
      String langDropElement = langList.get(i).getText();
      if (langDropElement.contains(lang + ":")) {
        language.selectByIndex(i);
        Assertion.assertEquals(languageSelectedIndicator.getText(), lang + ".");
        break;
      }
    }
  }

  public void typeInWikiName(String name) {
    wikiName.sendKeys(name);
    LOG.success("typeInWikiName ", "Typed wiki name" + name);
  }

  public void typeInWikiDomain(String domain) {
    wikiDomain.clear();
    wikiDomain.sendKeys(domain);
    LOG.success("typeInWikiDomain ", "Typed wiki domain " + domain);
  }

  /**
   * @author Karol Kujawiak
   */
  public void verifySuccessIcon() {
    wait.forElementVisible(successIcon);
    wait.forElementVisible(submitButton);
    LOG.success("waitForSuccessIcon", "Success icon found", true);
  }

  public void verifyOccupiedWikiAddress(String wikiName) {
    wait.forTextInElement(wikiDomainErrorMessage, wikiName.toLowerCase());
    LOG.success("verifyOccupiedWikiAddress", "Verified occupied wiki address");
  }

  public void verifyIncorrectWikiName() {
    wait.forTextInElement(wikiDomainErrorMessage, CreateWikiMessages.WIKINAME_VIOLATES_POLICY);
    LOG.success("verifyIncorrectWikiName", "Verified wiki name violates naming policy");
  }

  public CreateNewWikiPageObjectStep2 submit() {
    scrollAndClick(submitButton);
    LOG.success("submit", "Submit button clicked", true);
    return new CreateNewWikiPageObjectStep2(driver);
  }

  public CreateNewWikiLogInSignUpPageObject submitToLogInSignUp() {
    scrollAndClick(submitButton);
    LOG.success("submit", "Submit button clicked", true);
    return new CreateNewWikiLogInSignUpPageObject(driver);
  }

  public void verifyWikiName(String expectedWikiName) {
    Assertion.assertEquals(wikiName.getAttribute("value"), expectedWikiName);
    LOG.success("verifyWikiName", "verified wiki name equals: " + expectedWikiName);
  }

}
