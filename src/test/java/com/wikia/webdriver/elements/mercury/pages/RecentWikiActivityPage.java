package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecentWikiActivityPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final RecentWikiActivity recentWikiActivity = new RecentWikiActivity();

  @FindBy(css = ".side-nav-toggle-2016")
  private WebElement topBar;

  private Navigation navigation;

  public RecentWikiActivityPage() {
    super();

    this.navigation = new Navigation(driver);
  }

  public RecentWikiActivityPage open(){
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(), URLsContent.RECENT_WIKI_ACTIVITY));

    return this;
  }
}
