package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.ArticlePurger;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;

import org.testng.annotations.Test;

/**
 * Set of Test Cases found on:
 * https://wikia-inc.atlassian.net/wiki/display/WW/Portable+Infoboxes+tests+plan
 */

@Test(groups = "PortableInfoboxTests")
@Execute(onWikia = "mediawiki119")
public class PortableInfoboxTests extends NewTestTemplate {

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

  public void infoboxInfoboxNavigationElements() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isInfoboxNavigationElementVisible());
    Assertion.assertTrue(infobox.getInternalNavigationLinksNumber() > 0);
    Assertion.assertTrue(infobox.getExternalNavigationLinksNumber() > 0);
  }

  public void verifyRedlinksRedirecting() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.clickRedLinkWithIndex(0).isCreateNewArticleModalVisible());
  }

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

    Assertion.assertEquals(externalLinkName, externalUrl);
  }

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

  public void verifyLightboxVisibilityAfterClickingImage() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.clickImage().isLightboxVisible());;
  }

  public void verifyVisibilityOfTabberAndItsImages() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isTabberVisible().isTabberImageVisible());
  }

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
    Assertion.assertEquals(oldBackground, infobox.getBackgroundColor());
  }

  public void verifyOrderedAndUnorderedListFontSizes() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertEquals(infobox.getItemValuesFontSize(1), infobox.getOrderedElementFontSize(1));
    Assertion.assertEquals(infobox.getItemValuesFontSize(1),
                           infobox.getUnorderedElementFontSize(1));
  }

  public void verifyInfoboxCategoryLinks() {
    PortableInfobox infobox =
        new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01);

    new ArticlePurger().purgeArticleAsAnon();

    String categoryLinkName = infobox.getCategoryLinkName();
    infobox.clickCategoryLink();
    new CategoryPageObject(driver).verifyCategoryPageTitle(categoryLinkName);
  }

  public void verifyHorizontalGroupFontSize() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertEquals(infobox.getItemLabelsFontSize(0),
                           infobox.getHorizontalItemLabelFontSize(0));
    Assertion.assertEquals(infobox.getItemValuesFontSize(0),
                           infobox.getHorizontalItemValuesFontSize(0));
  }

  @Execute(asUser = User.USER_9)
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    TemplatePage template = new TemplatePage();
    ArticlePageObject article = new ArticlePageObject();
    PortableInfobox infobox = new PortableInfobox();

    String templateSyntax =
        template.openArticleByName(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE)
            .editArticleInSrcUsingDropdown().copyContent();

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

  public void verifyNavigationElementPadding() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    infobox.verifyPaddingNavigationElementWithIndex(1);
  }

  public void verifyGroupHeadersPadding() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    infobox.verifyGroupHeaderPaddingWithIndex(1);
  }

  public void verifyDivsWrappersAreNotIncluded() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    infobox
        .verifyDivsNotAppearingInImage()
        .verifyDivsNotAppearingInTitle()
        .verifyDivsNotAppearingInHeaderWithIndex(0);
  }

  public void verifyEmptyTagsAreNotAppearing() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_EMPTY_TAGS);
    new ArticlePurger().purgeArticleAsAnon();

    infobox.verifyEmptyTags();
  }

  public void insertEmptyInfoboxInVE() {
    ArticlePageObject article = new ArticlePageObject();

    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsAnon();

    article.openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2).clickApplyChanges()
        .isInfoboxInsertedInEditorArea();
  }

  public void insertInfoboxWithParametersInVE() {
    ArticlePageObject article = new ArticlePageObject();

    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsAnon();

    article
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges()
        .isInfoboxInsertedInEditorArea();
  }

  public void editInfoboxInVEbyPopup() {
    ArticlePageObject article = new ArticlePageObject();
    (new ArticleContent()).clear();

    article.open();
    new ArticlePurger().purgeArticleAsAnon();

    article
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges()
        .isInfoboxInsertedInEditorArea()
        .clickInfoboxPopup()
        .typeInParameterField(2, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges()
        .isInfoboxInsertedInEditorArea();
  }

  @Execute(asUser = User.STAFF)
  public void insertInfoboxWithParamsInVEusingDarkTheme() {
    ArticlePageObject article = new ArticlePageObject();
    (new ArticleContent()).clear();

    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    theme.openSpecialDesignerPage(wikiURL).selectTheme(3);
    theme.submitTheme();

    article.open();
    new ArticlePurger().purgeArticleAsLoggedUser();

    article
        .open()
        .openVEModeWithMainEditButton()
        .clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu()
        .selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges()
        .isInfoboxInsertedInEditorArea();
  }

  public void infoboxImageOnCategoryPage() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    String imageName = infobox.getDataImageName();

    CategoryPageObject categoryPage = infobox.clickCategoryWithIndex(0);

    String categoryImageURL = categoryPage.getPageImageURL(
        categoryPage.getArticleIndexInGalleryByName(PageContent.PORTABLE_INFOBOX_02)
    );

    infobox.compareInfoboxAndCategoryPageImages(categoryImageURL, imageName);
  }
}
