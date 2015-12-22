package com.wikia.webdriver.testcases.seotests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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
  private static final String TEST_WIKI_CHAT = "sktest123";

  @DataProvider
  private Object[][] dataHtmlTitleTest() {
    return new Object[][]{
        {
            TEST_WIKI_ENGLISH,
            "wiki/Sktest123_Wiki",
            "Sktest123 Wiki - Wikia",
            // SEO-194
            new TestSkipper(TestSkipper.VARIANT_ONLY_DESKTOP),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Style-5H2",
            "Style-5H2 - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Style-5H2?action=edit",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ONLY_ANONYMOUS),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Style-5H3?vaction=edit",
            "Style-5H3 - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Style-5H2?action=history",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Special:Version",
            "Version - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Special:Videos",
            "Videos on this wiki - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Special:NewFiles",
            "New files on this wiki - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Blog:Recent_posts",
            "Blog:Recent posts - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/User_blog:Sktest/test_blog_1",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Special:Forum",
            "Forum - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Board:General_Discussion",
            "General Discussion board - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Thread:2610",
            "Test post - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Category:Premium_Videos",
            "Category:Premium Videos - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Message_Wall:Sktest",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Thread:2160",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Special:Maps",
            "Maps - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Template:Welcome",
            "Template:Welcome - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/MediaWiki:Common.css",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/MediaWiki:Edit",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/User:Sktest",
            "User:Sktest - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/File:THE_HOBBIT_Trailer_-_2012_Movie_-_Official_HD",
            "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/File:Giant_prominence_on_the_sun_erupted.jpg",
            "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_ENGLISH,
            "wiki/Special:NonExistingSpecialPage",
            "Error - Sktest123 Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "wiki/WikiDex",
            "WikiDex - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "wiki/Lista_de_Pokémon",
            "Lista de Pokémon - WikiDex - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "wiki/Special:UnusedVideos",
            "Unused videos - WikiDex - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_NON_ENGLISH,
            "wiki/Categoría:Regiones",
            "Categoría:Regiones - WikiDex - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        // Corporate pages
        {
            TEST_WIKI_CORPORATE,
            "",
            "Wikia - The Home of Fandom",
            new TestSkipper(TestSkipper.VARIANT_ONLY_ANONYMOUS),
        },
        {
            TEST_WIKI_CORPORATE,
            "WAM",
            "Wikia Activity Monitor (WAM) - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_CORPORATE,
            "About",
            "About - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_CORPORATE_FR,
            "À_propos",
            "À propos - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ALL),
        },
        {
            TEST_WIKI_CURATED_CONTENT,
            "wiki/Main_Page",
            "Wookieepedia - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ONLY_MOBILE),
        },
        {
            TEST_WIKI_CURATED_CONTENT,
            "wiki/Droid_starfighter",
            "Droid starfighter - Wookieepedia - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ONLY_MOBILE),
        },
        {
            TEST_WIKI_CURATED_CONTENT,
            "main/category/Future_films",
            // SEO-198: Future films - Wookieepedia - Wikia
            "http://starwars.wikia.com/main/category/Future_films",
            // XW-868
            new TestSkipper(TestSkipper.VARIANT_ONLY_ANONYMOUS_MOBILE),
        },
        {
            TEST_WIKI_CURATED_CONTENT,
            "main/section/Films",
            // SEO-198: "Films - Wookieepedia - Wikia",
            "http://starwars.wikia.com/main/section/Films",
            // XW-868
            new TestSkipper(TestSkipper.VARIANT_ONLY_ANONYMOUS_MOBILE),
        },
        {
            TEST_WIKI_DISCUSSION,
            "d/f/3035/latest",
            "Fallout Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ONLY_MOBILE),
        },
        {
            TEST_WIKI_DISCUSSION,
            "d/p/2594243417559008375",
            "Fallout Wiki - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ONLY_MOBILE),
        },
        {
            TEST_WIKI_CHAT,
            "wiki/Special:Chat",
            // Starting from January 2016: "Chat - Sktest123 Wiki - Wikia",
            "Sktest123 Wiki: Welcome to the Sktest123 Wiki chat - Wikia",
            new TestSkipper(TestSkipper.VARIANT_ONLY_LOGGED_IN_DESKTOP),
        }
    };
  }

  /**
   * Check HTML title (the contents of <title>) for anon users
   */
  @Test(
      dataProvider = "dataHtmlTitleTest",
      groups = "SeoHtmlTitleLoggedOut"
  )
  public void HtmlTitleLoggedOutTest(String wiki, String path, String expectedTitle,
                                     TestSkipper skipper) {
    if (skipper.shouldSkip(false)) {
      return;
    }

    wikiURL = urlBuilder.getUrlForPathWithoutWiki(wiki, path);
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
  public void HtmlTitleLoggedInTest(String wiki, String path, String expectedTitle, TestSkipper skipper) {
    if (skipper.shouldSkip(true)) {
      return;
    }

    wikiURL = urlBuilder.getUrlForPathWithoutWiki(wiki, path);
    driver.get(wikiURL);
    String actualTitle = driver.getTitle();
    Assertion.assertEquals(actualTitle, expectedTitle);
  }
}
