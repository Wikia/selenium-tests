package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.LOG;

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
    super(driver);
  }

  private void selectContributorUserName() {
    wait.forElementVisible(userNameRadio);
    userNameRadio.click();
    LOG.log("selectContributorUserName", "by username selected", LOG.Type.SUCCESS);
  }

  private void typeInUserName(String userName) {
    wait.forElementVisible(userNameField);
    userNameField.sendKeys(userName);
    LOG.log("typeInUserName", userName + " username typed in", LOG.Type.SUCCESS);
  }

  private void clickSearchButton() {
    wait.forElementVisible(searchButton);
    scrollAndClick(searchButton);
    LOG.log("clickSearchButton", "search button clicked", LOG.Type.SUCCESS);
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
    LOG
        .logResult("verifyNewPageOnList", pageName + " page verified on the contribution list",
                   true);
  }
}
