package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage extends WikiBasePageObject {

  @FindBy(css = ".wiki-page-header__subtitle")
  private WebElement pageHeaderSubtitle;

  @FindBy(css = ".nav-menu")
  private WebElement navMenu;

  private Navigate navigate;

  public ErrorPage() {
    super();

    navigate = new Navigate();
  }

  private String errorPageSubtitle = "Sorry, we can't find the page you are looking for.";

  public ErrorPage navigateToErrorPageFromUrl() {
    navigate.toPageByPath("/wiki/404_Error_Page");
    wait.forElementVisible(pageHeaderSubtitle);
    Assertion.assertEquals(pageHeaderSubtitle.getText(), errorPageSubtitle);
    Log.info("Navigated to error 404 page");

    return this;
  }

}
