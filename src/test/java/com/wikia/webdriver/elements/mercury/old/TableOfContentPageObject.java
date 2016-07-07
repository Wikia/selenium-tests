package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TableOfContentPageObject {

  @FindBy(css = "nav.table-of-contents")
  private WebElement tocAll;
  @FindBy(css = "nav.table-of-contents a")
  private List<WebElement> listOfLinks;
  @FindBy(css = "nav.table-of-contents ol")
  private WebElement TOCMenu;
  @FindBy(css = "nav.table-of-contents div")
  private WebElement tocButton;
  @FindBy(xpath = "//section[contains(@class, 'article-body')]/article[contains(@class, 'article-content')]/*[1]")
  private WebElement tocAtTheTop;
  @FindBy(xpath = "//section[contains(@class, 'article-body')]/article[contains(@class, 'article-content')]/aside[contains(@class, 'portable-infobox')][position() = 1]/following-sibling::*[2]")
  private WebElement tocBelowFirstAdSlot;
  @FindBy(css = "nav.table-of-contents li")
  private List<WebElement> tocItems;

  private String section_selector = "section.article-body h2[section=\"__index__\"]";

  private WebDriver driver;
  private Wait wait;

  public TableOfContentPageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public boolean isTOCDisplayed() {
    try {
      wait.forElementVisible(tocAll, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTOCAtTheTopOfTheArticle() {
    wait.forElementVisible(tocAtTheTop);
    return tocAtTheTop.isDisplayed() &&
           tocAtTheTop.findElement(By.cssSelector("nav.table-of-contents")).isDisplayed();
  }

  public boolean isTOCBelowFirstAdSlot() {
    wait.forElementVisible(tocBelowFirstAdSlot);
    return tocBelowFirstAdSlot.isDisplayed() &&
           tocBelowFirstAdSlot.findElement(By.cssSelector("nav.table-of-contents")).isDisplayed();
  }

  public void clickOnTOC() {
    wait.forElementVisible(tocButton);
    tocButton.click();
  }

  public void clickOnTOCListElement(int index) {
    wait.forElementVisible(listOfLinks.get(index));
    listOfLinks.get(index).click();
  }

  public boolean isUserMovedToSectionByIndex(String sectionIndex) {
    By sectionBy = By.cssSelector(section_selector.replace("__index__", sectionIndex));
    WebElement section = driver.findElement(sectionBy);
    wait.forElementVisible(section);
    return true;
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
