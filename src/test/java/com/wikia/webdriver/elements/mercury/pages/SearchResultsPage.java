package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends WikiBasePageObject {

  @FindBy(css = ".search-results")
  private WebElement searchResultsContainer;

  public SearchResultsPage() {
    super();
  }

  public boolean isSearchResultsPageOpen() {
    try {
      wait.forElementVisible(searchResultsContainer);
      return true;
    } catch(NoSuchElementException e) {
      return false;
    }
  }

}
