package com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropDownComponentObject extends WikiBasePageObject {

  private static final String LOGIN_DROPDOWN_TRIGGER_CSS = "#AccountNavigation";

  @FindBy(id = "exploreWikiaDropdown")
  private WebElement exploreWikiaDropdown;

  public DropDownComponentObject(WebDriver driver) {
    super();
  }

  /**
   * Open dropdown - we need to move mouse outside the element and move back to element to trigger
   * the event
   */
  public DropDownComponentObject openDropDown() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, 20, 2000).until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
          if (!driver.findElement(By.cssSelector(LOGIN_DROPDOWN_TRIGGER_CSS)).getAttribute("class")
                  .contains("active")) {
            ((JavascriptExecutor) driver)
                    .executeScript("$j('.ajaxLogin .avatar-container').trigger('click')");
            return false;
          }
          return true;
        }
      });
    } finally {
      restoreDeaultImplicitWait();
    }

    PageObjectLogging.log("DropdownVisible", "Login dropdown is visible", true, driver);

    return this;
  }

  public DropDownComponentObject openDropDownWithEntryPoint(final WebElement entryPoint) {
    try {
      wait.forElementClickable(entryPoint);
      PageObjectLogging.log("DropdownClickable", "Dropdown is clickable", true, driver);
      entryPoint.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.log("DropdownClickable", "Dropdown is not clickable", false, driver);
    }

    return this;
  }

  public List<WebElement> getAllLinksInExploreWikiaDropdown() {
    return exploreWikiaDropdown.findElements(By.cssSelector("a"));
  }
}
