package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.StandaloneContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.StandaloneEditor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ReplyCreatorMobile extends StandaloneContributionEditor {

  public boolean isPresent() {
    return !driver.findElements(By.className("discussion-editor-entry-point-container")).isEmpty();
  }

}
