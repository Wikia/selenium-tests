package com.wikia.webdriver.pageobjectsfactory.componentobject.article;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;

public class OasisFooterComponentObject extends WikiBasePageObject {

  private JavascriptActions js;

  public OasisFooterComponentObject(WebDriver driver) {
    super();

    js = new JavascriptActions(driver);
  }

  public ArticlePageObject clickMobileSiteLink() {
    js.execute("$('.global-footer a[href=\"#\"]').click()");
    return new ArticlePageObject(driver);
  }
}
