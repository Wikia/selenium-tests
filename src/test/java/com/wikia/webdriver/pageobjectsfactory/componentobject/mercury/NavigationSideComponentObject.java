package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class NavigationSideComponentObject extends BasePageObject {

  @FindBy(css = ".ember-text-field")
  private WebElement searchInput;
  @FindBy(css = ".cancel")
  private WebElement cancelSearchCaption;
  @FindBy(css = ".local-wikia-search a")
  private List<WebElement> searchSuggestions;
  @FindBy(css = ".local-nav-menu > li > div")
  private List<WebElement> navList;
  @FindBy(css = ".back")
  private WebElement backChevron;
  @FindBy(css = ".overlay")
  private WebElement overlay;
  @FindBy(css = ".local-wikia-search")
  private WebElement resultField;
  @FindBy(css = ".local-nav-menu")
  private WebElement menuField;
  @FindBy(css = ".nav")
  private WebElement searchButton;
  @FindBy(css = "nav.side-nav")
  private WebElement menuView;

  public NavigationSideComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickSearchField() {
    waitForElementByElement(searchInput);
    searchInput.click();
  }

  public void clickSearchButton() {
    waitForElementVisibleByElement(searchButton);
    searchButton.click();
  }

  public void clickBackChevron() {
    waitForElementVisibleByElement(backChevron);
    backChevron.click();
  }

  public void clickCancelButton() {
    waitForElementVisibleByElement(cancelSearchCaption);
    cancelSearchCaption.click();
  }

  public void clickSuggestion(int index) {
    waitForElementByElement(searchSuggestions.get(index));
    searchSuggestions.get(index).click();
  }

  public void clickNavListElement(int index) {
    waitForElementVisibleByElement(navList.get(index));
    navList.get(index).click();
  }

  public void clickOverlay() {
    waitForElementVisibleByElement(overlay);
    tapOnElement(overlay);
  }

  public boolean isSuggestionListDisplayed() {
    try {
      if (searchSuggestions.size() > 0) {
        waitForElementByElement(searchSuggestions.get(0));
        return searchSuggestions.get(0).isDisplayed();
      }
    } catch (NoSuchElementException e) {
    }
    return false;
  }

  public boolean isNavMenuVisible() throws WebDriverException {
    if (menuView.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !menuView.getAttribute("class").contains("collapsed");
  }

  public boolean isBackLinkDisplayed() {
    try {
      return backChevron.isDisplayed();
    } catch (NoSuchElementException e) {
    }
    return false;
  }

  public boolean isNavListElementEllipsized(int index) {
    waitForElementVisibleByElement(navList.get(index));
    return navList.get(index).getCssValue("text-overflow").equals("ellipsis");
  }

  public boolean isMenuFieldVisible() {
    return menuField.getCssValue("visibility").equals("visible");
  }

  public boolean isResultFieldVisible() {
    return resultField.getCssValue("visibility").equals("visible");
  }

  public void typeInSearchField(String content) {
    waitForElementVisibleByElement(searchInput);
    searchInput.sendKeys(content);
  }
}
