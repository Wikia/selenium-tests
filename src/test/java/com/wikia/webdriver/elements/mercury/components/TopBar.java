package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopBar {

  @FindBy(css = ".side-nav-toggle")
  private WebElement openNavButton;

  @FindBy(css = ".side-nav-menu__header a.close")
  private WebElement closeNavButton;

  private By navigationComponent = By.cssSelector(".side-nav-menu");

  private Wait wait;

  public TopBar(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
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
