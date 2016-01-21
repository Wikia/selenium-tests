package com.wikia.webdriver.elements.mercury;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.old.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopBar extends BasePageObject {

  @FindBy(css = ".side-nav-toggle")
  private WebElement openNavButton;

  @FindBy(css = ".side-nav-menu__header a.close")
  private WebElement closeNavButton;

  private By navigationComponent = By.cssSelector(".side-nav-menu");

  public TopBar(WebDriver driver) {
    super(driver);
  }

  public TopBar openNavigation() {
    PageObjectLogging.logInfo("Open navigation");
    wait.forElementClickable(openNavButton);
    openNavButton.click();

    PageObjectLogging.logInfo("Navigation is opened");
    wait.forElementVisible(navigationComponent);

    return this;
  }

  public TopBar closeNavigation() {
    PageObjectLogging.logInfo("Close navigation");
    wait.forElementClickable(closeNavButton);
    closeNavButton.click();

    PageObjectLogging.logInfo("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    return this;
  }
}
