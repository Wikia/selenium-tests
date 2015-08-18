package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts.WikiTextShortCutsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rodriuki on 12/06/15. Set of Test Cases found on
 * https://one.wikia-inc.com/wiki/Portable_Infoboxes_Test_Plan
 *
 * TC01: Verify elements visibility: infobox title, image, headers, italic, bold, quotation marks,
 * references
 * TC02: Verify correct redirects in mediawiki119.wikia.com/wiki/RodriInfobox01 for:
 * external links, internal links, red links
 * TC03: Verify images used in infoboxes appear in Special:WhatLinksHere page
 * TC04: Verify adding a category to infobox markup and then invoking that template in an article
 * page will display category in categories section at the bottom of the page automatically
 *
 * Created by nikodamn 20/07/15
 * TC05: Verify lightbox opens when clicking infobox image
 * TC06: Verify visibility of tabber and it's images
 * TC07: Verify infobox color has changed after changing colors in wiki Theme Designer
 * TC08: Verify if ordered and unordered lists are parsed correctly after adding them
 * TC13: Verify category links inside infoboxes
 * TC14: Verify if horizontal group font size matches other elements font
 * TC15: Copy syntax from template page to article and verify presence of all new information
 * provided
 * TC16: Verify if navigation element has same left and right padding
 * TC17: Verify if group headers and titles has same left and right padding
 * TC18: Additional <div> wrappers from title, header and image HTML are removed
 * TC19: Verify that any of the tags which do not have a value won't appear
 */
@Test(groups = "PortableInfoboxTests")
public class PortableInfoboxTests extends NewTestTemplate {

  private final static Logger LOGGER = Logger.getLogger(PortableInfoboxTests.class.getName());

  // TODO: compress this test
  @Test(groups = "PortableInfoboxTests_001")
  public void verifyElementsVisibility() {
    PortableInfoboxPageObject info = new ArticlePageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .getInfoboxPage();

    Assertion.assertTrue(info.getBoldElements().size() > 0);
    Assertion.assertTrue(info.getItalicElements().size() > 0);
    Assertion.assertTrue(info.getHeaderElements().size() > 0);
    info.verifyQuotationMarksPresence();
    info.verifyReferencesPresence();
    info.verifyImagePresence();
    info.verifyInfoboxTitlePresence();
  }

