package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Sidebar extends WikiBasePageObject {

  @FindBy(css = ".infobox-builder-sidebar-header .infobox-builder-sidebar-header-icon-delete")
  private WebElement deleteButton;

  @FindBy(css = ".infobox-builder-sidebar-padding .check-box-input")
  private WebElement sidebarCheckbox;

  @FindBy(css = ".infobox-builder-sidebar-padding .text-field-input")
  private WebElement sidebarInputField;

  @FindBy(css = ".infobox-builder-sidebar-header-icon-back")
  private WebElement backArrowButton;

  @FindBy(css = ".infobox-builder-buttons")
  private WebElement componentsButtonsWrapper;

  @FindBy(css = "#go-to-source")
  private WebElement goToSourceButton;

  @FindBy(css = ".pi-header")
  private WebElement infoboxHeader;

  @FindBy(css = ".infobox-builder-button")
  private List<WebElement> componentsButtons;

  public Sidebar() { super(); }

  public boolean isSidebarInputFieldFocused() {
    return sidebarInputField.equals(driver.switchTo().activeElement());
  }

  public boolean isInputFieldPresent() {
    wait.forXMilliseconds(5000);
    wait.forElementVisible(sidebarInputField);

    return true;
  }

  public boolean isTitleUsingArticleName() {
    wait.forElementClickable(sidebarCheckbox);

    return sidebarCheckbox.isSelected();
  }

  public Sidebar setTitleToUseArticleName() {
    wait.forElementClickable(sidebarCheckbox);

    if (!sidebarCheckbox.isSelected()) {
      sidebarCheckbox.click();
    }

    return this;
  }

  public boolean areAddButtonsPresent() {
    wait.forElementVisible(componentsButtonsWrapper);

    return true;
  }

  public Sidebar addRowComponent() {
    WebElement rowComponentButton = componentsButtons.get(2);
    wait.forElementClickable(rowComponentButton);
    rowComponentButton.click();

    return this;
  }

  public Sidebar addImageComponent() {
    WebElement imageComponentButton = componentsButtons.get(1);
    wait.forElementClickable(imageComponentButton);
    imageComponentButton.click();

    return this;
  }

  public Sidebar addTitleComponent() {
    WebElement titleComponentButton = componentsButtons.get(0);
    wait.forElementClickable(titleComponentButton);
    titleComponentButton.click();

    return this;
  }

  public Sidebar addHeaderComponent() {
    WebElement headerComponentButton = componentsButtons.get(3);
    wait.forElementClickable(headerComponentButton);
    headerComponentButton.click();

    return this;
  }

  public Sidebar clickBackArrow() {
    wait.forElementClickable(backArrowButton);
    backArrowButton.click();
    wait.forElementNotVisible(backArrowButton);

    return this;
  }

  public Sidebar changeHeaderCollapsibilityState() {
    wait.forElementClickable(sidebarCheckbox);
    sidebarCheckbox.click();



    String chevronContent = getPseudoElementValue(infoboxHeader, ":after", "content");

    if (sidebarCheckbox.isSelected()) {
      Assertion.assertFalse(chevronContent.isEmpty());
    } else {
      Assertion.assertTrue(chevronContent.isEmpty());
    }

    return this;
  }

  public Sidebar clickDeleteButton() {
    wait.forElementClickable(deleteButton);
    deleteButton.click();

    return this;
  }

  public Sidebar typeInInputField(String labelName) {
    wait.forElementClickable(sidebarInputField);
    sidebarInputField.click();
    sidebarInputField.clear();
    sidebarInputField.sendKeys(labelName);

    return this;
  }

  public TemplateEditPage clickGoToSourceButton() {
    wait.forElementClickable(goToSourceButton);
    goToSourceButton.click();

    return new TemplateEditPage();
  }

}
