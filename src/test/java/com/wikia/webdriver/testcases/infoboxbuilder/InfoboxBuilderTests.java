package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.PortableInfoboxObject;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.infoboxbuilder.SpecialInfoboxBuilderPageObject;
import org.testng.annotations.Test;

/**
 * @ownshership: Content West-Wing
 */
public class InfoboxBuilderTests extends NewTestTemplate {

  private SpecialInfoboxBuilderPageObject builder;
  private TemplatePage templatePage;

  void init() {
    builder = new SpecialInfoboxBuilderPageObject(driver);
  }

  @Test(groups = {"InfoboxBuilderTests_001"})
  @Execute(onWikia = "mediawiki119")
  public void defaultStructure() {
    init();
    builder.open("test123")
        .switchToIFrame()
        .verifyDefaultTemplateStructure();
  }

  @Test(groups = {"InfoboxBuilderTests_002"})
  @Execute(onWikia = "mediawiki119")
  public void addingComponents() {
    init();
    builder.open("test123")
        .switchToIFrame()
        .verifyRowAdded()
        .verifyTitleAdded()
        .verifyImageAdded();
  }

  @Test(groups = {"InfoboxBuilderTests_003"})
  @Execute(onWikia = "mediawiki119")
  public void saveTemplate() {
    init();
    templatePage = builder.open("test123")
        .switchToIFrame()
        .addRowComponent()
        .save();
    //investigate why save isn't redirecting to new template page
  }

  @Test(groups = {"InfoboxBuilderTests_003"})
  @Execute(onWikia = "mediawiki119")
  public void deletingComponents() {
    init();
    builder.open("test123")
        .switchToIFrame()
        .verifyDeletingTitleWithIndex(0)
        .verifyDeletingImageWithIndex(0)
        .verifyDeletingRowWithIndex(0);
  }

  @Test(groups = {"InfoboxBuilderTests_004"})
  @Execute(onWikia = "mediawiki119")
  public void customizingComponents() {
    init();
    builder.open("test123")
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
    builder.open("test123")
        .switchToIFrame()
        .selectRowWithIndex(0)
        .verifyHelpDialog()
        .selectTitleWithIndex(0)
        .verifyHelpDialog()
        .selectRowWithIndex(0)
        .verifyBackFunctionality();
  }



}
