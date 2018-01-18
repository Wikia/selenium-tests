package com.wikia.webdriver.elements.oasis.components.navigation;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NestedNavigation extends BasePageObject {

  @FindBy(xpath = "/html/body/div[3]/header/nav/ul/li[2]/div")
  private WebElement communityButton;
  @FindBy(xpath = "/html/body/div[3]/header/nav/ul/li[2]/div/div[2]/ul/li[3]")
  private WebElement scrollTest3Button;
  @FindBy(xpath = "/html/body/div[3]/header/nav/ul/li[2]/div/div[2]/ul/li[3]/div/ul/li[8]")
  private WebElement cat_3Button;

  public NestedNavigation openCommunity() {
    wait.forElementVisible(communityButton);
    hover(communityButton);
    return this;
  }

  public NestedNavigation openScrollTest3() {
    new Actions(driver).moveToElement(scrollTest3Button).perform();

    return this;
  }

  public NestedNavigation clickCat_3() {
    new Actions(driver).click(cat_3Button).perform();

    return this;
  }
}
