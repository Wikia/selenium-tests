package com.wikia.webdriver.elements.mercury.components.discussions.common;


import lombok.AllArgsConstructor;
import com.google.common.base.Predicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@AllArgsConstructor
public class DeleteDialog extends ConfirmationDialog {

  @FindBy(className = "post-detail")
  private List<WebElement> postList;

  public void confirmAndWait() {
    super.clickConfirm();
    new WebDriverWait(driver, DiscussionsConstants.TIMEOUT).until(
      (Predicate<WebDriver>) input -> postList.stream().allMatch(p -> p.getAttribute("class").contains("is-deleted"))
    );
  }

}
