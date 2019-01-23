package com.wikia.webdriver.testcases.desktop.spamwikireview;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.pages.SpamWikiReviewPage;

import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Test(groups = "SpamWikiReview")
public class ViewSpamWikisTests extends NewTestTemplate {


  private final String XPATH_LANGUAGE_COLUMN = "./td[4]";
  private final String QUERY_PARAM_LANG = "?lang=";

  /**
   * Tests if a normal user correctly doesn't have access to Spam Wiki Review
   */
  @Test
  @Execute(asUser = User.USER)
  public void authorizationFailSpamWikiReviewTest() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    Assertion.assertTrue(spamWikiReviewPage.getRawVisibleBodyText()
                             .matches(".*(User not authorized|Unauthorized).*"),
                         "No 'unauthorized' type message was displayed to the user");

    Assertion.assertTrue(spamWikiReviewPage.getListDisplayedWikisTableRows().isEmpty(),
                         "It shouldn't be possible to see Wikis' list when unauthorized");
  }

  @Test
  @Execute(asUser = User.STAFF)
  public void filterJapaneseLanguage() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    String spamWikiReviewListViewUrl = spamWikiReviewPage.getCurrentUrl();

    // select 'ja' language
    spamWikiReviewPage.selectLanguageOfWikis(SpamWikiReviewPage.LANGUAGE_CODE.ja);


    // assert current page contains query param selecting the language
    Assertion.assertEquals(spamWikiReviewPage.getCurrentUrl(),
                           spamWikiReviewListViewUrl + QUERY_PARAM_LANG + SpamWikiReviewPage.LANGUAGE_CODE.ja);

    // check if all Wikis on a presumably filtered page have the 'ja' language
    for(WebElement wikiRow : spamWikiReviewPage.getListDisplayedWikisTableRows()) {
      Assertion.assertEquals(wikiRow.findElement(By.xpath(XPATH_LANGUAGE_COLUMN)).getText(), "ja");
    }

  }


  @Test
  @Execute(asUser = User.STAFF)
  public void filterAllLanguages() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    String spamWikiReviewListViewUrl = spamWikiReviewPage.getCurrentUrl();

    // select all languages
    spamWikiReviewPage.selectLanguageOfWikis(SpamWikiReviewPage.LANGUAGE_CODE.all);


    // assert current page contains query param selecting the language ALL
    Assertion.assertEquals(spamWikiReviewPage.getCurrentUrl(),
                           spamWikiReviewListViewUrl + QUERY_PARAM_LANG + SpamWikiReviewPage.LANGUAGE_CODE.all);
  }


  @Test
  @Execute(asUser = User.STAFF)
  public void filterOtherLanguages() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    String spamWikiReviewListViewUrl = spamWikiReviewPage.getCurrentUrl();

    // select 'ja' language
    spamWikiReviewPage.selectLanguageOfWikis(SpamWikiReviewPage.LANGUAGE_CODE.other);


    // assert current page contains query param selecting the language
    Assertion.assertEquals(spamWikiReviewPage.getCurrentUrl(),
                           spamWikiReviewListViewUrl + QUERY_PARAM_LANG + SpamWikiReviewPage.LANGUAGE_CODE.other);

    // check if all Wikis on a presumably filtered page have languages other than those in LANGUAGE_CODE
    for(WebElement wikiRow : spamWikiReviewPage.getListDisplayedWikisTableRows()) {
      Assertion.assertFalse(EnumUtils.isValidEnum(SpamWikiReviewPage.LANGUAGE_CODE.class,
                                                 wikiRow.findElement(By.xpath(XPATH_LANGUAGE_COLUMN)).getText()),
                           "Spam Wiki Review other languages view contained languages from"
                           + "LANGUAGE CODE - those that can be filtered individually, "
                           + "namely " + wikiRow.findElement(By.xpath(XPATH_LANGUAGE_COLUMN)).getText());
    }
  }

}