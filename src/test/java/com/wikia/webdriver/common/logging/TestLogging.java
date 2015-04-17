package com.wikia.webdriver.common.logging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertchan on 4/13/15.
 */
public class TestLogging {

  private String relatedIssueID;
  private String testName;
  private String className;
  private List<TestStepsLogging> testSteps;

  public TestLogging() {
    testSteps = new ArrayList<TestStepsLogging>();
  }

  public void addSteps(TestStepsLogging steps) {
    testSteps.add(steps);
  }

  public List<TestStepsLogging> getSteps() {
    return testSteps;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public void setRelatedIssueID(String relatedIssueID) { this.relatedIssueID = relatedIssueID; }
}
