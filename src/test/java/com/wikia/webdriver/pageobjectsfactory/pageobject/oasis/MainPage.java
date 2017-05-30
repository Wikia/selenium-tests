package com.wikia.webdriver.pageobjectsfactory.pageobject.oasis;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CuratedContentToolModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends ArticlePageObject {

  @FindBy(css = "#CuratedContentTool")
  protected WebElement curatedContentToolButton;

  @FindBy(css = "body")
  protected WebElement body;

  public MainPage open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()));
    return this;
  }

  public CuratedContentToolModal clickCuratedContentToolButton() {
    wait.forElementClickable(curatedContentToolButton);
    scrollAndClick(curatedContentToolButton);
    return new CuratedContentToolModal(driver);
  }

  public boolean isMainPage() {
    return body.getAttribute("class").contains(" mainpage ");
  }
}
