package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialContributionsPageObject extends SpecialPageObject {

  @FindBy(css = ".mw-contributions-table #user")
  private WebElement userNameRadio;
  @FindBy(css = "[name='target']")
  private WebElement userNameField;
  @FindBy(css = ".mw-contributions-table [type='submit']")
  private WebElement searchButton;

  public SpecialContributionsPageObject(WebDriver driver) {
    super();
  }

  private void selectContributorUserName() {
    wait.forElementVisible(userNameRadio);
    userNameRadio.click();
    PageObjectLogging.log("selectContributorUserName", "by username selected", true);
  }

  private void typeInUserName(String userName) {
    wait.forElementVisible(userNameField);
    userNameField.sendKeys(userName);
    PageObjectLogging.log("typeInUserName", userName + " username typed in", true);
  }

  private void clickSearchButton() {
    wait.forElementVisible(searchButton);
    scrollAndClick(searchButton);
    PageObjectLogging.log("clickSearchButton", "search button clicked", true);
  }

  public void searchContributions(String userName) {
    selectContributorUserName();
    typeInUserName(userName);
    clickSearchButton();
  }

  public void verifyNewPageOnList(String pageName, String pageContent) {
    wait.forElementVisible(By.xpath(
        "//a[@title='" + pageName + "' and contains(text(), '" + pageName + "')]"));
    wait.forElementVisible(By.xpath(
        "//span[@class='comment' and contains(text(), '(Created page with \"" + pageContent
        + "\")')]"));
    PageObjectLogging
        .log("verifyNewPageOnList", pageName + " page verified on the contribution list", true);
  }
}
