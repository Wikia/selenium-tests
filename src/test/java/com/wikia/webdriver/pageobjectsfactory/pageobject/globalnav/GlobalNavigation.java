package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import com.wikia.webdriver.common.core.ElementStateHelper;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class GlobalNavigation extends BasePageObject {

  @FindBy(css = ".wikia-logo-container .wikia-logo")
  private WebElement wikiaLogo;

  @FindBy(css = "#searchSelect")
  private WebElement searchSelect;

  @FindBy(css = "#searchInput")
  private WebElement searchInput;

  @FindBy(id = "exploreWikiaEntryPoint")
  private WebElement exploreWikiaDropdownEntryPoint;

  @FindBy(id = "exploreWikiaDropdown")
  private WebElement exploreWikiaDropdown;

  @FindBy(css = ".global-navigation .hubs-links a")
  private List<WebElement> hubsLinks;

  @FindBy(css = ".wikia-logo__subtitle")
  private WebElement fandomLogo;

  private DropDownComponentObject accountNavigation;
  private DropDownComponentObject exploreWikiaDropdownComponent;

  public HomePage clickWikiaLogo() {
    wait.forElementVisible(wikiaLogo);
    wikiaLogo.click();

    return new HomePage();
  }

  public SearchPageObject searchGlobally(String query) {
    new Select(searchSelect).selectByValue("global");
    searchInput.sendKeys(query);
    searchInput.submit();
    return new SearchPageObject(driver);
  }

  public DropDownComponentObject openAccountNavigation() {
    return getAccountNavigation().openDropDown();
  }

  public boolean isLocalSearchDisabled() {
    return !ElementStateHelper.isElementVisible(searchSelect, driver);
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

  public boolean isUserLoggedOut() {
    return driver.findElements(By.cssSelector("a[data-id='login']")).size() > 0;
  }
}
