package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.List;

import javax.sql.CommonDataSource;

import org.openqa.selenium.remote.server.handler.UploadFile;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClickTrackingScripts;
import com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents.EventsArticleEditModeTests;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.SearchContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;
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
		visualEditMode.executeScript(ClickTrackingScripts.trackerInstallation);
		PreviewEditModePageObject preview = visualEditMode.previewArticle();
		
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
		visualEditMode.executeScript(ClickTrackingScripts.trackerInstallation);
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		photoAddPhoto.clickFlickr();
		photoAddPhoto.clickFlickr();
		photoAddPhoto.typeSearchQuery(SearchContent.searchPhrase);
		photoAddPhoto.clickFind();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.clickAddThisPhoto(0);
		photoAddPhoto = photoOptions.selectAnotherPhoto();
		photoAddPhoto.chooseFileToUpload(PageContent.file);
		photoAddPhoto.clickUpload();

		List<String> expectedEvents;
		expectedEvents = EventsArticleEditModeTests.getExpectedEventsForTest002();
		visualEditMode.compareTrackedEventsTo(expectedEvents);
	}
}
