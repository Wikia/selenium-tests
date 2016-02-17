package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiffPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final RecentWikiActivity recentWikiActivity = new RecentWikiActivity();

  @FindBy(css = ".diff-page_undo")
  private WebElement undoButton;

  public DiffPage() {
    super();
  }

  public DiffPage open() {
    getUrl(String.format("%s%s%s%s", urlBuilder.getUrlForWiki(), URLsContent.DIFF_PAGE));

    return this;
  }

//  public DiffPage visibilityOfUndoButton() {
//    if (undoButton.isDisplayed()){
//      PageObjectLogging.logInfo("Undo button is visible");
//    }
//    else {
//      PageObjectLogging.logInfo("Undo button is NOT visible");
//    }
//  }
}
