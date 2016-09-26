package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.TemplateContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.ArticlePurger;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AceEditor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.Test;

/**
 * Set of Test Cases found on:
 * https://wikia-inc.atlassian.net/wiki/display/WW/Portable+Infoboxes+tests+plan
 */

@Execute(onWikia = "mediawiki119")
public class PortableInfoboxTests extends NewTestTemplate {

  private static final ContentLoader loader = new ContentLoader();
  private static final String
      INFOBOX_EMPTY_TAGS_INVOCATION = loader.loadWikiTextContent("Infobox_Empty_Tags_Invocation");
  private static final String
      INFOBOX2_INVOCATION = loader.loadWikiTextContent("Infobox2_Invocation");
  private static final String
      INFOBOX2_TEMPLATE = loader.loadWikiTextContent("Infobox2_Template");

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyElementsVisibility() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertTrue(infobox.getHeadersNumber() > 0 );
    Assertion.assertTrue(infobox.isImageVisible());
    Assertion.assertTrue(infobox.isInfoboxTitleVisible());
    Assertion.assertTrue(infobox.getBoldElementsNumber() > 0 );
    Assertion.assertTrue(infobox.getItalicElementsNumber() > 0 );
    Assertion.assertTrue(infobox.areQuotationMarksPresented());
    Assertion.assertTrue(infobox.isReferenceElementVisible());

  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void infoboxInfoboxNavigationElements() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertTrue(infobox.isInfoboxNavigationElementVisible(0));
    Assertion.assertTrue(infobox.getInternalNavigationLinksNumber() > 0);
    Assertion.assertTrue(infobox.getExternalNavigationLinksNumber() > 0);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyRedlinksRedirecting() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertTrue(infobox.clickRedLinkWithIndex(0).isCreateNewArticleModalVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyInternalLinksRedirecting() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    String internalLinkName = infobox
        .open(PageContent.INFOBOX_2)
        .getInternalLinkRedirectTitle(2);

    String internalURL = infobox
        .clickInternalLinkWithIndex(2)
        .getUrlAfterPageIsLoaded();

    Assertion.assertTrue(internalURL.contains(internalLinkName));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyExternalLinksRedirecting() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    String externalLinkName = infobox
        .open(PageContent.INFOBOX_2)
        .getExternalLinkRedirectTitle(0);

    String externalUrl = infobox
        .clickExternalLinkWithIndex(0)
        .getUrlAfterPageIsLoaded();

    Assertion.assertEquals(externalLinkName.toLowerCase(), externalUrl.toLowerCase());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyImagesInWhatLinksHerePage() {
    new ArticleContent().push(String.format("[[%s]]", PageContent.INFOBOX_2),
                              "Infobox2_WhatLinksHere");
    //provide an article linking to testing Infobox
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    ArticlePageObject article = new ArticlePageObject();
    article.open("Infobox2_WhatLinksHere");
    String articleName = article.getArticleName();

    SpecialWhatLinksHerePageObject whatLinksHere = article.openSpecialWhatLinksHere(wikiURL);

    whatLinksHere
        .clickPageInputField()
        .typeInfoboxImageName("Infobox2")
        .clickShowButton();

    Assertion.assertTrue(whatLinksHere.whatLinksHereContainsArticleName(articleName));
  }

  @InBrowser(browser = Browser.FIREFOX, browserSize = "1200x720")
  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyLightboxVisibilityAfterClickingImage() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertTrue(infobox.clickImage().isLightboxVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyVisibilityOfTabberAndItsImages() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.INFOBOX_2);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isTabberVisible().isTabberImageVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  @Execute(asUser = User.STAFF)
  public void verifyInfoboxLayoutChange() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);

    theme.openSpecialDesignerPage(wikiURL).selectTheme(4);
    theme.submitTheme();

    infobox.open(PageContent.INFOBOX_2);
    new ArticlePurger().purgeArticleAsLoggedUser();

    String oldBackground = infobox.getBackgroundColor();

    theme.openSpecialDesignerPage(wikiURL).selectTheme(1);
    theme.submitTheme();

    infobox.open(PageContent.INFOBOX_2);
    new ArticlePurger().purgeArticleAsLoggedUser();

