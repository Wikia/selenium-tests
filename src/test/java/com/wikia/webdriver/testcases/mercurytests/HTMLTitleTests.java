package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Head;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HTMLTitleTests extends NewTestTemplate {

  /**
   * [0] wikiName
   * [1] path
   * [2] expected title
   */
  private String[][] testCases = {
      {
          "sktest123",
          "/wiki/Sktest123_Wiki",
          "Sktest123 Wiki - Wikia"
      },
      {
          "sktest123",
          "/wiki/Style-5H2",
          "Style-5H2 - Sktest123 Wiki - Wikia"
      },
      {
          "sktest123",
          "/wiki/TestDisplayTitle",
          "testing abc - Sktest123 Wiki - Wikia"
      },
      {
          "sktest123",
          "/wiki/Category:Premium_Videos",
          "Category:Premium Videos - Sktest123 Wiki - Wikia"
      },
      {
          "sktest123",
          "/wiki/Category:Non-premium_Videos",
          "Category:Non-premium Videos - Sktest123 Wiki - Wikia"
      },
      {
          "sktest123",
          "/wiki/Category:Premium",
          "Category:PremiumVideos - Sktest123 Wiki - Wikia"
      },
      {
          "es.pokemon",
          "/wiki/WikiDex",
          "WikiDex - Wikia"
      },
      {
          "es.pokemon",
          "/wiki/Lista_de_Pokémon",
          "Lista de Pokémon - WikiDex - Wikia"
      },
      {
          "es.pokemon",
          "/wiki/Categoría:Regiones",
          "Categoría:Regiones - WikiDex - Wikia"
      },
      {
          "starwars",
          "/wiki/Main_Page",
          "Wookieepedia - Wikia"
      },
      {
          "starwars",
          "/wiki/Droid_starfighter",
          "Droid starfighter - Wookieepedia - Wikia"
      },
      {
          "starwars",
          "/main/category/Future_films",
          "Future films - Wookieepedia - Wikia"
      },
      {
          "starwars",
          "/main/section/Films",
          "Films - Wookieepedia - Wikia"
      },
      {
          "mediawiki119",
          "/d/f/203236/latest",
          "Discussions - Mediawiki 1.19 test Wiki - Wikia"
      },
      {
          "mediawiki119",
          "/d/p/2706859396041803285",
          "Discussions - Mediawiki 1.19 test Wiki - Wikia"
      },
      {
          "mediawiki119",
          "/d/u/27334045",
          "Discussions - Mediawiki 1.19 test Wiki - Wikia"
      },
      {
          "dnd4",
          "/wiki/Dungeons_&_Dragons",
          "Dungeons & Dragons - D&D4 Wiki - Wikia"
      }
  };

  private Head head;
  private Navigate navigate;

  @BeforeMethod(alwaysRun = true)
  private void init() {
    this.head = new Head();
    this.navigate = new Navigate();
  }

  @Test(groups = {"mercury_htmlTitleSet", "Mercury_htmlTitleSet"})
  public void mercury_htmlTitleSet() {
    for (String[] testCase : testCases) {
      String testUrl = urlBuilder.appendQueryStringToURL
          (new Page(testCase[0], testCase[1]).getUrl(),
           "cb=" + DateTime.now().getMillis());

      navigate.toUrl(testUrl);
      String actualTitle = head.getDocumentTitle();

      Assertion.assertEquals(actualTitle, testCase[2]);
    }
  }

  @Test(groups = "seo_guard")
  @Execute(asUser = User.USER)
  public void seo_guard() {
    String originalEnv = Configuration.getEnv();

    try {
      PrintWriter out = new PrintWriter("./logs/seo-guard.txt");
      PrintWriter out2 = new PrintWriter("./logs/seo-guard2.txt");

      for (String[] testCase : testCases) {
        navigate.toUrl(new Page(testCase[0], testCase[1]).getUrl());
        new Wait(driver).forElementVisible(By.cssSelector(".side-nav-toggle-2016"));
        this.pushMetaTagsToFile(out);

        if (!originalEnv.equals("prod")) {
          Configuration.setTestValue("env", "prod");

          navigate.toUrl(new Page(testCase[0], testCase[1]).getUrl());
          new Wait(driver).forElementVisible(By.cssSelector(".side-nav-toggle-2016"));
          this.pushMetaTagsToFile(out2);

          Configuration.setTestValue("env", originalEnv);
        }
      }

      out.close();
      out2.close();
    } catch (FileNotFoundException e) {}
  }

  private void pushMetaTagsToFile(PrintWriter file) {
    file.println("==============================================================================");
    file.println(driver.getCurrentUrl());
    file.println("==============================================================================");
    List<WebElement> metaTags =  driver.findElements(
        By.cssSelector("head meta, head title, head link"));
    ArrayList<String> headData = new ArrayList<>();

    for (WebElement metaTag : metaTags) {
      headData.add(metaTag.getAttribute("outerHTML"));
    }

    Collections.sort(headData);

    for (String item : headData) {
      file.println(item);
    }

    file.println("==============================================================================\n");
  }
}
