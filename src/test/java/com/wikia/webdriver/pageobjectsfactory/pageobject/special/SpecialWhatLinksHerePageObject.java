package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
    super();
    PageFactory.initElements(driver, this);
  }

  public SpecialWhatLinksHerePageObject clickShowbutton() {
    wait.forElementVisible(showButton);
    showButton.click();
    return this;
  }

  public SpecialWhatLinksHerePageObject clickPageInputField() {
    wait.forElementVisible(pageInputField);
    pageInputField.click();
    return this;
  }

  public SpecialWhatLinksHerePageObject typeInfoboxImageName(String imageFileName) {
    pageInputField.sendKeys(imageFileName);
    return this;
  }

  public SpecialWhatLinksHerePageObject verifyInfoboxArticleInList(String articleName) {
    wait.forElementVisible(filtersSection);
    String firstResult = whatLinksList.get(0).getText();
    Assertion.assertStringContains(firstResult, articleName);
    return this;
  }
}
