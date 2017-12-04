package com.webdriver.common.contentpatterns;

public class MercuryMessages {

  public static final String VISIBLE_MSG = "element is visible";
  public static final String INVISIBLE_MSG = "element is not visible";

  public static final String IMAGE_INVISIBLE_MSG = "Image is not visible";

  public static final String COLLAPSED_MSG = "element is collapsed";
  public static final String EXPANDED_MSG = "element is expanded";

  public static final String LIST_ITEMS_ARE_UNIQUE_MSG = "all list elements are unique";
  public static final String LIST_ITEMS_ARE_NOT_UNIQUE_MSG =
      "there are at least two elements with the same name";

  public static final String ALL_VALID_WIDGETS_ARE_SWAPPED_MSG =
      "all valid widget placeholders are swapped for widget inline frames";
  public static final String SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG =
      "some valid widget placeholders are not swapped for widget inline frames";

  private MercuryMessages() {
  }
}
