package com.wikia.webdriver.pageobjectsfactory.pageobject.oasis;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CuratedContentToolModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by wikia on 2015-08-19.
 */
public class MainPage extends ArticlePageObject {

  @FindBy(css = "#CuratedContentTool")
  protected WebElement curatedContentToolButton;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  /*
  This method opens main page of the current wiki
   */
  public MainPage open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()));
    return this;
  }

  public CuratedContentToolModal clickCuratedContentToolButton() {
    wait.forElementVisible(curatedContentToolButton);
    curatedContentToolButton.click();
    return new CuratedContentToolModal(driver);
  }
}
