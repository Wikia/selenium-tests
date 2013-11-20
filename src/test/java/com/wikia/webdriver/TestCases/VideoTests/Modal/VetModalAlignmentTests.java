package com.wikia.webdriver.TestCases.VideoTests.Modal;

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

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_001"})
	public void Vet_Modal_001_leftOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle1 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle1);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(PositionsVideo.left);
		vetOptions.submit();
		visualEditMode.verifyVideoPosition(PositionsVideo.left);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoAlignment(PositionsVideo.left);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoAlignment(PositionsVideo.left);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoAlignment(PositionsVideo.left);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_002"}, dependsOnMethods="Vet_Modal_001_leftOnPage")
	public void Vet_Modal_002_leftInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.goToArticleEditPage(wikiURL, articleTitle1);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoAlignmentSelected(PositionsVideo.left);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_003"})
	public void Vet_Modal_003_centerOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle2 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle2);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(PositionsVideo.center);
		vetOptions.submit();
		visualEditMode.verifyVideoPosition(PositionsVideo.center);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoAlignment(PositionsVideo.center);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoAlignment(PositionsVideo.center);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoAlignment(PositionsVideo.center);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_004"}, dependsOnMethods = "Vet_Modal_003_centerOnPage")
	public void Vet_Modal_004_centerInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.goToArticleEditPage(wikiURL, articleTitle2);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoAlignmentSelected(PositionsVideo.center);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_005"})
	public void Vet_Modal_005_rightOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle3 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle3);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(PositionsVideo.right);
		vetOptions.submit();
		visualEditMode.verifyVideoPosition(PositionsVideo.right);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoAlignment(PositionsVideo.right);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoAlignment(PositionsVideo.right);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoAlignment(PositionsVideo.right);
	}

	@Test(groups = {"VetModalAlignment", "VetModalAlignment_006"}, dependsOnMethods = "Vet_Modal_005_rightOnPage")
	public void Vet_Modal_006_rightInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.goToArticleEditPage(wikiURL, articleTitle3);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoAlignmentSelected(PositionsVideo.right);
	}
}
