package com.wikia.webdriver.common.contentpatterns;

import com.wikia.webdriver.common.core.url.UrlBuilder;

public class URLsContent {

  // Common url component
  public static final String WIKI_DIR = "/wiki/";
  public static final String WIKIA_DIR = "Wikia";

  // Special Urls - links to special pages
  public static final String USER_LOGIN = "/signin";
  public static final String USER_SIGNUP = "/register";
  public static final String USER_FORGOT_PASSWORD = "/forgot-password";
  public static final String SPECIAL_CREATE_NEW_WIKI = "/Special:CreateNewWiki";
  public static final String SPECIAL_WHERE_IS_EXTENSION = "/Special:WhereIsExtension";

  // Special Urls - used with WIKI_DIR
  public static final String SPECIAL_WHAT_LINKS_HERE = "Special:WhatLinksHere";
  public static final String SPECIAL_NEW_FILES = "Special:NewFiles";
  public static final String SPECIAL_VIDEOS = "Special:Videos";
  public static final String SPECIAL_UPLOAD = "Special:Upload";
  public static final String SPECIAL_UPLOAD_SZL = "Specjalna:Prze%C5%9Blij";
  public static final String SPECIAL_MULTIPLE_UPLOAD = "Special:MultipleUpload";
  public static final String SPECIAL_WATCHLIST = "Special:Watchlist";
  public static final String SPECIAL_PREFERENCES = "Special:Preferences";
  public static final String SPECIAL_RENAME_TOOL = "Special:UserRenameTool";
  public static final String
      SPECIAL_EDITING_PREFERENCES
      = "Special:Preferences#mw-prefsection-editing";
  public static final String
      SPECIAL_EDITING_PREFERENCES_EMAIL
      = "Special:Preferences#mw-prefsection-emailv2";
  public static final String SPECIAL_PROMOTE = "Special:Promote";
  public static final String SPECIAL_CREATE_PAGE = "Special:CreatePage";
  public static final String SPECIAL_CREATE_BLOGPAGE = "Special:CreateBlogPage";
  public static final String SPECIAL_ADMIN_DASHBOARD = "Special:AdminDashboard";
  public static final String SPECIAL_CSS = "Special:CSS";
  public static final String SPECIAL_CURATED_CONTENT = "Special:CuratedContent";
  public static final String SPECIAL_FOLLOW = "Special:Following";
  public static final String SPECIAL_FORUM = "Special:Forum";
  public static final String SPECIAL_THEME_DESIGNER = "Special:ThemeDesigner";
  public static final String SPECIAL_WIKI_ACTIVITY = "Special:WikiActivity";
  public static final String SPECIAL_EDIT_ACCOUNT = "Special:EditAccount";
  public static final String USER_MESSAGE_WALL = "Message_Wall:";
  public static final String SPECIAL_MULTI_WIKI_FINDER = "Special:Multiwikifinder";
  public static final String LOGOUT = "Special:UserLogout?noexternals=1";
  public static final String USER_PROFILE = "User:%userName%";
  public static final String SPECIAL_WIKI_FACTORY = "Special:WikiFactory";
  public static final String SPECIAL_CONTRIBUTIONS = "Special:Contributions";
  public static final String SPECIAL_BLOCK = "Special:Block";
  public static final String SPECIAL_CHAT = "Special:Chat";
  public static final String SPECIAL_EDIT_HUB = "Special:EditHub";
  public static final String SPECIAL_VIDEO_PAGE_ADMIN = "Special:VideoPageAdmin/edit";
  public static final String SPECIAL_VERSION = "Special:Version";
  public static final String SPECIAL_INFOBOX_BUILDER = "Special:InfoboxBuilder";
  public static final String SPECIAL_ANALYTICS = "Special:Analytics";
  public static final String SPECIAL_WIKI_FEATURES = "Special:WikiFeatures/";
  public static final String COMMUNITY_CENTRAL = "Community_Central";
  public static final String FANDOM_UNIVERSITY = "Fandom_University";
  public static final String NOT_A_VALID_COMMUNITY = "Community_Central:Not_a_valid_community";
  public static final String SPECIAL_ANONYMIZATION = "Special:RequestToBeForgottenInternal";

  // Mediawiki Urls
  public static final String MEDIAWIKI_CSS = "MediaWiki:Wikia.css";

  // Blog list page url
  public static final String BLOG_LIST = "Blog:%listName%/";
  public static final String BLOG_NAMESPACE = "User_blog:%userName%/";