  // TODO: compress this test
  @Test(groups = "PortableInfoboxTests_002")
  public void verifyElementsRedirects() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.open(PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    //Verify if red link redirects
    info.clickRedLink(0);
    info.verifyCreateNewArticleModal();
    //Verify if external link redirects
    article.open(PageContent.PORTABLE_INFOBOX01).getInfoboxPage();
    String externalLinkName = info.getExternalLinkRedirectTitle();
    info.clickExternalLink();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException exception) {
      LOGGER.log(Level.SEVERE, "Exception occur", exception);
    }
    String externalNavigatedURL = driver.getCurrentUrl();
    info.compareURLAndExternalLink(externalLinkName, externalNavigatedURL);
    //Verify if internal link redirects
    article.open(PageContent.PORTABLE_INFOBOX01);
    article.getInfoboxPage();
    String internalLinkName = info.getInternalLinkRedirectTitle(0);
    info.clickInternalLink(0);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException exception) {
      LOGGER.log(Level.SEVERE, "Exception occur", exception);
    }
    String internalNavigatedURL = driver.getCurrentUrl();
    info.compareURLAndInternalLink(internalLinkName, internalNavigatedURL);
  }

  // TODO: compress this test
  @Test(groups = "PortableInfoboxTests_003")
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01);
    String articleName = article.getArticleName();
    SpecialWhatLinksHerePageObject links = article.openSpecialWhatLinksHere(wikiURL);
    links.clickPageInputField();
    links.typeInfoboxImageName(PageContent.FILE_IMAGE_NAME);
    links.clickShowbutton();
    links.verifyInfoboxArticleInList(articleName);
  }

  // TODO: compress this test
  @Test(groups = "PortableInfoboxTests_004")
  public void verifyCategoriesInTemplateInvocation() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.open(PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    SourceEditModePageObject
        src =
        info.navigateToArticleEditPageSrc(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE);
    src.focusTextArea();
    String catName = src.getRandomDigits(9);
    WikiTextShortCutsComponentObject shortcuts = src.clickMore();
    src = shortcuts.clickCategory();
    src.addContent(catName);
    TemplatePageObject temp = src.clickPublishButtonInTemplateNamespace();
    temp.verifyCategoryInTemplatePage(catName);
    article.open(PageContent.PORTABLE_INFOBOX01);
    info.verifyCategoryInArticlePage(catName);
  }

  @Test(groups = "PortableInfoboxTests_006")
  public void verifyLightboxVisibilityAfterClickingImage() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .clickImage()
        .verifyLightboxPresence();
  }

  @Test(groups = "PortableInfoboxTests_008")
  public void verifyVisibilityOfTabberAndItsImages() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX02)
        .verifyTabberPresence()
        .verifyTabberImagePresence();
  }

  // TODO: compress this test
  @Execute(asUser = User.STAFF)
  @Test(groups = "PortableInfoboxTests_009")
  public void verifyInfoboxLayoutChange() {
    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    ArticlePageObject article = new ArticlePageObject(driver);
    theme.openSpecialDesignerPage(wikiURL);
    theme.selectTheme(4);
    theme.submitThemeSelection();
    article.open(PageContent.PORTABLE_INFOBOX01);
    PortableInfoboxPageObject info = article.getInfoboxPage();
    String oldBackground = info.getBackgroundColor();

    theme.openSpecialDesignerPage(wikiURL);
    theme.selectTheme(1);
    theme.submitThemeSelection();
    article.open(PageContent.PORTABLE_INFOBOX01);
    info.verifyChangedBackground(oldBackground, info.getBackgroundColor());
  }

  @Test(groups = "PortableInfoboxTests_012")
  public void verifyOrderedAndUnorderedLists() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX02)
        .compareFontSizesBetweenItemValueAndOrderedListItem(1)
        .compareFontSizesBetweenItemValueAndUnorderedListItem(1);
  }

  @Test(groups = "PortableInfoboxTests_013")
  public void verifyInfoboxCategoryLink() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .clickCategoryLink();
    new CategoryPageObject(driver)
        .verifyCategoryPageTitle();
  }

  @Test(groups = "PortableInfoboxTests_014")
  public void verifyHorizontalGroupFontSize() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .compareFontSizesBetweenHorizontalItemLabelAndItemLabel()
        .compareFontSizesBetweenHorizontalItemValueAndItemValue();
  }

  // TODO: compress this test
  @Execute(asUser = User.USER_9)
  @Test(groups = "PortableInfoboxTests_015")
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    TemplatePageObject template = new TemplatePageObject(driver);
    template.openArticleByName(wikiURL,
                               PageContent.PI_TEMPLATE_WEBSITE_SIMPLE);
    ArticlePageObject article = new ArticlePageObject(driver);
    SourceEditModePageObject editor = template.editArticleInSrcUsingDropdown();
    String templateSyntax = editor.copyContent();
    ArticlePageObject randomArticle = article.open("Random" + article.getRandomDigits(5));
    SourceEditModePageObject newEditor = randomArticle.openCurrectArticleSourceMode();
    newEditor.addContentInSourceMode(templateSyntax);
    newEditor.submitArticle();
    PortableInfoboxPageObject info = article.getInfoboxPage();
    info.verifyImagePresence();
    info.verifyInfoboxTitlePresence();
  }

  @Test(groups = "PortableInfoboxTests_016")
  public void verifyNavigationElementPadding() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .verifyPaddingNavigationElement(1);
  }

  @Test(groups = "PortableInfoboxTest_017")
  public void verifyGroupHeadersPadding() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .verifyGroupHeaderPadding(1);
  }

  @Test(groups = "PortableInfoboxTest_018")
  public void verifyDivsWrappersAreNotAppearing() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .verifyDivsNotAppearingInImage()
        .verifyDivsNotAppearingInTitle()
        .verifyDivsNotAppearingInHeader(0);
  }

  @Test(groups = "PortableInfoboxTest_019")
  public void verifyEmptyTagsAreNotAppearing() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX_EMPTY_TAGS)
        .verifyEmptyTags();
  }
}
