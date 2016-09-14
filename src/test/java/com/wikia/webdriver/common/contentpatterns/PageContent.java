package com.wikia.webdriver.common.contentpatterns;

import java.io.File;

public class PageContent {

  //wiki
  public static final String LOREM_IPSUM_SHORT = "Lorem ipsum dolor";
  public static final String
      LOREM_IPSUM_LONG =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit "
      + "Lorem ipsum dolor sit amet, consectetur adipiscing Lorem ipsum dolor sit amet, consectetur "
      + "adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing Lorem ipsum dolor sit amet,"
      + " consectetur adipiscing elit Lorem ipsum dolor sit adipiscing";

  //articles
  public static final String ARTICLE_NAME_PREFIX = "QAarticle";
  public static final String
      ARTICLE_TEXT =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
  public static final String ARTICLE_TEXT_EDIT = "Brand new content";
  public static final String ARTICLE_TEXT_SECOND_EDIT =
      "Ut enim ad minim veniam, quis nostrud exercitation " +
      "ullamco laboris nisi ut aliquip ex ea commodo consequat.";
  public static final String COMMENT_TEXT = "Lorem ipsum dolor sit amet, comment";
  public static final String REPLY_TEXT = "Brand new reply";

  //articles with TOC
  public static final String ARTICLE_WITH_TOC_LINES =
      "==First Heading==\n" +
      "text of the sub-heading number 2\n" +
      "==Second heading==\n" +
      "text of the second heading\n" +
      "===sub-heading number 1===\n" +
      "text of the sub-heading number 1\n" +
      "===sub-heading number 2===\n" +
      "text of the sub-heading number 2\n";

  //blogs
  public static final String BLOG_POST_NAME_PREFIX = "blogPost";
  public static final String BLOG_CONTENT = "blogContent";

  //categories
  public static final String CATEGORY_NAME_PREFIX = "Cat";

  //generic
  public static final String CAPTION = "TestDataCaption1";
  public static final String CAPTION2 = "TestDataCaption2";
  public static final String WIKIA_CONTRIBUTOR = "A Wikia contributor";

  //image serving
  public static final String FILE = "Image001.jpg";
  public static final String FILEPNG = "Image001.png";
  public static final String FILE2PNG = "Image002.png";
  public static final String SMALLFILE = "Image011-Small-image.png";
  public static final String BROKENEXTENSIONFILE = "broken-extension-image.fer";

  //image storage
  public static final String FILERENAME = "Image003.jpg";
  public static final String ARTICLESOURCEMODEFILE = "VE_ContributeDropDown.png";

  public static final String[] LIST_OF_FILES = {
      "Image001.jpg", "Image002.jpg", "Image003.jpg", "Image004.jpg", "Image005.jpg",
      "Image006.jpg", "Image007.jpg", "Image008.jpg", "Image009.jpg", "Image010.jpg"
  };
  public static final String IMAGE_UPLOAD_RESOURCES_PATH =
      "." + File.separator + "src" + File.separator +
      "test" + File.separator + "resources" + File.separator +
      "ImagesForUploadTests" + File.separator;

  //message wall
  public static final String MESSAGE_WALL_TITLE_PREFIX = "QAMessageWallTitle";
  public static final String MESSAGE_WALL_MESSAGE_PREFIX = "QAMessageWallMessage";
  public static final String MESSAGE_WALL_MESSAGE_EDIT_PREFIX = "QAMessageWallEditMessage";
  public static final String MESSAGE_WALL_QUOTE_PREFIX = "QAQuote";

  //links
  public static final String EXTERNAL_LINK = "http://www.wikia.com/";
  public static final String INTERNAL_LINK = "Home";
  public static final String REDIRECT_LINK = "Formatting";
  public static final String TEXT_LINK = "qaLink";
  public static final String REDLINK = "QAasdfasjsad123213lj";
  public static final String DISCUSSIONS_LINK = "/d/";

  //forum
  public static final String FORUM_TITLE_PREFIX = "QABoardTitle%s";
  public static final String FORUM_TITLE_EDIT_PREFIX = "QABoardTitleEdit ";
  public static final String FORUM_TITLE_40_CHAR_PREFIX = "QABoardlongtitle 40 forty c";
  public static final String FORUM_TITLE_SLASH_PREFIX = "QABoard/Title";
  public static final String FORUM_TITLE_UNDER_SCORE_PREFIX = "QABoard_Title";
  public static final String FORUM_TITLE_NON_LATIN_PREFIX = "QABoard查爾斯";
  public static final String
      FORUM_DESCRIPTION_PREFIX =
      "Duis quam ante, fringilla at cursus tristique ";
  public static final String
      FORUM_DESCRIPTION_EDIT_PREFIX =
      "Duis quam ante, fringilla at cursus tristique edit ";
  public static final String FORUM_MESSAGE = "QAforumMessage%s";
  public static final String FORUM_BOARD = "QABoardForMoveThreadTest";
  public static final String CLOSE_REASON = "QA reason";

  //Messages
  public static final String LOGIN_REQUIRED = "Login required";
  public static final String NOT_LOGGED_IN_MESSAGE = "Not logged in";
  public static final String
      NEW_PASSWORD_SENT_MESSAGE =
      "We've sent a new password to the email address for %userName%.";
  public static final String
      PHALANX_BLOCK_TITLE_MESSAGE =
      "Sorry, the page title was rejected by our spam filter. Please use a different title.";
  public static final String
      SIGN_UP_TOO_YOUNG_MESSAGE =
      "Sorry, we're not able to register your account at this time.";
  public static final String
      SIGN_UP_INVALID_CAPTCHA_MESSAGE =
      "The word you entered didn't match the word in the box, try again!";
  public static final String
      SIGN_UP_USER_EXISTS_MESSAGE =
      "Someone already has this username. Try a different one!";

  //wikiText
  public static final String WIKI_TEXT_PHOTO = "[[File:%photoName%|thumb|%s]]";

  //SignUp correct
  public static final String WIKI_SIGN_UP_BIRTHDAY = "11";
  public static final String WIKI_SIGN_UP_BIRTHMONTH = "11";
  public static final String WIKI_SIGN_UP_BIRTHYEAR = "1954";
  public static final int MIN_AGE = 12;

  //Hero Module edit field default message
  public static final String
      WIKIA_HERO_PROMOTE_MESSAGE =
      "Add a summary that will promote your wikia in searches and on hubs.";

  //Portable Infoboxes content
  public static final String PORTABLE_INFOBOX_02 = "Infobox4Automation01";
  public static final String PORTABLE_INFOBOX_01 = "Infobox4Automation02";
  public static final String PORTABLE_INFOBOX_EMPTY_TAGS = "Infobox4Automation09";
  public static final String PI_TEMPLATE_WEBSITE_SIMPLE = "Template:Infobox_Website_Simple";
  public static final String FILE_IMAGE_NAME = "File:WallPaperHD_138.jpg";

  //Customized toolbar
  public static final String FOLLOW = "follow";

  private PageContent() {
  }
}
