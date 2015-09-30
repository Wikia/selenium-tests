package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
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
    wait.forElementVisible(descriptionField);
    descriptionField.sendKeys(description);
    LOG
        .result("describeYourTopic", "describe your topic populated with: " + description, true);
  }

  public void selectCategory(String category) {
    wait.forElementVisible(wikiCategory);
    Select dropList = new Select(wikiCategory);
    dropList.selectByVisibleText(category);
    LOG.logResult("selectCategory", "selected " + category + " category", true, driver);
  }

  public CreateNewWikiPageObjectStep3 submit() {
    wait.forElementVisible(submitButton);
    scrollAndClick(submitButton);
    LOG.success("submit", "Submit button clicked");
    return new CreateNewWikiPageObjectStep3(driver);
  }

  public void selectAllAgesCheckbox() {
    scrollAndClick(allAgesCheckBox);
    LOG.success("selectAllAgesCheckbox", "all ages checkbox selected");
  }

  public void verifyCategoryError() {
    wait.forElementVisible(categoryErrorMsg);
    Assertion.assertEquals(categoryErrorMsg.getText(), CreateWikiMessages.CATEGORY_ERROR_MESSAGE);
  }
}
