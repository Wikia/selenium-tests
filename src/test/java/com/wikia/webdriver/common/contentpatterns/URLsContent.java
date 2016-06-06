package com.wikia.webdriver.common.contentpatterns;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

public class URLsContent {

  // Common url component
  public static final String WIKI_DIR = "wiki/";
  public static final String WIKIA_DIR = "Wikia";

  // Special Urls - links to special pages
  public static final String SPECIAL_USER_LOGIN = "wiki/Special:UserLogin";
  public static final String SPECIAL_USER_SIGNUP = "wiki/Special:UserSignup";
  public static final String SPECIAL_NEW_FILES = "wiki/Special:NewFiles";
  public static final String SPECIAL_VIDEOS = "wiki/Special:Videos";
  public static final String SPECIAL_UPLOAD = "wiki/Special:Upload";
  public static final String SPECIAL_MULTIPLE_UPLOAD = "wiki/Special:MultipleUpload";
  public static final String SPECIAL_WATCHLIST = "wiki/Special:Watchlist";
  public static final String SPECIAL_PREFERENCES = "wiki/Special:Preferences";
  public static final String SPECIAL_EDITING_PREFERENCES =
      "wiki/Special:Preferences#mw-prefsection-editing";
  public static final String SPECIAL_EDITING_PREFERENCES_EMAIL =
      "wiki/Special:Preferences#mw-prefsection-emailv2";
  public static final String SPECIAL_PROMOTE = "wiki/Special:Promote";
  public static final String SPECIAL_CREATE_PAGE = "wiki/Special:CreatePage";
  public static final String SPECIAL_CREATE_BLOGPAGE = "wiki/Special:CreateBlogPage";
  public static final String SPECIAL_ADMIN_DASHBOARD = "wiki/Special:AdminDashboard";
  public static final String SPECIAL_CSS = "wiki/Special:CSS";
  public static final String SPECIAL_CURATED_CONTENT = "wiki/Special:CuratedContent";
  public static final String SPECIAL_FOLLOW = "wiki/Special:Following";
  public static final String SPECIAL_FORUM = "wiki/Special:Forum";
  public static final String SPECIAL_THEME_DESIGNER = "wiki/Special:ThemeDesigner";
  public static final String SPECIAL_WIKI_ACTIVITY = "wiki/Special:WikiActivity";
  public static final String SPECIAL_EDIT_ACCOUNT = "wiki/Special:EditAccount";
  public static final String USER_MESSAGE_WALL = "wiki/Message_Wall:";
  public static final String SPECIAL_MULTI_WIKI_FINDER = "wiki/Special:Multiwikifinder";
  public static final String LOGOUT = "wiki/Special:UserLogout?noexternals=1";
  public static final String USER_PROFILE = "wiki/User:%userName%";
  public static final String SPECIAL_CREATE_NEW_WIKI = "Special:CreateNewWiki";
  public static final String SPECIAL_WIKI_FACTORY = "wiki/Special:WikiFactory";
  public static final String SPECIAL_WHERE_IS_EXTENSION = "Special:WhereIsExtension";
  public static final String SPECIAL_CONTRIBUTIONS = "wiki/Special:Contributions";
  public static final String SPECIAL_BLOCK = "wiki/Special:Block";
  public static final String SPECIAL_CHAT = "wiki/Special:Chat";
  public static final String SPECIAL_EDIT_HUB = "wiki/Special:EditHub";
  public static final String SPECIAL_MAPS = "wiki/Special:Maps";
  public static final String SPECIAL_VIDEO_PAGE_ADMIN = "wiki/Special:VideoPageAdmin/edit";
  public static final String SPECIAL_WHAT_LINKS_HERE = "Special:WhatLinksHere";
  public static final String SPECIAL_VERSION = "wiki/Special:Version";
  public static final String SPECIAL_INFOBOX_BUILDER = "wiki/Special:InfoboxBuilder/";
  public static final String SPECIAL_WIKI_FEATURES = "wiki/Special:WikiFeatures/";

  // Mediawiki Urls
  public static final String MEDIAWIKI_CSS = "MediaWiki:Wikia.css";

  // Blog list page url
  public static final String BLOG_LIST = "wiki/Blog:%listName%/";
  public static final String BLOG_NAMESPACE = "wiki/User_blog:%userName%/";

  // Mediawiki template url
  public static final String TEMPLATE_NAMESPACE = "Template";

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

  // WAM Scores page url
  public static final String WAM_PAGE = "WAM";

  // replace %title% with new article name
  public static final String ADD_ARTICLE = "index.php?title=%title%&action=edit";
  public static final String API_URL = new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
      + "api.php";

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
  public static final String STUMPLEUPON_DOMAIN = "stumbleupon.com";

  // Facebook
  public static final String FACEBOOK_MAINPAGE = "http://www.facebook.com/";
  public static final String FACEBOOK_SETTINGSPAGE = "http://www.facebook.com/settings";
  public static final String FACEBOOK_SETTINGS_APP_TAB =
      "http://www.facebook.com/settings?tab=applications";

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
  public static final String VIDEO_TEST_WIKI = "sktest123";
  public static final String COMMUNITY_WIKI = "community";

  // External URL
  public static final String EXTERNAL_URL = "http://www.wikia.com";

  // Embed map URL for Interactive Maps
  public static final String EMBEDED_MAP_ARTICLE = "EmbedMap";

  // Escaped frament URL for Maps
  public static final String ESCAPED_FRAGMENT = "?_escaped_fragment_";

  // Recent wiki activity on mobile
  public static final String RECENT_WIKI_ACTIVITY = "recent-wiki-activity";

  // Search results page on mobile, replace %query% with searched string
  public static final String MOBILE_SEARCH_RESULTS_PAGE = "search?query=%query%";

  private URLsContent() {
  }
}
