package com.wikia.webdriver.TestCases.MediaTests.Modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetModalWidthTests extends NewTestTemplate{

	/**
	 * 1. Verify width in visual mode, source mode, preview, and published page,
	 * 2. Verify width in vet modal when page edited,
	 */

	Credentials credentials = config.getCredentials();

	String articleTitle;

	final static int WIDTH = 250;

	@Test(groups = {"VetModalWidth", "VetModalwidth_001", "Media"})
	public void Vet_Modal_001_widthOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
		vetOptions.adjustWith(WIDTH);
		vetOptions.submit();
		visualEditMode.verifyVideoWidth(WIDTH);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoWidth(WIDTH);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoWidth(WIDTH);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoWidth(WIDTH);
	}

	@Test(groups = {"VetModalWidth", "VetModalwidth_002", "Media"}, dependsOnMethods = "Vet_Modal_001_widthOnPage")
	public void Vet_Modal_002_widthInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
		vetOptions.verifyVideoWidth(WIDTH);
	}
}
