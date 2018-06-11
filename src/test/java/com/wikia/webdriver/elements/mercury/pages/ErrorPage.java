package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage extends WikiBasePageObject {

  @FindBy(css = ".wiki-page-error a")
  private WebElement linkToOpenNavigation;

  @FindBy(css = ".nav-menu")
  private WebElement navMenu;

  private Navigate navigate;

  public ErrorPage() {
    super();

    navigate = new Navigate();
  }

  public ErrorPage navigateToErrorPageFromUrl() {
    navigate.toPageByPath("/wiki/404_Error_Page");
    wait.forElementVisible(linkToOpenNavigation);
    Log.info("Navigated to error 404 page");

    return this;
  }

  public Navigation openNavigationByClickOnLink() {
    wait.forElementClickable(linkToOpenNavigation);
    linkToOpenNavigation.click();

    wait.forElementVisible(navMenu);
    Log.info("Navigation is opened");

    return new Navigation();
  }
}
