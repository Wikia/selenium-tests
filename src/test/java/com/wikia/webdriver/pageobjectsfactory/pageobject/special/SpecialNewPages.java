package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;

public class SpecialNewPages extends WikiBasePageObject {
  @FindBys(@FindBy(className = "mw-newpages-pagename"))
  private List<WebElement> newArticleLinks;

  public SpecialNewPages openSpecialNewPages(String wikiUrl) {
    getUrl(wikiUrl + "/wiki/Special:NewPages");
    PageObjectLogging.log("openSpecialNewPages", "Special:NewPages was opened", true);

    return this;
  }

  public void verifyContainsLinkToArticle(String articleTitle) {
    Assert.assertTrue(
        newArticleLinks.stream().map(WebElement::getText).anyMatch(articleTitle::equals),
        "Special:NewPages does not have link to article named " + articleTitle
    );
  }
}
