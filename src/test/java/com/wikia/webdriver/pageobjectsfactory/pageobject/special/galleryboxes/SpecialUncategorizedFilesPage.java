package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.PageObjectLogging;

final public class SpecialUncategorizedFilesPage extends GalleryBox {

  public static final String SPECIAL_UNCATEGORIZED_FILES_PATH = "Special:UncategorizedFiles";

  public SpecialUncategorizedFilesPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNCATEGORIZED_FILES_PATH));
    PageObjectLogging.log("Special Uncategorized Files Page",
        SPECIAL_UNCATEGORIZED_FILES_PATH + " opened", true);

    return this;
  }
}
