package com.wikia.webdriver.testcases.templateclassification;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;

@Execute(asUser = User.STAFF, onWikia = "aga")
@InBrowser(browser = Browser.CHROME)
public class TemplateClassificationTest extends NewTestTemplate {

  @Test(groups = "templateClassification_openAndClose")
  public void templateClassification_openAndClose() {
    new TemplatePage(driver)
        .open("T")
        .getTemplateClassification()
        .open()
        .close();
  }

  @Test(groups = "templateClassification_changeTemplateType")
  public void templateClassification_changeTemplateType() {
    new TemplatePage(driver)
        .open("T")
        .getTemplateClassification()
        .open()
        .selectTemplateType()
        .close();
  }

  @Test(groups = "templateClassification_saveTemplateTypeForNewTemplate")
  public void templateClassification_saveTemplateTypeForNewTemplate() {
    new TemplateEditPage(driver)
        .open("AutoTest1")
        .getTemplateClassification()
        .selectTemplateType()
        .save();
  }
}
