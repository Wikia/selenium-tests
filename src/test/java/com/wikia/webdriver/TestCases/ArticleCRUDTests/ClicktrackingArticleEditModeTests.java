package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClicktrackingScripts;
import com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents.EventsArticleEditModeTests;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;

public class ClicktrackingArticleEditModeTests extends NewTestTemplate{

	@Test(
			groups = {
					"ClicktrackingArticleEditModeTests",
					"ClicktrackingArticleEditModeTests_001" }
		 )
	public void ClicktrackingArticleEditMode_001_verifyPreviewModalEvents() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		visualEditMode.verifyContentLoaded();
		visualEditMode.executeScript(ClicktrackingScripts.trackerInstallation);
//		PreviewEditModePageObject preview = visualEditMode.previewArticle();
//		preview.closePreviewModal();

		List<String> expectedEvents;
		expectedEvents = EventsArticleEditModeTests.getExpectedEventsForTest001();
		visualEditMode.compareTrackedEventsTo(expectedEvents);
	}
}
