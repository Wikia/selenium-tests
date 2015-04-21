package com.wikia.webdriver.common.logging;

/**
 * Created by robertchan on 4/13/15.
 */
public class TestStepsLogging {

  private String command;
  private String className;
  private String description;
  private boolean success;
  private String screenshot;
  private String htmlSource;

  public TestStepsLogging(String className, String command, String description, boolean success,
                          String screenshot, String htmlSource) {
    this.className = className;
    this.command = command;
    this.description = description;
    this.success = success;
    this.screenshot = screenshot;
    this.htmlSource = htmlSource;
  }

  public TestStepsLogging(String className, String command, String description, boolean success,
                          String screenshot) {
    this.className = className;
    this.command = command;
    this.description = description;
    this.success = success;
    this.screenshot = screenshot;
  }

  public TestStepsLogging(String className, String command, String description, boolean success) {
    this.className = className;
    this.command = command;
    this.description = description;
    this.success = success;
  }
}
