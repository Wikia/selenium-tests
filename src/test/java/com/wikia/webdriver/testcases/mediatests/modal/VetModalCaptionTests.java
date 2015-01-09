package com.wikia.webdriver.testcases.mediatests.modal;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
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

	String articleTitleCaption;
	String articleTitleNoCaption;
	String articleTitleNotEditable;
	String caption;

	@Test(groups = {"VetModalCaption", "VetModalCaption_001", "Media"})
	public void VetModalCaption_001_captionOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitleCaption = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitleCaption);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
			.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
		caption = PageContent.CAPTION + vetOptions.getTimeStamp();
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

	@Test(groups = {"VetModalCaption", "VetModalCaption_002", "Media"}, dependsOnGroups = "VetModalCaption_001")
	public void VetModalCaption_002_captionInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitleCaption);
		VetOptionsComponentObject vetOptions =
			(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
		vetOptions.verifyCaption(caption);
	}

	@Test(groups = {"VetModalCaption", "VetModalCaption_005", "Media"})
	public void VetModalCaption_005_videoNameNotEditable() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitleNotEditable = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitleNotEditable);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
			.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 1);
		vetOptions.verifyNameNotEditable();
	}
}
