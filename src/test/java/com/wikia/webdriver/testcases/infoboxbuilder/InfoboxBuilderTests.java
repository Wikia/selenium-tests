package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
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



}
