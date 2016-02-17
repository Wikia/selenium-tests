package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.infoboxbuilder.SpecialInfoboxBuilderPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;

import org.testng.annotations.Test;

/**
 * @ownshership: Content West-Wing
 */
public class InfoboxBuilderTests extends NewTestTemplate {

  private SpecialInfoboxBuilderPageObject builder;
  private PortableInfoboxPageObject infobox;
  private TemplatePage templatePage;
  private SpecialThemeDesignerPageObject themeDesigner;

  void init() {
    builder = new SpecialInfoboxBuilderPageObject(driver);
    infobox = new PortableInfoboxPageObject(driver);
    themeDesigner = new SpecialThemeDesignerPageObject(driver);
  }

  @Test(groups = {"InfoboxBuilderTests_001"})
  @Execute(onWikia = "mediawiki119")
  public void verifyDefaultStructure() {
    init();
    builder.open("verifyDefaultStructure")
        .switchToIFrame()
        .verifyDefaultTemplateStructure();
  }

  @Test(groups = {"InfoboxBuilderTests_002"})
  @Execute(onWikia = "mediawiki119")
  public void addingComponents() {
    init();
    builder.open("addingComponents")
        .switchToIFrame()
        .verifyRowAdded()
        .verifyTitleAdded()
        .verifyImageAdded();
  }

  @Test(groups = {"InfoboxBuilderTests_003"})
  @Execute(onWikia = "mediawiki119")
  public void savingTemplate() {
    init();
    templatePage = builder.open("savingTemplate")
        .switchToIFrame()
        .addRowComponent()
        .save();
    //investigate why save isn't redirecting to new template page
  }

  @Test(groups = {"InfoboxBuilderTests_003"})
  @Execute(onWikia = "mediawiki119")
  public void deletingComponents() {
    init();
    builder.open("deletingComponents")
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
  @Execute(onWikia = "mediawiki119")
  public void customizingComponents() {
    init();
    builder.open("customizingComponents")
        .switchToIFrame()
        .setRowLabelWithIndex(0, "AutomatedTest");

//    templatePage = builder.open("test1234")
//        .switchToIFrame()
//        .setTitleToUseArticleName(0)
//        .save();
//
//    String templateTitle = templatePage.getNameForArticle();
//
//    System.out.println(templateTitle);
//    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
//    System.out.println(infobox.getHeaderName(0));
  }

  @Test(groups = {"InfoboxBuilderTests_005"})
  @Execute(onWikia = "mediawiki119")
  public void verifyInterfaceFunctionality() {
    init();
    builder.open("verifyInterfaceFunctionality")
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
    init();
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
  @Execute(onWikia = "mediawiki119")
  public void verifyScrolling() {
    init();
    builder.open("verifyScrolling")
        .switchToIFrame()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .verifyScrollbarIsVisible()
        .scrollAndSelectLastComponent();
  }


}
