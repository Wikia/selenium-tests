package com.wikia.webdriver.testcases.templateclassification;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.TemplateClassification;
import org.testng.annotations.Test;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;

@Execute(asUser = User.STAFF, onWikia = "aga")
@InBrowser(browser = Browser.CHROME)
@Test(groups = {"templateClassification"})
public class TemplateClassificationTest extends NewTestTemplate {

  private String templateName = "T";

  @Test(groups = "templateClassification_createTemplateAndChangeItsType")
  public void templateClassification_createTemplateAndChangeItsType() {
    TemplatePage templatePage = new TemplatePage();

    TemplateClassification templateClassification = templatePage
            .createTemplate(this.templateName)
            .open(this.templateName)
            .getTemplateClassification();

    templateClassification
            .resetTemplateType();

    Assertion.assertTrue(templateName.equals("Unknown"), "Template type was reset");
    PageObjectLogging.logInfo("Template type was reset");

    templateClassification
            .changeTemplateType("Infobox");

    String templateName = templateClassification.getTemplateType();

    Assertion.assertTrue(templateName.equals("Infobox"), "Template type set to Infobox");
    PageObjectLogging.logInfo("Template type set to: '" + templateName + "'");

    templateClassification
            .changeTemplateType("Quote");

    String oldTemplateName = templateName;
    String currentTemplateName = templateClassification.getTemplateType();

    Assertion.assertFalse(currentTemplateName.equals(oldTemplateName), "Template type did not change");
    PageObjectLogging.logInfo(
            "Template type changed from: '" + oldTemplateName + "', to: '" + currentTemplateName + "'");

  }
}
