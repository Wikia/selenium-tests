package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedEditorFormPageObject;

/**
 * This class represents Section editor Form That object differs from Category and Item Form: Done
 * button redirects to section items view It has one text field to fill instead of two It doesn't
 * allow adding namespace item, it creates a section
 *
 * @ownership: Content X-Wing
 */
public class SectionFormPageObject extends CuratedEditorFormPageObject {

  public SectionFormPageObject(WebDriver driver) {
    super(driver);
  }

  public SectionItemListPageObject clickDone() {
    waitAndClick(doneButton);
    return new SectionItemListPageObject(driver);
  }
}
