package com.wikia.webdriver.TestCases.VideoTests.AddVideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetRelatedVideoTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * skipped due VID-1325
	 */
	@Test(enabled = false, groups = {"RelatedVideo_001", "RelatedVideo"})
	public void RelatedVideo_001_Provider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		VisualEditModePageObject rVmoduleMessageEdit = base.navigateToArticleEditPage(wikiURL, URLsContent.relatedVideosList);
		rVmoduleMessageEdit.deleteUnwantedVideoFromMessage(VideoContent.youtubeVideoURL2name);
		ArticlePageObject article = rVmoduleMessageEdit.submitArticle();
		article.openRandomArticle(wikiURL);
		article.verifyRelatedVideosModule();
		VetAddVideoComponentObject vetAddingVideo = article.clickAddRelatedVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		article.verifyRelatedVideoAdded(VideoContent.youtubeVideoURL2name);
	}

	/**
	 * skipped due VID-1325
	 */
	@Test(enabled = false, groups = {"RelatedVideo_002", "RelatedVideo"})
	public void RelatedVideo_002_Library() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		VisualEditModePageObject rVmoduleMessageEdit = base.navigateToArticleEditPage(wikiURL, URLsContent.relatedVideosList);
		rVmoduleMessageEdit.deleteUnwantedVideoFromMessage(VideoContent.youtubeVideoURL2name);
		ArticlePageObject article = rVmoduleMessageEdit.submitArticle();
		article.openRandomArticle(wikiURL);
		article.verifyRelatedVideosModule();
		VetAddVideoComponentObject vetAddingVideo = article.clickAddRelatedVideo();
		vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 1);
		article.verifyRelatedVideoAdded(vetAddingVideo.getVideoName());
	}
}
