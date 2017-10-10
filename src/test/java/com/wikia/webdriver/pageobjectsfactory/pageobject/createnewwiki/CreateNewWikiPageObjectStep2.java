package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateNewWikiPageObjectStep2 extends BasePageObject {

  @FindBy(css = "textarea#Description")
  private WebElement descriptionField;
  @FindBy(css = "#DescWiki .wds-dropdown")
  private WebElement wikiCategoryDropdown;
  @FindBy(css = "#DescWiki .wds-dropdown .wds-list li")
  private List<WebElement> wikiCategoryList;
  @FindBy(css = "form[name='desc-form'] input.next")
  private WebElement submitButton;
  @FindBy(css = "label[for='allAges']")
  private WebElement allAgesCheckBox;
  @FindBy(css = "#DescWiki .wiki-vertical-error.error-msg")
  private WebElement categoryErrorMsg;

  public CreateNewWikiPageObjectStep2(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public void selectCategory(int categoryId) {
    if (wikiCategoryList.isEmpty()) {
      throw new WebDriverException("No categories to choose from");
    }
    if (wikiCategoryList.size() < categoryId + 1) {
      throw new WebDriverException("Cannot find a categpory with this index");
    }
    scrollTo(wikiCategoryDropdown);
    wait.forElementClickable(wikiCategoryDropdown);
    WebElement selectedCategory;
    try {
      selectedCategory = wikiCategoryList.get(categoryId);
    } catch (IndexOutOfBoundsException e){
      PageObjectLogging.log("selectCategory","There is no category with index " + categoryId, false);
      throw new WebDriverException("There is no category with index " + categoryId, e);
    }
    hover(wikiCategoryDropdown);
    wait.forElementClickable(selectedCategory);
    selectedCategory.click();

    PageObjectLogging.log("selectCategory", "selected " + selectedCategory.getText() + " category", true, driver);
  }

  public CreateNewWikiPageObjectStep3 submit() {
    wait.forElementVisible(submitButton);
    scrollAndClick(submitButton);
    PageObjectLogging.log("submit", "Submit button clicked", true);
    return new CreateNewWikiPageObjectStep3(driver);
  }

  public void selectAllAgesCheckbox() {
    scrollAndClick(allAgesCheckBox);
    PageObjectLogging.log("selectAllAgesCheckbox", "all ages checkbox selected", true);
  }

  public void verifyCategoryError() {
    wait.forElementVisible(categoryErrorMsg);
    Assertion.assertEquals(categoryErrorMsg.getText(), CreateWikiMessages.CATEGORY_ERROR_MESSAGE);
  }
}
