package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import org.testng.annotations.Test;

/**
 * Created by Rodriuki on 12/06/15.
 * Set of Test Cases found on https://one.wikia-inc.com/wiki/Portable_Infoboxes_Test_Plan
 *
 * TC01: Verify elements visibility: infobox title, image, headers, italic, bold, 0 values, quotation marks, references
 * TC02: Verify correct redirects in mediawiki119.wikia.com/wiki/RodriInfobox01 for:
 * external links, internal links, red links
 * TC03: Verify images used in infoboxes appear in Special:WhatLinksHere page
 * TC04: Verify adding a category to infobox markup and then invoking that template in an article
 * page will display category in categories section at the bottom of the page automatically
 *
 */
public class PortableInfoboxTests extends NewTestTemplate{

  Credentials credentials = config.getCredentials();

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_001"})
  public void verifyElementsVisibility() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, "Infobox4Automation02");
    PortableInfoboxPageObject info = article.getInfoboxPage();
    Assertion.assertTrue(info.getBoldElements().size() > 0);
    Assertion.assertTrue(info.getItalicElements().size() > 0);
    Assertion.assertTrue(info.getHeaderElements().size() > 0);
    info.verifyImagePresence();
    info.verifyInfoboxTitlePresence();

  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_002"})
  public void verifyElementsRedirects() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, "Infobox4Automation02");
    PortableInfoboxPageObject info = article.getInfoboxPage();
    String externalLinkName = info.getExternalLinkRedirectTitle();
    info.clickExternalLink();
    String externalNavigatedURL = .getURL();
    article.openArticleByName(wikiURL, "Infobox4Automation02");
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.compareURLAndExternalLink(externalLinkName, externalNavigatedURL);
    String internalLinkName = info.getInternalLinkRedirectTitle();
    info.clickInternalLink();
    String internalNavigatedURL = .getURL();
    article.openArticleByName(wikiURL, "Infobox4Automation02");
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.compareURLAndInternalLink(internalLinkName, internalNavigatedURL);



    @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_003"})
    public void verifyImagesInWhatLinksHerePage() {
      ArticlePageObject article = new ArticlePageObject(driver);
      article.openArticleByName(wikiURL, "Infobox4Automation02");
      PortableInfoboxPageObject info = article.getInfoboxPage();
      String articleName = info.getNameForArticle();
      SpecialWhatLinksHerePageObject links = info.openArticleByName(wikiURL, "Special:WhatLinksHere");
      links.clickPageInputField();
      links.typeInfoboxImageName();
      links.clickShowbutton();
      links.verifyInfoboxArticleInList(articleName);
    }

    @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_004"})
    public void verifyCategoriesInTemplateInvocation() {
      ArticlePageObject article = new ArticlePageObject(driver);
      article.openArticleByName(wikiURL, "Infobox4Automation02");
      PortableInfoboxPageObject info = article.getInfoboxPage();
      SourceEditModePageObject src = info.navigateToArticleEditPageSrc(wikiURL, "Template:Infobox_Website")
      String catName = src.getRandomDigits(9);
      src.addCategoryToSourceCode(catName);
      TemplatePageObject temp = src.clickPublishButton();
      temp.verifyCategoryInTemplatePage(catName);
      info = temp.openArticleByName(wikiURL, "Infobox4Automation02");
      info.verifyCategoryInArticlePage(catName);
    }
}
