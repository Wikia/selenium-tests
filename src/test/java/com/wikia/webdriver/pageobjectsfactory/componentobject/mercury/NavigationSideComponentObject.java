package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class NavigationSideComponentObject extends MercuryBasePageObject {

  @FindBy(css = ".ember-text-field")
  private WebElement searchInput;
  @FindBy(css = ".cancel")
  private WebElement cancelSearchCaption;
  @FindBy(css = ".local-wikia-search a")
  private List<WebElement> searchSuggestions;
  @FindBy(css = ".local-nav-menu > li > div")
  private List<WebElement> chevrons;
  @FindBy(css = ".local-nav-menu > li > a")
  private List<WebElement> noChevrons;
  @FindBy(css = ".back")
  private WebElement backChevron;
  @FindBy(css = ".overlay")
  private WebElement overlay;
  @FindBy(css = "a[href='/wiki/Special:Random']")
  private WebElement randomPage;
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

  public void clickRandomPage() {
    waitForElementVisibleByElement(randomPage);
    randomPage.click();
  }

  public void clickSuggestion(int index) {
    waitForElementByElement(searchSuggestions.get(index));
    searchSuggestions.get(index).click();
  }

  public void clickLinkWithoutChevron(int index) {
    waitForElementVisibleByElement(noChevrons.get(index));
    noChevrons.get(index).click();
  }

  public void clickLinkWithChevron(int index) {
    waitForElementVisibleByElement(chevrons.get(index));
    chevrons.get(index).click();
  }

  public void clickOverlay() {
    waitForElementVisibleByElement(overlay);
    tapOnElement(overlay);
  }

  public boolean isSuggestionListDisplayed() {
    try {
      waitForElementByElement(searchSuggestions.get(0));
      if (searchSuggestions.get(0).isDisplayed()) {
        return true;
      }
      return false;
    } catch (NoSuchElementException|IndexOutOfBoundsException e) {}
    return false;
  }

  public boolean isNavMenuVisible() {
    if (menuView.getAttribute("class").contains("collapsed")) {
      return false;
    }
    return true;
  }

  public boolean isBackLinkDisplayed() {
    try {
      if (backChevron.isDisplayed()) {
        return true;
      }
      return false;
    } catch (NoSuchElementException e) {}
    return false;
  }

  public boolean isLinkWithChevronEllipsized(int index) {
    waitForElementVisibleByElement(chevrons.get(index));
    if (chevrons.get(index).getCssValue("text-overflow").equals("ellipsis")) {
      return true;
    }
    return false;
  }

  public boolean isLinkWithoutChevronEllipsized(int index) {
    waitForElementVisibleByElement(noChevrons.get(index));
    if (noChevrons.get(index).getCssValue("text-overflow").equals("ellipsis")) {
      return true;
    }
    return false;
  }

  public boolean isMenuFieldVisible() {
    if (menuField.getCssValue("visibility").equals("visible")) {
      return true;
    }
    return false;
  }

  public boolean isResultFieldVisible() {
    if (resultField.getCssValue("visibility").equals("visible")) {
      return true;
    }
    return false;
  }

  public void typeInSearchField(String content, int length) {
    waitForElementVisibleByElement(searchInput);
    searchInput.sendKeys(content.substring(0, length));
  }
}
