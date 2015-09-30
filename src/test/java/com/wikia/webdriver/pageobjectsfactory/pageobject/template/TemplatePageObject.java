package com.wikia.webdriver.pageobjectsfactory.pageobject.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;

/**
 * Created by Rodriuki on 16/06/15. Created by nikodamn on 20/07/15.
 */
public class TemplatePageObject extends WikiBasePageObject {

  @FindBy(css = "#ca-edit")
  protected WebElement editUsingClassicEditor;
  @FindBy(css = ".wikia-button #edit")
  private WebElement createButton;
  @FindBy(css = "#articleCategories")
  private WebElement categoriesModule;

  public TemplatePageObject(WebDriver driver) {
    super(driver);
  }

  public SourceEditModePageObject clickCreate() {
    wait.forElementVisible(createButton);
    createButton.click();
    return new SourceEditModePageObject(driver);
  }

  public SourceEditModePageObject editArticleInSrcUsingDropdown() {
    editUsingClassicEditor.click();
    return new SourceEditModePageObject(driver);
  }

  public void verifyCategoryInTemplatePage(String catName) {
    wait.forElementVisible(categoriesModule);
  }

  public TemplatePageObject openArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
    return new TemplatePageObject(driver);
  }
}
