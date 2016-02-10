package com.wikia.webdriver.pageobjectsfactory.pageobject.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.TemplateClassification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;

public class TemplatePage extends WikiBasePageObject {

  @FindBy(css = "#ca-edit")
  protected WebElement editUsingClassicEditor;

  public TemplatePage(WebDriver driver) {
    super(driver);
  }

  public TemplatePage open(String templateName) {
    getUrl(String.format("%s%s%s:%s", urlBuilder.getUrlForWiki(), URLsContent.WIKI_DIR,
        URLsContent.TEMPLATE_NAMESPACE, templateName));

    return this;
  }

  public TemplateClassification getTemplateClassification(){
    return new TemplateClassification(driver);
  }

  public SourceEditModePageObject editArticleInSrcUsingDropdown() {
    editUsingClassicEditor.click();
    return new SourceEditModePageObject(driver);
  }

  public TemplatePage openArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
    return new TemplatePage(driver);
  }
}
