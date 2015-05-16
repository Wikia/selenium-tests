package com.wikia.webdriver.testcases.articlecrudtests;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.clicktracking.events.EventsArticleEditMode;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

public class ClicktrackingArticleEditModeTests extends NewTestTemplate {

    @RelatedIssue(issueID = "QAART-555")
    @Test(groups = { "ClickTracking", "ClickTrackingArticleEditModeTests",
            "ClickTrackingArticleEditMode_001"}, enabled = false)
    public void ClickTrackingArticleEditMode_001_verifyPreviewModalEvents() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        ArticlePageObject article = base.openRandomArticle(wikiURL);
        VisualEditModePageObject visualEditMode = article
                .editArticleInRTEUsingDropdown();
        visualEditMode.verifyContentLoaded();
        visualEditMode
                .executeScript(ClickTrackingScriptsProvider.TRACKER_INSTALLATION);
        visualEditMode.previewArticle();

        List<JsonObject> expectedEvents = Arrays
                .asList(EventsArticleEditMode.previewEvent);
        visualEditMode.compareTrackedEventsTo(expectedEvents);
    }
}
