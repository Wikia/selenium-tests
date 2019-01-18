package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  public SpamWikiReviewPage selectLanguageOfWikis(LANGUAGE_CODE language){
    selectLanguageButton.click();
    selectLanguageButton
        .findElement(By.xpath(String.format("//a[contains(text(),'%s')]", language)))
        .click();

    return this;
  }

  public String getDisplayedWikis()
  {
    return displayedWikisTable.getText();
  }

}
