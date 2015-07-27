package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.PortableInfobox;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileCategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.testng.annotations.Test;

import java.util.Locale;

/**
 * Created by Rodriuki on 12/06/15.
 * Set of Test Cases found on https://one.wikia-inc.com/wiki/Portable_Infoboxes_Test_Plan
 *
 * TC01: Verify elements visibility: infobox title, image, headers, italic, bold, quotation marks, references
 * TC02: Verify correct redirects in mediawiki119.wikia.com/wiki/RodriInfobox01 for:
 * external links, internal links, red links
 * TC03: Verify images used in infoboxes appear in Special:WhatLinksHere page
 * TC04: Verify adding a category to infobox markup and then invoking that template in an article
 * page will display category in categories section at the bottom of the page automatically
 *
 * Created by nikodamn 20/07/15
 * TC06: Verify lightbox opens when clicking infobox image
 * TC08: Verify visibility of tabber and it's images
 * TC09: Verify infobox color has changed after changing colors in wiki Theme Designer
 * TC12: Verify if ordered and unordered lists are parsed correctly after adding them
 * TC13: Verify category links inside infoboxes
 */
public class PortableInfoboxTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_001"})
  public void verifyElementsVisibility() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    Assertion.assertTrue(info.getBoldElements().size() > 0);
    Assertion.assertTrue(info.getItalicElements().size() > 0);
    Assertion.assertTrue(info.getHeaderElements().size() > 0);
//    info.verifyQuotationMarksPresence();
//    info.verifyReferencesPresence();
    info.verifyImagePresence();
    info.verifyInfoboxTitlePresence();

  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_002"})
  public void verifyElementsRedirects() {
    ArticlePageObject article = new ArticlePageObject(driver);
    //Red link
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.clickRedLink();
    info.verifyCreateNewArticleModal();
    //External Link
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    info = article.getInfoboxPage();
    String externalLinkName = info.getExternalLinkRedirectTitle();
    info.clickExternalLink();
    String externalNavigatedURL = info.getCurrentUrl();
    info.compareURLAndExternalLink(externalLinkName, externalNavigatedURL);
    //Internal Link
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    info = article.getInfoboxPage();
    String internalLinkName = info.getInternalLinkRedirectTitle();
    info.clickInternalLink();
    String internalNavigatedURL = info.getCurrentUrl();
    info.compareURLAndInternalLink(internalLinkName, internalNavigatedURL);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_003"})
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    String articleName = info.getNameForArticle();
//    SpecialWhatLinksHerePageObject links = article.openSpecialWhatLinksHere(wikiURL);
//    links.clickPageInputField();
//    links.typeInfoboxImageName("FILE:" + PageContent.FILE_IMAGE_NAME);
//    links.clickShowbutton();
//    links.verifyInfoboxArticleInList(articleName);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_004"})
  public void verifyCategoriesInTemplateInvocation() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    SourceEditModePageObject src = info.navigateToArticleEditPageSrc(wikiURL, PageContent.PORTABLE_INFOBOX_WEBSITE_TEMPLATE);
    String catName = src.getRandomDigits(9);
    src.addCategoryToSourceCode(catName);
    TemplatePageObject temp = src.clickPublishButtonInTemplateNamespace();
    temp.verifyCategoryInTemplatePage(catName);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    info.verifyCategoryInArticlePage(catName);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_006"})
  public void verifyLightboxVisibilityAfterClickingImage() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.clickImage();
    info.verifyLightboxPresence();
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_008"})
  public void verifyVisibilityOfTabberAndItsImages() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.verifyTabberPresence();
    info.verifyTabberImagePresence();
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_009"})
  public void verifyInfoboxLayoutChange() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
    String textThemeDesigner = toolbar.getThemeDesignerText();
    toolbar.clickOnTool(textThemeDesigner);
    SpecialThemeDesignerPageObject special = new SpecialThemeDesignerPageObject(driver);
    special.openSpecialDesignerPage(wikiURL);
    special.selectTheme(1);
    special.submitThemeSelection();
    String oldBackground = info.getBackgroundColor();
    toolbar.clickOnTool(textThemeDesigner);
    special.openSpecialDesignerPage(wikiURL);
    special.selectTheme(4);
    special.submitThemeSelection();
    String newBackground = info.getBackgroundColor();
    info.verifyChangedBackground(oldBackground, newBackground);

  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_012"})
  public void verifyOrderedAndUnorderedLists() {
    ArticlePageObject article = new ArticlePageObject(driver);
    TemplatePageObject template = article.openTemplatePage(wikiURL, "Template");
    SourceEditModePageObject editor = template.clickCreate();
    editor.addContent(PortableInfobox.INFOBOX_TEMPLATE);
    editor.clickPublishButton();
    PortableInfoboxPageObject info = article.getInfoboxPage();
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    editor = article.editArticleInSrcUsingDropdown();
    editor.addContent(PortableInfobox.INFOBOX_INVOCATION);
    editor.clickPublishButton();
    info.verifyOrderedListPresence();
    info.verifyUnorderedListPresence();
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfoboxTests_013"})
  public void verifyInfoboxCategoryLink() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openArticleByName(wikiURL, PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.clickCategoryLink();
    CategoryPageObject category = new CategoryPageObject(driver);
    String categoryName = category.getCategoryName();
    category.verifyCategoryPageTitle(categoryName);
  }

}

