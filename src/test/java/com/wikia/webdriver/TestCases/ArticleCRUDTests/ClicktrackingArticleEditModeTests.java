package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.Common.Clicktracking.Events.EventsArticleEditMode;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;

public class ClicktrackingArticleEditModeTests extends NewTestTemplate{

	@Test(groups = {
			"ClickTracking",
			"ClickTrackingArticleEditModeTests",
			"ClickTrackingArticleEditMode_001"
	})
	public void ClickTrackingArticleEditMode_001_verifyPreviewModalEvents() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.editArticleInRTEUsingDropdown();
		visualEditMode.verifyContentLoaded();
		visualEditMode.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
		visualEditMode.previewArticle();

		List<JsonObject> expectedEvents = Arrays.asList(
				EventsArticleEditMode.previewEvent
		);
		visualEditMode.compareTrackedEventsTo(expectedEvents);
	}
}
