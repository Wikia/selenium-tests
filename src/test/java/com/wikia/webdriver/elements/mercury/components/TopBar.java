package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class TopBar extends BasePageObject {

  @FindBy(css = ".wds-global-navigation")
  private WebElement navBar;

  @FindBy(css = ".wds-global-navigation__modal-control-anon")
  private WebElement userAvatar;

  @FindBy(css = ".wds-global-navigation__logo-image")
  private WebElement logoFandom;

  @FindBy(css = ".wds-global-navigation__modal-control-search")
  private WebElement searchIcon;

  @FindBy(css = ".wds-global-navigation__modal-control-search > svg.wds-icon")
  private WebElement searchIconClickableLink;

  @FindBy(css = ".wds-global-navigation__modal-control-close")
  private WebElement closeButton;

  @FindBy(css = ".wds-global-navigation__modal-control-search")
  private WebElement navMenu;

  @FindBy(css = ".wds-global-navigation__search-input-wrapper")
  private WebElement searchInput;

  @FindBy(css = ".wds-global-navigation__search-suggestions .wds-global-navigation__dropdown-link")
  private List<WebElement> searchSuggestions;

  private By navigationComponent = By.cssSelector(".wds-search-modal");
  private By parentBy = By.xpath("./..");

  public Navigation openNavigation() {
    Log.info("Open navigation");
    wait.forElementVisible(navMenu).click();
    Log.info("Navigation is opened");
    return new Navigation();
  }

  public SignInPage clickOnAvatar() {
    Log.info("Wait and click on avatar");
    wait.forElementVisible(userAvatar).click();

    return new AttachedSignInPage();
  }

  public String typeInDesktopSearchAndSelectSuggestion(String query, int suggestionIndex) {
    wait.forElementVisible(searchInput);
    searchInput.sendKeys(query);

    WebElement selectedSearchSuggestion = searchSuggestions.get(suggestionIndex);
    wait.forElementClickable(selectedSearchSuggestion);

    String selectedSearchSuggestionText = selectedSearchSuggestion.getText();
    selectedSearchSuggestion.click();

    return selectedSearchSuggestionText;
  }

  public Search openSearch() {
    Log.info("Open search");
    wait.forElementClickable(searchIcon);
    searchIcon.click();

    Log.info("Search is opened");
    wait.forElementVisible(navigationComponent);

    return new Search();
  }

  public Navigation clickCloseButton() {
    // Clicking on the inner element doesn't always work so we click the parent (<svg>) instead

    wait.forElementClickable(closeButton);
    closeButton.click();

    return new Navigation();
  }

  public void clickFandomLogo() {
    Log.info("Click Wikia logoFandom");
    wait.forElementClickable(logoFandom).click();
  }


  public boolean isNavigationBarVisible() {
    try {
      return navBar.isDisplayed();
    } catch (NoSuchElementException e) {
      Log.info(e.getMessage());
      return false;
    }
  }

  public boolean isLogoVisible() {
    try {
      return logoFandom.isDisplayed();
    } catch (NoSuchElementException e) {
      Log.info(e.getMessage());
      return false;
    }
  }


  public boolean isSearchIconVisible() {
  return wait.forElementVisible(searchIcon).isDisplayed();
  }

  public boolean isSearchIconClickable() {
    try {
      searchIconClickableLink.isDisplayed();
      wait.forElementClickable(searchIconClickableLink, 0);
      return true;
    } catch (NoSuchElementException e) {
      Log.info(e.getMessage());
      return false;
    } catch (TimeoutException e) {
      Log.info(e.getMessage());
      return false;
    }
  }

  public boolean isCloseIconVisible() {
    try {
      return closeButton.isDisplayed();
    } catch (NoSuchElementException e) {
      Log.info(e.getMessage());
      return false;
    }
  }
}
