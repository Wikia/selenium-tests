package com.wikia.webdriver.common.properties;

import com.wikia.webdriver.common.core.Global;

import java.io.File;

/**
 * This file is added to .gitignore Developer is supposed to set here paths to selenium-config and
 * selenium-captcha folders. Developer can adjsut this file for individual needs.
 *
 * @author Bogna 'bognix' Knychała
 */
public class PropertiesSetter {

  /**
   * This method is used by Properties.class when developer is running tests using IDE
   */

  public static void setPropertiesManually() {
    Global.BROWSER = "FF";
    Global.DOMAIN = "http://mediawiki119.wikia.com/";
    Global.LIVE_DOMAIN = "http://www.wikia.com/";
    String seleniumConfigDir = "c:" + File.separator + "selenium-config";
    Global.CONFIG_FILE = new File(seleniumConfigDir + File.separator + "config.xml");
    Global.CAPTCHA_FILE = new File(seleniumConfigDir + File.separator + "captcha.txt");

    Global.LOG_VERBOSE = 2;
    if (Global.DOMAIN.contains("dev") || Global.DOMAIN.contains("sandbox")) {
      Global.LOGIN_BY_COOKIE = false;
    } else {
      Global.LOGIN_BY_COOKIE = true;
    }
    Global.LOG_ENABLED = true;
  }
}
