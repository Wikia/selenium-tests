package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

public class AdsFloorAdhesionSkinContext {

  private static final String OASIS_CREATIVE_ID = "66223683972";
  private static final String OASIS_LINE_ITEM_ID = "260166732";
  private static final String OASIS_SLOT_NAME = "INVISIBLE_SKIN";

  private static final String MERCURY_CREATIVE_ID = "94178805972";
  private static final String MERCURY_LINE_ITEM_ID = "270609492";
  private static final String MERCURY_SLOT_NAME = "invisible_high_impact_2";

  private String creativeId;
  private String lineItemId;
  private String slotName;

  public AdsFloorAdhesionSkinContext(Boolean isMobile) {
    creativeId = OASIS_CREATIVE_ID;
    lineItemId = OASIS_LINE_ITEM_ID;
    slotName = OASIS_SLOT_NAME;

    if (isMobile) {
      creativeId = MERCURY_CREATIVE_ID;
      lineItemId = MERCURY_LINE_ITEM_ID;
      slotName = MERCURY_SLOT_NAME;
    }
  }

  public String getCreativeId() {
    return creativeId;
  }

  public String getLineItemId() {
    return lineItemId;
  }

  public String getSlotName() {
    return slotName;
  }
}
