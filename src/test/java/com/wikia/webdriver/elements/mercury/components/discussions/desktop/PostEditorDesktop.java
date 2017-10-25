package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.StandaloneContributionEditor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostEditorDesktop extends StandaloneContributionEditor {

  @Getter
  @FindBy(css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

}
