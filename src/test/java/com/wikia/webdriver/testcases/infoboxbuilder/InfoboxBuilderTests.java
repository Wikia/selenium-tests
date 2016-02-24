package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.elements.oasis.pages.InfoboxBuilder;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import org.testng.annotations.Test;

/**
 * @ownshership: Content West-Wing
 */
public class InfoboxBuilderTests extends NewTestTemplate {

  @Test(groups = {"InfoboxBuilderTests_001"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void verifyDefaultStructure() {
    new InfoboxBuilder()
        .open("verifyDefaultStructure")
        .switchToIFrame()
        .verifyDefaultTemplateStructure();
  }

  @Test(groups = {"InfoboxBuilderTests_002"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void addingComponents() {
    new InfoboxBuilder().open("addingComponents")
        .switchToIFrame()
        .verifyRowAdded()
        .verifyTitleAdded()
        .verifyImageAdded();
  }

  @Test(groups = {"InfoboxBuilderTests_003"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void savingTemplate() {
    InfoboxBuilder builder = new InfoboxBuilder();
    TemplatePage templatePage = builder.open("savingTemplate")
        .switchToIFrame()
        .addRowComponent()
        .addImageComponent()
        .save();

    builder.verifyCreatedTemplateName("savingTemplate", templatePage);
  }

  @Test(groups = {"InfoboxBuilderTests_003"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void deletingComponents() {
    new InfoboxBuilder()
        .open("deletingComponents")
        .switchToIFrame()

        /* deleting default components */
        .verifyDeletingTitleWithIndex(0)
        .verifyDeletingImageWithIndex(0)
        .verifyDeletingRowWithIndex(1)
        .verifyDeletingRowWithIndex(0)

        /* deleting newly added components */
        .addRowComponent()
        .addImageComponent()
        .addTitleComponent()
        .verifyDeletingRowWithIndex(0)
        .verifyDeletingImageWithIndex(0)
        .verifyDeletingTitleWithIndex(0);
  }

  @Test(groups = {"InfoboxBuilderTests_004"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void customizingComponents() {
    InfoboxBuilder builder = new InfoboxBuilder();

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

  @Test(groups = {"InfoboxBuilderTests_005"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void verifyInterfaceFunctionality() {
    new InfoboxBuilder().open("verifyInterfaceFunctionality")
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

  @Test(groups = {"InfoboxBuilderTests_006"})
  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void verifyInfoboxPreviewTheme() {
    InfoboxBuilder builder = new InfoboxBuilder();
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
  @Test(groups = {"InfoboxBuilderTests_007"})
  @Execute(asUser = User.USER, onWikia = "mediawiki119")
  public void verifyScrolling() {
    new InfoboxBuilder().open("verifyScrolling")
        .switchToIFrame()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .verifyScrollbarIsVisible()
        .scrollAndSelectLastComponent();
  }

  @Test(groups = {"InfoboxBuilderTests_008"})
  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void verifyUserInteractions() {
    new InfoboxBuilder().open("verifySelectedBorderStyling")
        .switchToIFrame()
        .verifySelectedComponentBorderStyle(0)
        .verifyTooltipOnHover();
  }

  @Test(groups = {"InfoboxBuilderTests_009"})
  @Execute(asUser = User.STAFF, onWikia = "mediawiki119")
  public void verifyReordering() {
    new InfoboxBuilder().open("verifyReordering")
        .switchToIFrame()
        .dragAndDropToTheTop(2)
        .dragAndDropToTheTop(3)
        .dragAndDropToTheTop(1);
  }

}
