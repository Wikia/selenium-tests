package com.wikia.webdriver.elements.mercury.components.discussions.desktop;


import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.InlineContributionEditor;
import org.openqa.selenium.By;

public class ReplyCreatorDesktop extends InlineContributionEditor {

  public boolean isPresent() {
    return !driver.findElements(By.cssSelector(".replies-list label:first-of-type")).isEmpty();
  }

}
