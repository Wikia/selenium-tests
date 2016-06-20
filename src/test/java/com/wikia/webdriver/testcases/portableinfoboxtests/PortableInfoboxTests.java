package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
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
 * https://one.wikia-inc.com/wiki/Portable_Infoboxes_Test_Plan
 */
@Test(groups = "PortableInfoboxTests")
public class PortableInfoboxTests extends NewTestTemplate {

  @Execute(onWikia = "mediawiki119", asUser = User.USER)
  public void verifyElementsVisibility() {
    PortableInfobox info = new PortableInfobox();

    info.open(PageContent.PORTABLE_INFOBOX_01);

    new ArticlePurger().purgeArticleAsLoggedUser();

    info.areBoldElementsMoreThanOne()
        .areItalicElementsMoreThanOne()
        .areHeadersMoreThanOne()
        .areQuotationMarksPresented()
        .verifyReferencesPresence()
        .isImagePresented()
        .isInfoboxTitlePresented()
        .areLinksInPoemTagPresented();
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyElementsRedirects() {
    PortableInfobox info = new PortableInfobox();

    info.open(PageContent.PORTABLE_INFOBOX_01).clickRedLinkWithIndex(0).verifyCreateNewArticleModal();

    String externalLinkName = info
        .open(PageContent.PORTABLE_INFOBOX_01)
        .getExternalLinkRedirectTitleWithIndex(0);

    String externalUrl = info
        .clickExternalLinkWithIndex(0)
        .getUrlAfterPageIsLoaded();

    info.compareURLAndExternalLink(externalLinkName, externalUrl);

    String internalLinkName = info
        .open(PageContent.PORTABLE_INFOBOX_01)
        .getInternalLinkRedirectTitleWithIndex(3);

    String internalURL = info
        .clickInternalLinkWithIndex(3)
        .getUrlAfterPageIsLoaded();

    info.compareURLAndInternalLink(internalLinkName, internalURL);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject();
    String articleName = article.open(PageContent.PORTABLE_INFOBOX_01).getArticleName();

    article.openSpecialWhatLinksHere(wikiURL).clickPageInputField()
        .typeInfoboxImageName(PageContent.FILE_IMAGE_NAME).clickShowbutton()
        .verifyInfoboxArticleInList(articleName);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyLightboxVisibilityAfterClickingImage() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01).clickImage()
        .isLightboxPresented();
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyVisibilityOfTabberAndItsImages() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_02).isTabberPresented()
        .isTabberImagePresented();
  }

  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void verifyInfoboxLayoutChange() {
    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    ArticlePageObject article = new ArticlePageObject();
    PortableInfobox info = new PortableInfobox();

    theme.openSpecialDesignerPage(wikiURL).selectTheme(4);
    theme.submitTheme();

    String oldBackground = info.open(PageContent.PORTABLE_INFOBOX_01).getBackgroundColor();

    theme.openSpecialDesignerPage(wikiURL).selectTheme(1);
    theme.submitTheme();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    info.verifyChangedBackground(oldBackground, info.getBackgroundColor());
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyOrderedAndUnorderedLists() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_02)
        .compareFontSizesBetweenItemValueAndOrderedListItemWithIndex(1)
        .compareFontSizesBetweenItemValueAndUnorderedListItemWithIndex(1);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyInfoboxCategoryLinks() {
    PortableInfobox info =
        new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01);

    String categoryLinkName = info.getCategoryLinkName();
    info.clickCategoryLink();
    new CategoryPageObject(driver).verifyCategoryPageTitle(categoryLinkName);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyHorizontalGroupFontSize() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01)
        .compareFontSizesBetweenHorizontalItemLabelAndItemLabel()
        .compareFontSizesBetweenHorizontalItemValueAndItemValue();
  }

  @Execute(asUser = User.USER_9, onWikia = "mediawiki119")
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    TemplatePage template = new TemplatePage();
    ArticlePageObject article = new ArticlePageObject();

    String templateSyntax =
        template.openArticleByName(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE)
            .editArticleInSrcUsingDropdown().copyContent();

    (new ArticleContent()).clear();

    article.open().openCurrectArticleSourceMode().addContentInSourceMode(templateSyntax)
        .submitArticle();

    new PortableInfobox().isImagePresented().isInfoboxTitlePresented();
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyNavigationElementPadding() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01)
        .verifyPaddingNavigationElementWithIndex(1);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyGroupHeadersPadding() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01)
        .verifyGroupHeaderPaddingWithIndex(1);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyDivsWrappersAreNotIncluded() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_01)
        .verifyDivsNotAppearingInImage().verifyDivsNotAppearingInTitle()
        .verifyDivsNotAppearingInHeaderWithIndex(0);
  }

  @Execute(onWikia = "mediawiki119")
  public void verifyEmptyTagsAreNotAppearing() {
    new PortableInfobox().open(PageContent.PORTABLE_INFOBOX_EMPTY_TAGS)
        .verifyEmptyTags();
  }

  @Execute(onWikia = "mediawiki119")
  public void insertEmptyInfoboxInVE() {
    (new ArticleContent()).clear();

    new ArticlePageObject().open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2).clickApplyChanges()
        .isInfoboxInsertedInEditorArea();
  }

  @Execute(onWikia = "mediawiki119")
  public void insertInfoboxWithParametersInVE() {
    (new ArticleContent()).clear();

    new ArticlePageObject().open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea();
  }

  @Execute(onWikia = "mediawiki119")
  public void editInfoboxInVEbyPopup() {
    (new ArticleContent()).clear();

    new ArticlePageObject().open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea().clickInfoboxPopup()
        .typeInParameterField(2, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea();
  }

  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void insertInfoboxWithParamsInVEusingDarkTheme() {
    (new ArticleContent()).clear();

    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    theme.openSpecialDesignerPage(wikiURL).selectTheme(3);
    theme.submitTheme();

    new ArticlePageObject().open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea();
  }

  public void infoboxImageOnCategoryPage() {
    PortableInfobox info = new PortableInfobox();

    String imageName = info
        .open(PageContent.PORTABLE_INFOBOX_02)
        .getDataImageName();

    CategoryPageObject categoryPage = info.clickCategoryWithIndex(0);

    String categoryImageURL = categoryPage.getPageImageURL(
        categoryPage.getArticleIndexInGalleryByName(PageContent.PORTABLE_INFOBOX_02)
    );

    info.compareInfoboxAndCategoryPageImages(categoryImageURL, imageName);
  }
}
