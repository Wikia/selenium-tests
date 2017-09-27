package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialCreatePage extends SpecialPageObject {

  @FindBy(css = "#HiddenFieldsDialog input[name='wpTitle']")
  private WebElement titleInput;
  @FindBy(css = "#HiddenFieldsDialog #ok")
  private WebElement submitTitleInput;

  public SpecialCreatePage() {
    super();
  }


  public SpecialCreatePage open(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CREATE_PAGE);
    return this;
  }

  public SpecialCreatePage open() {
    return open(urlBuilder.getUrlForWiki());
  }

  public VisualEditModePageObject populateTitleField(String title) {
    wait.forElementVisible(titleInput);
    titleInput.sendKeys(title);
    wait.forElementClickable(submitTitleInput);
    submitTitleInput.click();
    waitForElementNotVisibleByElement(submitTitleInput);
    return new VisualEditModePageObject();
  }
}
