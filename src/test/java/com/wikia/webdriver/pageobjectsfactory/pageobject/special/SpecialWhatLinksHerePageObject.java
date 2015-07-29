package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Rodriuki on 16/06/15.
 */
public class SpecialWhatLinksHerePageObject extends SpecialPageObject {
  @FindBy(css = "input[name=target]")
  private WebElement pageInputField;
  @FindBy(css = ".namespaceselector + input[type=submit]")
  private WebElement showButton;
  @FindBy(css = "#mw-whatlinkshere-list")
  private List<WebElement> whatLinksList;
  @FindBy(css = "#mw-content-text > fieldset > legend")
  private WebElement filtersSection;

  public SpecialWhatLinksHerePageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void clickShowbutton() {
    waitForElementByElement(showButton);
    showButton.click();
  }

  public void clickPageInputField() {
    waitForElementByElement(pageInputField);
    pageInputField.click();
  }

  public void typeInfoboxImageName(String imageFileName) {
    pageInputField.sendKeys(imageFileName);
  }

  public void verifyInfoboxArticleInList(String articleName) {
    waitForElementByElement(filtersSection);
    String firstResult = whatLinksList.get(0).getText();
    Assertion.assertStringContains(firstResult, articleName);
  }
}
