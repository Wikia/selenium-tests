package com.wikia.webdriver.testcases.templateclassification;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.TemplateClassification;

import org.testng.annotations.Test;

@Execute(asUser = User.STAFF, onWikia = "aga")
@InBrowser(browser = Browser.CHROME)
public class TemplateClassificationTest extends NewTestTemplate {

  private Navigate navigate;
  private TemplateClassification templateClassification;

  private void init() {
    this.navigate = new Navigate(driver);
    this.templateClassification = new TemplateClassification(driver);
  }

  @Test(groups = "templateClassification_openAndClose")
  public void templateClassification_openAndClose() {
    init();

    navigate.toPage("/wiki/Template:T");
    templateClassification.open();
    templateClassification.close();
  }

  @Test(groups = "templateClassification_changeTemplateType")
  public void templateClassification_changeTemplateType() {
    init();

    navigate.toPage("/wiki/Template:T");
    templateClassification.open();
    templateClassification.selectTemplateType();
    templateClassification.save();
  }

  @Test(groups = "templateClassification_saveTemplateTypeForNewTemplate")
  public void templateClassification_saveTemplateTypeForNewTemplate() {
    init();

    navigate.toPage("/wiki/Template:AutoTest1", new String[]{"action=edit"});
    templateClassification.selectTemplateType();
    templateClassification.save();
  }
}
