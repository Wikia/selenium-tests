package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

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

  public SpecialWhatLinksHerePageObject clickShowButton() {
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

  public String getWhatLinksHereArticleName(int index) {
    wait.forElementVisible(whatLinksList.get(index));
    return whatLinksList.get(index).getText();
  }
}
