package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class WikiHistoryPageObject extends WikiBasePageObject {

  private String getFirstCssRevision() {
    wait.forElementVisible(cssEditSummary);
    String summary = cssEditSummary.getText();
    PageObjectLogging.log("cssEditSummary",
        "the following edit summary was get from Wikia.css: " + summary, true);
    return summary;
  }

  public void verifyLatestEditSummary(String text) {
    String editSummary = getFirstCssRevision();
    editSummary = editSummary.substring(1, editSummary.length() - 1);
    Assertion.assertEquals(editSummary, text);
  }
}
