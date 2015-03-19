package com.wikia.webdriver.testcases.articlecrudtests;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.clicktracking.events.EventsArticleEditMode;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

public class ClicktrackingArticleEditModeTests extends NewTestTemplate {

    private static final String script = "window.onbeforeunload = function () "
            + "{return 'blocking redirect - performing clicktracking test';}";

    @Test(groups = { "ClickTracking", "ClickTrackingArticleEditModeTests",
            "ClickTrackingArticleEditMode_001" })
    public void ClickTrackingArticleEditMode_001_verifyPreviewModalEvents() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openMainPage(wikiURL);
        NavigationBar navbar = new NavigationBar(driver);
        navbar.executeScript(script);

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
