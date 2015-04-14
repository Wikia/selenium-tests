package com.wikia.webdriver.common.logging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertchan on 4/13/15.
 */
public class TestSuiteLogging {

  private String testName;
  private String className;
  private String date;
  private String time;
  private String browser;
  private String OS;
  private String testDomain;
  private String prodDomain;
  private String version;
  private List<TestLogging> testLoggings;

  public TestSuiteLogging (String date, String time, String browser, String OS, String testDomain, String prodDomain, String version) {

    this.date = date;
    this.time = time;
    this.browser = browser;
    this.OS = OS;
    this.testDomain = testDomain;
    this.prodDomain = prodDomain;
    this.version = version;

    testLoggings = new ArrayList<TestLogging>();
  }

  public void addTest(TestLogging test) {
    testLoggings.add(test);
  }

  public List<TestLogging> getTests() {
    return testLoggings;
  }
}
