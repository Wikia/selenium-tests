package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import lombok.Getter;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public final class SpecialMostLinkedFilesPage extends WikiBasePageObject {

  private static final String SPECIAL_MOST_LINKED_FILES_PATH = "Special:MostLinkedFiles";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialMostLinkedFilesPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_MOST_LINKED_FILES_PATH));
    PageObjectLogging.log("Special Most Linked Files Page",
        SPECIAL_MOST_LINKED_FILES_PATH + " opened", true);

    return this;
  }
}
