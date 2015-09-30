package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;

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
    LOG.success("selectContributorUserName", "by username selected");
  }

  private void typeInUserName(String userName) {
    wait.forElementVisible(userNameField);
    userNameField.sendKeys(userName);
    LOG.success("typeInUserName", userName + " username typed in");
  }

  private void clickSearchButton() {
    wait.forElementVisible(searchButton);
    scrollAndClick(searchButton);
    LOG.success("clickSearchButton", "search button clicked");
  }

  public void searchContributions(String userName) {
    selectContributorUserName();
    typeInUserName(userName);
    clickSearchButton();
  }

  public void verifyNewPageOnList(String pageName, String pageContent) {
    wait.forElementVisible(By.xpath("//a[@title='" + pageName + "' and contains(text(), '"
        + pageName + "')]"));
    wait.forElementVisible(By
        .xpath("//span[@class='comment' and contains(text(), '(Created page with \"" + pageContent
               + "\")')]"));
    LOG.result("verifyNewPageOnList", pageName + " page verified on the contribution list", true);
  }
}
