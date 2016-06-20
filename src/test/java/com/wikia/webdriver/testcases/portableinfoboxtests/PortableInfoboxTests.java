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
    Assertion.assertTrue(infobox.isInfoboxTitlePresented());
    Assertion.assertTrue(infobox.getBoldElementsNumber() > 0 );
    Assertion.assertTrue(infobox.getItalicElementsNumber() > 0 );
    Assertion.assertTrue(infobox.areQuotationMarksPresented());
    Assertion.assertTrue(infobox.isReferenceElementVisible());

  }

  public void infoboxNavigationElements() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    Assertion.assertTrue(infobox.isInfoboxNavigationElementVisible());
    Assertion.assertTrue(infobox.getInternalNavigationLinksNumber() > 0);
    Assertion.assertTrue(infobox.getExternalNavigationLinksNumber() > 0);
  }

  public void verifyElementsRedirects() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01).clickRedLinkWithIndex(0).verifyCreateNewArticleModal();
    new ArticlePurger().purgeArticleAsAnon();

    String externalLinkName = infobox
        .open(PageContent.PORTABLE_INFOBOX_01)
        .getExternalLinkRedirectTitleWithIndex(0);

    String externalUrl = infobox
        .clickExternalLinkWithIndex(0)
        .getUrlAfterPageIsLoaded();

    infobox.compareURLAndExternalLink(externalLinkName, externalUrl);

    String internalLinkName = infobox
        .open(PageContent.PORTABLE_INFOBOX_01)
        .getInternalLinkRedirectTitleWithIndex(3);

    String internalURL = infobox
        .clickInternalLinkWithIndex(3)
        .getUrlAfterPageIsLoaded();

    infobox.compareURLAndInternalLink(internalLinkName, internalURL);
  }

  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    String articleName = article.getArticleName();

    article
        .openSpecialWhatLinksHere(wikiURL).clickPageInputField()
        .typeInfoboxImageName(PageContent.FILE_IMAGE_NAME).clickShowbutton()
        .verifyInfoboxArticleInList(articleName);
  }

  public void verifyLightboxVisibilityAfterClickingImage() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_01);
    new ArticlePurger().purgeArticleAsAnon();

    infobox.clickImage().isLightboxPresented();
  }

  public void verifyVisibilityOfTabberAndItsImages() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    infobox.isTabberPresented().isTabberImagePresented();
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
    infobox.verifyChangedBackground(oldBackground, infobox.getBackgroundColor());
  }

  public void verifyOrderedAndUnorderedLists() {
    PortableInfobox infobox = new PortableInfobox();

    infobox.open(PageContent.PORTABLE_INFOBOX_02);
    new ArticlePurger().purgeArticleAsAnon();

    infobox
        .compareFontSizesBetweenItemValueAndOrderedListItemWithIndex(1)
        .compareFontSizesBetweenItemValueAndUnorderedListItemWithIndex(1);
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

    infobox
        .compareFontSizesBetweenHorizontalItemLabelAndItemLabel()
        .compareFontSizesBetweenHorizontalItemValueAndItemValue();
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
    Assertion.assertTrue(infobox.isInfoboxTitlePresented());
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
