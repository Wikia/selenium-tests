package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.ArticlePurger;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AceEditor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.Test;

/**
 * Set of Test Cases found on:
 * https://wikia-inc.atlassian.net/wiki/display/WW/Portable+Infoboxes+tests+plan
 */

@Execute(onWikia = "mediawiki119")
public class PortableInfoboxTests extends NewTestTemplate {

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyElementsVisibility() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

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
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isInfoboxNavigationElementVisible());
    Assertion.assertTrue(infobox.getInternalNavigationLinksNumber() > 0);
    Assertion.assertTrue(infobox.getExternalNavigationLinksNumber() > 0);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyRedlinksRedirecting() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.clickRedLinkWithIndex(0).isCreateNewArticleModalVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyInternalLinksRedirecting() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    String internalLinkName = infobox
        .open(PageContent.PORTABLE_INFOBOX_01)
        .getInternalLinkRedirectTitle(3);

    String internalURL = infobox
        .clickInternalLinkWithIndex(3)
        .getUrlAfterPageIsLoaded();

    Assertion.assertEquals(internalLinkName, internalURL);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyExternalLinksRedirecting() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    String externalLinkName = infobox
        .open(PageContent.PORTABLE_INFOBOX_01)
        .getExternalLinkRedirectTitle(0);

    String externalUrl = infobox
        .clickExternalLinkWithIndex(0)
        .getUrlAfterPageIsLoaded();

    Assertion.assertEquals(externalLinkName.toLowerCase(), externalUrl.toLowerCase());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    String articleName = article.getArticleName();

    String whatLinkHereResult = article
        .openSpecialWhatLinksHere(wikiURL)
        .clickPageInputField()
        .typeInfoboxImageName(PageContent.FILE_IMAGE_NAME)
        .clickShowButton()
        .getWhatLinksHereArticleName(0);

    Assertion.assertTrue(whatLinkHereResult.contains(articleName));
  }

  @InBrowser(browser = Browser.FIREFOX, browserSize = "1200x720")
  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyLightboxVisibilityAfterClickingImage() {
    PortableInfobox infobox = new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01);

    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.clickImage().isLightboxVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyVisibilityOfTabberAndItsImages() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isTabberVisible().isTabberImageVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  @Execute(asUser = User.STAFF)
  public void verifyInfoboxLayoutChange() {
    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    ArticlePageObject article = new ArticlePageObject();
    PortableInfobox infobox = new PortableInfobox();

    theme.openSpecialDesignerPage(wikiURL).selectTheme(4);
    theme.submitTheme();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsLoggedUser();

    String oldBackground = infobox.getBackgroundColor();

    theme.openSpecialDesignerPage(wikiURL).selectTheme(1);
    theme.submitTheme();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsLoggedUser();

    Assertion.assertNotEquals(oldBackground, infobox.getBackgroundColor());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyOrderedAndUnorderedListFontSizes() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertEquals(infobox.getItemValuesFontSize(1), infobox.getOrderedElementFontSize(1));
    Assertion.assertEquals(infobox.getItemValuesFontSize(1),
                           infobox.getUnorderedElementFontSize(1));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_002"})
  public void verifyInfoboxCategoryLinks() {
    PortableInfobox infobox =
        new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01);

    new ArticlePurger().purgeArticleAsAnon();

    String categoryLinkName = infobox.getCategoryLinkName();
    infobox.clickCategoryLink();
    new CategoryPageObject(driver).verifyCategoryPageTitle(categoryLinkName);
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_001"})
  public void verifyHorizontalGroupFontSize() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

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
        .openArticleByName(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE)
        .editArticleInSrcUsingDropdown();
    String templateSyntax = editor.getContent();

    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsLoggedUser();

    article
        .openCurrectArticleSourceMode()
        .addContentInSourceMode(templateSyntax)
        .submitArticle();

    Assertion.assertTrue(infobox.isImageVisible());
    Assertion.assertTrue(infobox.isInfoboxTitleVisible());
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyNavigationElementPadding() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isNavigationPaddingLeftAndRightEqual(1));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyGroupHeadersPadding() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isHeaderPaddingLeftAndRightEqual(1));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyDivsWrappersAreNotIncluded() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertFalse(infobox.imageContainsDiv(0));
    Assertion.assertFalse(infobox.headerContainsDiv(0));
    Assertion.assertFalse(infobox.titleContainsDiv(0));
  }

  @Test(groups = {"PortableInfoboxTests", "PortableInfobox_003"})
  public void verifyEmptyTagsAreNotAppearing() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_EMPTY_TAGS);
    new ArticlePurger().purgeArticleAsAnon();

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
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsLoggedUser();

    String imageName = infobox.getDataImageName();

    CategoryPageObject categoryPage = infobox.clickCategoryWithIndex(0);
    new ArticlePurger().purgeArticleAsLoggedUser();

    String categoryImageURL = categoryPage.getPageImageURL(
        categoryPage.getArticleIndexInGalleryByName(PageContent.PORTABLE_INFOBOX_02)
    );

    Assertion.assertTrue(categoryImageURL.contains(imageName));
  }
}
