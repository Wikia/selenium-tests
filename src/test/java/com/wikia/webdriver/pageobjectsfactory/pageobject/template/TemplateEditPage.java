package com.wikia.webdriver.pageobjectsfactory.pageobject.template;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.TemplateClassification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class TemplateEditPage extends WikiBasePageObject {

  public TemplateEditPage(WebDriver driver) {
    super(driver);
  }

  public TemplateEditPage open(String templateName) {
    getUrl(urlBuilder.appendQueryStringToURL(String.format("%s%s%s:%s", urlBuilder.getUrlForWiki(),
        URLsContent.WIKI_DIR, URLsContent.TEMPLATE_NAMESPACE, templateName),
        URLsContent.ACTION_EDIT));

    return this;
  }

  public TemplateClassification getTemplateClassification() {
    return new TemplateClassification(driver);
  }
}
