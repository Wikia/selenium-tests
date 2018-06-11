package com.wikia.webdriver.elements.fandom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class HeroBlock extends BasePageObject {

  @FindBy(css = "section.hero-block")
  private WebElement heroMosaic;

  public boolean isDisplayed() {
    try {
      return heroMosaic.isDisplayed();
    } catch (ElementNotFoundException e) {
      return false;
    }
  }

  public boolean hasFiveHeroUnits() {
    return heroMosaic.findElements(By.cssSelector(".hero")).size() == 5;
  }
}
