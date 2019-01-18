package com.wikia.webdriver.testcases.desktop.spamwikireview;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.pages.SpamWikiReviewPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Test(groups = "SpamWikiReview")
public class ViewSpamWikisTests extends NewTestTemplate {

  @Test
  @Execute(asUser = User.STAFF)
  public void openSpamWikiReviewTest() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    //TODO: assert here that it was opened
  }

  @Test
  @Execute(asUser = User.USER)
  public void authorizationFailSpamWikiReviewTest() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    // TODO: assert here that it returned a failure
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
                           spamWikiReviewListViewUrl+"?lang=ja");

    // check if all Wikis on a presumably filtered page have the 'ja' language
    for(WebElement wikiRow : spamWikiReviewPage.getListDisplayedWikisTableRows()) {
      Assertion.assertEquals(wikiRow.findElement(By.xpath("./td[4]")).getText(), "ja");
    }

  }


  @Test
  @Execute(asUser = User.STAFF)
  public void filterAllLanguages() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    // TODO: assert here that it opened all languages
  }


  @Test
  @Execute(asUser = User.STAFF)
  public void filterOtherLanguages() {
    SpamWikiReviewPage spamWikiReviewPage = new SpamWikiReviewPage();
    spamWikiReviewPage.open();

    // TODO: assert here that it filtred to all but enum languages
  }

}