package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Implements <a href="https://www.mediawiki.org/wiki/Help:New_pages">Special:NewPages</a>
 *
 * This is a special page that lists recently created pages in reverse order of creation,
 * with creation date and time, current size, user who created the page, and first edit summary.
 * It also provides links to patrol these new pages when that feature is enabled.
 */
public class SpecialNewPages extends WikiBasePageObject {

  @FindBy(className = "mw-newpages-pagename")
  private List<WebElement> newArticleLinks;

  public void openSpecialNewPages() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
           + "Special:NewPages");
  }

  /**
   * Checks if the list of new pages contains a link to a specific named article
   * @param articleTitle the title to search for
   * @return whether there is a link to this article in the list
   */
  public boolean containsLinkToArticle(String articleTitle) {
    return newArticleLinks.stream().map(WebElement::getText).anyMatch(articleTitle::equals);
  }
}
