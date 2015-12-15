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

  // TODO: QAART-681 Sonar should not report duplicate strings warnings for data providers
  private static final String TEST_WIKI_FR = "fr";
  private static final String TEST_WIKI_PL = "pl";
  private static final String TEST_WIKI_WWW = "www";
  private static final String TEST_WIKI_ES_POKEMON = "es.pokemon";
  private static final String TEST_WIKI_SKTEST123 = "sktest123";
  private static final String TEST_WIKI_POZNAN = "poznan";
  private static final String TEST_WIKI_STARWARS = "starwars";
  private static final String TEST_WIKI_FALLOUT = "fallout";

  Credentials credentials = Configuration.getCredentials();

  @DataProvider
  private Object[][] dataHtmlTitleTest() {
    return new Object[][]{
        // Original title
        {
            TEST_WIKI_SKTEST123,
            "Sktest123_Wiki",
            "Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Style-5H2",
            "Style-5H2 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Style-5H2?action=edit",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Style-5H3?vaction=edit",
            "Style-5H3 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Style-5H2?action=history",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Special:Version",
            "Version - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Special:Videos",
            "Videos on this wiki - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Special:NewFiles",
            "New files on this wiki - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Blog:Recent_posts",
            "Blog:Recent posts - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "User_blog:Sktest/test_blog_1",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Special:Forum",
            "Forum - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Board:General_Discussion",
            "General Discussion board - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Thread:2610",
            "Test post - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Category:Premium_Videos",
            "Category:Premium Videos - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Message_Wall:Sktest",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Thread:2160",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Special:Maps",
            "Maps - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Template:Welcome",
            "Template:Welcome - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "MediaWiki:Common.css",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "MediaWiki:Edit",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "User:Sktest",
            "User:Sktest - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "File:THE_HOBBIT_Trailer_-_2012_Movie_-_Official_HD",
            "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "File:Giant_prominence_on_the_sun_erupted.jpg",
            "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia",
        },
        {
            TEST_WIKI_SKTEST123,
            "Special:NonExistingSpecialPage",
            "Error - Sktest123 Wiki - Wikia",
        },
        // Em-dash instead of regular dash for some languages
        {
            TEST_WIKI_POZNAN,
            "Ulica_Kazimierza_Wielkiego",
            "Ulica Kazimierza Wielkiego - Poznańska Wiki - Wikia",
        },
        // Custom title
        {
            TEST_WIKI_ES_POKEMON,
            "WikiDex",
            "WikiDex - Wikia",
        },
        {
            TEST_WIKI_ES_POKEMON,
            "Lista_de_Pokémon",
            "Lista de Pokémon - WikiDex - Wikia",
        },
        {
            TEST_WIKI_ES_POKEMON,
            "Special:UnusedVideos",
            "Unused videos - WikiDex - Wikia",
        },
        {
            TEST_WIKI_ES_POKEMON,
            "Categoría:Regiones",
            "Categoría:Regiones - WikiDex - Wikia",
        },
        // Corporate pages
        {
            TEST_WIKI_WWW,
            "",
            "Wikia",
        },
        /* MAIN-5823 {
            TEST_WIKI_WWW,
            "WAM",
            "Wikia Activity Monitor (WAM) - Wikia",
        },*/
        {
            TEST_WIKI_WWW,
            "About",
            "About - Wikia",
        },
        {
            TEST_WIKI_FR,
            "À_propos",
            "À propos - Wikia",
        },
        {
            TEST_WIKI_PL,
            "",
            "Wikia",
        },
    };
  }

    @DataProvider
    private Object[][] dataHtmlTitleMercuryTest() {
        return new Object[][]{
            {
                TEST_WIKI_STARWARS,
                "wiki/Main_Page",
                "Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_STARWARS,
                "wiki/Droid_starfighter",
                "Droid starfighter - Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_STARWARS,
                "main/category/Future_films",
                "Future films - Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_STARWARS,
                "main/section/Films",
                "Films - Wookieepedia, the Star Wars Wiki - Wikia",
            },
            {
                TEST_WIKI_FALLOUT,
                "d/f/3035/latest",
                "The Fallout wiki - Fallout: New Vegas and more - Wikia",
            },
            {
                TEST_WIKI_FALLOUT,
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
