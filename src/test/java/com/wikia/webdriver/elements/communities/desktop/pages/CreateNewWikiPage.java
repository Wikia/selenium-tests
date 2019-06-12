package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateNewWikiPage extends WikiBasePageObject {

  private static final String DATA_THEME_LIST = "li[data-theme]";
  private static final String SPECIAL_CREATE_NEW_WIKI_PAGE = "Special:CreateNewWiki";
  private static final By LOADING_INDICATOR_BY = By.cssSelector(".wikiaThrobber");
  private static final String THEME_LOCATOR = "li[data-theme='%name%']";
  private static final int CREATE_WIKI_TIMEOUT = 180;

  @FindBy(name = "wiki-name")
  private WebElement wikiName;
  @FindBy(name = "wiki-domain")
  private WebElement wikiDomain;
  @FindBy(css = ".next.enabled")
  private WebElement nextButton;
  @FindBy(css = "#NameWiki .wds-dropdown")
  private WebElement wikiLanguageDropdown;
  @FindBy(css = "#NameWiki .wds-dropdown .wds-list li:not(.spacer)")
  private List<WebElement> wikiLanguageList;
  @FindBy(css = ".wiki-base-domain")
  private WebElement domainSufix;
  @FindBy(css = ".wiki-domain-error.error-msg")
  private WebElement wikiDomainErrorMessage;
  @FindBy(css = "#DescWiki .wds-dropdown")
  private WebElement wikiCategoryDropdown;
  @FindBy(css = "#DescWiki .wds-dropdown .wds-list li")
  private List<WebElement> wikiCategoryList;
  @FindBy(css = "form[name='desc-form'] input.next")
  private WebElement createMyWikiButton;
  @FindBy(css = "label[for='allAges']")
  private WebElement allAgesCheckBox;
  @FindBy(css = "#DescWiki .wiki-vertical-error.error-msg")
  private WebElement categoryErrorMsg;
  @FindBy(css = "li[id='ThemeWiki'] input.next.enabled")
  private WebElement submitButton;
  @FindBy(css = "#CreateNewWiki")
  private WebElement wikiTaskId;

  /**
   * Open special Page to create new Wikia. This special page 'Special:CreateNewWiki' is only
   * available on community.fandom.com domain
   */
  public CreateNewWikiPage open() {
    getUrl(
        UrlBuilder.createUrlBuilderForWiki("community")
            .getUrlForWikiPage(SPECIAL_CREATE_NEW_WIKI_PAGE));

    return this;
  }

  public String getWikiName() {
    return CreateWikiMessages.WIKINAME_PREFIX + DateTime.now().getMillis();
  }

  public String getDomainSufix() {
    return domainSufix.getText();
  }

  public CreateNewWikiPage selectLanguage(String lang) {
    jsActions.scrollToElement(wait.forElementClickable(wikiLanguageDropdown));
    String langSelector = lang + ":";

    WebElement langElementInDropdown = wikiLanguageList.stream()
        .filter(e -> e.getAttribute("innerHTML").trim().contains(langSelector))
        .findAny()
        .orElseThrow(() -> new WebDriverException(String.format("Couldn't find language [%s]",
                                                                lang
        )));
    hover(wikiLanguageDropdown);
    langElementInDropdown.click();

    Log.log("selectLanguage",
            "selected " + langElementInDropdown.getAttribute("innerHTML").trim() + " language",
            true,
            driver
    );

    return this;
  }

  public CreateNewWikiPage typeInWikiName(String name) {
    wikiName.sendKeys(name);
    Log.log("typeInWikiName ", "Typed wiki name" + name, true);

    return this;
  }

  public CreateNewWikiPage typeInWikiDomain(String domain) {
    wikiDomain.clear();
    wikiDomain.sendKeys(domain);
    Log.log("typeInWikiDomain ", "Typed wiki domain " + domain, true);

    return this;
  }

  public String getDomainErrorMessage() {
    wait.forTextNotEmpty(wikiDomainErrorMessage);

    return wikiDomainErrorMessage.getText().toLowerCase();
  }

  public CreateNewWikiPage clickNext() {
    wait.forElementClickable(nextButton);
    Log.log("waitForNextButton", "Next button enabled", true, driver);

    scrollAndClick(nextButton);
    Log.log("submit", "Submit button clicked", true, driver);
    return this;
  }

  public DetachedRegisterPage clickNextToSignIn() {
    scrollAndClick(nextButton);
    Log.log("submit", "button \"Next\" clicked", true, driver);
    return new DetachedRegisterPage();
  }

  public CreateNewWikiPage selectCategory(int categoryId) {
    if (wikiCategoryList.isEmpty()) {
      throw new WebDriverException("No categories to choose from");
    }
    if (wikiCategoryList.size() < categoryId + 1) {
      throw new WebDriverException("Cannot find a category with this index");
    }
    scrollTo(wikiCategoryDropdown);
    wait.forElementClickable(wikiCategoryDropdown);
    WebElement selectedCategory;
    try {
      selectedCategory = wikiCategoryList.get(categoryId);
    } catch (ArrayIndexOutOfBoundsException e) {
      Log.log("selectCategory", "There is no category with index " + categoryId, false);
      throw new WebDriverException("There is no category with index " + categoryId, e);
    }
    hover(wikiCategoryDropdown);
    wait.forElementClickable(selectedCategory);
    selectedCategory.click();

    Log.log("selectCategory", "selected " + selectedCategory.getText() + " category", true, driver);

    return this;
  }

  public CreateNewWikiPage createMyWiki() {
    wait.forElementVisible(createMyWikiButton);
    scrollAndClick(createMyWikiButton);
    Log.log("submit", "Submit button clicked", true);
    return this;
  }

  public CreateNewWikiPage selectAllAgesCheckbox() {
    scrollAndClick(allAgesCheckBox);
    Log.log("selectAllAgesCheckbox", "all ages checkbox selected", true);

    return this;
  }

  public String getCategoryErrorMessage() {
    wait.forTextNotEmpty(categoryErrorMsg);

    return categoryErrorMsg.getText().toLowerCase();
  }

  public CreateNewWikiPage selectThemeByName(String name) {
    wait.forElementVisible(By.cssSelector(DATA_THEME_LIST));
    String themeName = THEME_LOCATOR.replace("%name%", name);
    scrollAndClick(driver.findElement(By.cssSelector(themeName)));
    Log.log("selectTheme", "skin " + name + " selected", true, driver);

    return this;
  }

  public ArticlePageObject showMeMyWiki() {

    changeImplicitWait(IMPLICIT_SHORT, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, CREATE_WIKI_TIMEOUT).until(CommonExpectedConditions.elementNotPresent(
          LOADING_INDICATOR_BY));
    } finally {
      restoreDefaultImplicitWait();
    }

    scrollAndClick(submitButton);
    Log.log("submit", "Submit button clicked", true, driver);
    return new ArticlePageObject();
  }

  public CreateNewWikiPage logWikiTaskId() {
    Log.info("Create New Wiki task ID=", wikiTaskId.getAttribute("data-task-id"));

    return this;
  }
}
