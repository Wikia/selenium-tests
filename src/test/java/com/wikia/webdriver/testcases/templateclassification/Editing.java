package com.wikia.webdriver.testcases.templateclassification;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.EntryPoint;
import com.wikia.webdriver.elements.oasis.components.templateclassificiation.TemplateModal;

import org.testng.annotations.Test;

@Execute(asUser = User.STAFF, onWikia = "aga")
public class Editing extends NewTestTemplate {

  private Navigate navigate;
  private EntryPoint entryPoint;
  private TemplateModal templateModal;

  private void init() {
    this.navigate = new Navigate(driver);
    this.entryPoint = new EntryPoint(driver);
    this.templateModal = new TemplateModal(driver);
  }

  @Test()
  public void openAndClose() {
    init();

    navigate.toPage("/wiki/Template:T");
    entryPoint.openTemplateClassificationModal();
    templateModal.closeTemplateClassificationModal();

  }

  @Test()
  public void openChooseSave() {
    init();

    navigate.toPage("/wiki/Template:T");
    entryPoint.openTemplateClassificationModal();
    templateModal.selectTemplateType(entryPoint.getTemplateName());
    templateModal.saveTemplateType();
    entryPoint.checkTemplateType();
  }

  @Test()
  public void actionEdit() {
    init();

    navigate.toPage("/wiki/Template:AutoTest1", new String[]{"action=edit"});
    templateModal.selectInfoboxTemplate();
    templateModal.clickEnter();
    entryPoint.checkTemplateType();
  }
}
