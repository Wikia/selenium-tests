package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Sidebar;
import com.wikia.webdriver.elements.mercury.components.Spinner;
import com.wikia.webdriver.elements.mercury.components.Subhead;
import com.wikia.webdriver.elements.mercury.pages.InfoboxBuilderPage;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.elements.oasis.pages.WikiFeatures;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
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
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderVerifyDefaultStructure");

    Assertion.assertEquals(builderPage.countTitles(), 1);
    Assertion.assertEquals(builderPage.countImages(), 1);
    Assertion.assertEquals(builderPage.countRows(), 2);
    Assertion.assertEquals(builderPage.countHeaders(), 0);

    builderPage.selectTitleWithIndex(0);

    Assertion.assertTrue(new Sidebar().isTitleUsingArticleName());
  }

  @Execute(asUser = User.USER)
  public void addingComponents() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderAddingComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();
    int headerComponents = builderPage.countHeaders();

    builderSidebar.addRowComponent().addImageComponent().addTitleComponent().addHeaderComponent();

    Assertion.assertEquals(rowComponents + 1, builderPage.countRows());
    Assertion.assertEquals(titleComponents + 1, builderPage.countTitles());
    Assertion.assertEquals(imageComponents + 1, builderPage.countImages());
    Assertion.assertEquals(headerComponents + 1, builderPage.countHeaders());
  }

  @Execute(asUser = User.USER)
  public void savingTemplate() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();

    builderPage.openExisting("InfoboxBuilderSavingTemplate").selectRowWithIndex(0);
    builderSidebar.clickDeleteButton();
    builderSidebar.addRowComponent();
    new Subhead().clickPublish();

    Assertion.assertTrue(new Spinner().isSpinnerPresent());

    Assertion.assertEquals("infoboxbuildersavingtemplate",
                           new TemplatePage().getHeaderText().toLowerCase());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponents() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingDefaultComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();

    builderPage.selectRowWithIndex(0);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(rowComponents - 1, builderPage.countRows());

    builderPage.selectTitleWithIndex(0);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(titleComponents - 1, builderPage.countTitles());

    builderPage.selectImageWithIndex(0);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(imageComponents - 1, builderPage.countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponentsUsingPopUp() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingDefaultComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();

    Assertion.assertEquals(rowComponents - 1,
                           builderPage.deleteRowUsingPopUp(0).countRows());
    Assertion.assertEquals(titleComponents - 1,
                           builderPage.deleteTitleUsingPopUp(0).countTitles());
    Assertion.assertEquals(imageComponents - 1,
                           builderPage.deleteImageUsingPopUp(0).countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingAddedComponents() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingAddedComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();
    int headerComponents = builderPage.countHeaders();

    builderSidebar.addImageComponent().addTitleComponent().addRowComponent().addHeaderComponent();

    /* deleting last (newly added) components */
    builderPage.selectRowWithIndex(builderPage.countRows() - 1);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(rowComponents, builderPage.countRows());

    builderPage.selectTitleWithIndex(builderPage.countTitles() - 1);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(titleComponents, builderPage.countTitles());

    builderPage.selectImageWithIndex(builderPage.countImages() - 1);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(imageComponents, builderPage.countImages());

    builderPage.selectHeaderWithIndex(builderPage.countImages() - 1);
    builderSidebar.clickDeleteButton();
    Assertion.assertEquals(headerComponents, builderPage.countHeaders());
  }

  @Execute(asUser = User.USER)
  public void customizingComponents() {
    TemplatePage template = new TemplatePage();
    Sidebar builderSidebar = new Sidebar();
    String labelText = "AutomatedTest";
    String labelLongText = "AutomatedTestVeryLongName";

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("InfoboxBuilderCustomizingComponents")
        .selectHeaderWithIndex(0);

    builderSidebar.changeHeaderCollapsibilityState();

    builderPage.selectRowWithIndex(0);
    builderSidebar.typeInInputField(labelText);
    Assertion.assertEquals(builderPage.getLabelText(0), labelText);

    builderPage.selectRowWithIndex(1);
    builderSidebar.typeInInputField(labelLongText);
    Assertion.assertEquals(builderPage.getLabelText(1), labelLongText);
    Assertion.assertEquals(builderPage.getLabelCssValue(1, "word-wrap"), "break-word");

    new Subhead().clickPublish();

    Assertion.assertTrue(new Spinner().isSpinnerPresent());
    Assertion.assertTrue(template.isTemplatePagePresent());

    String infoboxTitle = template.getPortableInfobox().getTitleTextWithIndex(0);
    Assertion.assertEquals("InfoboxBuilderCustomizingComponents", infoboxTitle);
  }

  @Execute(asUser = User.USER)
  public void setInfoboxTitleToUseArticleName() {
    TemplatePage template = new TemplatePage();
    Sidebar builderSidebar = new Sidebar();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("SetInfoboxTitleToUseArticleName")
        .deleteTitleUsingPopUp(0);

    builderSidebar.addTitleComponent();
    builderPage.selectTitleWithIndex(0);

    builderSidebar.setTitleToUseArticleName();
    new Subhead().clickPublish();

    Assertion.assertTrue(new Spinner().isSpinnerPresent());
    Assertion.assertTrue(template.isTemplatePagePresent());

    String infoboxTitle = template.getPortableInfobox().getTitleTextWithIndex(0);
    Assertion.assertEquals("SetInfoboxTitleToUseArticleName", infoboxTitle);
  }

  @Execute(asUser = User.USER)
  public void newTemplateCreation() {
    new TemplateEditPage().open("InfoboxBuilderNewTemplateCreation")
        .getTemplateClassification()
        .changeTemplateType()
        .clickAddButton();

    Assertion.assertTrue(new InfoboxBuilderPage().isInfoboxBuilderPresent());
  }

  @Execute(asUser = User.USER)
  public void verifySidebarBackArrow() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyInterfaceFunctionality");

    builderPage.selectRowWithIndex(0);
    builderSidebar.clickBackArrow();
    Assertion.assertTrue(builderSidebar.areAddButtonsPresent());

    builderPage.selectTitleWithIndex(0);
    builderSidebar.clickBackArrow();
    Assertion.assertTrue(builderSidebar.areAddButtonsPresent());

    builderPage.selectHeaderWithIndex(0);
    builderSidebar.clickBackArrow();
    Assertion.assertTrue(builderSidebar.areAddButtonsPresent());

    builderPage.selectImageWithIndex(0);
    builderSidebar.clickBackArrow();
    Assertion.assertTrue(builderSidebar.areAddButtonsPresent());
  }

  @Execute(asUser = User.STAFF)
  public void verifyInfoboxPreviewTheme() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();
    SpecialThemeDesignerPageObject themeDesigner = new SpecialThemeDesignerPageObject(driver);
    TemplatePage template = new TemplatePage();

    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(0);
    themeDesigner.submitTheme();

    String templateBgColor =
        template.open(PageContent.PORTABLE_INFOBOX_01).getPageBackgroundColor();
    String previewBgColor =
        builderPage.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroundColor();

    Assertion.assertEquals(previewBgColor, templateBgColor);

    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(2);
    themeDesigner.submitTheme();

    templateBgColor = template.open(PageContent.PORTABLE_INFOBOX_01).getPageBackgroundColor();
    previewBgColor =
        builderPage.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroundColor();

    Assertion.assertEquals(templateBgColor, previewBgColor);
  }

  /* Verify if scrolling is enabled when Infobox's height in
  preview is greater than the preview area height. */
  @Execute(asUser = User.USER)
  public void verifyScrolling() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();

    new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyScrolling");
    new Sidebar().addImageComponent().addImageComponent().addImageComponent().addImageComponent();

    builderPage.verifyScrollbarIsVisible().moveToLastComponent();
  }

  @Execute(asUser = User.STAFF)
  public void verifyUserInteractions() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("InfoboxBuilderVerifySelectedBorderStyling")
        .verifyTooltipOnHover();

    String borderStyle = builderPage.getBorderStyle();
    Assertion.assertEquals(borderStyle, "1px solid rgb(26, 94, 184)");

    borderStyle = builderPage.clickBuilderBackground().getComponentBackgroundColor(0);
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
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();
    String labelText = "AutomatedTest";

    builderPage.openExisting("InfoboxBuilderImmutableRows").selectRowWithIndex(0);

    new Sidebar().typeInInputField(labelText);
    Assertion.assertEquals(builderPage.getLabelText(0), labelText);

    new Subhead().clickPublish();

    String invocationLabelText = new PortableInfobox().open("InfoboxBuilderImmutableExample")
        .getDataLabelTextWithIndex(0);

    Assertion.assertEquals("AutomatedTest", invocationLabelText);
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorClickOnModalBackground() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("Infobox_verify_go_to_source");

    new Sidebar().clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickGoToSourceModalBackground();

    Assertion.assertTrue(builderPage.isInfoboxBuilderOpened());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorSaveChanges() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("Infobox_verify_go_to_source_save_changes");

    builderSidebar.addRowComponent();
    builderPage.selectRowWithIndex(1);

    builderSidebar.clickDeleteButton().clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickSaveChangesButton();

    TemplateEditPage template = new TemplateEditPage();

    Assert.assertTrue(template.isEditAreaDisplayed());
    Assert.assertFalse(template.isEditAreaEmpty());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorDropChanges() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("Infobox_verify_go_to_source_drop_changes");

    new Sidebar().clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickDropChangesButton();

    TemplateEditPage template = new TemplateEditPage();
    template.getTemplateClassification()
        .selectInfoboxTemplate()
        .clickAddButton();

    Assertion.assertTrue(template.isEditAreaDisplayed());
    Assertion.assertTrue(template.isEditAreaEmpty());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorNonEditedInfobox() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderSavingTemplate");
    new Sidebar().clickGoToSourceButton();

    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
    Assertion.assertTrue(driver.getCurrentUrl().contains("InfoboxBuilderSavingTemplate"));
  }

  @Execute(asUser = User.USER)
  public void verifyIfInputFieldIsFocusedOnSelectItem() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openNew("Infobox_verify_focus");

    builderSidebar.addHeaderComponent();
    builderPage.selectRowWithIndex(0);

    Assertion.assertTrue(builderSidebar.isInputFieldPresent());
    Assertion.assertTrue(builderSidebar.isSidebarInputFieldFocused());

    builderPage.selectHeaderWithIndex(0);

    Assertion.assertTrue(builderSidebar.isInputFieldPresent());
    Assertion.assertTrue(builderSidebar.isSidebarInputFieldFocused());
  }

  @Execute(asUser = User.USER)
  public void verifyChevronTooltip() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openExisting("InfoboxBuilderChevronPopup");

    Assertion.assertTrue(builderPage.isSectionTooltipPresentBelow(0));
    Assertion.assertTrue(builderPage.isSectionTooltipPresentAbove(1));

    builderSidebar.addHeaderComponent();
    builderPage.selectHeaderWithIndex(2);
    builderSidebar.changeHeaderCollapsibilityState();

    Assertion.assertTrue(builderPage.isSectionTooltipPresentAbove(2));
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
    Sidebar builderSidebar = new Sidebar();
    final String templateName = "Infobox_other_content";
    final String infoboxRegexp = "(?s)<infobox[^>]*>.*</infobox>";

    String beforePublish = new TemplatePage().getRawContent(templateName)
        .replaceAll(infoboxRegexp, "");

    new TemplatePage().open(templateName).loginAs(User.USER);

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting(templateName);

    builderSidebar.addRowComponent();
    builderPage.selectRowWithIndex(0);
    builderSidebar.clickDeleteButton();
    new Subhead().clickPublish();

    builderPage.logOut();

    String afterPublish =
        new TemplatePage().getRawContent(templateName).replaceAll(infoboxRegexp, "");

    Assert.assertEquals(beforePublish, afterPublish);
  }

  @Execute(asUser = User.USER)
  public void verifyNamingConflictTypingNameAfterClickingPublish() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().open();

    new Subhead().clickPublish();

    Assertion.assertTrue(builderPage.isModalEditTitlePresent());
    Assertion.assertTrue(builderPage.isEditTemplateTitleInputPresent());

    builderPage.insertTemplateTitle("InfoboxNamingConflict").clickPublishEditedTitleButton();

    Assertion.assertTrue(builderPage.isErrorMessagePresent());
  }
}
