package com.wikia.webdriver.testcases.templateclassification;

import com.wikia.webdriver.common.contentpatterns.TemplateTypes;
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

  @Test(groups = "templateClassification_createTemplateAndChangeItsType")
  public void templateClassification_createTemplateAndChangeItsType() {
    TemplatePage templatePage = new TemplatePage();

    TemplateClassification templateClassification = templatePage
            .createTemplate(TemplateTypes.DEFAULT_TEMPLATE_NAME)
            .open(TemplateTypes.DEFAULT_TEMPLATE_NAME)
            .getTemplateClassification()
            .open()
            .resetTemplateType()
            .save();

    String templateType = templateClassification.getTemplateType();

    Assertion.assertTrue(templateType.equals(TemplateTypes.TEMPLATE_TYPE_UNKNOWN), "Template type was reset");
    PageObjectLogging.logInfo("Template type was reset");

    templateClassification
            .open()
            .changeTemplateType(TemplateTypes.TEMPLATE_TYPE_INFOBOX)
            .save();

    templateType = templateClassification.getTemplateType();

    Assertion.assertTrue(templateType.equals(TemplateTypes.TEMPLATE_TYPE_INFOBOX), "Template type set to Infobox");
    PageObjectLogging.logInfo("Template type set to: '" + templateType + "'");

    templateClassification
            .open()
            .changeTemplateType(TemplateTypes.TEMPLATE_TYPE_QUOTE)
            .save();

    String oldTemplateType = templateType;
    String currentTemplateType = templateClassification.getTemplateType();

    Assertion.assertFalse(currentTemplateType.equals(oldTemplateType), "Template type did not change");
    PageObjectLogging.logInfo(
            "Template type changed from: '" + oldTemplateType + "', to: '" + currentTemplateType + "'");

  }
}
