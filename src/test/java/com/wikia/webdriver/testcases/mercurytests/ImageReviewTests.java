package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ImageReview;

import org.testng.annotations.Test;

@Test(groups = "ImageReview")
public class ImageReviewTests extends NewTestTemplate {

  @Test(groups = "staffUserCanAccessImageReview")
  public void staffUserCanAccessImageReview() {
    new ImageReview().open();
  }

}
