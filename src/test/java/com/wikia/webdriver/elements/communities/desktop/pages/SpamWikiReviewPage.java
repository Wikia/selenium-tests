package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;

public class SpamWikiReviewPage extends BasePageObject {

  public enum LANGUAGE_CODE {
    all, en, ar, de, es, fi, fr, id, hi, hu, it, ja, ka, kn, ko, nl, pt, pl, ru, uk, vi, zh, other
  }

  public final static String QUESTIONABLE_STATUS = "questionable";

  public final static String QUESTIONABLE_BUTTON_TEXT = "Questionable";
  public final static String SPAM_BUTTON_TEXT = "Spam";
  public final static String NOT_SPAM_BUTTON_TEXT = "Not Spam";

  public final static String XPATH_LANGUAGE_COLUMN = "./td[4]";
  public final static String XPATH_STATUS_COLUMN = "./td[6]";

  public final static String LANG_QUERY_PARAM = "lang=";
  public final static String STATUS_QUERY_PARAM = "status=";

  private static final String SPAM_WIKI_REVIEW_SERVICE_NAME = "spam-wiki-review";
  private static final String VIEW_WIKIS_SUBPAGE = "/wiki";

  @FindBy(xpath="/html[1]/body[1]/nav[1]/div[1]/a[1]/strong[1]")
  private WebElement spamWikiReviewHeaderLogo;

  @FindBy(xpath = "/html[1]/body[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]")
  private WebElement showQuestionableWikisButton;

  @FindBy(xpath = "//button[contains(text(),'Select language of Wikis')]")
  private WebElement selectLanguageButton;

  @FindBy(xpath = "//table[@class='table table-hover']//tbody")
  private WebElement displayedWikisTable;

  @FindBy(xpath = "/html//body")
  private WebElement bodyHTML;

  public SpamWikiReviewPage open() {
    getUrl(urlBuilder.getUrlForService(SPAM_WIKI_REVIEW_SERVICE_NAME)+VIEW_WIKIS_SUBPAGE);
    return this;
  }

  public SpamWikiReviewPage returnToMainPageByClickingHeaderLogo(){
    spamWikiReviewHeaderLogo.click();
    return this;
  }

  /**
   * Gets Raw Visible Text visible between <body> </body> tags
   */
  public String getRawVisibleBodyText() {
    return bodyHTML.getText();
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
   * Navigates in-browser to Show Questionable Wikis by clicking the button in header
   */
  public SpamWikiReviewPage showQuestionableWikis(){
    showQuestionableWikisButton.click();
    return this;
  }

  /**
   * This gets a list of WebElements containing Rows of displayed table with Wikis' info
   * excludes thead, header of the table
   */
  public List<WebElement> getListDisplayedWikisTableRows() {
    try {
      return displayedWikisTable.findElements(By.xpath("./tr"));
    } catch (org.openqa.selenium.NoSuchElementException notFoundException) {
      // if no elements were found return an empty list
    return Collections.emptyList();
    }
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
