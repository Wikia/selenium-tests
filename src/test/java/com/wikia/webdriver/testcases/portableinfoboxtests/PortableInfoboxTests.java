package com.wikia.webdriver.testcases.portableinfoboxtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import org.testng.annotations.Test;

/**
 * Set of Test Cases found on:
 * https://one.wikia-inc.com/wiki/Portable_Infoboxes_Test_Plan
 */
@Test(groups = "PortableInfoboxTests")
public class PortableInfoboxTests extends NewTestTemplate {

  @Test(groups = {"PortableInfoboxTests_001", "PortableInfoboxTests_1"})
  @Execute(onWikia = "mediawiki119")
  public void verifyElementsVisibility() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01)
        .areBoldElementsMoreThanOne().areItalicElementsMoreThanOne().areHeadersMoreThanOne()
        .areQuotationMarksPresented().verifyReferencesPresence().isImagePresented()
        .isInfoboxTitlePresented().areLinksInPoemTagPresented();
  }

  @Test(groups = {"PortableInfoboxTests_002", "PortableInfoboxTests_2"})
  @Execute(onWikia = "mediawiki119")
  public void verifyElementsRedirects() {
    PortableInfoboxPageObject info = new PortableInfoboxPageObject(driver);

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

  @Test(groups = {"PortableInfoboxTests_003", "PortableInfoboxTests_3"})
  @Execute(onWikia = "mediawiki119")
  public void verifyImagesInWhatLinksHerePage() {
    ArticlePageObject article = new ArticlePageObject(driver);
    String articleName = article.open(PageContent.PORTABLE_INFOBOX_01).getArticleName();

    article.openSpecialWhatLinksHere(wikiURL).clickPageInputField()
        .typeInfoboxImageName(PageContent.FILE_IMAGE_NAME).clickShowbutton()
        .verifyInfoboxArticleInList(articleName);
  }

  @Test(groups = {"PortableInfoboxTests_004", "PortableInfoboxTests_2"})
  @Execute(onWikia = "mediawiki119")
  public void verifyLightboxVisibilityAfterClickingImage() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01).clickImage()
        .isLightboxPresented();
  }

  @Test(groups = {"PortableInfoboxTests_005", "PortableInfoboxTests_3"})
  @Execute(onWikia = "mediawiki119")
  public void verifyVisibilityOfTabberAndItsImages() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_02).isTabberPresented()
        .isTabberImagePresented();
  }

  @Test(groups = {"PortableInfoboxTests_006", "PortableInfoboxTests_1"})
  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void verifyInfoboxLayoutChange() {
    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    ArticlePageObject article = new ArticlePageObject(driver);
    PortableInfoboxPageObject info = new PortableInfoboxPageObject(driver);

    theme.openSpecialDesignerPage(wikiURL).selectTheme(4);
    theme.submitTheme();

    String oldBackground = info.open(PageContent.PORTABLE_INFOBOX_01).getBackgroundColor();

    theme.openSpecialDesignerPage(wikiURL).selectTheme(1);
    theme.submitTheme();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    info.verifyChangedBackground(oldBackground, info.getBackgroundColor());
  }

  @Test(groups = {"PortableInfoboxTests_007", "PortableInfoboxTests_2"})
  @Execute(onWikia = "mediawiki119")
  public void verifyOrderedAndUnorderedLists() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_02)
        .compareFontSizesBetweenItemValueAndOrderedListItemWithIndex(1)
        .compareFontSizesBetweenItemValueAndUnorderedListItemWithIndex(1);
  }

  @Test(groups = {"PortableInfoboxTests_008", "PortableInfoboxTests_3"})
  @Execute(onWikia = "mediawiki119")
  public void verifyInfoboxCategoryLinks() {
    PortableInfoboxPageObject info =
        new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01);

    String categoryLinkName = info.getCategoryLinkName();
    info.clickCategoryLink();
    new CategoryPageObject(driver).verifyCategoryPageTitle(categoryLinkName);
  }

  @Test(groups = {"PortableInfoboxTests_09", "PortableInfoboxTests_2"})
  @Execute(onWikia = "mediawiki119")
  public void verifyHorizontalGroupFontSize() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01)
        .compareFontSizesBetweenHorizontalItemLabelAndItemLabel()
        .compareFontSizesBetweenHorizontalItemValueAndItemValue();
  }

  @Test(groups = {"PortableInfoboxTests_010", "PortableInfoboxTests_3"})
  @Execute(asUser = User.USER_9, onWikia = "mediawiki119")
  public void verifyCopiedTemplateSyntaxInArticlePresence() {
    TemplatePageObject template = new TemplatePageObject(driver);
    ArticlePageObject article = new ArticlePageObject(driver);

    String templateSyntax =
        template.openArticleByName(wikiURL, PageContent.PI_TEMPLATE_WEBSITE_SIMPLE)
            .editArticleInSrcUsingDropdown().copyContent();

    (new ArticleContent()).clear();

    article.open().openCurrectArticleSourceMode().addContentInSourceMode(templateSyntax)
        .submitArticle();

    new PortableInfoboxPageObject(driver).isImagePresented().isInfoboxTitlePresented();
  }

  @Test(groups = {"PortableInfoboxTests_011", "PortableInfoboxTests_1"})
  @Execute(onWikia = "mediawiki119")
  public void verifyNavigationElementPadding() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01)
        .verifyPaddingNavigationElementWithIndex(1);
  }

  @Test(groups = {"PortableInfoboxTests_012", "PortableInfoboxTests_2"})
  @Execute(onWikia = "mediawiki119")
  public void verifyGroupHeadersPadding() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01)
        .verifyGroupHeaderPaddingWithIndex(1);
  }

  @Test(groups = {"PortableInfoboxTests_013", "PortableInfoboxTests_3"})
  @Execute(onWikia = "mediawiki119")
  public void verifyDivsWrappersAreNotIncluded() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_01)
        .verifyDivsNotAppearingInImage().verifyDivsNotAppearingInTitle()
        .verifyDivsNotAppearingInHeaderWithIndex(0);
  }

  @Test(groups = {"PortableInfoboxTests_015", "PortableInfoboxTests_1"})
  @Execute(onWikia = "mediawiki119")
  public void verifyEmptyTagsAreNotAppearing() {
    new PortableInfoboxPageObject(driver).open(PageContent.PORTABLE_INFOBOX_EMPTY_TAGS)
        .verifyEmptyTags();
  }

  @Test(groups = {"PortableInfoboxTests_014", "PortableInfoboxTests_2"})
  @Execute(onWikia = "mediawiki119")
  public void insertEmptyInfoboxInVE() {
    (new ArticleContent()).clear();

    new ArticlePageObject(driver).open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2).clickApplyChanges()
        .isInfoboxInsertedInEditorArea();
  }

  @Test(groups = {"PortableInfoboxTests_015", "PortableInfoboxTests_3"})
  @Execute(onWikia = "mediawiki119")
  public void insertInfoboxWithParametersInVE() {
    (new ArticleContent()).clear();

    new ArticlePageObject(driver).open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea();
  }

  @Test(groups = {"PortableInfoboxTests_016", "PortableInfoboxTests_1"})
  @Execute(onWikia = "mediawiki119")
  public void editInfoboxInVEbyPopup() {
    (new ArticleContent()).clear();

    new ArticlePageObject(driver).open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea().clickInfoboxPopup()
        .typeInParameterField(2, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea();
  }

  @Test(groups = {"PortableInfoboxTests_017", "PortableInfoboxTests_2"})
  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void insertInfoboxWithParamsInVEusingDarkTheme() {
    (new ArticleContent()).clear();

    SpecialThemeDesignerPageObject theme = new SpecialThemeDesignerPageObject(driver);
    theme.openSpecialDesignerPage(wikiURL).selectTheme(3);
    theme.submitTheme();

    new ArticlePageObject(driver).open().openVEModeWithMainEditButton().clickInsertToolButton()
        .clickInsertInfoboxFromInsertToolMenu().selectInfoboxTemplate(2)
        .typeInParameterField(0, new SourceEditModePageObject(driver).getRandomDigits(5))
        .clickApplyChanges().isInfoboxInsertedInEditorArea();
  }

  @Test(groups = {"PortableInfoboxTests_018", "PortableInfoboxTests_2"})
  public void infoboxImageOnCategoryPage() {
    PortableInfoboxPageObject info = new PortableInfoboxPageObject(driver);

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
