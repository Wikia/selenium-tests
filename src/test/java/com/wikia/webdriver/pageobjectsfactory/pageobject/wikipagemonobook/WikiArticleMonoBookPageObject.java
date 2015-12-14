package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipagemonobook;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BaseMonoBookPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikiArticleMonoBookPageObject extends BaseMonoBookPageObject {

  @FindBy(css = "#wpTextbox1")
  private WebElement editionArea;
  @FindBy(css = "#ca-edit a")
  private WebElement editLink;
  @FindBy(css = ".oasis-only-warning")
  private WebElement oasisOnly;

  public WikiArticleMonoBookPageObject(WebDriver driver) {
    super(driver);
  }

  public WikiArticleMonoBookPageObject open(String articleTitle) {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
        + articleTitle);
    return this;
  }

  public void clickEdit() {
    scrollAndClick(editLink);
    PageObjectLogging.log("clickEdit", "click on Edit link", true);
  }

  public void verifyEditionArea() {
    wait.forElementVisible(editionArea);
    PageObjectLogging.log("verifyEditArea", "verify that edition area is present", true);
  }

  public void verifyOasisOnly() {
    wait.forElementVisible(oasisOnly);
    PageObjectLogging.log("verifyOasisOnly", "Oasis only warning is present", true);
  }
}
