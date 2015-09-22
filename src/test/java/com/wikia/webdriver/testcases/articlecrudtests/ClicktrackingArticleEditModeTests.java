package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.clicktracking.events.EventsArticleEditMode;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

/**
 * @ownership Content X-Wing
 */
public class ClicktrackingArticleEditModeTests extends NewTestTemplate {

  @RelatedIssue(issueID = "QAART-555")
  @Test(groups = {"ClickTracking", "ClickTrackingArticleEditModeTests",
      "ClickTrackingArticleEditMode_001"}, enabled = false)
  public void ClickTrackingArticleEditMode_001_verifyPreviewModalEvents() {

    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.editArticleInRTEUsingDropdown();
    visualEditMode.verifyContentLoaded();
    visualEditMode.startTracking();
    visualEditMode.previewArticle();

    List<JsonObject> expectedEvents = Arrays.asList(EventsArticleEditMode.PREVIEW_EVENT);
    visualEditMode.compareTrackedEventsTo(expectedEvents);
  }
}
