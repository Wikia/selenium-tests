package com.wikia.webdriver.common.contentpatterns;

import com.wikia.webdriver.common.core.Global;

public class URLsContent {

  //api
  public static final String WIKIA_PHP = "wikia.php";

  public static final String FOLLOWING_TOOLBAR_TEST = "QAautoPage";

  // Common url component
  public static final String WIKI_DIR = "wiki/";
  public static final String WIKIA_DIR = "Wikia";

  // Hubs
  public static final String VIDEOGAMES_HUB_URL = "/Video_Games";
  public static final String ENTERTAINMENT_HUB_URL = "/Entertainment";
  public static final String LIFESTYLE_HUB_URL = "/Lifestyle";

  //Special Urls - links to special pages
  public static final String SPECIAL_USER_LOGIN = "wiki/Special:UserLogin";
  public static final String SPECIAL_USER_SIGNUP = "wiki/Special:UserSignup";
  public static final String SPECIAL_ADD_BLOG_LISTING_PAGE = "wiki/Special:CreateBlogListingPage";
  public static final String SPECIAL_NEW_FILES = "wiki/Special:NewFiles";
  public static final String SPECIAL_VIDEOS = "wiki/Special:Videos";
  public static final String SPECIAL_UPLOAD = "wiki/Special:Upload";
  public static final String SPECIAL_MULTIPLE_UPLOAD = "wiki/Special:MultipleUpload";
  public static final String SPECIAL_WATCHLIST = "wiki/Special:Watchlist";
  public static final String SPECIAL_PHALANX = "wiki/Special:Phalanx";
  public static final String SPECIAL_PHALANXTEST = "wiki/Special:Phalanx/test";
  public static final String SPECIAL_PREFERENCES = "wiki/Special:Preferences";
  public static final String
      SPECIAL_EDITING_PREFERENCES =
      "wiki/Special:Preferences#mw-prefsection-editing";
  public static final String SPECIAL_PROMOTE = "wiki/Special:Promote";
  public static final String SPECIAL_CREATE_PAGE = "wiki/Special:CreatePage";
  public static final String SPECIAL_CREATE_BLOGPAGE = "wiki/Special:CreateBlogPage";
  public static final String SPECIAL_ADMIN_DASHBOARD = "wiki/Special:AdminDashboard";
  public static final String SPECIAL_CSS = "wiki/Special:CSS";
  public static final String SPECIAL_RANDOM = "wiki/Special:Random";
  public static final String SPECIAL_FOLLOW = "wiki/Special:Following";
  public static final String SPECIAL_FORUM = "wiki/Special:Forum";
  public static final String SPECIAL_THEME_DESIGNER = "wiki/Special:ThemeDesigner";
  public static final String SPECIAL_WIKI_ACTIVITY = "wiki/Special:WikiActivity";
  public static final String SPECIAL_EDIT_ACCOUNT = "wiki/Special:EditAccount";
  public static final String USER_MESSAGE_WALL = "wiki/Message_Wall:";
  public static final String SPECIAL_MULTI_WIKI_FINDER = "wiki/Special:Multiwikifinder";
  public static final String LOGOUT = "wiki/Special:UserLogout?noexternals=1";
  public static final String LOGOUT_RETURNTO = "wiki/Special:UserLogout?returnto=";
  public static final String SPECIAL_UNDELETE = "wiki/Special:Undelete";
  public static final String USER_PROFILE = "wiki/User:%userName%";
  public static final String SPECIAL_CREATE_NEW_WIKI = "Special:CreateNewWiki";
  public static final String SPECIAL_WIKI_FACTORY = "wiki/Special:WikiFactory";
  public static final String SPECIAL_WHERE_IS_EXTENSION = "Special:WhereIsExtension";
  public static final String SPECIAL_CONTRIBUTIONS = "wiki/Special:Contributions";
  public static final String SPECIAL_BLOCKLIST = "wiki/Special:BlockList";
  public static final String SPECIAL_UNBLOCK = "wiki/Special:Unblock";
  public static final String SPECIAL_BLOCK = "wiki/Special:Block";
  public static final String SPECIAL_CHAT = "wiki/Special:Chat";
  public static final String SPECIAL_MANAGE_WIKIA_HOME = "wiki/Special:ManageWikiaHome";
  public static final String SPECIAL_UNUSED_FILES = "wiki/Special:UnusedFiles";
  public static final String SPECIAL_UNUSED_VIDEOS = "wiki/Special:UnusedVideos";
  public static final String SPECIAL_UNCATEGORIZED_FILES = "wiki/Special:UncategorizedFiles";
  public static final String SPECIAL_MOST_LINKED_FILES = "wiki/Special:MostLinkedFiles";
  public static final String SPECIAL_LICENSED_VIDEO_SWAP = "wiki/Special:LicensedVideoSwap";
  public static final String SPECIAL_EDIT_HUB = "wiki/Special:EditHub";
  public static final String SPECIAL_MAPS = "wiki/Special:Maps";
  public static final String SPECIAL_VIDEO_PAGE_ADMIN = "wiki/Special:VideoPageAdmin/edit";

