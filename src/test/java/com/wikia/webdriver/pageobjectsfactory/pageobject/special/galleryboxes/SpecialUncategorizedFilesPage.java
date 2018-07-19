package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public final class SpecialUncategorizedFilesPage extends WikiBasePageObject {

  private static final String SPECIAL_UNCATEGORIZED_FILES_PATH = "Special:UncategorizedFiles";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialUncategorizedFilesPage open() {
    getUrl(urlBuilder.getUrlForWikiPage(SPECIAL_UNCATEGORIZED_FILES_PATH));
    Log.log("Special Uncategorized Files Page", SPECIAL_UNCATEGORIZED_FILES_PATH + " opened", true);

    return this;
  }
}
