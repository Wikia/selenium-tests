package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ContentReviewModule;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ludwik on 2015-09-25.
 */
public class SpecialJsPage extends WikiBasePageObject {

  @FindBy(css = ".source-javascript")
  private WebElement scriptArea;

  private ContentReviewModule contentReviewModule;

  public SpecialJsPage() {
    super();
  }

  /**
   * Open article with name that is the following combination: TEST CLASS NAME + TEST METHOD NAME
   */
  public SpecialJsPage open() {
    getUrl(urlBuilder.getUrlForWikiPage(String.format("mediawiki:%s.js",
                                                      TestContext.getCurrentMethodName()
    )));

    return this;
  }

  public SpecialJsPage open(String articleTitle) {
    getUrl(urlBuilder.getUrlForWikiPage(String.format("mediaWiki:%s.js", articleTitle)));

    return this;
  }

  public String getScriptContent() {
    wait.forElementVisible(scriptArea);

    return scriptArea.getText();
  }

  public ContentReviewModule getReviewModule() {
    if (contentReviewModule == null) {
      contentReviewModule = new ContentReviewModule();
    }
    return contentReviewModule;
  }
}
