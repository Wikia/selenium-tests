package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpamWikiReviewPage extends BasePageObject {

  private static final String SPAM_WIKI_REVIEW_SERVICE_NAME = "spam-wiki-review";
  private static final String VIEW_WIKIS_SUBPAGE = "/wiki";

  public static enum LANGUAGE_CODE {
    all, en, ar, de, es, fi, fr, id, hi, hu, it, ja, ka, kn, ko, nl, pt, pl, ru, uk, vi, zh, other
  }

  @FindBy(xpath = "//button[contains(text(),'Select language of Wikis')]")
  private WebElement selectLanguageButton;

  @FindBy(xpath = "//table[@class='table table-hover']//tbody")
  private WebElement displayedWikisTable;

  public SpamWikiReviewPage open() {
    getUrl(urlBuilder.getUrlForService(SPAM_WIKI_REVIEW_SERVICE_NAME)+VIEW_WIKIS_SUBPAGE);
    return this;
  }

  /**
   * This selects filtering of displayed wikis with selectLanguagesOfWikis button
   * and loads a new page with the filter applied
   * @param language one of LANGUAGE_CODE enum
   */
  public SpamWikiReviewPage selectLanguageOfWikis(LANGUAGE_CODE language){
    selectLanguageButton.click();
    selectLanguageButton
        .findElement(By.xpath(String.format("//a[contains(text(),'%s')]", language)))
        .click();

    return this;
  }

  /**
   * This gets a list of WebElements containing Rows of displayed table with Wikis' info
   * excludes thead, header of the table
   */
  public List<WebElement> getListDisplayedWikisTableRows() {
    return displayedWikisTable.findElements(By.xpath("./tr"));
  }

  /**
   * This gets a WebElement containing nth Row of displayed table with Wiki information
   * excludes thead, header of the table
   */
  public WebElement getNthDisplayedWikisTableRow(int wikiaRowNumber) {
    List<WebElement> allRows = getListDisplayedWikisTableRows();
    return allRows.get(wikiaRowNumber);
  }
}
