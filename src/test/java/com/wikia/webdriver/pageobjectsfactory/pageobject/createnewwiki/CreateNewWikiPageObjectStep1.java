package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateNewWikiPageObjectStep1 extends WikiBasePageObject {

  @FindBy(name = "wiki-name")
  private WebElement wikiName;
  @FindBy(name = "wiki-domain")
  private WebElement wikiDomain;
  @FindBy(css = ".next.enabled")
  private WebElement submitButton;
  @FindBy(css = "#NameWiki .wds-dropdown")
  private WebElement wikiLanguageDropdown;
  @FindBy(css = "#NameWiki .wds-dropdown .wds-list li:not(.spacer)")
  private List<WebElement> wikiLanguageList;
  @FindBy(css = ".domain-country")
  private WebElement languageSelectedIndicator;
  @FindBy(css = ".wiki-domain-error.error-msg")
  private WebElement wikiDomainErrorMessage;

  private String wikiNameString;

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
    jsActions.scrollToElement(wait.forElementClickable(wikiLanguageDropdown));
    String langSelector = lang + ":";

    WebElement langElementInDropdown = wikiLanguageList.stream()
            .filter(e -> e.getAttribute("innerHTML").trim().contains(langSelector)).findAny()
            .orElseThrow(() -> new WebDriverException(String.format("Couldn't find language [%s]", lang)));
    hover(wikiLanguageDropdown);
    langElementInDropdown.click();

    PageObjectLogging
            .log("selectLanguage", "selected " + langElementInDropdown.getAttribute("innerHTML").trim() + " language", true, driver);

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

  public RegisterPage clickNextToSignIn() {
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "button \"Next\" clicked", true, driver);
    return new DetachedRegisterPage();
  }
}
