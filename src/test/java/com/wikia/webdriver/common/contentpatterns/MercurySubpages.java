package com.wikia.webdriver.common.contentpatterns;

public class MercurySubpages {

  // Articles prepared for mercuryautomationtesting.wikia.com wiki
  public static final String MAIN_PAGE = "/wiki/Mercury_automation_testing_Wikia";
  public static final String GALLERY = "/wiki/Gallery";
  public static final String LINKED_IMAGES = "/wiki/LinkedImages";
  public static final String TOC = "/wiki/TOC";
  public static final String TOC_WITHOUT_H2 = "/wiki/TOCWithoutH2";
  public static final String TOC_WITH_PORTABLE_INFOBOX = "/wiki/TOC_with_portable_infobox";
  public static final String COMMENTS = "/wiki/Comments";
  public static final String MAP = "/wiki/Map";
  public static final String QUESTION_MARK = "/wiki/Question?mark?question";
  public static final String COLON = "/wiki/Colon:colon:colon";
  public static final String NO_INFOBOX = "/wiki/Article_without_infobox";

  public static final String INFOBOX_1 = "/wiki/Infobox1";
  public static final String INFOBOX_2 = "/wiki/Infobox2";
  public static final String INFOBOX_3 = "/wiki/Infobox3";
  public static final String INFOBOX_4 = "/wiki/Infobox4";
  public static final String INFOBOX_5 = "/wiki/Infobox5";

  public static final String CATEGORY_WITH_ARTICLE_AND_WITH_MEMBERS =
      "/wiki/Category:Category_page_test_-_Category_with_description";
  public static final String CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS =
      "/wiki/Category:Category_page_test_-_Category_with_description_and_no_members";
  public static final String CATEGORY_WITHOUT_ARTICLE_AND_WITH_MEMBERS =
      "/wiki/Category:Category_page_test_-_Category_without_description";
  public static final String ARTICLE_WITH_CATEGORY_COMPONENT = "/wiki/Category_test_001";
  public static final String ARTICLE_WITH_LINK_TO_CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS =
      "/wiki/Category_page_test_-_link_to_an_empty_category";

  /**
   * Legend:
   * FC - Featured Content
   * CC - Curated Content
   * TA - Trending Articles
   * TV - Trending Videos
   */

  // Articles prepared for mercurycc.wikia.com wiki (with FC, CC, TA and TV)
  public static final String CC_MAIN_PAGE = "/wiki/Mercury_CC_Wikia";
  public static final String CC_REDIRECT_SOURCE_1 = "/wiki/RedirectSource1";
  public static final String CC_REDIRECT_DESTINATION = "/wiki/RedirectDestination1";

  // Categories in mercurycc.wikia.com
  public static final String CC_CATEGORY_ARTICLES = "/main/category/Articles";
  public static final String CC_CATEGORY_BLOGS = "/main/category/Blog_posts";
  public static final String CC_CATEGORY_TEMPLATES = "/main/category/Templates";
  public static final String CC_EMPTY_CATEGORY = "/main/category/Qwerty";
  public static final String CC_CATEGORY_28_ITEMS = "/main/category/28itemsCategory";
  public static final String CC_CATEGORY_10_ITEMS = "/main/category/10itemsCategory";

  // Sections in mercurycc.wikia.com
  public static final String CC_SECTION_CATEGORIES = "/main/section/Categories";
  public static final String CC_NOT_EXISTING_SECTION = "/main/section/Qwerty";

  // Articles prepared for mercuryntacc.wikia.com wiki (with FC, CC and TV)
  public static final String NTACC_MAIN_PAGE = "/wiki/Mercuryntacc_Wikia";

  // Articles prepared for mercuryntvcc.wikia.com wiki (with FC, CC and TA)
  public static final String NTVCC_MAIN_PAGE = "/wiki/Mercuryntvcc_Wikia";

  // Articles prepared for mercuryntavcc.wikia.com wiki (with FC and CC)
  public static final String NTAVCC_MAIN_PAGE = "/wiki/Mercuryntavcc_Wikia";

  // Articles prepared for mercuryemptycc.wikia.com wiki (without FC, CC, TA and TV)
  public static final String ECC_MAIN_PAGE = "/wiki/Mercury_empty_CC_Wikia";

  // Articles on mlp.wikia.com
  public static final String MLP_MAIN_PAGE = "/wiki/My_Little_Pony_Friendship_is_Magic_Wiki";

  // Login and SignUp pages
  public static final String JOIN_PAGE = "/join";
  public static final String REGISTER_PAGE = "/register";

  private MercurySubpages() {
  }

}
