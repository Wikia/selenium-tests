package com.wikia.webdriver.pageobjectsfactory.pageobject.special.anonymization;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialAnonymizationUserPage extends SpecialPageObject {

  @FindBy(css = "#username")
  private WebElement AnonymizationTextBox;
  @FindBy(css = "input[type=\"submit\"]")
  private WebElement submitButton;
  @FindBy(css = "#mw-content-text > section > span")
  private WebElement requestConfirmation;

  public SpecialAnonymizationUserPage open() {
    getUrl(urlBuilder.getUrlForWikiPage(URLsContent.SPECIAL_ANONYMIZATION));

    driver.navigate().refresh();
    return this;
  }

  public SpecialAnonymizationUserPage fillFutureAnon(String anonymizedUser) {
    wait.forElementClickable(submitButton);
    AnonymizationTextBox.sendKeys(anonymizedUser);
    return this;
  }

  public SpecialAnonymizationUserPage submitAnonymization() {
    jsActions.scrollToElement(submitButton);
    submitButton.click();
    return this;
  }

  public String getAnonConfirmation() {
    wait.forElementVisible(requestConfirmation);
    return requestConfirmation.getText();
  }
}
