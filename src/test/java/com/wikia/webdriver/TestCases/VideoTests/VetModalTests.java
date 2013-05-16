package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;


public class VetModalTests extends TestTemplate {
	
	private String pageName;	
	
	/**
	 * TC001 - Verifies left video alignment on editor, 'left' string on source mode and left alignment on article 
	 * 
	 * @author Rodrigo 'RodriGomez' Molinero
	 */
	
	@Test(groups = { "VetModalTests001", "VetModalTests" })
	public void Vet_Tests_001_VerifyLeftAlignmentOnEditorSourceAndArticle() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		CommonFunctions.logInCookie(Properties.userName, Properties.password);	
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(1);
		vetOptions.submit();
		edit.verifyLeftVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("left");
		wiki = edit.clickOnPublishButton();
		wiki.verifyVideoOnTheLeftOnAritcle();	}
		
	/**
	 * TC002 - Verifies left video alignment on editor, 'left' string on source mode,
	 * left alignment on preview modal and left alignment on article 
	 * 
	 * @author Rodrigo 'RodriGomez' Molinero
	 */
		
	@Test(groups = { "VetModalTests002", "VetModalTests" })
	public void Vet_Tests_002_VerifyLeftAlignmentOnEditorSourcePreviewModalAndArticle() {
		
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(1);
		vetOptions.submit();
		edit.verifyLeftVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("left");
		edit.clickOnVisualButton();
		edit.clickOnPreviewButton();
		edit.verifyVideoOnTheLeftInPreview();
		edit.clickClosePreviewModalButton();
		wiki = edit.clickOnPublishButton();
		wiki.verifyVideoOnTheLeftOnAritcle();
		
	
	}
	
	/**
	 * TC003 - Verifies left video alignment on editor, left alignment on article and then checks left
	 * alignment is chosen back in the VET options modal	 *  
	 * 
	 * @author Rodrigo 'RodriGomez' Molinero
	 */
		
	@Test(groups = { "VetModalTests003", "VetModalTests" })
	public void Vet_Tests_003_VerifyLeftAlignmentOnEditorArticleAndVETOptions() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(1);
		vetOptions.submit();
		edit.verifyLeftVideoInEditMode();
		wiki = edit.clickOnPublishButton();
		wiki.verifyVideoOnTheLeftOnAritcle();
		edit = wiki.clickEditButton(pageName);
		edit.clickModifyButtonVideo();
		vetOptions.verifyAlignmentOptionIsSelected(1);
		vetOptions.clickUpdateVideo();
		wiki = edit.clickOnPublishButton();
		CommonFunctions.logoutCookie(cookieName);
		
	}
	
	/**
	 * TC004 - Verifies right video alignment on editor, 'right' string on source mode and right alignment on article 
	 * 
	 * @author Rodrigo 'RodriGomez' Molinero
	 */
	
	@Test(groups = { "VetModalTests004", "VetModalTests" })
	public void Vet_Tests_004_VerifyRightAlignmentOnEditorSourceAndArticle() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(3);
		vetOptions.submit();
		edit.verifyRightVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("right");
		wiki = edit.clickOnPublishButton();
		wiki.verifyVideoOnTheRightOnAritcle();
		CommonFunctions.logoutCookie(cookieName);
		
	}
	
	/**
	 * TC005 - Verifies right video alignment on editor, 'right' string on source mode,
	 * right alignment on preview modal and right alignment on article 
	 * 
	 * @author Rodrigo 'RodriGomez' Molinero
	 */
	
	@Test(groups = { "VetModalTests005", "VetModalTests" })
	public void Vet_Tests_005_VerifyRightAlignmentOnEditorSourcePreviewModalAndArticle() {
		
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustPosition(3);
		vetOptions.submit();
		edit.verifyRightVideoInEditMode();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("right");
		edit.clickOnVisualButton();
		edit.clickOnPreviewButton();
		edit.verifyVideoOnTheRightInPreview();
		edit.clickClosePreviewModalButton();
		wiki = edit.clickOnPublishButton();
		wiki.verifyVideoOnTheRightOnAritcle();
		CommonFunctions.logoutCookie(cookieName);
	
	}
	
	   /**
	    * TC006 - Verifies right video alignment on editor, right alignment on article and then checks right
	    * alignment is chosen back in the VET options modal	 *  
	    * 
	    * @author Rodrigo 'RodriGomez' Molinero
	    */	
	
		@Test(groups = { "VetModalTests006", "VetModalTests" })
		public void Vet_Tests_006_VerifyRightAlignmentOnEditorArticleAndVETOptions() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustPosition(3);
			vetOptions.submit();
			edit.verifyRightVideoInEditMode();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoOnTheRightOnAritcle();
			edit = wiki.clickEditButton(pageName);
			edit.clickModifyButtonVideo();
			vetOptions.verifyAlignmentOptionIsSelected(3);
			vetOptions.clickUpdateVideo();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
			
		}
	
	   /**
		* TC007 - Verifies center video alignment on editor, 'center' string on source mode and center alignment on article 
		* 
		* @author Rodrigo 'RodriGomez' Molinero
		*/	
		
		@Test(groups = { "VetModalTests007", "VetModalTests" })
		public void Vet_Tests_007_VerifyCenterAlignmentOnEditorSourceAndArticle() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustPosition(2);
			vetOptions.submit();
			edit.verifyCenterVideoInEditMode();
			edit.clickOnSourceButton();
			edit.verifyWikiTextInSourceMode("center");
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoOnTheCenterOnArticle();
			CommonFunctions.logoutCookie(cookieName);
			
		}	
		
		/**
		 * TC008 - Verifies center video alignment on editor, 'center' string on source mode,
		 * center alignment on preview modal and center alignment on article 
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests008", "VetModalTests" })
		public void Vet_Tests_008_VerifyCenterAlignmentOnEditorSourcePreviewModalAndArticle() {
			
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustPosition(2);
			vetOptions.submit();
			edit.verifyCenterVideoInEditMode();
			edit.clickOnSourceButton();
			edit.verifyWikiTextInSourceMode("center");
			edit.clickOnVisualButton();
			edit.clickOnPreviewButton();
			edit.verifyVideoOnTheCenterInPreview();
			edit.clickClosePreviewModalButton();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoOnTheCenterOnArticle();
			CommonFunctions.logoutCookie(cookieName);
		
		}
		
		/**
		 * TC009 - Verifies center video alignment on editor, center alignment on article and then checks center
		 * alignment is chosen back in the VET options modal	 *  
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests009", "VetModalTests" })
		public void Vet_Tests_009_VerifyCenterAlignmentOnEditorArticleAndVETOptions() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustPosition(2);
			vetOptions.submit();
			edit.verifyCenterVideoInEditMode();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoOnTheCenterOnArticle();
			edit = wiki.clickEditButton(pageName);
			edit.clickModifyButtonVideo();
			vetOptions.verifyAlignmentOptionIsSelected(2);
			vetOptions.clickUpdateVideo();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
				
		}	
		
		/**
		 * TC010 - Verifies video width on editor, source mode and on article 
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */	
		
		@Test(groups = { "VetModalTests010", "VetModalTests" })
		public void Vet_Tests_010_VerifyVideoWidthOnEditorSourceAndArticle() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();			
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustWith(250);
			vetOptions.submit();			
			edit.verifyVideoWidthInEditMode();
			edit.clickOnSourceButton();
			edit.verifyWikiTextInSourceMode("250");		
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoWidthOnAritcle();
			CommonFunctions.logoutCookie(cookieName);
			
		}
		
		/**
		 * TC011 - Verifies video width on editor, source mode, preview modal and on article 
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests011", "VetModalTests" })
		public void Vet_Tests_011_VerifyVideoWidthOnEditorSourcePreviewModalAndArticle() {
			
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustWith(250);
			vetOptions.submit();
			edit.verifyVideoWidthInEditMode();			
			edit.clickOnSourceButton();
			edit.verifyWikiTextInSourceMode("250");
			edit.clickOnVisualButton();
			edit.clickOnPreviewButton();
			edit.verifyVideoWidthOnPreview();
			edit.clickClosePreviewModalButton();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoWidthOnAritcle();
			CommonFunctions.logoutCookie(cookieName);
		
		}
		
		/**
		 * TC012 - Verifies video width on editor, article and then checks back in VET options modal	 
		 *  
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests012", "VetModalTests" })
		public void Vet_Tests_012_VerifyVideoWidthOnEditorArticleAndVETOptions() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustWith(250);
			vetOptions.submit();
			edit.verifyVideoWidthInEditMode();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoWidthOnAritcle();
			edit = wiki.clickEditButton(pageName);
			edit.clickModifyButtonVideo();
			vetOptions.verifyVideoWidthInVETOptionsModal();
			vetOptions.clickUpdateVideo();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
			
		}
		
		/**
		 * TC013 - Verifies video caption on editor, source mode and article 
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests013", "VetModalTests" })
		public void Vet_Tests_013_VerifyVideoCaptionOnEditorSourceAndArticle() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.setCaption(PageContent.caption);
			vetOptions.submit();
			edit.verifyCaptionInEditMode();
			edit.clickOnSourceButton();
			edit.verifyWikiTextInSourceMode(PageContent.caption);
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoCaptionOnAritcle();
			CommonFunctions.logoutCookie(cookieName);		
		}
		
		/**
		 * TC014 - Verifies video caption on editor, source mode, preview modal and article 
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests014", "VetModalTests" })
		public void Vet_Tests_014_VerifyVideoCaptionOnEditorSourcePreviewModalAndArticle() {
			
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.setCaption(PageContent.caption);
			vetOptions.submit();
			edit.verifyCaptionInEditMode();
			edit.clickOnSourceButton();
			edit.verifyWikiTextInSourceMode(PageContent.caption);
			edit.clickOnPreviewButton();
			edit.verifyTheCaptionOnThePreview(PageContent.caption);
			edit.clickClosePreviewModalButton();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoCaptionOnAritcle();
			CommonFunctions.logoutCookie(cookieName);
		}
		
		/**
		 * TC015 - Verifies video caption on editor, article and then back in VET options modal	 *  
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
			
		@Test(groups = { "VetModalTests015", "VetModalTests" })
		public void Vet_Tests_015_VerifyVideoCaptionOnEditorArticleAndVETOptions() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.setCaption(PageContent.caption);
			vetOptions.submit();
			edit.verifyCaptionInEditMode();
			wiki = edit.clickOnPublishButton();
			wiki.verifyVideoCaptionOnAritcle();
			edit = wiki.clickEditButton(pageName);
			edit.clickModifyButtonVideo();
			vetOptions.verifyCaptionInVETModal(PageContent.caption);
			vetOptions.clickUpdateVideo();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
			
		}
		
		/**
		 * TC016 - Verifies no video caption on editor, article and then back in VET options modal  
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests016", "VetModalTests" })
		public void Vet_Tests_016_VerifyNoCaptionOnEditorArticle() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
			vetOptions.adjustStyle(2);
			vetOptions.submit();
			edit.verifyNoVideoCaptionInEditMode();
			wiki = edit.clickOnPublishButton();
			wiki.verifyNoVideoCaptionOnAritcle();
			edit = wiki.clickEditButton(pageName);
			edit.clickModifyButtonVideo();
			vetOptions.verifyNoCaptionInVETModal();
			vetOptions.clickUpdateVideo();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
			
		}
		
		/**
		 * TC017 - Verifies video name field is not editable for Wikia Premium Videos
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests017", "VetModalTests" })
		public void Vet_Tests_017_VerifyVideoNameFieldIsNotEditable() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.wikiaVideoURL);
			vetOptions.verifyVideoNameFieldIsNotEditable();
			vetOptions.submit();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
			
		}
		
		/**
		 * TC018 - Verifies video name field is editable for Non-Premium Wikia videos
		 * 
		 * @author Rodrigo 'RodriGomez' Molinero
		 */
		
		@Test(groups = { "VetModalTests018", "VetModalTests" })
		public void Vet_Tests_018_VerifyVideoNameFieldIsEditable() {
			CommonFunctions.logOut(driver);
			WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
			wiki.openWikiPage();
			String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
			pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
			WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
			edit.deleteArticleContent();
			VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
			VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
			vetOptions.verifyVideoNameFieldIsEditable();
			vetOptions.submit();
			wiki = edit.clickOnPublishButton();
			CommonFunctions.logoutCookie(cookieName);
			
		}
}
