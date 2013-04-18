package com.wikia.webdriver.Common.ContentPatterns;

public class PageContent {
	//articles
	public static String articleNamePrefix = "QAarticle";
	public static String articleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	public static String articleTextEdit = "Brand new content";
	public static String commentText = "Lorem ipsum dolor sit amet, comment";
	public static String commentTextEdit = "Brand new comment";
	public static String replyText = "Brand new reply";
	//blogs
	public static String blogPostNamePrefix = "blogPost";
	public static String blogContent = "blogContent";
	public static String blogContentEdit = "blogContentEdit";
	public static String blogComment = "blogComment";
	public static String blogCommentReply = "blogCommentReply";
	public static String blogCommentEdit = "blogCommentEdit";
	//categories
	public static String categoryPageNamePrefix = "QACategoryTest";
	public static String categoryNamePrefix = "TestCategory";
	public static String jsAlertMessage = "Unhandled syntax detected - switching back to visual mode impossible.";
	//generic
	public static String caption = "QAWebdriverCaption1";
	public static String caption2 = "QAWebdriverCaption2";
	public static String wikiaContributor = "A Wikia contributor";
	//create new wiki
	public static String wikiNamePrefix = "QaTestWiki";
	public static String wikiTopic = "Duis quam ante, fringilla at cursus tristique, laoreet vel elit. Nullam rhoncus, magna ut dictum ultrices, mauris lectus consectetur tellus, sed dignissim elit justo vel ante.";
	public static String wikiCategory = "Auto";
	//image serving
	public static String file = "Image001.jpg";
	public static String[] listOfFiles = {"Image001.jpg","Image002.jpg", "Image003.jpg", "Image004.jpg", "Image005.jpg", "Image006.jpg", "Image007.jpg", "Image008.jpg", "Image009.jpg", "Image010.jpg"};

	//message wall
	public static String messageWallTitlePrefix = "QAMessageWallTitle";
	public static String messageWallTitleEditPrefix = "QAMessageWallTitleEdit";
	public static String messageWallMessagePrefix = "QAMessageWallMessage";
	public static String messageWallMessageEditPrefix = "QAMessageWallMessage";
	public static String messageWallMessageNonLatinPrefix = "Гсторыя śćąęłńó";
	
	//top 10 list
	public static String top10Description = "DescriptionForList";
	public static String top10TestListQAfull = "Top_10_list:TestListQA";
	public static String top10TestListQAshort = "TestListQA";
	public static String top10listNamePrefix = "Top10list";
	public static String relatedPageName = "PageToCheckTop10ListFetching";
	//links
	public static String externalLink = "www.wikia.com";
	public static String internalLink = "Formatting";
	
	//forum
	public static String forumTitlePrefix = "QABoardTitle ";
	public static String forumTitleEditPrefix = "QABoardTitleEdit ";
	public static String forumTitle40CharPrefix = "This forum title is forty c";
	public static String forumTitleSlashPrefix = "QA/boardTitle";
	public static String forumTitleUnderScorePrefix = "QA_boardTitle";	
	public static String forumTitleNonlatinPrefix = "查爾斯";	
	public static String forumDescriptionPrefix = "Duis quam ante, fringilla at cursus tristique ";
	public static String forumDescriptionEditPrefix = "Duis quam ante, fringilla at cursus tristique edit ";
	public static String forumMessage = "QAforumMessage";
	public static String forumBoard = "QAboardForMoveThreadTest";

        //Messages
        public static String articleDeletedMessage = "This page has been deleted.";
        public static String createNewBlogPostMessage = "Create a new blog post";
        public static String loginRequired = "Login required";
        public static String notLoggedInMessage = "Not logged in";
        public static String newPasswordSentMessage = "We've sent a new password to the email address for %userName%.";
        public static String phalanxBlockMessage = "The page you wanted to save was blocked by the spam filter.";
        public static String phalanxBlockTitleMessage = "Sorry, the page title was rejected by our spam filter. Please use a different title.";

        //Blocks for Phalanx
        public static String titleFilterPlain = "BadTitle";

	//wikiText
	public static String wikiTextPhoto = "[[File:Image009.jpg|thumb|%s]]";
	
	public static String wikiaGlobalUrl = "http://www.wikia.com/";

}
