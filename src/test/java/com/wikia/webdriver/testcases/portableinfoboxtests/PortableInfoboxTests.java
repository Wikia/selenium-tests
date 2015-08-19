package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts.WikiTextShortCutsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import org.testng.annotations.Test;

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

  @Test(groups = "PortableInfoboxTests_001")
  @Execute(onWikia = "mediawiki119")
  public void verifyElementsVisibility() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .areBoldElementsMoreThanOne()
        .areItalicElementsMoreThanOne()
        .areHeadersMoreThanOne()
        .verifyQuotationMarksPresence()
        .verifyReferencesPresence()
        .verifyImagePresence()
        .verifyInfoboxTitlePresence();
  }

  @Test(groups = "PortableInfoboxTests_002")
  @Execute(onWikia = "mediawiki119")
  public void verifyElementsRedirects() {
    PortableInfoboxPageObject info = new PortableInfoboxPageObject(driver);

    info
        .open(PageContent.PORTABLE_INFOBOX01)
        .clickRedLink(0)
        .verifyCreateNewArticleModal();

    String externalLinkName = info
        .open(PageContent.PORTABLE_INFOBOX01)
        .getExternalLinkRedirectTitle();

    String externalUrl = info
        .clickExternalLink()
        .getUrlFromExternalLinkaAfterPageIsLoaded();

    String internalLinkName = info
        .compareURLAndExternalLink(externalLinkName, externalUrl)
        .open(PageContent.PORTABLE_INFOBOX01)
        .getInternalLinkRedirectTitle(0);

    String internalURL = info
        .clickInternalLink(0)
        .getUrlFromInternalLinkaAfterPageIsLoaded();

    info.compareURLAndInternalLink(internalLinkName, internalURL);
  }

  @Test(groups = "PortableInfoboxTests_003")
  @Execute(onWikia = "mediawiki119")
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject(driver);
    String articleName = article
        .open(PageContent.PORTABLE_INFOBOX01)
        .getArticleName();

    article
        .openSpecialWhatLinksHere(wikiURL)
        .clickPageInputField()
        .typeInfoboxImageName(PageContent.FILE_IMAGE_NAME)
        .clickShowbutton()
        .verifyInfoboxArticleInList(articleName);
  }

  @Test(groups = "PortableInfoboxTests_004")
  @Execute(onWikia = "mediawiki119")
  public void verifyCategoriesInTemplateInvocation() {
    PortableInfoboxPageObject info = new PortableInfoboxPageObject(driver);
     ArticlePageObject article = new ArticlePageObject(driver)
            .open(PageContent.PORTABLE_INFOBOX01);

    SourceEditModePageObject src = info
        .navigateToArticleEditPageSrc(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE);
    String categoryName = src
        .focusTextArea()
        .getRandomDigits(9);

    WikiTextShortCutsComponentObject shortcuts = src
        .clickMore();

    shortcuts
        .clickCategory()
        .addContent(categoryName);
    src
        .clickPublishButtonInTemplateNamespace()
        .verifyCategoryInTemplatePage(categoryName);

    article.open(PageContent.PORTABLE_INFOBOX01);
    info.verifyCategoryInArticlePage(categoryName);
  }

  @Test(groups = "PortableInfoboxTests_006")
  @Execute(onWikia = "mediawiki119")
  public void verifyLightboxVisibilityAfterClickingImage() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .clickImage()
        .verifyLightboxPresence();
  }

  @Test(groups = "PortableInfoboxTests_008")
  @Execute(onWikia = "mediawiki119")
  public void verifyVisibilityOfTabberAndItsImages() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX02)
        .verifyTabberPresence()
        .verifyTabberImagePresence();
  }

  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  @Test(groups = "PortableInfoboxTests_009")
  public void verifyInfoboxLayoutChange() {
    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    ArticlePageObject article = new ArticlePageObject(driver);
    PortableInfoboxPageObject info = new PortableInfoboxPageObject(driver);

    theme
        .openSpecialDesignerPage(wikiURL)
        .selectTheme(4);
    theme
        .submitThemeSelection();

    String oldBackground = info
        .open(PageContent.PORTABLE_INFOBOX01)
        .getBackgroundColor();

    theme
        .openSpecialDesignerPage(wikiURL)
        .selectTheme(1);
    theme
        .submitThemeSelection();

    article.open(PageContent.PORTABLE_INFOBOX01);
    info.verifyChangedBackground(oldBackground, info.getBackgroundColor());
  }

  @Test(groups = "PortableInfoboxTests_012")
  @Execute(onWikia = "mediawiki119")
  public void verifyOrderedAndUnorderedLists() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX02)
        .compareFontSizesBetweenItemValueAndOrderedListItem(1)
        .compareFontSizesBetweenItemValueAndUnorderedListItem(1);
  }

  @Test(groups = "PortableInfoboxTests_013")
  @Execute(onWikia = "mediawiki119")
  public void verifyInfoboxCategoryLink() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .clickCategoryLink();
    new CategoryPageObject(driver)
        .verifyCategoryPageTitle();
  }

  @Test(groups = "PortableInfoboxTests_014")
  @Execute(onWikia = "mediawiki119")
  public void verifyHorizontalGroupFontSize() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .compareFontSizesBetweenHorizontalItemLabelAndItemLabel()
        .compareFontSizesBetweenHorizontalItemValueAndItemValue();
  }

  @Execute(asUser = User.USER_9, onWikia = "mediawiki119")
  @Test(groups = "PortableInfoboxTests_015")
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    TemplatePageObject template = new TemplatePageObject(driver);
    ArticlePageObject article = new ArticlePageObject(driver);

    String templateSyntax = template
        .openArticleByName(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE)
        .editArticleInSrcUsingDropdown()
        .copyContent();

    article
        .open("Random" + article.getRandomDigits(5))
        .openCurrectArticleSourceMode()
        .addContentInSourceMode(templateSyntax)
        .submitArticle();

    new PortableInfoboxPageObject(driver)
        .verifyImagePresence()
        .verifyInfoboxTitlePresence();
  }

  @Test(groups = "PortableInfoboxTests_016")
  @Execute(onWikia = "mediawiki119")
  public void verifyNavigationElementPadding() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .verifyPaddingNavigationElement(1);
  }

  @Test(groups = "PortableInfoboxTest_017")
  @Execute(onWikia = "mediawiki119")
  public void verifyGroupHeadersPadding() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .verifyGroupHeaderPadding(1);
  }

  @Test(groups = "PortableInfoboxTest_018")
  @Execute(onWikia = "mediawiki119")
  public void verifyDivsWrappersAreNotAppearing() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX01)
        .verifyDivsNotAppearingInImage()
        .verifyDivsNotAppearingInTitle()
        .verifyDivsNotAppearingInHeader(0);
  }

  @Test(groups = "PortableInfoboxTest_019")
  @Execute(onWikia = "mediawiki119")
  public void verifyEmptyTagsAreNotAppearing() {
    new PortableInfoboxPageObject(driver)
        .open(PageContent.PORTABLE_INFOBOX_EMPTY_TAGS)
        .verifyEmptyTags();
  }
}
