package com.wikia.webdriver.testcases.seotests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"Seo", "SeoHtmlTitle"})
public class HtmlTitleTests extends NewTestTemplate {

  private static final String TEST_WIKI_CORPORATE = "www";
  private static final String TEST_WIKI_CORPORATE_FR = "fr";
  private static final String TEST_WIKI_ENGLISH = "sktest123";
  private static final String TEST_WIKI_NON_ENGLISH = "es.pokemon";
  private static final String TEST_WIKI_CURATED_CONTENT = "starwars";
  private static final String TEST_WIKI_DISCUSSION = "fallout";

  Credentials credentials = Configuration.getCredentials();

  @DataProvider
  private Object[][] dataHtmlTitleTest() {
    return new Object[][]{
        {
            TEST_WIKI_ENGLISH,
            "Sktest123_Wiki",
            "Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Style-5H2",
            "Style-5H2 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Style-5H2?action=edit",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Style-5H3?vaction=edit",
            "Style-5H3 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Style-5H2?action=history",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Special:Version",
            "Version - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Special:Videos",
            "Videos on this wiki - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Special:NewFiles",
            "New files on this wiki - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Blog:Recent_posts",
            "Blog:Recent posts - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "User_blog:Sktest/test_blog_1",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Special:Forum",
            "Forum - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Board:General_Discussion",
            "General Discussion board - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Thread:2610",
            "Test post - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Category:Premium_Videos",
            "Category:Premium Videos - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Message_Wall:Sktest",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Thread:2160",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Special:Maps",
            "Maps - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Template:Welcome",
            "Template:Welcome - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "MediaWiki:Common.css",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "MediaWiki:Edit",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "User:Sktest",
            "User:Sktest - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "File:THE_HOBBIT_Trailer_-_2012_Movie_-_Official_HD",
            "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "File:Giant_prominence_on_the_sun_erupted.jpg",
            "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_ENGLISH,
            "Special:NonExistingSpecialPage",
            "Error - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "WikiDex",
            "WikiDex - Wikia",
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "Lista_de_Pokémon",
            "Lista de Pokémon - WikiDex - Wikia",
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "Special:UnusedVideos",
            "Unused videos - WikiDex - Wikia",
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "Categoría:Regiones",
            "Categoría:Regiones - WikiDex - Wikia",
        },
        // Corporate pages
        {
            TEST_WIKI_CORPORATE,
            "",
            "Wikia - The Home of Fandom",
        },
        /* MAIN-5823 {
            TEST_WIKI_CORPORATE,
            "WAM",
            "Wikia Activity Monitor (WAM) - Wikia",
        },*/
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
      groups = "SeoHtmlTitleLoggedOut"
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
      groups = "SeoHtmlTitleLoggedIn"
  )
  @Execute(asUser = User.USER)
  public void HtmlTitleLoggedInTest(String wiki, String path, String expectedTitle) {
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
      groups = "SeoHtmlTitleMercury"
  )
  public void HtmlTitleMercuryTest(String wiki, String path, String expected) {
    wikiURL = urlBuilder.getUrlForPathWithoutWiki(wiki, path);
    driver.get(wikiURL);
    String title = driver.getTitle();
    Assertion.assertEquals(title, expected);
  }
}
