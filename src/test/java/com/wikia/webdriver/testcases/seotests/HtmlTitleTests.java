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

  Credentials credentials = Configuration.getCredentials();

  @DataProvider
  private Object[][] dataHtmlTitleTest001() {
    return new Object[][]{
        // original title
        {
            "sktest123",
            "",
            "Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Style-5H2",
            "Style-5H2 - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Style-5H2?action=edit",
            "Editing Style-5H2 - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Style-5H2?vaction=edit",
            "Style-5H2 - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Style-5H2?action=history",
            "Revision history of \"Style-5H2\" - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Special:Version",
            "Version - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Special:Videos",
            "Videos on this wiki - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Special:NewFiles",
            "New files on this wiki - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Blog:Recent_posts",
            "Blog:Recent posts - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "User_blog:Sktest/test_blog_1",
            "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Special:Chat",
            "Sktest123 Wiki: Welcome to the Sktest123 Wiki chat - Wikia",
            true
        },
        {
            "sktest123",
            "Special:Forum",
            "Forum - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Board:General_Discussion",
            "General Discussion board - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Thread:2610",
            "Test post - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Category:Premium_Videos",
            "Premium Videos - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Message_Wall:Sktest",
            "Message Wall:Sktest - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Thread:2160",
            "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "Special:Maps",
            "Maps - Wikia",
            false
        },
        {
            "sktest123",
            "Template:Welcome",
            "Template:Welcome - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "MediaWiki:Common.css",
            "MediaWiki:Common.css - Sktest123 Wiki - Wikia",
            false
        },
        {
            "sktest123",
            "MediaWiki:Edit",
            "MediaWiki:Edit - Sktest123 Wiki - Wikia",
            false
        },
        // custom title
        {
            "es.pokemon",
            "",
            "WikiDex, la enciclopedia Pokémon - Wikia",
            false
        },
        {
            "es.pokemon",
            "Lista_de_Pokémon",
            "Lista de Pokémon - WikiDex, la enciclopedia Pokémon - Wikia",
            false
        },
        {
            "es.pokemon",
            "Special:Version",
            "Versión - WikiDex, la enciclopedia Pokémon - Wikia",
            false
        },
        {
            "es.pokemon",
            "Categoría:Regiones",
            "Regiones - WikiDex, la enciclopedia Pokémon - Wikia",
            false
        },
        {
            "wikia",
            "About",
            "About - Wikis from Wikia - Join the best wiki communities - Wikia",
            false
        },
    };
  }

  /**
   * Check html title (the contents of <title>)
   */
  @Test(
      dataProvider = "dataHtmlTitleTest001",
      groups = {"Seo", "SeoHtmlTitle", "SeoHtmlTitleTest_001"}
  )
  public void HtmlTitleTest_001(String wiki, String path, String exp, boolean loggedInUser) {
    if (loggedInUser) {
      WikiBasePageObject base = new WikiBasePageObject(driver);
      base.loginAs(credentials.userName, credentials.password, wikiURL);
    }

    if (path.isEmpty()) {
      wikiURL = urlBuilder.getUrlForWiki(wiki);
    } else {
      wikiURL = urlBuilder.getUrlForPath(wiki, path);
    }

    driver.get(wikiURL);
    String title = driver.getTitle();
    Assertion.assertEquals(title, exp);
  }
}
