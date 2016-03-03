package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.InfoboxBuilderPage;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import org.testng.annotations.Test;

@Test(groups = "InfoboxBuilderTests")
@Execute(onWikia = "mediawiki119")
public class InfoboxBuilderTests extends NewTestTemplate {

  @Execute(asUser = User.USER)
  public void verifyDefaultStructure() {
    new InfoboxBuilderPage()
        .open("verifyDefaultStructure")
        .switchToIFrame()
        .verifyDefaultTemplateStructure();
  }

  @Execute(asUser = User.USER)
  public void addingComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();

    builder.open("addingComponents").switchToIFrame();
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();

    builder.addRowComponent().addImageComponent().addTitleComponent();

    Assertion.assertEquals(rowComponents + 1, builder.countRows());
    Assertion.assertEquals(titleComponents + 1, builder.countTitles());
    Assertion.assertEquals(imageComponents + 1, builder.countImages());
  }

  @Execute(asUser = User.USER)
  public void savingTemplate() {
    TemplatePage templatePage = new InfoboxBuilderPage()
        .open("savingTemplate")
        .switchToIFrame()
        .addRowComponent()
        .addImageComponent()
        .save();

    Assertion.assertEquals("savingtemplate", templatePage.getHeaderText().toLowerCase());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    builder.open("deletingDefaultComponents").switchToIFrame();
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();

    Assertion.assertEquals(rowComponents - 1,
                           builder.deleteRowComponentWithIndex(0).countRows());
    Assertion.assertEquals(titleComponents - 1,
                           builder.deleteTitleComponentWithIndex(0).countTitles());
    Assertion.assertEquals(imageComponents - 1,
                           builder.deleteImageComponentWithIndex(0).countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingAddedComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    builder.open("deletingDefaultComponents").switchToIFrame();
    int rowComponents = builder.countRows();
    int titleComponents = builder.countTitles();
    int imageComponents = builder.countImages();

    builder.addImageComponent().addTitleComponent().addRowComponent();

    /* deleting last (newly added) components */
    Assertion.assertEquals(
        rowComponents , builder.deleteRowComponentWithIndex(builder.countRows() - 1).countRows()
    );
    Assertion.assertEquals(
        titleComponents,
        builder.deleteTitleComponentWithIndex(builder.countTitles() - 1).countTitles()
    );
    Assertion.assertEquals(
        imageComponents,
        builder.deleteImageComponentWithIndex(builder.countImages() - 1).countImages()
    );
  }

  @Execute(asUser = User.USER)
  public void customizingComponents() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();

    String infoboxPreviewBackground = builder.open("customizingComponents")
        .switchToIFrame()
        .getBackgroundColor();

    TemplatePage template = builder
        .open("customizingComponents")
        .switchToIFrame()
        .setAndVerifyRowLabelWithIndex(0, "AutomatedTest")
        .setLongLabelNameAndVerifyBreakLine(1, "AutomatedTestVeryLabelName")
        .setTitleToUseArticleName(0)
        .save();

    String infoboxBackground = template.getPortableInfobox().getBackgroundColor();
    Assertion.assertEquals(infoboxPreviewBackground, infoboxBackground);
  }

  @Execute(asUser = User.USER)
  public void verifyInterfaceFunctionality() {
    new InfoboxBuilderPage().open("verifyInterfaceFunctionality")
        .switchToIFrame()
        .selectRowWithIndex(0)
        .verifyHelpDialog()
        .selectTitleWithIndex(0)
        .verifyHelpDialog()
        .selectRowWithIndex(0)
        .verifyBackArrowFunctionality()
        .selectTitleWithIndex(0)
        .verifyBackArrowFunctionality()
        .selectImageWithIndex(0)
        .verifyBackArrowFunctionality();
  }

  @Execute(asUser = User.STAFF)
  public void verifyInfoboxPreviewTheme() {
    InfoboxBuilderPage builder = new InfoboxBuilderPage();
    SpecialThemeDesignerPageObject themeDesigner = new SpecialThemeDesignerPageObject(driver);
    PortableInfobox infobox = new PortableInfobox();

    /* select light theme */
    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(0);
    themeDesigner.submitTheme();

    String invocationBackgroundColor = infobox.open(PageContent.PORTABLE_INFOBOX_01)
        .getBackgroundColor();

    builder.open("verifyInfoboxTheme")
        .switchToIFrame()
        .verifyInfoboxPreviewBackgroundColor(invocationBackgroundColor);

    /* select dark theme */
    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(3);
    themeDesigner.submitTheme();

    invocationBackgroundColor = infobox.open(PageContent.PORTABLE_INFOBOX_02)
        .getBackgroundColor();

    builder.open("verifyInfoboxTheme")
        .switchToIFrame()
        .verifyInfoboxPreviewBackgroundColor(invocationBackgroundColor);
  }

  /* Verify if scrolling is enabled when Infobox's height in
  preview is greater than the preview area height. */
  @Execute(asUser = User.USER)
  public void verifyScrolling() {
    new InfoboxBuilderPage().open("verifyScrolling")
        .switchToIFrame()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .verifyScrollbarIsVisible()
        .scrollAndSelectLastComponent();
  }

  @Execute(asUser = User.STAFF)
  public void verifyUserInteractions() {
    new InfoboxBuilderPage().open("verifySelectedBorderStyling")
        .switchToIFrame()
        .verifySelectedComponentBorderStyle(0)
        .verifyTooltipOnHover();
  }

  @Execute(asUser = User.STAFF)
  public void verifyReordering() {
    new InfoboxBuilderPage().open("verifyReordering")
        .switchToIFrame()
        .dragAndDropToTheTop(2)
        .dragAndDropToTheTop(3)
        .dragAndDropToTheTop(1);
  }

}
