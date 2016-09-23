package com.wikia.webdriver.pageobjectsfactory.pageobject.search;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPagePageObject extends WikiBasePageObject {

  @FindBy(css = ".SearchInput input#search-v2-input")
  private WebElement searchField;

  @FindBy(css = "button#search-v2-button")
  private WebElement searchButton;

  @FindBy (css = ".exact-wiki-match__result")
  private WebElement relatedCommunityModule;

  public SearchPagePageObject() {
    super();
  }

  public SearchPagePageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.SPECIAL_SEARCH_PAGE);

    return this;
  }

  public SearchPagePageObject typeInCommunityName() {

    searchField.click();
    searchField.sendKeys("pokemon");
    return this;
  }

  public void clickSearchButton() {
    searchButton.click();
  }

  public boolean isRelatedCommunityModulePresent() {
    return relatedCommunityModule.isDisplayed();
  }

}
