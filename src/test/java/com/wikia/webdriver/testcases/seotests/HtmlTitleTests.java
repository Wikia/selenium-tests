package com.wikia.webdriver.testcases.seotests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @ownership Seo
 */
public class HtmlTitleTests extends NewTestTemplate {

  private static final String TEST_WIKI_CUSTOM_TITLE = "es.pokemon";
  private static final String TEST_WIKI_ORIGINAL_TITLE = "sktest123";

  Credentials credentials = Configuration.getCredentials();

  @DataProvider
  private Object[][] dataHtmlTitleTest001() {
    return new Object[][]{
        // original title
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Sktest123_Wiki",
            "Sktest123 Wiki - Wikia",
            "Sktest123 Wiki - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H2",
            "Style-5H2 - Sktest123 Wiki - Wikia",
            "Style-5H2 - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H2?action=edit",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H3?vaction=edit",
            "Style-5H3 - Sktest123 Wiki - Wikia",
            "Style-5H3 - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H2?action=history",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Version",
            "Version - Sktest123 Wiki - Wikia",
            "Version - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Videos",
            "Videos on this wiki - Sktest123 Wiki - Wikia",
            "Videos on this wiki - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:NewFiles",
            "New files on this wiki - Sktest123 Wiki - Wikia",
            "New files on this wiki - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Blog:Recent_posts",
            "Blog:Recent posts - Sktest123 Wiki - Wikia",
            "Blog:Recent posts - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "User_blog:Sktest/test_blog_1",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Forum",
            "Forum - Sktest123 Wiki - Wikia",
            "Forum - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Board:General_Discussion",
            "General Discussion board - Sktest123 Wiki - Wikia",
            "General Discussion board - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Thread:2610",
            "Test post - Sktest123 Wiki - Wikia",
            "Test post - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Category:Premium_Videos",
            "Premium Videos - Sktest123 Wiki - Wikia",
            "Premium Videos - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Message_Wall:Sktest",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Thread:2160",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Maps",
            "Maps - Wikia",
            "Maps - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Template:Welcome",
            "Template:Welcome - Sktest123 Wiki - Wikia",
            "Template:Welcome - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "MediaWiki:Common.css",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "MediaWiki:Edit",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "User:Sktest",
            "User:Sktest - Sktest123 Wiki - Wikia",
            "User:Sktest - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "File:THE_HOBBIT_Trailer_-_2012_Movie_-_Official_HD",
            "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia",
            "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia"
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "File:Giant_prominence_on_the_sun_erupted.jpg",
            "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia",
            "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia"
        },
        // custom title
        {
            TEST_WIKI_CUSTOM_TITLE,
            "WikiDex",
            "WikiDex, la enciclopedia Pokémon - Wikia",
            "WikiDex - WikiDex - Wikia"
        },
        {
            TEST_WIKI_CUSTOM_TITLE,
            "Lista_de_Pokémon",
            "Lista de Pokémon - WikiDex, la enciclopedia Pokémon - Wikia",
            "Lista de Pokémon - WikiDex - Wikia"
        },
        {
            TEST_WIKI_CUSTOM_TITLE,
            "Special:Version",
            "Versión - WikiDex, la enciclopedia Pokémon - Wikia",
            "Versión - WikiDex, la enciclopedia Pokémon - Wikia"
        },
        {
            TEST_WIKI_CUSTOM_TITLE,
            "Categoría:Regiones",
            "Regiones - WikiDex, la enciclopedia Pokémon - Wikia",
            "Regiones - Wikia"
        },
        {
            "wikia",
            "About",
            "About - Wikis from Wikia - Join the best wiki communities - Wikia",
            "About - Wikis from Wikia - Join the best wiki communities - Wikia"
        },
        {
            "wikia",
            "WAM",
            "Wikia Activity Monitor (WAM) - Wikia.com/WAM - Wikia",
            "Wikia Activity Monitor (WAM) - Wikia.com/WAM - Wikia"
        },
    };
  }

  @DataProvider
  private Object[][] dataHtmlTitleTest002() {
    return new Object[][]{
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Chat",
            "Sktest123 Wiki: Welcome to the Sktest123 Wiki chat - Wikia"
        },
     };
  }

  /**
   * Check html title for anon users (the contents of <title>)
   */
  @Test(
      dataProvider = "dataHtmlTitleTest001",
      groups = {"Seo", "SeoHtmlTitle", "SeoHtmlTitleTest_001"}
  )
  public void HtmlTitleTest_001(String wiki, String path, String expDesktop, String expMercury) {
    String exp;
    if ("CHROMEMOBILEMERCURY".equalsIgnoreCase(Configuration.getBrowser())) {
      exp = expMercury;
    } else {
      exp = expDesktop;
    }

    wikiURL = urlBuilder.getUrlForPath(wiki, path);
    driver.get(wikiURL);
    String title = driver.getTitle();
    Assertion.assertEquals(title, exp);
  }

  /**
   * Check html title for logged in users (desktop only)
   */
  @Test(
      dataProvider = "dataHtmlTitleTest002",
      groups = {"Seo", "SeoHtmlTitle", "SeoHtmlTitleTest_002"}
  )
  public void HtmlTitleTest_002(String wiki, String path, String exp) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, wikiURL);

    wikiURL = urlBuilder.getUrlForPath(wiki, path);
    driver.get(wikiURL);
    String title = driver.getTitle();
    Assertion.assertEquals(title, exp);
  }

}