    Assertion.assertNotEquals(oldBackground, infobox.getBackgroundColor());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyOrderedAndUnorderedListFontSizes() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertEquals(infobox.getItemValuesFontSize(1), infobox.getOrderedElementFontSize(1));
    Assertion.assertEquals(infobox.getItemValuesFontSize(1),
                           infobox.getUnorderedElementFontSize(1));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyInfoboxCategoryLinks() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    String categoryLinkName = infobox.getCategoryLinkName();
    infobox.clickCategoryLink();
    String categoryPageTitle = new CategoryPageObject(driver).getCategoryPageTitle();

    Assertion.assertTrue(categoryLinkName.contains(categoryPageTitle));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyHorizontalGroupFontSize() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertEquals(infobox.getItemLabelsFontSize(0),
                           infobox.getHorizontalItemLabelFontSize(0));
    Assertion.assertEquals(infobox.getItemValuesFontSize(0),
                           infobox.getHorizontalItemValuesFontSize(0));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  @Execute(asUser = User.USER_9)
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    TemplatePage template = new TemplatePage();
    ArticlePageObject article = new ArticlePageObject();
    PortableInfobox infobox = new PortableInfobox();
    AceEditor editor = new AceEditor();


    template
        .openArticleByName(wikiURL, String.format("%s:%s",
                           PageContent.TEMPLATE_NAMESPACE, PageContent.INFOBOX_2))
        .editArticleInSrcUsingDropdown();
    String templateSyntax = editor.getContent();

    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsLoggedUser();

    article
        .openCurrectArticleSourceMode()
        .addContentInSourceMode(templateSyntax)
        .submitArticle();

    Assertion.assertTrue(infobox.isInfoboxTitleVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyGroupHeadersPadding() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertTrue(infobox.isHeaderPaddingLeftAndRightEqual(1));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyDivsWrappersAreNotIncluded() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    Assertion.assertFalse(infobox.imageContainsDiv(0));
    Assertion.assertFalse(infobox.headerContainsDiv(0));
    Assertion.assertFalse(infobox.titleContainsDiv(0));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyEmptyTagsAreNotAppearing() {
    new ArticleContent().push(INFOBOX_EMPTY_TAGS_INVOCATION, PageContent.INFOBOX_1);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_1);

    Assertion.assertTrue(infobox.infoboxContainsEmptyTag());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void insertEmptyInfoboxInVE() {
    ArticlePageObject article = new ArticlePageObject();

    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsAnon();

    VisualEditorPageObject visualEditor = article
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .clickApplyChanges();

    Assertion.assertTrue(visualEditor.isInfoboxInsertedInEditorArea());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void insertInfoboxWithParametersInVE() {
    ArticlePageObject article = new ArticlePageObject();

    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsAnon();

    VisualEditorPageObject visualEditor = article
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges();

    Assertion.assertTrue(visualEditor.isInfoboxInsertedInEditorArea());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void editInfoboxInVEbyPopup() {
    ArticlePageObject article = new ArticlePageObject();
    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsAnon();

    VisualEditorPageObject visualEditor = article
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges();

    Assertion.assertTrue(visualEditor.isInfoboxInsertedInEditorArea());

    visualEditor
        .clickInfoboxPopup()
        .typeInParameterField(2, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges();

    Assertion.assertTrue(visualEditor.isInfoboxInsertedInEditorArea());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  @Execute(asUser = User.STAFF)
  public void insertInfoboxWithParamsInVEusingDarkTheme() {
    ArticlePageObject article = new ArticlePageObject();
    (new ArticleContent()).clear();

    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    theme.openSpecialDesignerPage(wikiURL).selectTheme(3);
    theme.submitTheme();

    article.open();
    new ArticlePurger().purgeArticleAsLoggedUser();

    VisualEditorPageObject visualEditor = article
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges();

    Assertion.assertTrue(visualEditor.isInfoboxInsertedInEditorArea());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  @Execute(asUser = User.USER)
  public void infoboxImageOnCategoryPage() {
    new TemplateContent().push(INFOBOX2_TEMPLATE, PageContent.INFOBOX_2);
    new ArticleContent().push(INFOBOX2_INVOCATION, PageContent.INFOBOX_2);
    PortableInfobox infobox = new PortableInfobox();
    infobox.open(PageContent.INFOBOX_2);

    String imageName = infobox.getDataImageName();

    CategoryPageObject categoryPage = infobox.clickCategoryWithIndex(0);
    new ArticlePurger().purgeArticleAsLoggedUser();

    String categoryImageURL = categoryPage.getPageImageURL(
        categoryPage.getArticleIndexInGalleryByName(PageContent.INFOBOX_2)
    );

    Assertion.assertTrue(categoryImageURL.contains(imageName));
  }
}
