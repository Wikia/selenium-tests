package com.wikia.webdriver.testcases.seotests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Our404HandlerTest extends NewTestTemplate {

  @java.lang.SuppressWarnings("all") // Lot's of duplication is fine in data providers
  @DataProvider
  private Object[][] testCasesForOur404Handler() {
    return new Object[][]{
        {"muppet", "sitemap-index.xml", "sitemap-index.xml"},
        {"muppet", "robots.txt", "robots.txt"},
        {"muppet", "Honeysuckle_Rose", "wiki/Honeysuckle_Rose"},
        {"pl.gta", "Special:Version", "wiki/Specjalna:Wersja"},
        {"pl.gta", "Special:Wersja", "wiki/Specjalna:Wersja"},
        {"pl.gta", "Specjalna:Version", "wiki/Specjalna:Wersja"},
        {"pl.gta", "Specjalna:Wersja", "wiki/Specjalna:Wersja"},
        {"www", "NoSuchPage", "NoSuchPage"},
        {"wowwiki", "NoSuchPage", "wiki/NoSuchPage"},
        {"muppet", "NoSuchPage", "wiki/NoSuchPage"},
        {"muppet", "wiki/Honeysuckle_Rose", "wiki/Honeysuckle_Rose"},
        {"www", "About", "About"},
// Does not work on Mercury (XW-727):
//        {"www", "wiki/About", "About"},
        {"muppet", "favicon.ico", "http://images.wikia.com/central/images/6/64/Favicon.ico"},
        {"muppet", "index.php?title=Honeysuckle_Rose", "index.php?title=Honeysuckle_Rose"},
        {"muppet", "index.php?title=NoSuchPage", "index.php?title=NoSuchPage"},
        {"muppet", "api.php", "api.php"},
        {"muppet", "Honeysuckle_Rose?action=edit", "wiki/Honeysuckle_Rose?action=edit"},
        {"wikiality", "Help:Achievements", "wiki/Help:Achievements"},
        {"kirkburn", "File:Penguin.png", "wiki/File:Penguin.png"},
        {"de.gta", "MediaWiki:Edit", "wiki/MediaWiki:Edit"},
        {"gta", "MediaWiki:Edit", "wiki/MediaWiki:Edit"},
    };
  }

  /**
   * Check the redirects work as expected
   */
  @Test(
      groups = {"Seo", "SeoOur404Handler"},
      dataProvider = "testCasesForOur404Handler"
  )
  public void testOur404Handler(String wiki, String inputPath, String expectedPath) {
    String wikiBase = urlBuilder.getUrlForWiki(wiki);
    String inputUrl = wikiBase + inputPath;
    String expectedUrl = wikiBase + expectedPath;

    // Special case for favicon:
    if (expectedPath.startsWith("http://")) {
      expectedUrl = expectedPath;
    }

    driver.get(inputUrl);
    String actualUrl = driver.getCurrentUrl();
    Assertion.assertEquals(actualUrl, expectedUrl);
  }
}
