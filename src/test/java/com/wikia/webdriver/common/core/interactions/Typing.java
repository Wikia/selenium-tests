package com.wikia.webdriver.common.core.interactions;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.WebElement;

/**
 * Created by Ludwik on 2015-07-22.
 */
public class Typing {

  private Typing() {

  }

  /**
   * Send keys at the speed of good typist human. based on research:
   * http://smallbusiness.chron.com/good-typing-speed-per-minute-71789.html
   * "A professional or good typist hits around 325 to 335 CPM (chars per minute)" This means 60 000
   * / 330 = 182ms
   */
  public static void sendKeysHumanSpeed(WebElement input, String keys) {
    int interval = 182;
    for (char c : keys.toCharArray()) {
      String character = String.valueOf(c);
      input.sendKeys(character);
      try {
        Thread.sleep(interval);
      } catch (InterruptedException e) {
        LOG.log("ERROR WHILE TYPING", e, LOG.Type.ERROR);
      }
    }
  }
}
