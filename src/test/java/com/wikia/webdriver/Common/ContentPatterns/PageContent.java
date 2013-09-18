package com.wikia.webdriver.Common.ContentPatterns;

public class PageContent {
	//articles
	public static String articleNamePrefix = "QAarticle";
	public static String articleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	public static String articleTextEdit = "Brand new content";
	public static String commentText = "Lorem ipsum dolor sit amet, comment";
	public static String commentText2 = "QAComment";
	public static String commentTextNonLatin = "QAComment 한국어 위키백과에 На Вама је да одлучите 佛教藝術";
	public static String commentTextEdit = "Brand new comment";
	public static String replyText = "Brand new reply";
	public static String replyText2 = "QAReply";
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
	//create new wiki
	public static String wikiNamePrefix = "QATestWiki";
	public static String wikiTopic = "Duis quam ante, fringilla at cursus tristique, laoreet vel elit. Nullam rhoncus, magna ut dictum ultrices, mauris lectus consectetur tellus, sed dignissim elit justo vel ante.";
	public static String wikiCategory = "Auto";
	//image serving
	public static String file = "Image001.jpg";
	public static String[] listOfFiles = {"Image001.jpg","Image002.jpg", "Image003.jpg", "Image004.jpg", "Image005.jpg", "Image006.jpg", "Image007.jpg", "Image008.jpg", "Image009.jpg", "Image010.jpg"};

	//message wall
	public static String messageWallTitlePrefix = "QAMessageWallTitle";
	public static String messageWallTitleEditPrefix = "QAMessageWallTitleEdit";
	public static String messageWallMessagePrefix = "QAMessageWallMessage";
	public static String messageWallMessageEditPrefix = "QAMessageWallEditMessage";
	public static String messageWallMessageNonLatinPrefix = "Гсторыя śćąęłńó";
	public static String messageWallQuotePrefix = "QAQuote";

	//top 10 list
	public static String top10Description = "DescriptionForList";
	public static String top10TestListQAfull = "Top_10_list:TestListQA";
	public static String top10TestListQAshort = "TestListQA";
	public static String top10listNamePrefix = "Top10list";
	public static String relatedPageName = "PageToCheckTop10ListFetching";
	//links
	public static String externalLink = "http://www.wikia.com/";
	public static String internalLink = "Formatting";
	public static String textLink = "qaLink";

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
	public static String phalanxBlockMessage = "The page you wanted to save was blocked by the spam filter.";
	public static String phalanxBlockTitleMessage = "Sorry, the page title was rejected by our spam filter. Please use a different title.";

	//Blocks for Phalanx
	public static String titleFilterPlain = "BadContent";

	//wikiText
	public static String wikiTextPhoto = "[[File:%photoName%|thumb|%s]]";
	public static String wikiTextSlideshow = "<gallery type=\"slideshow\" position=\"center\">\n%image1%\n%image2%\n%image3%\n%image4%\n</gallery>";

	public static String wikiaGlobalUrl = "http://www.wikia.com/";

	//SignUp correct
	public static String wikiSignUpBirthDay = "11";
	public static String wikiSignUpBirthMonth = "11";
	public static String wikiSignUpBirthYear = "1954";
	public static final int MIN_AGE = 12;

}
