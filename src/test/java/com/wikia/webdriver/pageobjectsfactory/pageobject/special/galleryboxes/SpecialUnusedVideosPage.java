package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public final class SpecialUnusedVideosPage extends GalleryBox {

  private static final String SPECIAL_UNUSED_VIDEOS_PATH = "Special:UnusedVideos";

  public SpecialUnusedVideosPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNUSED_VIDEOS_PATH));
    PageObjectLogging.log("Special Unused Videos Page", SPECIAL_UNUSED_VIDEOS_PATH + " opened", true);

    return this;
  }
}
