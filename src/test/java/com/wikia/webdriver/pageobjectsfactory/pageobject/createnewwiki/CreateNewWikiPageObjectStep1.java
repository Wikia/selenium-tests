package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CreateNewWikiPageObjectStep1 extends WikiBasePageObject {

  @FindBy(name = "wiki-name")
  private WebElement wikiName;
  @FindBy(name = "wiki-domain")
  private WebElement wikiDomain;
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

  private String wikiNameString;

  public CreateNewWikiPageObjectStep1(WebDriver driver) {
    super();
  }

  /**
   * Open special Page to create new Wikia. This special page 'Special:CreateNewWiki'
   * is only available on www.wikia.com domain
   * @return
   */
  public CreateNewWikiPageObjectStep1 open(){
    getUrl(urlBuilder.getUrlForWiki("wikia") + URLsContent.SPECIAL_CREATE_NEW_WIKI);

    return this;
  }

  public String getWikiName() {
    wikiNameString = CreateWikiMessages.WIKINAME_PREFIX + DateTime.now().getMillis();
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
    PageObjectLogging.log("typeInWikiName ", "Typed wiki name" + name, true);
  }

  public void typeInWikiDomain(String domain) {
    wikiDomain.clear();
    wikiDomain.sendKeys(domain);
    PageObjectLogging.log("typeInWikiDomain ", "Typed wiki domain " + domain, true);
  }

  public void verifyNextButtonEnabled() {
    wait.forElementVisible(submitButton);
    PageObjectLogging.log("waitForNextButton", "Next button enabled", true, driver);
  }

  public void verifyOccupiedWikiAddress(String wikiName) {
    wait.forTextInElement(wikiDomainErrorMessage, wikiName.toLowerCase());
    PageObjectLogging.log("verifyOccupiedWikiAddress", "Verified occupied wiki address", true);
  }

  public void verifyIncorrectWikiName() {
    wait.forTextInElement(wikiDomainErrorMessage,
                                             CreateWikiMessages.WIKINAME_VIOLATES_POLICY);
    PageObjectLogging.log("verifyIncorrectWikiName",
                          "Verified wiki name violates naming policy", true);
  }

  public CreateNewWikiPageObjectStep2 submit() {
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "Submit button clicked", true, driver);
    return new CreateNewWikiPageObjectStep2(driver);
  }

  public AuthModal clickNextToSignIn() {
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "button \"Next\" clicked", true, driver);
    return new AuthModal();
  }

  public void verifyWikiName(String expectedWikiName) {
    Assertion.assertEquals(wikiName.getAttribute("value"), expectedWikiName);
    PageObjectLogging.log("verifyWikiName", "verified wiki name equals: " + expectedWikiName, true);
  }

}
