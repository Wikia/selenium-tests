package com.wikia.webdriver.testcases.templateclassification;

import com.wikia.webdriver.common.contentpatterns.TemplateTypes;
import com.wikia.webdriver.common.core.Assertion;
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

  private static final String DEFAULT_TEMPLATE_NAME = "T";

  @Test(groups = "templateClassification_createTemplateAndChangeItsType")
  public void templateClassification_createTemplateAndChangeItsType() {
    TemplatePage templatePage = new TemplatePage();

    TemplateClassification templateClassification = templatePage
      .createTemplate(DEFAULT_TEMPLATE_NAME)
      .open(DEFAULT_TEMPLATE_NAME)
      .getTemplateClassification()
      .open()
      .resetTemplateType()
      .save();

    String oldTemplateType = templateClassification.getTemplateType();

    Assertion.assertEquals(templateClassification.getTemplateType(), TemplateTypes.UNKNOWN.getType());

    templateClassification
      .open()
      .changeTemplateType(TemplateTypes.INFOBOX)
      .save();

    Assertion.assertEquals(templateClassification.getTemplateType(), TemplateTypes.INFOBOX.getType());

    templateClassification
      .open()
      .changeTemplateType(TemplateTypes.QUOTE)
      .save();

    String currentTemplateType = templateClassification.getTemplateType();

    Assertion.assertNotEquals(currentTemplateType, oldTemplateType);
  }
}
