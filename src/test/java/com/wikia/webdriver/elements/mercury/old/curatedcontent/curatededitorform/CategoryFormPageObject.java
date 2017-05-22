package com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform;

import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;

/**
 * This class represents category editor Form
 * That object differs from Section Form:
 * Done button may redirect you to main editor home or section items form
 * It has two text fields to fill instead of one
 * That object differs from Item Form:
 * Done button may redirect you to main editor home or section items form
 * It allows only category namespace
 */
public class CategoryFormPageObject extends CuratedEditorFormPageObject {

  public SectionItemListPageObject clickDone() {
    wait.forElementVisible(doneButton);
    doneButton.click();

    return new SectionItemListPageObject();
  }
}
