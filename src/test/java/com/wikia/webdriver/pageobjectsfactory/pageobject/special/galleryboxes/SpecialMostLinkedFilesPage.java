package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public final class SpecialMostLinkedFilesPage extends WikiBasePageObject {

  private static final String SPECIAL_MOST_LINKED_FILES_PATH = "Special:MostLinkedFiles";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialMostLinkedFilesPage open() {
    getUrl(urlBuilder.getUrlForWikiPage(SPECIAL_MOST_LINKED_FILES_PATH));
    Log.log("Special Most Linked Files Page", SPECIAL_MOST_LINKED_FILES_PATH + " opened", true);

    return this;
  }
}
