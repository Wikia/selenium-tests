package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.core.annotations.Browser;

public class AdsFloorAdhesionSkinContext {

  private static final String OASIS_CREATIVE_ID = "66223683972";
  private static final String OASIS_LINE_ITEM_ID = "158514252";
  private static final String OASIS_SLOT_NAME = "INVISIBLE_SKIN";
  private static final String OASIS_MODAL_CSS = "#blackout_ext-wikia-adEngine-template-modal";
  private static final String OASIS_MODAL_CLOSE_CSS = "#ext-wikia-adEngine-template-modal .close";

  private static final String MERCURY_CREATIVE_ID = "66223684092";
  private static final String MERCURY_LINE_ITEM_ID = "158514372";
  private static final String MERCURY_SLOT_NAME = "INVISIBLE_HIGH_IMPACT";
  private static final String MERCURY_MODAL_CSS = ".lightbox-ads";
  private static final String MERCURY_MODAL_CLOSE_CSS = ".lightbox-close-wrapper";

  private String creativeId;
  private String lineItemId;
  private String modalCloseSelector;
  private String modalSelector;
  private String slotName;

  public AdsFloorAdhesionSkinContext(String browser) {
    creativeId = OASIS_CREATIVE_ID;
    lineItemId = OASIS_LINE_ITEM_ID;
    modalSelector = OASIS_MODAL_CSS;
    modalCloseSelector = OASIS_MODAL_CLOSE_CSS;
    slotName = OASIS_SLOT_NAME;

    if (Browser.CHROME_MOBILE_MERCURY.equalsIgnoreCase(browser)) {
      creativeId = MERCURY_CREATIVE_ID;
      lineItemId = MERCURY_LINE_ITEM_ID;
      modalSelector = MERCURY_MODAL_CSS;
      modalCloseSelector = MERCURY_MODAL_CLOSE_CSS;
      slotName = MERCURY_SLOT_NAME;
    }
  }

  public String getCreativeId() {
    return creativeId;
  }

  public String getLineItemId() {
    return lineItemId;
  }

  public String getModalCloseSelector() {
    return modalCloseSelector;
  }

  public String getModalSelector() {
    return modalSelector;
  }

  public String getSlotName() {
    return slotName;
  }
}