  //Urls for mobile
  public static final String MOBILETEST_MAINPAGE = "wiki/Mobileregressiontesting_Wiki";
  public static final String ARTICLE_SECTIONS = "wiki/Sections";
  public static final String ARTICLE_MODAL = "wiki/Modal";
  public static final String ARTICLE_COMMENTS = "wiki/Article_comments";
  public static final String CATEGORY_PMG = "wiki/Category:PMG";
  public static final String ARTICLE_TOPBAR = "wiki/Topbar";
  public static final String GAMEGUIDES_CONTROLLER_QS = "controller=GameGuides";
  public static final String RENDER_FULL_QS = "method=renderFullPage";
  public static final String PAGENAME = "page=";

  //Mediawiki Urls
  public static final String MEDIAWIKI_CSS = "MediaWiki:Wikia.css";

  //Blog list page url
  public static final String BLOG_LIST = "wiki/Blog:%listName%/";
  public static final String BLOG_NAMESPACE = "wiki/User_blog:%userName%/";

  // Mediawiki template url
  public static final String TEMPLATE_NAMESPACE = "Template";
  public static final String TEMPLATE_URL = "wiki/" + TEMPLATE_NAMESPACE + ":%name%";

  // Extra switches - appear after ? in link
  public static final String NOEXTERNALS = "?noexternals=1";
  public static final String ACTION_EDIT = "action=edit";
  public static final String VEACTION_EDIT = "veaction=edit";
  public static final String USE_DEFAULT_FORMAT = "useFormat=1";
  public static final String WIKIA_TRACKER = "og_level=info";
  public static final String ACTION_UNFOLLOW = "action=unwatch";
  public static final String ACTION_DELETE = "action=delete";
  public static final String DISABLE_CAPTCHA = "nocaptchatest=1";
  public static final String SOURCE_MODE = "useeditor=source";
  public static final String REDLINK = "redlink=1";
  public static final String MOST_RECENT = "?sort=recent";

  // WAM Scores page url
  public static final String WAM_PAGE = "WAM";

  //Preview url for testing video suggestions
  public static final String
      VIDEO_SUGGESTIONS_URL =
      "http://preview.callofduty.wikia.com/wiki/Frank_Woods";

  // replace %title% with new article name
  public static final String ADD_ARTICLE = "index.php?title=%title%&action=edit";
  public static final String VIEW_ARTICLE = "index.php?title=%title%";
  public static final String API_URL = Global.DOMAIN + "api.php";

  // File Pages
  public static final String FILE_NAMESPACE = "File:";
  public static final String FILENAME_001 = "Grammy_Muppet_Critics.jpeg";
  public static final String FILENAME_002 = "New_Batman_Year_One_Clip";

  // History Pages
  public static final String ACTION_HISTORY = "action=history";

  // Article Pages
  public static final String ARTICLENAME_001 = "TestVid001";
  public static final String ARTICLENAME_002 = "TestVid002";
  public static final String ARTICLENAME_003 = "TestVid003";
  public static final String ARTICLENAME_004 = "TestVid004";

  public static final String SPECIAL_SEARCH = "wiki/Special:Search";

  //languages
  public static final String TRANSLATABLE_LANGUAGE = "uselang=qqx";

  //External sites
  public static final String FACEBOOK_DOMAIN = "facebook.com";
  public static final String TWITTER_DOMAIN = "twitter.com";
  public static final String GOOGLE_DOMAIN = "accounts.google.com";
  public static final String REDDIT_DOMAIN = "reddit.com";
  public static final String STUMPLEUPON_DOMAIN = "stumbleupon.com";

  //Facebook
  public static final String FACEBOOK_MAINPAGE = "http://www.facebook.com/";
  public static final String FACEBOOK_SETTINGSPAGE = "http://www.facebook.com/settings";
  public static final String FACEBOOK_SETTINGS_APP_TAB = "tab=applications";
  public static final String FACEBOOK_WIKIA_APP_ID = "112328095453510";
  public static final String FACEBOOK_WIKIA_APP_DEV_ID = "116800565037587";

  //avatars
  public static final String AVATAR_GENERIC = "Avatar.jpg";

  //Urls for VE editor
  public static final String VE_ENABLED_WIKI = "vetest";
  public static final String VE_DISABLED_WIKI = "vedisabledtest";
  public static final String RTE_DISABLED_WIKI = "ckdisabledtest";
  public static final String VE_AND_RTE_DISABLED_WIKI = "veandckdisabledtest";
  public static final String TESTINGPAGE = "Testingpage";

  //Urls for different namespace pages
  public static final String WIKI_MAINPAGE = "Main_Page";
  public static final String CATEGORY_PAGE = "Category:General_wiki_templates";
  public static final String TEMPLATE_PAGE = "Template:Infobox";
  public static final String LIST_PAGE = "List:Listing";
  public static final String MEDIAWIKI = "mediawiki119";

  //Other wikis to test on
  public static final String VIDEO_TEST_WIKI = "sktest123";
  public static final String COMMUNITY_WIKI = "community";

  //External URL
  public static final String EXTERNAL_URL = "http://www.wikia.com";

  //Embed map URL for Interactive Maps
  public static final String EMBEDED_MAP_EDITPAGE = "wiki/EmbedMap?action=edit";

  //Escaped frament URL for Maps
  public static final String ESCAPED_FRAGMENT = "?_escaped_fragment_";

  //Wikis with smart banners
  public static final String SMART_BANNER_GTA = "gta";
  public static final String SMART_BANNER_GLEE = "glee";
}
