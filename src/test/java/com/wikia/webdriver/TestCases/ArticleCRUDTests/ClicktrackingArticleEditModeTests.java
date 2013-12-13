package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClicktrackingScripts;
import com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents.EventsArticleEditModeTests;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;

public class ClicktrackingArticleEditModeTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {
					"ClickTracking",
					"ClickTrackingArticleEditModeTests",
					"ClickTrackingArticleEditModeTests_001" }
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

	@Test(
			groups = {
					"ClickTracking",
					"ClicktrackingArticleEditModeTests",
					"ClicktrackingArticleEditModeTests_002" }
		 )
	public void ClicktrackingArticleEditMode_002_verifyAddPhotoModalEvents() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();

		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Photo);
		photoOptions.setCaption(PageContent.caption2);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.verifyPhoto();

		List<String> expectedEvents;
		expectedEvents = EventsArticleEditModeTests.getExpectedEventsForTest001();
		visualEditMode.compareTrackedEventsTo(expectedEvents);
	}
}
