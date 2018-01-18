package com.wikia.webdriver.elements.oasis.components.navigation;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LocalNavigation extends BasePageObject{

  @FindBy(xpath = "//*[name()='svg' and @id='wds-icons-explore-tiny']/../..")
  private WebElement exploreButton;
  @FindBy(css = "a[data-tracking='explore-images']")
  private WebElement imagesButton;
  @FindBy(xpath = "//a[text()='Videos']/..")
  private WebElement videosButton;

  public LocalNavigation openExplore() {
    new Actions(driver).moveToElement(exploreButton).perform();

    return this;
  }

  public LocalNavigation clickImages() {
    new Actions(driver).click(imagesButton).perform();

    return this;
  }

  public LocalNavigation clickVidoesButton() {
    new Actions(driver).click(videosButton).perform();

    return this;
  }
}
