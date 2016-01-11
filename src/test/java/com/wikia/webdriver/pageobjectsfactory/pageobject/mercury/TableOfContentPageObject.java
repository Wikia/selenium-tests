package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TableOfContentPageObject extends BasePageObject {

  @FindBy(css = "nav.table-of-contents")
  private WebElement tocAll;
  @FindBy(css = "nav.table-of-contents a")
  private List<WebElement> listOfLinks;
  @FindBy(css = "nav.table-of-contents ol")
  private WebElement TOCMenu;
  @FindBy(css = "nav.table-of-contents div")
  private WebElement tocButton;
  @FindBy(xpath = "//section[contains(@class, 'article-body')]/h1[position() = 1]/following-sibling::*[2]")
  private WebElement tocUnderH1;
  @FindBy(css = "nav.table-of-contents li")
  private List<WebElement> tocItems;

  public TableOfContentPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isTOCDisplayed() {
    try {
      wait.forElementVisible(tocAll, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTOCUnderArticleName() throws WebDriverException {
    wait.forElementVisible(tocUnderH1);
    return tocUnderH1.isDisplayed() && "nav".equals(tocUnderH1.getTagName());
  }

  public void clickOnTOC() {
    wait.forElementVisible(tocButton);
    tocButton.click();
  }

  public void clickOnTOCListElement(int index) {
    wait.forElementVisible(listOfLinks.get(index));
    listOfLinks.get(index).click();
  }

  public boolean isUserMovedToRightSection(int index) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String h2PosString =
        js.executeScript(
            "return Math.floor($('section.article-body h2').eq(" + index + ").offset().top)")
            .toString();
    int h2Pos = Integer.parseInt(h2PosString);
    int windowYPos =
        Integer.parseInt(js.executeScript("return $(window).scrollTop()").toString());
    return h2Pos == windowYPos;
  }

  public boolean isH2PaddingTopMoreThan(int index, int value) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String h2PaddingString =
        js.executeScript("return $('h2').eq(" + index + ").css('padding-top')").toString();
    h2PaddingString = h2PaddingString.substring(0, h2PaddingString.length() - 2);
    int h2Padding = Integer.parseInt(h2PaddingString);
    return h2Padding >= value;
  }

  public boolean isTOCMenuVisible() {
    return !"none".equals(TOCMenu.getCssValue("display"));
  }

  public void TOCItemNotContainsText(int itemIndex, String itemText) {
    Assertion.assertNotEquals(tocItems.get(itemIndex).getText(), itemText);
  }
}
