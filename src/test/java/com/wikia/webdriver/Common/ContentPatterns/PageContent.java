package com.wikia.webdriver.Common.ContentPatterns;

import java.io.File;

public class PageContent {
	//wiki
	public static String wikiHeadline = "Lorem ipsum dolor";
	public static String wikiDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	public static String wikiPromoteHeadline = "Lorem ipsum dolor Lorem";
	public static String wikiPromoteDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit "
		+ "Lorem ipsum dolor sit amet, consectetur adipiscing Lorem ipsum dolor sit amet, consectetur "
		+ "adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing Lorem ipsum dolor sit amet,"
		+ " consectetur adipiscing elit Lorem ipsum dolor sit adipiscing";

	//articles
	public static String articleNamePrefix = "QAarticle";
	public static String articleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	public static String articleTextEdit = "Brand new content";
	public static String articleTextSecondEdit =
		"Ut enim ad minim veniam, quis nostrud exercitation "+
		"ullamco laboris nisi ut aliquip ex ea commodo consequat.";
	public static String commentText = "Lorem ipsum dolor sit amet, comment";
	public static String commentText2 = "QAComment";
	public static String commentTextNonLatin = "QAComment 한국어 위키백과에 На Вама је да одлучите 佛教藝術";
	public static String commentTextEdit = "Brand new comment";
	public static String replyText = "Brand new reply";
	public static String replyText2 = "QAReply";
	//articles with TOC
	public static String articleWithTOClines =
			"==First Heading==\n"+
			"text of the sub-heading number 2\n"+
			"==Second heading==\n"+
			"text of the second heading\n"+
			"===sub-heading number 1===\n"+
			"text of the sub-heading number 1\n"+
			"===sub-heading number 2===\n"+
			"text of the sub-heading number 2\n";
	//blogs
	public static String blogPostNamePrefix = "blogPost";
	public static String blogContent = "blogContent";
	public static String blogContentEdit = "blogContentEdit";
	public static String blogComment = "blogComment";
	public static String blogCommentReply = "blogCommentReply";
	public static String blogCommentEdit = "blogCommentEdit";
	public static String blogListName = "BlogPostList";
	//categories
	public static String categoryNamePrefix = "TestCategory";
	//generic
	public static String caption = "QAWebdriverCaption1";
	public static String caption2 = "QAWebdriverCaption2";
	public static String wikiaContributor = "A Wikia contributor";
	//image serving
	public static String file = "Image001.jpg";
	public static String filePng = "Image001.png";
	public static String file2Png = "Image002.png";
	public static String[] listOfFiles = {
			"Image001.jpg","Image002.jpg", "Image003.jpg", "Image004.jpg", "Image005.jpg",
			"Image006.jpg", "Image007.jpg", "Image008.jpg", "Image009.jpg", "Image010.jpg"
	};
	public static String resourcesPath =
			"." + File.separator + "src" + File.separator +
			"test" + File.separator + "resources" + File.separator +
			"ImagesForUploadTests" + File.separator;
	//message wall
	public static String messageWallTitlePrefix = "QAMessageWallTitle";
	public static String messageWallTitleEditPrefix = "QAMessageWallTitleEdit";
	public static String messageWallMessagePrefix = "QAMessageWallMessage";
	public static String messageWallMessageEditPrefix = "QAMessageWallEditMessage";
	public static String messageWallMessageNonLatinPrefix = "Гсторыя śćąęłńó";
	public static String messageWallQuotePrefix = "QAQuote";

	//links
	public static String externalLink = "http://www.wikia.com/";
	public static String internalLink = "Home";
	public static String redirectLink = "Formatting";
	public static String textLink = "qaLink";
	public static String redLink = "QAasdfasjsad123213lj";

	//forum
	public static String forumTitlePrefix = "QABoardTitle ";
	public static String forumTitleEditPrefix = "QABoardTitleEdit ";
	public static String forumTitle40CharPrefix = "QABoardlongtitle 40 forty c";
	public static String forumTitleSlashPrefix = "QABoard/Title";
	public static String forumTitleUnderScorePrefix = "QABoard_Title";
	public static String forumTitleNonlatinPrefix = "QABoard查爾斯";
	public static String forumDescriptionPrefix = "Duis quam ante, fringilla at cursus tristique ";
	public static String forumDescriptionEditPrefix = "Duis quam ante, fringilla at cursus tristique edit ";
	public static String forumMessage = "QAforumMessage";
	public static String forumBoard = "QABoardForMoveThreadTest";
	public static String closeReason = "QA reason";

	//Messages
	public static String articleDeletedMessage = "This page has been deleted.";
	public static String createNewBlogPostMessage = "Create a new blog post";
	public static String loginRequired = "Login required";
	public static String notLoggedInMessage = "Not logged in";
	public static String newPasswordSentMessage = "We've sent a new password to the email address for %userName%.";
	public static String newPasswordWasSent		= "We've already sent a password reminder to this account in the last 24 hours. Please check your email.";
	public static String phalanxBlockMessage = "The page you wanted to save was blocked by the spam filter.";
	public static String phalanxBlockTitleMessage = "Sorry, the page title was rejected by our spam filter. Please use a different title.";
	public static String signUpTooYoungMessage = "Sorry, we're not able to register your account at this time.";
	public static String signUpInvalidCaptchaMessage = "The word you entered didn't match the word in the box, try again!";
	public static String signUpUserExistsMessage = "Someone already has this username. Try a different one!";

	//Blocks for Phalanx
	public static String titleFilterPlain = "BadContent";

	//wikiText
	public static String wikiTextPhoto = "[[File:%photoName%|thumb|%s]]";
	public static String wikiTextSlideshow = "<gallery type=\"slideshow\" position=\"center\">\n%image1%\n%image2%\n%image3%\n%image4%\n</gallery>";

	//SignUp correct
	public static String wikiSignUpBirthDay = "11";
	public static String wikiSignUpBirthMonth = "11";
	public static String wikiSignUpBirthYear = "1954";
	public static final int MIN_AGE = 12;

}
