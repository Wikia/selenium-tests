package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.Common.Clicktracking.Events.EventsArticleEditMode;
import com.wikia.webdriver.Common.Clicktracking.Events.EventsModalAddPhoto;
import com.wikia.webdriver.Common.Clicktracking.Events.EventsModalAddPhotoOptions;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.SearchContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;

public class ClickTrackingArticleEditModeTests extends NewTestTemplate{

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
		visualEditMode.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
		visualEditMode.previewArticle();

		List<String> expectedEvents = Arrays.asList(
				EventsArticleEditMode.EventPreviewButtonClick
				);

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
		visualEditMode.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		photoAddPhoto.clickFlickr();
		photoAddPhoto.clickThisWiki();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.clickAddThisPhoto(0);
		photoAddPhoto = photoOptions.selectAnotherPhoto();
		photoAddPhoto.typeSearchQuery(SearchContent.searchPhrase);
		photoAddPhoto.clickFind();
		photoAddPhoto.chooseFileToUpload(PageContent.file);
		photoAddPhoto.clickUpload();

		List<String> expectedEvents = Arrays.asList(
				EventsModalAddPhoto.EventAddRecentPhotoClick,
				EventsModalAddPhoto.EventFindButtonClick,
				EventsModalAddPhoto.EventThisWikiLinkClick,
				EventsModalAddPhoto.EventUploadButtonClick,
				EventsModalAddPhotoOptions.EventBackButtonClick
				);

		visualEditMode.compareTrackedEventsTo(expectedEvents);
	}
}
