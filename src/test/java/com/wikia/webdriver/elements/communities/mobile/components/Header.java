package com.wikia.webdriver.elements.communities.mobile.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePageObject {

  @FindBy(css = ".ember-application .wiki-page-header")
  private WebElement header;

  @FindBy(css = ".ember-application .wiki-page-header__title")
  private WebElement pageTitle;

  public String getPageTitle() {
    return this.pageTitle.getText();
  }

  public boolean isHeaderVisible() {
    try {
      return header.isDisplayed();
    } catch (TimeoutException | ElementNotFoundException e) {
      return false;
    }
  }

  public void waitForLoaded() {
    wait.forElementVisible(header);
  }

  public boolean isPageTitleVisible() {
    try {
      return pageTitle.isDisplayed();
    } catch (TimeoutException | ElementNotFoundException e) {
      return false;
    }
  }

  public boolean isHeroImageVisible() {
    try {
      return header.getAttribute("class").contains("has-hero-image");
    } catch (TimeoutException | ElementNotFoundException e) {
      return false;
    }
  }
}
