package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class ImageReview extends WikiBasePageObject {

  public ImageReview open() {
    getUrl("http://www.wikia.com/image-review");

    return this;
  }

}
