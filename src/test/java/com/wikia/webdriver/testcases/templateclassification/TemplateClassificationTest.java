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
@Test(groups = {"templateClassification"})
public class TemplateClassificationTest extends NewTestTemplate {

  private String templateName = "T";

  @Test(groups = "templateClassification_openAndClose")
  public void templateClassification_openAndClose() {
    new TemplatePage()
        .createTemplate(this.templateName)
        .open(this.templateName)
        .getTemplateClassification()
        .open()
        .close();
  }

  @Test(groups = "templateClassification_changeTemplateType")
  public void templateClassification_changeTemplateType() {
    new TemplatePage()
        .createTemplate(this.templateName)
        .open(this.templateName)
        .getTemplateClassification()
        .open()
        .changeTemplateType()
        .close();
  }

  @Test(groups = "templateClassification_saveTemplateTypeForNewTemplate")
  public void templateClassification_saveTemplateTypeForNewTemplate() {
    new TemplateEditPage()
        .open("AutoTestInfobox")
        .getTemplateClassification()
        .selectQuoteTemplate()
        .save()
        .compareTemplateTypes();
  }
}
