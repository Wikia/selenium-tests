package com.wikia.webdriver.TestCases.VideoTests.Modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject.StyleVideo;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetModalCaptionTests extends NewTestTemplate {

	/**
	 * 1. Verify caption in visual mode, source mode, preview, and published page,
	 * 2. Verify caption in vet modal when page edited,
	 * 3. Verify no caption in visual mode, source mode, preview, and published page,
	 * 4. Verify no caption in vet modal when page edited,
	 * 5. Verify video name is not editable for premium videos,
	 */

	Credentials credentials = config.getCredentials();

	String articleTitle_caption;
	String articleTitle_noCaption;
	String articleTitle_notEditable;
	String caption;

	@Test(groups = {"VetModalCaption", "VetModalCaption_001"})
	public void VetModalCaption_001_captionOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle_caption = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle_caption);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		caption = PageContent.caption + vetOptions.getTimeStamp();
		vetOptions.setCaption(caption);
		vetOptions.submit();
		visualEditMode.verifyVideoCaption(caption);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoCaption(caption);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoCaption(caption);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoCaption(caption);
	}

	@Test(groups = {"VetModalCaption", "VetModalCaption_002"}, dependsOnMethods = "Vet_Modal_001_captionOnPage")
	public void VetModalCaption_002_captionInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle_caption);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyCaption(caption);
	}

	@Test(groups = {"VetModalCaption", "VetModalCaption_003"})
	public void VetModalCaption_003_noCaptionOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle_noCaption = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle_noCaption);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustStyle(StyleVideo.nocaption);
		vetOptions.submit();
		visualEditMode.verifyVideoNoCaption();
		PreviewEditModePageObject previewMode = visualEditMode.previewArticle();
		previewMode.verifyVideoNoCaption();
		previewMode.closePreviewModal();
		ArticlePageObject article = visualEditMode.submitArticle();
		article.verifyVideoNoCaption();
	}

	@Test(groups = {"VetModalCaption", "VetModalCaption_004"}, dependsOnMethods = "Vet_Modal_003_noCaptionOnPage")
	public void VetModalCaption_004_noCaptionInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle_noCaption);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyNoCaption();
	}

	@Test(groups = {"VetModalCaption", "VetModalCaption_005"})
	public void VetModalCaption_005_videoNameNotEditable() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle_notEditable = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle_notEditable);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByQuery(VideoContent.wikiaVideoQuery, 1);
		vetOptions.verifyNameNotEditable();
	}
}
