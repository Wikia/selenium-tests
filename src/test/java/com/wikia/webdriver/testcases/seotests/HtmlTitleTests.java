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

  // TODO: QAART-681 Sonar should not report duplicate strings warnings for data providers
  private static final String TEST_WIKI_CORPORATE_FR = "fr";
  private static final String TEST_WIKI_CORPORATE_PL = "pl";
  private static final String TEST_WIKI_CORPORATE = "www";
  private static final String TEST_WIKI_CUSTOM_TITLE = "es.pokemon";
  private static final String TEST_WIKI_ORIGINAL_TITLE = "sktest123";
  private static final String TEST_WIKI_ORIGINAL_TITLE_WITH_EM_DASH = "poznan";
  private static final String TEST_WIKI_CURATED_CONTENT = "starwars";
  private static final String TEST_WIKI_DISCUSSION = "fallout";


  Credentials credentials = Configuration.getCredentials();

  @DataProvider
  private Object[][] dataHtmlTitleTest() {
    return new Object[][]{
        // Original title
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Sktest123_Wiki",
            "Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H2",
            "Style-5H2 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H2?action=edit",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H3?vaction=edit",
            "Style-5H3 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Style-5H2?action=history",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Version",
            "Version - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Videos",
            "Videos on this wiki - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:NewFiles",
            "New files on this wiki - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Blog:Recent_posts",
            "Blog:Recent posts - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "User_blog:Sktest/test_blog_1",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Forum",
            "Forum - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Board:General_Discussion",
            "General Discussion board - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Thread:2610",
            "Test post - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Category:Premium_Videos",
            "Category:Premium Videos - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Message_Wall:Sktest",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Thread:2160",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:Maps",
            "Maps - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Template:Welcome",
            "Template:Welcome - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "MediaWiki:Common.css",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "MediaWiki:Edit",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "User:Sktest",
            "User:Sktest - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "File:THE_HOBBIT_Trailer_-_2012_Movie_-_Official_HD",
            "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "File:Giant_prominence_on_the_sun_erupted.jpg",
            "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ORIGINAL_TITLE,
            "Special:NonExistingSpecialPage",
            "Error - Sktest123 Wiki - Wikia",
        },
        // Em-dash instead of regular dash for some languages
        {
            TEST_WIKI_ORIGINAL_TITLE_WITH_EM_DASH,
            "Ulica_Kazimierza_Wielkiego",
            "Ulica Kazimierza Wielkiego – Poznańska Wiki – Wikia",
        },
        // Custom title
        {
            TEST_WIKI_CUSTOM_TITLE,
            "WikiDex",
            "WikiDex, la enciclopedia Pokémon - Wikia",
        },
        {
            TEST_WIKI_CUSTOM_TITLE,
            "Lista_de_Pokémon",
            "Lista de Pokémon - WikiDex, la enciclopedia Pokémon - Wikia",
        },
        {
            TEST_WIKI_CUSTOM_TITLE,
            "Special:UnusedVideos",
            "Unused videos - WikiDex, la enciclopedia Pokémon - Wikia",
        },
        {
            TEST_WIKI_CUSTOM_TITLE,
            "Categoría:Regiones",
            "Categoría:Regiones - WikiDex, la enciclopedia Pokémon - Wikia",
        },
        // Corporate pages
        {
            TEST_WIKI_CORPORATE,
            "",
            "Collaborative communities for everyone! - Wikia",
        },
        {
            TEST_WIKI_CORPORATE,
            "WAM",
            "Wikia Activity Monitor (WAM) - Wikia",
        },
        {
            TEST_WIKI_CORPORATE,
            "About",
            "About - Wikia",
        },
        {
            TEST_WIKI_CORPORATE_FR,
            "À_propos",
            "À propos - Wikia",
        },
        {
            TEST_WIKI_CORPORATE_PL,
            "",
            "Wikia po polsku – Wikia",
        },
    };
  }

    @DataProvider
    private Object[][] dataHtmlTitleMercuryTest() {
        return new Object[][]{
            {
                TEST_WIKI_CURATED_CONTENT,
                "wiki/Main_Page",
                "Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_CURATED_CONTENT,
                "wiki/Droid_starfighter",
                "Droid starfighter - Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_CURATED_CONTENT,
                "main/category/Future_films",
                "Future films - Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_CURATED_CONTENT,
                "main/section/Films",
                "Films - Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_DISCUSSION,
                "d/f/3035/latest",
                "The Fallout wiki - Fallout: New Vegas and more - Wikia",
            },
            {
                TEST_WIKI_DISCUSSION,
                "d/p/2594243417559008375",
                "The Fallout wiki - Fallout: New Vegas and more - Wikia",
            },
        };
    }

  /**
   * Check HTML title (the contents of <title>) for anon users
   */
  @Test(
      dataProvider = "dataHtmlTitleTest",
      groups = {"Seo", "SeoHtmlTitle", "SeoHtmlTitleLoggedOut"}
  )
  public void HtmlTitleLoggedOutTest(String wiki, String path, String expectedTitle) {
    wikiURL = urlBuilder.getUrlForPath(wiki, path);
    driver.get(wikiURL);
    String actualTitle = driver.getTitle();
    Assertion.assertEquals(actualTitle, expectedTitle);
  }

  /**
   * Check HTML title (the contents of <title>) for logged in users
   */
  @Test(
      dataProvider = "dataHtmlTitleTest",
      groups = {"Seo", "SeoHtmlTitle", "SeoHtmlTitleLoggedIn"}
  )
  public void HtmlTitleLoggedInTest(String wiki, String path, String expectedTitle) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, wikiURL);

    wikiURL = urlBuilder.getUrlForPath(wiki, path);
    driver.get(wikiURL);
    String actualTitle = driver.getTitle();
    Assertion.assertEquals(actualTitle, expectedTitle);
  }

  /**
   * Check HTML title (the contents of <title>) for Mercury
   */
  @Test(
      dataProvider = "dataHtmlTitleMercuryTest",
      groups = {"Seo", "SeoHtmlTitle", "SeoHtmlTitleMercury"}
  )
  public void HtmlTitleMercuryTest(String wiki, String path, String expected) {
    wikiURL = urlBuilder.getUrlForPathWithoutWiki(wiki, path);
    driver.get(wikiURL);
    String title = driver.getTitle();
    Assertion.assertEquals(title, expected);
  }

}
