package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.TemplateTypes;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.InfoboxBuilderPage;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.elements.oasis.pages.WikiFeatures;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import org.joda.time.DateTime;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InfoboxBuilderTests extends NewTestTemplate {

  private static final int EUROPA_INFOBOX_WIDTH = 300;
  private static final int DEFAULT_INFOBOX_WIDTH = 270;

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
  @Execute(asUser = User.USER)
  public void savingTemplate() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();
    String templateName = "InfoboxBuilderSavingTemplate";

    builderPage.openExisting(templateName).selectRowWithIndex(0);
    builderSidebar.clickDeleteButton();
    builderSidebar.addRowComponent();
    new Subhead().clickPublish();

    Assertion.assertEquals(templateName.toLowerCase(),
        new TemplatePage().getHeaderText().toLowerCase());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
  @Execute(asUser = User.USER)
  public void deletingDefaultComponentsUsingPopUp() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingDefaultComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();

    Assertion.assertEquals(rowComponents - 1, builderPage.deleteRowUsingPopUp(0).countRows());
    Assertion.assertEquals(titleComponents - 1, builderPage.deleteTitleUsingPopUp(0).countTitles());
    Assertion.assertEquals(imageComponents - 1, builderPage.deleteImageUsingPopUp(0).countImages());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_001"})
  @Execute(asUser = User.USER)
  public void customizingComponents() {
    TemplatePage template = new TemplatePage();
    Sidebar builderSidebar = new Sidebar();
    String labelText = "AutomatedTest";
    String labelLongText = "AutomatedTestVeryLongName";

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("InfoboxBuilderCustomizingComponents").selectHeaderWithIndex(0);

    builderSidebar.changeHeaderCollapsibilityState();

    builderPage.selectRowWithIndex(0);
    builderSidebar.typeInInputField(labelText);
    Assertion.assertEquals(builderPage.getLabelText(0), labelText);

    builderPage.selectRowWithIndex(1);
    builderSidebar.typeInInputField(labelLongText);
    Assertion.assertEquals(builderPage.getLabelText(1), labelLongText);
    Assertion.assertEquals(builderPage.getLabelCssValue(1, "word-wrap"), "break-word");

    new Subhead().clickPublish();

    Assertion.assertTrue(template.isTemplatePagePresent());

    String infoboxTitle = template.getPortableInfobox().getTitleTextWithIndex(0);
    Assertion.assertEquals("InfoboxBuilderCustomizingComponents", infoboxTitle);
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002"})
  @Execute(asUser = User.USER)
  public void setInfoboxTitleToUseArticleName() {
    TemplatePage template = new TemplatePage();
    Sidebar builderSidebar = new Sidebar();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("SetInfoboxTitleToUseArticleName").deleteTitleUsingPopUp(0);

    builderSidebar.addTitleComponent();
    builderPage.selectTitleWithIndex(0);

    builderSidebar.setTitleToUseArticleName();
    new Subhead().clickPublish();

    Assertion.assertTrue(template.isTemplatePagePresent());

    String infoboxTitle = template.getPortableInfobox().getTitleTextWithIndex(0);
    Assertion.assertEquals("SetInfoboxTitleToUseArticleName", infoboxTitle);
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002"})
  @Execute(asUser = User.USER)
  public void newInfoboxTemplateCreationRedirectsToInfoboxBuilder() {
    new TemplateEditPage()
        .open("NewInfoboxTemplateCreationRedirectsToInfoboxBuilder" + DateTime.now().getMillis())
        .getTemplateClassification()
        .changeTemplateType(TemplateTypes.INFOBOX)
        .clickAddButton();

    Assertion.assertTrue(new InfoboxBuilderPage().isInfoboxBuilderPresent());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002"})
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002"})
  @Execute(asUser = User.STAFF)
  public void verifyInfoboxPreviewTheme() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();
    SpecialThemeDesignerPageObject themeDesigner = new SpecialThemeDesignerPageObject(driver);
    TemplatePage template = new TemplatePage();

    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(0);
    themeDesigner.submitTheme();

    String templateBgColor =
        template.open(PageContent.INFOBOX_2).getPageBackgroundColor();
    String previewBgColor =
        builderPage.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroundColor();

    Assertion.assertEquals(previewBgColor, templateBgColor);

    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(2);
    themeDesigner.submitTheme();

    templateBgColor = template.open(PageContent.INFOBOX_2).getPageBackgroundColor();
    previewBgColor =
        builderPage.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroundColor();

    Assertion.assertEquals(templateBgColor, previewBgColor);
  }

  /*
   * Verify if scrolling is enabled when Infobox's height in preview is greater than the preview
   * area height.
   */
  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002"})
  @Execute(asUser = User.USER)
  public void verifyScrolling() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();

    new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyScrolling");
    new Sidebar().addImageComponent().addImageComponent().addImageComponent().addImageComponent();

    builderPage.verifyScrollbarIsVisible().moveToLastComponent();
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002"})
  @Execute(asUser = User.STAFF)
  public void verifyUserInteractions() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("InfoboxBuilderVerifySelectedBorderStyling").hoverMouseOverComponent(0);

    Assertion.assertTrue(builderPage.isTooltipVisible(), "Component tooltip is not visible");

    String borderStyle = builderPage.getBorderStyle();
    Assertion.assertEquals(borderStyle, "1px solid rgb(26, 94, 184)");

    borderStyle = builderPage.clickBuilderBackground().getComponentBackgroundColor(0);
    Assertion.assertNotEquals(borderStyle, "1px solid rgb(26, 94, 184)");
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_002", "test_verify"})
  @Execute(asUser = User.STAFF)
  public void verifyReordering() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage infoboxBuilder =
        new InfoboxBuilderPage().openNew("InfoboxBuilderVerifyReordering");

    builderSidebar.addRowComponent();
    infoboxBuilder.selectRowWithIndex(0);
    builderSidebar.typeInInputField("First Label");

    infoboxBuilder.selectRowWithIndex(1);
    builderSidebar.typeInInputField("Second Label");

    infoboxBuilder.selectRowWithIndex(2);
    builderSidebar.typeInInputField("Third Label");

    WebElement element = infoboxBuilder.getInfoboxComponent(2);
    WebElement topElement = infoboxBuilder.dragAndDropToTheTop(element);
    Assertion.assertEquals(element.getText(), topElement.getText());

    element = infoboxBuilder.getInfoboxComponent(3);
    topElement = infoboxBuilder.dragAndDropToTheTop(element);
    Assertion.assertEquals(element.getText(), topElement.getText());

    element = infoboxBuilder.getInfoboxComponent(1);
    topElement = infoboxBuilder.dragAndDropToTheTop(element);
    Assertion.assertEquals(element.getText(), topElement.getText());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
  @Execute(asUser = User.USER)
  public void verifyRedirectingUnsupportedInfoboxes() {
    new TemplatePage().open("InfoboxBuilderMultipleInfoboxes").editArticleInSrcUsingDropdown();
    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());

    new TemplatePage().open("InfoboxBuilderUnsupportedMarkup").editArticleInSrcUsingDropdown();
    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
  @Execute(asUser = User.USER)
  public void immutableSources() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();
    String labelText = "AutomatedTest";

    builderPage.openExisting("InfoboxBuilderImmutableRows").selectRowWithIndex(0);

    new Sidebar().typeInInputField(labelText);
    Assertion.assertEquals(builderPage.getLabelText(0), labelText);

    new Subhead().clickPublish();

    String invocationLabelText =
        new PortableInfobox().open("InfoboxBuilderImmutableExample").getDataLabelTextWithIndex(0);

    Assertion.assertEquals("AutomatedTest", invocationLabelText);
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorClickOnModalBackground() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("Infobox_verify_go_to_source");

    new Sidebar().clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickGoToSourceModalBackground();

    Assertion.assertTrue(builderPage.isInfoboxBuilderOpened());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorSaveChanges() {
    Sidebar builderSidebar = new Sidebar();
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openExisting("Infobox_verify_go_to_source_save_changes");

    builderSidebar.addRowComponent();
    builderPage.selectRowWithIndex(1);

    builderSidebar.clickDeleteButton().clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickSaveChangesButton();

    TemplateEditPage template = new TemplateEditPage();

    Assert.assertTrue(template.isEditAreaDisplayed());
    Assert.assertFalse(template.isEditAreaEmpty());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorDropChanges() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("Infobox_verify_go_to_source_drop_changes");

    new Sidebar().clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickDropChangesButton();

    TemplateEditPage template = new TemplateEditPage();
    template
      .getTemplateClassification()
      .changeTemplateType(TemplateTypes.INFOBOX)
      .clickAddButton();

    Assertion.assertTrue(template.isEditAreaDisplayed());
    Assertion.assertTrue(template.isEditAreaEmpty());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorNonEditedInfobox() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderSavingTemplate");
    new Sidebar().clickGoToSourceButton();

    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
    Assertion.assertTrue(driver.getCurrentUrl().contains("InfoboxBuilderSavingTemplate"));
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_003"})
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "MAIN-8926")
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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  @Execute(asUser = User.STAFF)
  public void verifyLoadingEuropaTheme() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting("Infobox_theme_europa");

    Assertion.assertEquals(builderPage.getInfoboxWidth(), EUROPA_INFOBOX_WIDTH);
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  @Execute(asUser = User.STAFF)
  public void verifyLoadingDefaultTheme() {
    new WikiFeatures().openWikiFeatures(wikiURL).disableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting("Infobox_theme_default");

    Assertion.assertEquals(builderPage.getInfoboxWidth(), DEFAULT_INFOBOX_WIDTH);
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  @Execute(asUser = User.ANONYMOUS)
  public void verifyBuilderAuthentication() {
    TemplateEditPage template = new TemplateEditPage().open("InfoboxBuilderAuthentication");

    Assertion.assertTrue(template.isPermissionErrorDisplayed());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  public void verifyOtherContentIsNotChanged() {
    Sidebar builderSidebar = new Sidebar();
    final String templateName = "Infobox_other_content";
    final String infoboxRegexp = "(?s)<infobox[^>]*>.*</infobox>";

    String beforePublish =
        new TemplatePage().getRawContent(templateName).replaceAll(infoboxRegexp, "");

    new TemplatePage().open(templateName).loginAs(User.USER_4);

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

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  @Execute(asUser = User.USER)
  public void verifyNamingConflictTypingNameAfterClickingPublish() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().open();

    new Subhead().clickPublish();

    Assertion.assertTrue(builderPage.isModalEditTitlePresent());
    Assertion.assertTrue(builderPage.isEditTemplateTitleInputPresent());

    builderPage.insertTemplateTitle("InfoboxNamingConflict").clickPublishEditedTitleButton();

    Assertion.assertTrue(builderPage.isErrorMessagePresent());
  }

  @Test(groups = {"InfoboxBuilderTests", "InfoboxBuilder_004"})
  @Execute(asUser = User.USER)
  public void changeTemplateNameByClickingSubhead() {
    Subhead subhead = new Subhead();
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().open();

    subhead.clickSubheadTitle();

    Assertion.assertTrue(builderPage.isModalEditTitlePresent());
    Assertion.assertTrue(builderPage.isEditTemplateTitleInputPresent());

    builderPage.insertTemplateTitle("InfoboxBuilderChangeTemplateNameBySubhead")
        .clickPublishEditedTitleButton();

    builderPage.waitUntilEditTitleModalIsClosed();

    Assertion.assertEquals(subhead.getSubheadTitle(),
        "Editing template: InfoboxBuilderChangeTemplateNameBySubhead");
  }
}
