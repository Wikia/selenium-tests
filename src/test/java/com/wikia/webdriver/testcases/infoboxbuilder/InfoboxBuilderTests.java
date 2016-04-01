package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.InfoboxBuilderPage;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.elements.oasis.pages.WikiFeatures;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "InfoboxBuilderTests")
@Execute(onWikia = "mediawiki119")
public class InfoboxBuilderTests extends NewTestTemplate {

  private static final int EUROPA_INFOBOX_WIDTH = 300;
  private static final int DEFAULT_INFOBOX_WIDTH = 270;

  @Execute(asUser = User.USER)
  public void verifyDefaultStructure() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("InfoboxBuilderVerifyDefaultStructure");

    Assertion.assertEquals(builderPage.countTitles(), 1);
    Assertion.assertEquals(builderPage.countImages(), 1);
    Assertion.assertEquals(builderPage.countRows(), 2);
    Assertion.assertEquals(builderPage.countHeaders(), 0);

    Assertion.assertTrue(builderPage.isTitleUsingArticleName(0));
  }

  @Execute(asUser = User.USER)
  public void addingComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();

    builder.openNew("InfoboxBuilderAddingComponents");
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();
    int headerComponents = builder.countHeaders();

    builder.addRowComponent().addImageComponent().addTitleComponent().addHeaderComponent();

    Assertion.assertEquals(rowComponents + 1, builder.countRows());
    Assertion.assertEquals(titleComponents + 1, builder.countTitles());
    Assertion.assertEquals(imageComponents + 1, builder.countImages());
    Assertion.assertEquals(headerComponents + 1, builder.countHeaders());
  }

  @Execute(asUser = User.USER)
  public void savingTemplate() {
    TemplatePage templatePage = new InfoboxBuilderPage()
        .openExisting("InfoboxBuilderSavingTemplate")
        .deleteRowUsingButton(0)
        .addRowComponent()
        .save();

    Assertion.assertEquals("infoboxbuildersavingtemplate",
                           templatePage.getHeaderText().toLowerCase());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    builder.openNew("InfoboxBuilderDeletingDefaultComponents");
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();

    Assertion.assertEquals(rowComponents - 1,
                           builder.deleteRowUsingButton(0).countRows());
    Assertion.assertEquals(titleComponents - 1,
                           builder.deleteTitleUsingButton(0).countTitles());
    Assertion.assertEquals(imageComponents - 1,
                           builder.deleteImageUsingButton(0).countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponentsUsingPopUp() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    builder.openNew("InfoboxBuilderDeletingDefaultComponents");
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();

    Assertion.assertEquals(rowComponents - 1,
                           builder.deleteRowUsingPopUp(0).countRows());
    Assertion.assertEquals(titleComponents - 1,
                           builder.deleteTitleUsingPopUp(0).countTitles());
    Assertion.assertEquals(imageComponents - 1,
                           builder.deleteImageUsingPopUp(0).countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingAddedComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    builder.openNew("InfoboxBuilderDeletingAddedComponents");
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();
    int headerComponents = builder.countHeaders();

    builder.addImageComponent().addTitleComponent().addRowComponent().addHeaderComponent();

    /* deleting last (newly added) components */
    Assertion.assertEquals(
        rowComponents, builder.deleteRowUsingButton(builder.countRows() - 1).countRows()
    );
    Assertion.assertEquals(
        titleComponents,
        builder.deleteTitleUsingButton(builder.countTitles() - 1).countTitles()
    );
    Assertion.assertEquals(
        imageComponents,
        builder.deleteImageUsingButton(builder.countImages() - 1).countImages()
    );
    Assertion.assertEquals(
        headerComponents,
        builder.deleteHeaderUsingButton(builder.countHeaders() - 1).countHeaders()
    );
  }

  @Execute(asUser = User.USER)
  public void customizingComponents() {
    TemplatePage template = new InfoboxBuilderPage()
        .openExisting("InfoboxBuilderCustomizingComponents")
        .changeHeaderCollapsibilityState(0)
        .setAndVerifyRowLabel(0, "AutomatedTest")
        .setLongLabelNameAndVerifyBreakLine(1, "AutomatedTestVeryLongName")
        .setAndVerifyHeaderName(0, "AutomatedTestHeader")
        .setTitleToUseArticleName(0)
        .save();

    String infoboxBackground = template.getPortableInfobox().getHeaderText();
    Assertion.assertEquals("InfoboxBuilderCustomizingComponents", infoboxBackground);
  }

  @Execute(asUser = User.USER)
  public void newTemplateCreation() {
    new TemplateEditPage().open("InfoboxBuilderNewTemplateCreation")
        .getTemplateClassification()
        .selectTemplateType()
        .clickAddButton();

    Assertion.assertTrue(new InfoboxBuilderPage().isInfoboxBuilderDisplayed());
  }


  @Execute(asUser = User.USER)
  public void verifySidebarBackArrow() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyInterfaceFunctionality")
        .selectRowWithIndex(0).verifyBackArrowFunctionality()
        .selectTitleWithIndex(0).verifyBackArrowFunctionality()
        .selectImageWithIndex(0).verifyBackArrowFunctionality()
        .selectHeaderWithIndex(0).verifyBackArrowFunctionality();
  }

  @Execute(asUser = User.STAFF)
  public void verifyInfoboxPreviewTheme() {
    //TODO: Figure out why assertion is not passing
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    SpecialThemeDesignerPageObject themeDesigner = new SpecialThemeDesignerPageObject(driver);
    ArticlePageObject article = new ArticlePageObject();

    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    /* select light theme */
    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(0);
    themeDesigner.submitTheme();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    String articleBackgroundColor = article.getPageBackgroundColor();
    String previewBackgroundColor = builder.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroudColor();

    Assertion.assertEquals(previewBackgroundColor, articleBackgroundColor);

    /* select dark theme */
    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(3);
    themeDesigner.submitTheme();

    article.open(PageContent.PORTABLE_INFOBOX_01);
    articleBackgroundColor = article.getPageBackgroundColor();
    previewBackgroundColor = builder.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroudColor();

    Assertion.assertEquals(articleBackgroundColor, previewBackgroundColor);
  }

  /* Verify if scrolling is enabled when Infobox's height in
  preview is greater than the preview area height. */
  @Execute(asUser = User.USER)
  public void verifyScrolling() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyScrolling")
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .verifyScrollbarIsVisible()
        .moveToLastComponent();
  }

  @Execute(asUser = User.STAFF)
  public void verifyUserInteractions() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builder = new InfoboxBuilderPage()
        .openNew("InfoboxBuilderVerifySelectedBorderStyling")
        .verifyTooltipOnHover();

    String borderStyle = builder.getBorderStyle();
    Assertion.assertEquals(borderStyle, "1px solid rgb(26, 94, 184)");

    borderStyle = builder.clickBuilderBackground().getComponentBackgroundColor(0);
    Assertion.assertNotEquals(borderStyle, "1px solid rgb(26, 94, 184)");
  }

  @Execute(asUser = User.STAFF)
  public void verifyReordering() {
    new InfoboxBuilderPage().openNew("InfoboxBuilderVerifyReordering")
        .dragAndDropToTheTop(2)
        .dragAndDropToTheTop(3)
        .dragAndDropToTheTop(1);
  }

  @Execute(asUser = User.USER)
  public void verifyRedirectingUnsupportedInfoboxes() {
    new TemplatePage().open("InfoboxBuilderMultipleInfoboxes").editArticleInSrcUsingDropdown();
    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());

    new TemplatePage().open("InfoboxBuilderUnsupportedMarkup").editArticleInSrcUsingDropdown();
    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
  }

  @Execute(asUser = User.USER)
  public void immutableSources() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderImmutableRows")
        .setAndVerifyRowLabel(0, "AutomatedTest")
        .save();

    String invocationLabelText = new PortableInfobox().open("InfoboxBuilderImmutableExample")
        .getDataLabelTextWithIndex(0);

    Assertion.assertEquals("AutomatedTest", invocationLabelText);
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorClickOnModalBackground() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openNew("Infobox_verify_go_to_source")
        .clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceDialogPresent());

    builderPage.clickGoToSourceModalBackground();

    Assertion.assertTrue(builderPage.isInfoboxBuilderOpened());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorSaveChanges() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openExisting("Infobox_verify_go_to_source_save_changes")
            .addRowComponent()
            .deleteRowUsingButton(1)
            .clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceDialogPresent());

    TemplateEditPage template = builderPage.clickSaveChangesButton();

    Assert.assertTrue(template.isEditAreaDisplayed());
    Assert.assertFalse(template.isEditAreaEmpty());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorDropChanges() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openNew(
        "Infobox_verify_go_to_source_drop_changes")
        .clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceDialogPresent());

    TemplateEditPage template = builderPage.clickDropChangesButton();
    template.getTemplateClassification()
        .selectInfoboxTemplate()
        .clickAddButton();

    Assertion.assertTrue(template.isEditAreaDisplayed());
    Assertion.assertTrue(template.isEditAreaEmpty());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorNonEditedInfobox() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderSavingTemplate")
        .clickGoToSourceButton();

    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
    Assertion.assertTrue(driver.getCurrentUrl().contains("InfoboxBuilderSavingTemplate"));
  }

  @Execute(asUser = User.USER)
  public void verifyIfInputFieldIsFocusedOnSelectItem() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openNew("Infobox_verify_focus")
        .addHeaderComponent()
        .selectRowWithIndex(0);

    Assertion.assertTrue(builderPage.isLabelInputFocused());

    builderPage.selectHeaderWithIndex(0);

    Assertion.assertTrue(builderPage.isHeaderInputFocused());
  }

  @Execute(asUser = User.USER)
  public void verifyChevronTooltip() {
    InfoboxBuilderPage
        builderPage = new InfoboxBuilderPage().openExisting("InfoboxBuilderChevronPopup");

    Assertion.assertTrue(builderPage.isSectionTooltipDisplayedBelow(0));
    Assertion.assertTrue(builderPage.isSectionTooltipDisplayedAbove(1));

    builderPage.addHeaderComponent().changeHeaderCollapsibilityState(2);

    Assertion.assertTrue(builderPage.isSectionTooltipDisplayedAbove(2));
  }

  @Execute(asUser = User.STAFF)
  public void verifyLoadingEuropaTheme() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting("Infobox_theme_europa");

    Assertion.assertEquals(builderPage.getInfoboxWidth(), EUROPA_INFOBOX_WIDTH);
  }

  @Execute(asUser = User.STAFF)
  public void verifyLoadingDefaultTheme() {
    new WikiFeatures().openWikiFeatures(wikiURL).disableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting("Infobox_theme_default");

    Assertion.assertEquals(builderPage.getInfoboxWidth(), DEFAULT_INFOBOX_WIDTH);
  }

  @Execute(asUser = User.ANONYMOUS)
  public void verifyBuilderAuthentication() {
    TemplateEditPage template = new TemplateEditPage().open("InfoboxBuilderAuthentication");

    Assertion.assertTrue(template.isPermissionErrorDisplayed());
  }

  public void verifyOtherContentIsNotChanged() {
    final String templateName = "Infobox_other_content";
    final String infoboxRegexp = "(?s)<infobox[^>]*>.*</infobox>";
    String beforePublish = new TemplatePage().getRawContent(templateName)
        .replaceAll(infoboxRegexp, "");

    new TemplatePage().open(templateName).loginAs(User.USER);
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting(templateName);
    builderPage.addRowComponent()
        .deleteRowUsingButton(0)
        .save();
    builderPage.logOut();

    String afterPublish =
        new TemplatePage().getRawContent(templateName).replaceAll(infoboxRegexp, "");

    Assert.assertEquals(beforePublish, afterPublish);
  }
}
