package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.ElementStateHelper;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HubBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

public class VenusGlobalNavPageObject {

  private static final String HUBS_XPATH_FORMAT =
      "//a[./span[@class='label'][contains(text(),'%s')]]";

  @FindBy(css = ".hubs-entry-point")
  private WebElement menuButton;

  @FindBy(css = "#hubs")
  private WebElement hubsMenu;

  @FindBy(css = "#hubs .hub-list")
  private WebElement hubsMenuList;

  @FindBy(css = ".gamestar-logo")
  private WebElement gameStarLink;

  @FindBy(css = ".wikia-logo")
  private WebElement wikiaLogo;

  @FindBy(css = "#searchSelect")
  private WebElement searchSelect;

  @FindBy(css = "#searchInput")
  private WebElement searchInput;

  private WebDriver driver;
  private Wait wait;

  private DropDownComponentObject accountNavigation;

  public VenusGlobalNavPageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(this.driver, this);
  }

  public HubBasePageObject openHub(Hub hub) {
    openHubsMenu();

    new Actions(driver).moveToElement(getDestinationHub(hub)).perform();

    new WebDriverWait(driver, 5, 150).until(CommonExpectedConditions
        .valueToBePresentInElementsAttribute(getDestinationHub(hub), "class", "active"));
    String expectedHref = getHubLink(getDestinationHub(hub));
    getDestinationHub(hub).click();

    new WebDriverWait(driver, 30).until(ExpectedConditions.urlToBe(expectedHref));

    return new HubBasePageObject(driver);
  }

  private WebElement getDestinationHub(Hub hub) {
    return wait.forElementPresent(By.xpath(String.format(HUBS_XPATH_FORMAT, hub.getLabelText())));
  }

  public String getHubLink(WebElement hub) {
    return hub.getAttribute("href");
  }

  private VenusGlobalNavPageObject openHubsMenu() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, 20, 2000).until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
          try {
            if (!hubsMenu.isDisplayed()) {
              ((JavascriptExecutor) driver)
                  .executeScript("$j('.hubs-menu-wrapper').trigger('click')");
              return false;
            }
            return true;
          } catch (StaleElementReferenceException e) {
            return false;
          }
        }
      });
      return this;
    } finally {
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
  }

  public boolean isGameStarLogoDisplayed() {
    return ElementStateHelper.isElementVisible(gameStarLink, driver);
  }

  public HomePageObject clickWikiaLogo() {
    String environment = Configuration.getEnv();
    if (!"prod".equals(environment) && !environment.contains("dev")) {
      WebDriverWait wait = new WebDriverWait(driver, 5);
      wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(wikiaLogo, "href",
          environment));
    }

    wikiaLogo.click();
    return new HomePageObject(driver);
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

  public DropDownComponentObject openAccountNAvigation() {
    return getAccountNavigation().openDropDown();
  }

  public DropDownComponentObject logOut() {
    return getAccountNavigation().openDropDown().clickLogOut();
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

  public enum Hub {
    COMICS("Comics"), TV("TV"), MOVIES("Movies"), MUSIC("Music"), BOOKS("Books"), GAMES("Games"), LIFESTYLE(
        "Lifestyle");

    private final String labelText;

    Hub(String labelText) {
      this.labelText = labelText;
    }

    public String getLabelText() {
      return labelText;
    }
  }
}
