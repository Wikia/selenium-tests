package com.wikia.webdriver.common.clicktracking;

import java.util.List;

import javax.json.JsonObject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

public class ClickTrackingSupport {

  public void compare(List<JsonObject> expectedEventList, List<JsonObject> trackedEventList) {
    for (JsonObject expectedEvent : expectedEventList) {
      Boolean equals = false;
      for (JsonObject trackedEvent : trackedEventList) {
        equals = trackedEvent.equals(expectedEvent);
        if (equals) {
          LOG.success("compare", "match for expected event found: \n" + "expected event: "
              + expectedEvent.toString() + "\n" + "compared event: " + trackedEvent.toString());
          trackedEventList.remove(trackedEvent);
          break;
        }
      }
      Assertion.assertTrue(equals, "didn't match expected event: " + expectedEvent.toString());
    }
  }
}
