package com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.interactions.Typing;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavigationBar extends WikiBasePageObject {

  final private String suggestionCss = ".autocomplete div";

  @FindBy(css = "#searchInput")
  private WebElement searchInput;
  @FindBy(css = "#searchSubmit")
  private WebElement searchSubmit;
  @FindBy(css = suggestionCss)
  private List<WebElement> suggestionsList;
  @FindBy(css = ".wds-global-navigation__account-menu-caption")
  private WebElement myAccount;
  @FindBy(css = "#global-navigation-anon-sign-in")
  private WebElement signInLink;
  @FindBy(css = "#global-navigation-anon-register")
  private WebElement registerLink;

  private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");

  public NavigationBar(WebDriver driver) {
    super();
  }

  public void triggerSuggestions(String query) {
    wait.forElementVisible(searchInput);
    searchInput.clear();
    wait.forElementClickable(searchInput);
    searchInput.click();
    wait.forElementPresent(jqueryAutocompleteBy);
    Typing.sendKeysHumanSpeed(searchInput, query);
    wait.forElementVisible(By.cssSelector(suggestionCss));
    wait.forElementVisible(suggestionsList.get(0));
  }

  public void verifySuggestions(String suggestionText) {
    wait.forElementVisible(suggestionsList.get(0));
    String allSuggestionTexts = "";
    for (int i = 0; i < suggestionsList.size(); i++) {
      if (suggestionsList.get(i).getAttribute("title") != null) {
        allSuggestionTexts += suggestionsList.get(i).getAttribute("title");
      }
    }
    Assertion.assertStringContains(allSuggestionTexts, suggestionText);
  }

  /**
   * Arrow down through suggestions, and click enter on the desired one
   */
  public ArticlePageObject ArrowDownAndEnterSuggestion(String suggestionText) {
    wait.forElementVisible(suggestionsList.get(0));

    int position = 0;
    for (WebElement suggestion : suggestionsList) {
      if (suggestion.getText().contains(suggestionText)) {
        position++;
      }
    }

    Assertion.assertNotEquals(0, position, "suggestion " + suggestionText + "not found");

    if (position != 0) {
      for (int i = 0; i < position; i++) {
        searchInput.sendKeys(Keys.ARROW_DOWN);
      }
      searchInput.sendKeys(Keys.ENTER);
      PageObjectLogging.log("ArrowDownToSuggestion", "arrowed down to desired suggestion"
                                                     + suggestionText + "and clicked enter", true);
      return new ArticlePageObject();
    } else {
      return null;
    }
  }

  public void typeQuery(String query) {
    wait.forElementVisible(searchInput);
    searchInput.clear();
    searchInput.sendKeys(query);
    PageObjectLogging.log("typeQuery", "typed query: " + query, true);
  }

  public IntraWikiSearchPageObject searchFor(String query) {
    PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
    typeQuery(query);
    return clickSearchButton();
  }

  public IntraWikiSearchPageObject clickSearchButton() {
    wait.forElementClickable(searchSubmit);
    searchSubmit.click();
    PageObjectLogging.log("clickSearchButton", "clicked on search button", true);
    return new IntraWikiSearchPageObject(driver);
  }

  /**
   * Returns article page object if invoked by user with goSearch preference turned on
   */
  public ArticlePageObject goSearchFor(String query) {
    searchInput.sendKeys(query);
    searchSubmit.click();
    PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
    return new ArticlePageObject();
  }

  public AttachedSignInPage clickOnSignIn(){
    myAccount.click();
    signInLink.click();
    return new AttachedSignInPage();
  }

  public AttachedRegisterPage clickOnRegister(){
    myAccount.click();
    registerLink.click();
    return new AttachedRegisterPage();
  }
}
