package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class VetModalTests extends TestTemplate {

	private String pageName;

	/**
	 * TC001 - Verifies left video alignment on editor, 'left' string on source
	 * mode and left alignment on article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests001", "VetModalTests" })
	public void Vet_Tests_001_VerifyLeftAlignmentOnEditorSourceAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(1);
		vetOptions.submit();
		edit.verifyLeftVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("left");
		edit.clickOnPublishButton();
		article.verifyVideoOnTheLeftOnAritcle();
	}

	/**
	 * TC002 - Verifies left video alignment on editor, 'left' string on source
	 * mode, left alignment on preview modal and left alignment on article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests002", "VetModalTests" })
	public void Vet_Tests_002_VerifyLeftAlignmentOnEditorSourcePreviewModalAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(1);
		vetOptions.submit();
		edit.verifyLeftVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("left");
		edit.clickOnVisualButton();
		edit.clickOnPreviewButton();
		edit.verifyVideoOnTheLeftInPreview();
		edit.clickClosePreviewModalButton();
		edit.clickOnPublishButton();
		article.verifyVideoOnTheLeftOnAritcle();
	}

	/**
	 * TC003 - Verifies left video alignment on editor, left alignment on
	 * article and then checks left alignment is chosen back in the VET options
	 * modal *
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests003", "VetModalTests" })
	public void Vet_Tests_003_VerifyLeftAlignmentOnEditorArticleAndVETOptions() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(1);
		vetOptions.submit();
		edit.verifyLeftVideoInEditMode();
		edit.clickOnPublishButton();
		article.verifyVideoOnTheLeftOnAritcle();
		edit = article.clickEditButton();
		edit.clickModifyButtonVideo();
		vetOptions.verifyAlignmentOptionIsSelected(1);
		vetOptions.clickUpdateVideo();
		edit.clickOnPublishButton();
	}

	/**
	 * TC004 - Verifies right video alignment on editor, 'right' string on
	 * source mode and right alignment on article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests004", "VetModalTests" })
	public void Vet_Tests_004_VerifyRightAlignmentOnEditorSourceAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(3);
		vetOptions.submit();
		edit.verifyRightVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("right");
		edit.clickOnPublishButton();
		article.verifyVideoOnTheRightOnAritcle();
	}

	/**
	 * TC005 - Verifies right video alignment on editor, 'right' string on
	 * source mode, right alignment on preview modal and right alignment on
	 * article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests005", "VetModalTests" })
	public void Vet_Tests_005_VerifyRightAlignmentOnEditorSourcePreviewModalAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(3);
		vetOptions.submit();
		edit.verifyRightVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("right");
		edit.clickOnVisualButton();
		edit.clickOnPreviewButton();
		edit.verifyVideoOnTheRightInPreview();
		edit.clickClosePreviewModalButton();
		edit.clickOnPublishButton();
		article.verifyVideoOnTheRightOnAritcle();
	}

	/**
	 * TC006 - Verifies right video alignment on editor, right alignment on
	 * article and then checks right alignment is chosen back in the VET options
	 * modal *
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests006", "VetModalTests" })
	public void Vet_Tests_006_VerifyRightAlignmentOnEditorArticleAndVETOptions() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(3);
		vetOptions.submit();
		edit.verifyRightVideoInEditMode();
		edit.clickOnPublishButton();
		article.verifyVideoOnTheRightOnAritcle();
		edit = article.clickEditButton();
		edit.clickModifyButtonVideo();
		vetOptions.verifyAlignmentOptionIsSelected(3);
		vetOptions.clickUpdateVideo();
		edit.clickOnPublishButton();
	}

	/**
	 * TC007 - Verifies center video alignment on editor, 'center' string on
	 * source mode and center alignment on article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests007", "VetModalTests" })
	public void Vet_Tests_007_VerifyCenterAlignmentOnEditorSourceAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(2);
		vetOptions.submit();
		edit.verifyCenterVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("center");
		edit.clickOnPublishButton();
		article.verifyVideoOnTheCenterOnArticle();
	}

	/**
	 * TC008 - Verifies center video alignment on editor, 'center' string on
	 * source mode, center alignment on preview modal and center alignment on
	 * article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests008", "VetModalTests" })
	public void Vet_Tests_008_VerifyCenterAlignmentOnEditorSourcePreviewModalAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(2);
		vetOptions.submit();
		edit.verifyCenterVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("center");
		edit.clickOnVisualButton();
		edit.clickOnPreviewButton();
		edit.verifyVideoOnTheCenterInPreview();
		edit.clickClosePreviewModalButton();
		edit.clickOnPublishButton();
		article.verifyVideoOnTheCenterOnArticle();
	}

	/**
	 * TC009 - Verifies center video alignment on editor, center alignment on
	 * article and then checks center alignment is chosen back in the VET
	 * options modal *
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests009", "VetModalTests" })
	public void Vet_Tests_009_VerifyCenterAlignmentOnEditorArticleAndVETOptions() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(2);
		vetOptions.submit();
		edit.verifyCenterVideoInEditMode();
		edit.clickOnPublishButton();
		article.verifyVideoOnTheCenterOnArticle();
		edit = article.clickEditButton();
		edit.clickModifyButtonVideo();
		vetOptions.verifyAlignmentOptionIsSelected(2);
		vetOptions.clickUpdateVideo();
		edit.clickOnPublishButton();
	}

	/**
	 * TC010 - Verifies video width on editor, source mode and on article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests010", "VetModalTests" })
	public void Vet_Tests_010_VerifyVideoWidthOnEditorSourceAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustWith(250);
		vetOptions.submit();
		edit.verifyVideoWidthInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("250");
		edit.clickOnPublishButton();
		article.verifyVideoWidthOnAritcle("250px");
	}

	/**
	 * TC011 - Verifies video width on editor, source mode, preview modal and on
	 * article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests011", "VetModalTests" })
	public void Vet_Tests_011_VerifyVideoWidthOnEditorSourcePreviewModalAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustWith(250);
		vetOptions.submit();
		edit.verifyVideoWidthInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("250");
		edit.clickOnVisualButton();
		edit.clickOnPreviewButton();
		edit.verifyVideoWidthOnPreview("250px");
		edit.clickClosePreviewModalButton();
		edit.clickOnPublishButton();
		article.verifyVideoWidthOnAritcle("250px");
	}

	/**
	 * TC012 - Verifies video width on editor, article and then checks back in
	 * VET options modal
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests012", "VetModalTests" })
	public void Vet_Tests_012_VerifyVideoWidthOnEditorArticleAndVETOptions() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustWith(250);
		vetOptions.submit();
		edit.verifyVideoWidthInEditMode();
		edit.clickOnPublishButton();
		article.verifyVideoWidthOnAritcle("250px");
		edit = article.clickEditButton();
		edit.clickModifyButtonVideo();
		vetOptions.verifyVideoWidthInVETOptionsModal();
		vetOptions.clickUpdateVideo();
		edit.clickOnPublishButton();
	}

	/**
	 * TC013 - Verifies video caption on editor, source mode and article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests013", "VetModalTests" })
	public void Vet_Tests_013_VerifyVideoCaptionOnEditorSourceAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyCaptionInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode(PageContent.caption);
		edit.clickOnPublishButton();
		article.verifyVideoCaptionOnAritcle();
	}

	/**
	 * TC014 - Verifies video caption on editor, source mode, preview modal and
	 * article
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests014", "VetModalTests" })
	public void Vet_Tests_014_VerifyVideoCaptionOnEditorSourcePreviewModalAndArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyCaptionInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheCaptionOnThePreview(PageContent.caption);
		edit.clickClosePreviewModalButton();
		edit.clickOnPublishButton();
		article.verifyVideoCaptionOnAritcle();
	}

	/**
	 * TC015 - Verifies video caption on editor, article and then back in VET
	 * options modal *
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests015", "VetModalTests" })
	public void Vet_Tests_015_VerifyVideoCaptionOnEditorArticleAndVETOptions() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyCaptionInEditMode();
		edit.clickOnPublishButton();
		article.verifyVideoCaptionOnAritcle();
		edit = article.clickEditButton();
		edit.clickModifyButtonVideo();
		vetOptions.verifyCaptionInVETModal(PageContent.caption);
		vetOptions.clickUpdateVideo();
		edit.clickOnPublishButton();
	}

	/**
	 * TC016 - Verifies no video caption on editor, article and then back in VET
	 * options modal
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests016", "VetModalTests" })
	public void Vet_Tests_016_VerifyNoCaptionOnEditorArticle() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustStyle(2);
		vetOptions.submit();
		edit.verifyNoVideoCaptionInEditMode();
		edit.clickOnPublishButton();
		article.verifyNoVideoCaptionOnAritcle();
		edit = article.clickEditButton();
		edit.clickModifyButtonVideo();
		vetOptions.verifyNoCaptionInVETModal();
		vetOptions.clickUpdateVideo();
		edit.clickOnPublishButton();
	}

	/**
	 * TC017 - Verifies video name field is not editable for Wikia Premium
	 * Videos
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@Test(groups = { "VetModalTests017", "VetModalTests" })
	public void Vet_Tests_017_VerifyVideoNameFieldIsNotEditable() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName,
				Properties.password);
		pageName = PageContent.articleNamePrefix + article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.wikiaVideoURL);
		vetOptions.verifyVideoNameFieldIsNotEditable();
		vetOptions.submit();
		edit.clickOnPublishButton();
	}
}
