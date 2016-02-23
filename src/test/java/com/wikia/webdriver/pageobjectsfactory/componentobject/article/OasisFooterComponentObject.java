package com.wikia.webdriver.pageobjectsfactory.componentobject.article;

import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OasisFooterComponentObject extends WikiBasePageObject {

  @FindBy(css = ".global-footer a[href='#']")
  private WebElement mobileSiteLink;

  public OasisFooterComponentObject(WebDriver driver) {
    super();
  }

  public ArticlePageObject clickMobileSiteLink() {
    waitAndClick(mobileSiteLink);
    return new ArticlePageObject(driver);
  }
}

