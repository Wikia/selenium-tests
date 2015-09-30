package com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.interactions.Typing;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

public class NavigationBar extends WikiBasePageObject {

  final private String suggestionCss = ".autocomplete div";

  @FindBy(css = "#searchInput")
  private WebElement searchInput;
  @FindBy(css = "#searchSubmit")
  private WebElement searchSubmit;
  @FindBys(@FindBy(css = suggestionCss))
  private List<WebElement> suggestionsList;

  private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");

  public NavigationBar(WebDriver driver) {
    super(driver);
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
      LOG.success("ArrowDownToSuggestion", "arrowed down to desired suggestion" + suggestionText
          + "and clicked enter");
      return new ArticlePageObject(driver);
    } else {
      return null;
    }
  }

  /**
   * click on desired suggestion
   */
  public ArticlePageObject clickSuggestion(String suggestion) {
    wait.forElementVisible(suggestionsList.get(0));
    for (int i = 0; i < suggestionsList.size(); i++) {
      WebElement currentSuggestion = suggestionsList.get(i);
      if (currentSuggestion.getText().contains(suggestion)) {
        currentSuggestion.click();
        LOG.result("clickSuggestion", "clicked on desired suggestion" + suggestion, true);
        return new ArticlePageObject(driver);
      }
    }
    LOG.error("clickSuggestion", "didn't find suggestion: " + suggestion);
    return null;
  }

  public void typeQuery(String query) {
    wait.forElementVisible(searchInput);
    searchInput.clear();
    searchInput.sendKeys(query);
    LOG.success("typeQuery", "typed query: " + query);
  }

  public IntraWikiSearchPageObject searchFor(String query) {
    LOG.success("searchFor", "searching for query: " + query, true);
    typeQuery(query);
    return clickSearchButton();
  }

  public IntraWikiSearchPageObject clickEnterToSearch() {
    wait.forElementClickable(searchInput);
    searchInput.sendKeys(Keys.ENTER);
    LOG.success("clickEnterInSearch", "clicked enter in search");
    return new IntraWikiSearchPageObject(driver);
  }

  public IntraWikiSearchPageObject clickSearchButton() {
    wait.forElementClickable(searchSubmit);
    searchSubmit.click();
    LOG.success("clickSearchButton", "clicked on search button");
    return new IntraWikiSearchPageObject(driver);
  }

  /**
   * Returns article page object if invoked by user with goSearch preference turned on
   */
  public ArticlePageObject goSearchFor(String query) {
    searchInput.sendKeys(query);
    searchSubmit.click();
    LOG.success("searchFor", "searching for query: " + query, true);
    return new ArticlePageObject(driver);
  }

  public void setUpTracking() {
    jsActions.execute(ClickTrackingScriptsProvider.REDIRECT_BLOCK);
    jsActions.execute(ClickTrackingScriptsProvider.TRACKER_INSTALLATION);
  }
}
