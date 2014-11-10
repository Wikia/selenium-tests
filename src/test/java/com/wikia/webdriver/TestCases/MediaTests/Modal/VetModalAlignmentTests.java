package com.wikia.webdriver.TestCases.MediaTests.Modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject.PositionsVideo;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;



/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetModalAlignmentTests extends NewTestTemplate{

	/**
	 * 1. Verify left alignment in visual mode, source mode, preview, and published page,
	 * 2. Verify left alignment in vet modal when page edited,
	 * 3. Verify right alignment in visual mode, source mode, preview, and published page,
	 * 4. Verify right alignment in vet modal when page edited,
	 * 5. Verify center alignment in visual mode, source mode, preview, and published page,
	 * 6. Verify center alignment in vet modal when page edited,
	 */

	Credentials credentials = config.getCredentials();

	String articleTitle1;
	String articleTitle2;
	String articleTitle3;

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_001", "Media"})
	public void Vet_Modal_001_leftOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle1 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle1);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(PositionsVideo.LEFT);
		vetOptions.submit();
		visualEditMode.verifyVideoPosition(PositionsVideo.LEFT);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoAlignment(PositionsVideo.LEFT);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoAlignment(PositionsVideo.LEFT);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoAlignment(PositionsVideo.LEFT);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_002", "Media"}, dependsOnMethods="Vet_Modal_001_leftOnPage")
	public void Vet_Modal_002_leftInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle1);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoAlignmentSelected(PositionsVideo.LEFT);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_003", "Media"})
	public void Vet_Modal_003_centerOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle2 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle2);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(PositionsVideo.CENTER);
		vetOptions.submit();
		visualEditMode.verifyVideoPosition(PositionsVideo.CENTER);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoAlignment(PositionsVideo.CENTER);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoAlignment(PositionsVideo.CENTER);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoAlignment(PositionsVideo.CENTER);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_004", "Media"}, dependsOnMethods = "Vet_Modal_003_centerOnPage")
	public void Vet_Modal_004_centerInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle2);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoAlignmentSelected(PositionsVideo.CENTER);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_005", "Media"})
	public void Vet_Modal_005_rightOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle3 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle3);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(PositionsVideo.RIGHT);
		vetOptions.submit();
		visualEditMode.verifyVideoPosition(PositionsVideo.RIGHT);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoAlignment(PositionsVideo.RIGHT);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoAlignment(PositionsVideo.RIGHT);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoAlignment(PositionsVideo.RIGHT);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_006", "Media"}, dependsOnMethods = "Vet_Modal_005_rightOnPage")
	public void Vet_Modal_006_rightInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle3);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoAlignmentSelected(PositionsVideo.RIGHT);
	}
}
