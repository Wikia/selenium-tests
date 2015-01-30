package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Karol
 */

public class CreateNewWikiPageObjectStep2 extends BasePageObject {

  @FindBy(css = "textarea#Description")
  private WebElement descriptionField;
  @FindBy(css = "select[name='wiki-vertical']")
  private WebElement wikiCategory;
  @FindBy(css = "form[name='desc-form'] input[class='next']")
  private WebElement submitButton;
  @FindBy(name = "all-ages")
  private WebElement allAgesCheckBox;
  @FindBy(css = "#DescWiki .submit-error.error-msg")
  private WebElement categoryErrorMsg;

  public CreateNewWikiPageObjectStep2(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void describeYourTopic(String description) {
    waitForElementByElement(descriptionField);
    descriptionField.sendKeys(description);
    PageObjectLogging
        .log("describeYourTopic", "describe your topic populated with: " + description, true);
  }

  public void selectCategory(String category) {
    waitForElementByElement(wikiCategory);
    Select dropList = new Select(wikiCategory);
    dropList.selectByVisibleText(category);
    PageObjectLogging.log("selectCategory", "selected " + category + " category", true, driver);
  }

  public CreateNewWikiPageObjectStep3 submit() {
    waitForElementByElement(submitButton);
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "Submit button clicked", true);
    return new CreateNewWikiPageObjectStep3(driver);
  }

  public void selectAllAgesCheckbox() {
    allAgesCheckBox.click();
    PageObjectLogging.log("selectAllAgesCheckbox", "all ages checkbox selected", true);
  }

  public void verifyCategoryError() {
    waitForElementByElement(categoryErrorMsg);
    Assertion.assertEquals(CreateWikiMessages.CATEGORY_ERROR_MESSAGE, categoryErrorMsg.getText());
  }
}
