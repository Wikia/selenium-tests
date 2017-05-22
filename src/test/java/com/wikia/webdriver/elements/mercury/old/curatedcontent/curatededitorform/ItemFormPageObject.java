package com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform;

import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.EditorHomePageObject;

/**
 * This class represents item editor Form That object differs from Section Form: Done button
 * redirects you to main editor home It has two text fields to fill instead of one That object
 * differs from Category Form: It allows more namespaces than category
 */
public class ItemFormPageObject extends CuratedEditorFormPageObject {

  public EditorHomePageObject clickDone() {
    wait.forElementVisible(doneButton);
    doneButton.click();

    return new EditorHomePageObject();
  }
}
