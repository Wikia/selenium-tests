package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.ElementStateHelper;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

public class GlobalNavigation extends BasePageObject {

  @FindBy(css = ".gamestar-logo")
  private WebElement gameStarLink;

  @FindBy(css = ".wikia-logo")
  private WebElement wikiaLogo;

  @FindBy(css = "#searchSelect")
  private WebElement searchSelect;

  @FindBy(css = "#searchInput")
  private WebElement searchInput;

  @FindBy(id = "exploreWikiaEntryPoint")
  private WebElement exploreWikiaDropdownEntryPoint;

  @FindBy(id = "exploreWikiaDropdown")
  private WebElement exploreWikiaDropdown;

  @FindBy(css = ".global-navigation-2016 .hubs-links a")
  private List<WebElement> hubsLinks;

  @FindBy(css = ".wikia-logo__subtitle")
  private WebElement fandomLogo;

  private DropDownComponentObject accountNavigation;
  private DropDownComponentObject exploreWikiaDropdownComponent;

  public boolean isGameStarLogoDisplayed() {
    return ElementStateHelper.isElementVisible(gameStarLink, driver);
  }

  public HomePage clickWikiaLogo() {
    String environment = Configuration.getEnv();
    if (!"prod".equals(environment) && !environment.contains("dev")) {
      WebDriverWait wait = new WebDriverWait(driver, 5);
      wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(wikiaLogo, "href",
          environment));
    }

    wikiaLogo.click();
    return new HomePage();
  }

  public SearchPageObject searchGlobally(String query) {
    new Select(searchSelect).selectByValue("global");
    searchInput.sendKeys(query);
    searchInput.submit();
    return new SearchPageObject(driver);
  }

  public SignUpPageObject signUp() {
    return getAccountNavigation().openDropDown().clickSignUpLink();
  }

  public DropDownComponentObject openAccountNavigation() {
    return getAccountNavigation().openDropDown();
  }

  public DropDownComponentObject logOut() {
    return getAccountNavigation().openDropDown().clickLogOut();
  }

  public boolean isLocalSearchDisabled() {
    return !ElementStateHelper.isElementVisible(searchSelect, driver);
  }

  public boolean isUserLoggedOut() {
    return driver.findElements(By.cssSelector(".avatar-container")).size() > 0;
  }

  private DropDownComponentObject getAccountNavigation() {
    if (accountNavigation == null) {
      accountNavigation = new DropDownComponentObject(driver);
    }
    return accountNavigation;
  }

  private DropDownComponentObject getExploreWikiaDropdownComponent() {
    if (exploreWikiaDropdownComponent == null) {
      exploreWikiaDropdownComponent = new DropDownComponentObject(driver);
    }

    return exploreWikiaDropdownComponent;
  }

  public DropDownComponentObject openExploreWikiaDropdown() {
    return getExploreWikiaDropdownComponent()
        .openDropDownWithEntryPoint(exploreWikiaDropdownEntryPoint);
  }

  public List<String> getDropdownLinks() {
    List<String> linksLabels = new ArrayList<>();
    List<WebElement> linksInDropdown =
        exploreWikiaDropdownComponent.getAllLinksInExploreWikiaDropdown();

    for (WebElement link : linksInDropdown) {
      if (link.isDisplayed()) {
        linksLabels.add(link.getText());
      }
    }

    return linksLabels;
  }

  public void closeDropdown() {
    driver.executeScript("arguments[0].classList.remove('active')", exploreWikiaDropdown);
  }

  public boolean areHubsLinksVisible() {
    for (WebElement hubLink : hubsLinks) {
      if (!hubLink.isDisplayed()) {
        return false;
      }
    }

    return true;
  }

  public boolean isFandomLogoVisible() {
    return fandomLogo.isDisplayed();
  }
}