  // Mediawiki template url
  public static final String TEMPLATE_NAMESPACE = "Template";

  //B Forum Board namespace
  public static final String FORUM_BOARD_NAMESPACE = "Board";

  // Extra switches - appear after ? in link
  public static final String NOEXTERNALS = "?noexternals=1";
  public static final String ACTION_EDIT = "action=edit";
  public static final String VEACTION_EDIT = "veaction=edit";
  public static final String USE_DEFAULT_FORMAT = "useFormat=1";
  public static final String ACTION_UNFOLLOW = "action=unwatch";
  public static final String ACTION_DELETE = "action=delete";
  public static final String DISABLE_CAPTCHA = "nocaptchatest=1";
  public static final String SOURCE_MODE = "useeditor=source";
  public static final String MOST_RECENT = "?sort=recent";
  public static final String ACTION_RAW = "action=raw";
  public static final String TEST_WIKI = "?istestwiki=1"; // used for disabling GTM survey

  // WAM Scores page url
  public static final String WAM_PAGE = "WAM";

  // replace %title% with new article name
  public static final String ADD_ARTICLE = "index.php?title=%title%&action=edit";
  public static final String API_URL = UrlBuilder.createUrlBuilder().getUrlForApiCalls() + "/api.php";

  // File Pages
  public static final String FILE_NAMESPACE = "File:";
  public static final String FILENAME_001 = "Grammy_Muppet_Critics.jpeg";

  // History Pages
  public static final String ACTION_HISTORY = "action=history";

  // languages
  public static final String TRANSLATABLE_LANGUAGE = "uselang=qqx";

  // External sites
  public static final String FACEBOOK_DOMAIN = "facebook.com";
  public static final String TWITTER_DOMAIN = "twitter.com";
  public static final String GOOGLE_DOMAIN = "accounts.google.com";
  public static final String REDDIT_DOMAIN = "reddit.com";

  // Facebook
  public static final String FACEBOOK_MAINPAGE = "https://www.facebook.com/";
  public static final String FACEBOOK_SETTINGSPAGE = "https://www.facebook.com/settings";
  public static final String
      FACEBOOK_SETTINGS_APP_TAB
      = "https://www.facebook.com/settings?tab=applications";

  // avatars
  public static final String AVATAR_GENERIC = "Avatar.jpg";

  // Urls for VE editor
  public static final String VE_ENABLED_WIKI = "vetest";
  public static final String VE_DISABLED_WIKI = "vedisabledtest";
  public static final String RTE_DISABLED_WIKI = "ckdisabledtest";
  public static final String VE_AND_RTE_DISABLED_WIKI = "veandckdisabledtest";
  public static final String TESTINGPAGE = "Testingpage";

  // Urls for different namespace pages
  public static final String CATEGORY_PAGE = "Category:General_wiki_templates";
  public static final String CATEGORY_HELP = "Category:Help";
  public static final String TEMPLATE_PAGE = "Template:";
  public static final String LIST_PAGE = "List:Listing";

  // Other wikis to test on
  public static final String MEDIAWIKI119_TEST_WIKI = "mediawiki119";
  public static final String VIDEO_TEST_WIKI = "sktest123";
  public static final String COMMUNITY_WIKI = "community";
  public static final String COMMUNITY_COUNCIL_WIKI = "communitycouncil";
  public static final String MUPPET_WIKI = "muppet";
  public static final String COMMUNITYTEST_WIKI = "communitytest";

  // For szl tests
  public static final String COMMUNITY_WIKI_SZL = "spolecznosc";
  public static final String COMMUNITY_CENTRAL_SZL = "Centrum_Spo%C5%82eczno%C5%9Bci";
  public static final String HUBS_SZL = "explore-pl?uselang=pl";
  public static final String SPECIAL_WATCHLIST_SZL = "Specjalna:Obserwowane";


  // External URL
  public static final String EXTERNAL_URL = "http://www.wikia.com";

  public static final String USER_SIGNOUT = EXTERNAL_URL + "/logout";

  // Recent wiki activity on mobile
  public static final String RECENT_WIKI_ACTIVITY = "/recent-wiki-activity";

  // Search results page on mobile, replace %query% with searched string
  public static final String MOBILE_SEARCH_RESULTS_PAGE = "/search?query=%query%";

  private URLsContent() {
  }
}
