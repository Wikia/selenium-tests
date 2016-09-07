package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.TemplateClassification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatePage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final TemplateClassification templateClassification = new TemplateClassification();
  private final PortableInfobox portableInfobox = new PortableInfobox();

  @FindBy(css = "#ca-edit")
  protected WebElement editUsingClassicEditor;

  @FindBy(css = "#WikiaPageBackground")
  private WebElement pageBackgroundColor;

  public TemplatePage() {
    super();
  }

  public TemplatePage open(String templateName) {
    getUrl(String.format("%s%s%s:%s", urlBuilder.getUrlForWiki(), URLsContent.WIKI_DIR,
                         URLsContent.TEMPLATE_NAMESPACE, templateName));

    return this;
  }

  public boolean isTemplatePagePresent() {
    wait.forElementVisible(pageBackgroundColor);

    return pageBackgroundColor.isDisplayed();
  }

  public String getRawContent(String templateName) {
    getUrl(urlBuilder.appendQueryStringToURL(String.format("%s%s%s:%s", urlBuilder.getUrlForWiki(),
                                                           URLsContent.WIKI_DIR,
                                                           URLsContent.TEMPLATE_NAMESPACE,
                                                           templateName),
                                             URLsContent.ACTION_RAW));
    wait.forElementPresent(By.cssSelector("body"));
    return body.getText();
  }

  public SourceEditModePageObject editArticleInSrcUsingDropdown() {
    editUsingClassicEditor.click();
    return new SourceEditModePageObject(driver);
  }

  public TemplatePage openArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
    return new TemplatePage();
  }

  public PortableInfobox getPortableInfobox() {
    return portableInfobox;
  }

  public String getPageBackgroundColor() {
    wait.forElementVisible(pageBackgroundColor);

    return pageBackgroundColor.getCssValue("background-color");
  }

  public TemplatePage createTemplate(String templateName) {
    new ArticleContent().push("", "Template:" + templateName);

    return this;
  }
}
