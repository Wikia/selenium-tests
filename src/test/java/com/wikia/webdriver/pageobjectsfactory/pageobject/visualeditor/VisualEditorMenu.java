package com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor;

import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Indentation;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertList;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMapDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorHyperLinkDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorInsertGalleryDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorInsertTemplateDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorKeyboardShortcutsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorOptionsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReferenceDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReferenceListDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSourceEditorDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorMenu extends WikiBasePageObject {

  private static final int STYLE_LIST = 1;
  private static final int INSERT_LIST = 2;
  private static final int HAMBURGER_LIST = 0;

  @FindBy(css = ".oo-ui-icon-bold-b")
  private WebElement boldButton;
  @FindBy(css = ".oo-ui-icon-italic-i")
  private WebElement italicButton;
  @FindBy(css = ".oo-ui-icon-link")
  private WebElement linkButton;
  @FindBy(css = ".oo-ui-frame")
  protected WebElement linkIframe;
  @FindBy(css = ".oo-ui-icon-code")
  private WebElement codeButton;
  @FindBy(css = ".oo-ui-icon-clear")
  private WebElement clearButton;
  @FindBy(css = ".oo-ui-menuToolGroup .oo-ui-indicator-down")
  private WebElement formattingDropDown;
  @FindBy(css = ".oo-ui-menuToolGroup")
  private WebElement formattingDropDownItems;
  @FindBy(css = ".oo-ui-listToolGroup .oo-ui-indicator-down")
  private List<WebElement> toolListDropDowns;
  @FindBy(css = ".oo-ui-listToolGroup")
  private List<WebElement> toolListItems;
  @FindBy(css = ".ve-ui-toolbar-saveButton.oo-ui-widget-enabled")
  private WebElement enabledPublishButton;
  @FindBy(css = ".oo-ui-icon-video")
  private WebElement videoButton;
  @FindBy(css = ".oo-ui-icon-gallery")
  private WebElement galleryButton;
  @FindBy(css = ".oo-ui-icon-image")
  private WebElement imageButton;
  @FindBy(css = ".oo-ui-icon-bullet-list")
  private WebElement bulletList;
  @FindBy(css = ".oo-ui-icon-template")
  private WebElement template;
  @FindBy(css = ".oo-ui-icon-number-list")
  private WebElement numberedList;
  @FindBy(css = ".oo-ui-tool-name-wikiaMapInsert .oo-ui-tool-title")
  private WebElement map;
  @FindBy(css = ".oo-ui-icon-reference")
  private WebElement reference;
  @FindBy(css = ".oo-ui-icon-references")
  private WebElement referenceList;
  @FindBy(css = ".oo-ui-icon-settings")
  private WebElement pageSettings;
  @FindBy(css = ".oo-ui-icon-tag")
  private WebElement categories;
  @FindBy(css = ".oo-ui-icon-keyboard")
  private WebElement keyboardShortcuts;
  @FindBy(css = ".oo-ui-icon-source")
  private WebElement sourceEditor;

  private By strikeStyleBy = By.cssSelector(".oo-ui-icon-strikethrough-s");
  private By underlineStyleBy = By.cssSelector(".oo-ui-icon-underline-u");
  private By subscriptStyleBy = By.cssSelector(".oo-ui-icon-subscript");
  private By superscriptStyleBy = By.cssSelector(".oo-ui-icon-superscript");
  private By indentBy = By.cssSelector(".oo-ui-icon-indent-list");
  private By outdentBy = By.cssSelector(".oo-ui-icon-outdent-list");
  private By publishButtonDisabled = By
      .cssSelector(".oo-ui-toolbar-saveButton.ve-ui-widget-disabled");
  private By mediaBy = By.cssSelector(".oo-ui-tool-name-wikiaMediaInsert .oo-ui-tool-title");
  private By paragraphBy = By.cssSelector(".oo-ui-tool-name-paragraph");
  private By headingBy = By.cssSelector(".oo-ui-tool-name-heading2");
  private By subHeading1By = By.cssSelector(".oo-ui-tool-name-heading3");
  private By subHeading2By = By.cssSelector(".oo-ui-tool-name-heading4");
  private By subHeading3By = By.cssSelector(".oo-ui-tool-name-heading5");
  private By subHeading4By = By.cssSelector(".oo-ui-tool-name-heading6");
  private By preformatedBy = By.cssSelector(".oo-ui-tool-name-preformatted");
  private By menuItemBy = By.cssSelector(".oo-ui-tool-title");
  private By labelBy = By.cssSelector(".oo-ui-labelElement-label");

  private void clickStyleItemFromDropDown(By styleBy) {
    WebElement styleListElement = toolListDropDowns.get(STYLE_LIST);
    WebElement styleItems = toolListItems.get(STYLE_LIST);
    wait.forElementVisible(styleListElement);
    Actions actions = new Actions(driver);
    actions.click(styleListElement).click(styleItems.findElement(styleBy)).build().perform();
  }

  public void selectStyle(Style style) {
    switch (style) {
      case BOLD:
        boldButton.click();
        break;
      case ITALIC:
        italicButton.click();
        break;
      case STRIKETHROUGH:
        clickStyleItemFromDropDown(strikeStyleBy);
        break;
      case SUBSCRIPT:
        clickStyleItemFromDropDown(subscriptStyleBy);
        break;
      case SUPERSCRIPT:
        clickStyleItemFromDropDown(superscriptStyleBy);
        break;
      case UNDERLINE:
        clickStyleItemFromDropDown(underlineStyleBy);
        break;
      default:
        throw new NoSuchElementException("Non-existing style selected");
    }
  }

  public void clickFormatting(By formatBy) {
    wait.forElementClickable(formattingDropDown);
    Actions actions = new Actions(driver);
    actions.click(formattingDropDown)
        .click(formattingDropDownItems.findElement(formatBy).findElement(menuItemBy)).build()
        .perform();
  }

  public void selectFormatting(Formatting format) {
    switch (format) {
      case PARAGRAPH:
        clickFormatting(paragraphBy);
        break;
      case HEADING:
        clickFormatting(headingBy);
        break;
      case SUBHEADING1:
        clickFormatting(subHeading1By);
        break;
      case SUBHEADING2:
        clickFormatting(subHeading2By);
        break;
      case SUBHEADING3:
        clickFormatting(subHeading3By);
        break;
      case SUBHEADING4:
        clickFormatting(subHeading4By);
        break;
      case PREFORMATTED:
        clickFormatting(preformatedBy);
        break;
      default:
        throw new NoSuchElementException("Non-existing format selected");
    }
  }

  public void selectIndentation(Indentation indent) {
    switch (indent) {
      case INCREASE:
        clickStyleItemFromDropDown(indentBy);
        break;
      case DECREASE:
        clickStyleItemFromDropDown(outdentBy);
        break;
      default:
        throw new NoSuchElementException("Non-existing indentation selected");
    }
  }

  public VisualEditorDialog openDialogFromMenu(InsertDialog insert) {
    switch (insert) {
      case MAP:
        clickInsertItemFromDropDown(map);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorAddMapDialog(driver);
      case REFERENCE:
        clickInsertItemFromDropDown(reference);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorReferenceDialog(driver);
      case REFERENCE_LIST:
        clickInsertItemFromDropDown(referenceList);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorReferenceListDialog(driver);
      case TEMPLATE:
        clickInsertItemFromDropDown(template);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorInsertTemplateDialog(driver);
      case PAGE_SETTINGS:
        clickHamburgerItemFromDropDown(pageSettings);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorOptionsDialog(driver);
      case CATEGORIES:
        clickHamburgerItemFromDropDown(categories);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorOptionsDialog(driver);
      case KEYBOARD_SHORTCUTS:
        clickHamburgerItemFromDropDown(keyboardShortcuts);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorKeyboardShortcutsDialog(driver);
      case SOURCE_EDITOR:
        clickHamburgerItemFromDropDown(sourceEditor);
        PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
        return new VisualEditorSourceEditorDialog(driver);
      default:
        throw new NoSuchElementException("Non-existing dialog selected");
    }
  }

  public void insertList(InsertList insert) {
    switch (insert) {
      case BULLET_LIST:
        clickInsertItemFromDropDown(bulletList);
        break;
      case NUMBERED_LIST:
        clickInsertItemFromDropDown(numberedList);
        break;
    }
    PageObjectLogging.log("selectInsertToInsertList", insert.toString() + " selected", true);
  }

  private void clickInsertItemFromDropDown(WebElement insertBy) {
    clickItemFromDropDown(toolListDropDowns.get(this.INSERT_LIST),
                          toolListItems.get(this.INSERT_LIST), insertBy);
  }

  private void clickHamburgerItemFromDropDown(WebElement insertBy) {
    clickItemFromDropDown(toolListDropDowns.get(this.HAMBURGER_LIST),
                          toolListItems.get(this.HAMBURGER_LIST), insertBy);
  }

  private void clickItemFromDropDown(WebElement list, WebElement item, WebElement insertBy) {
    wait.forElementClickable(list);
    Actions actions = new Actions(driver);
    actions.click(list).click(insertBy).build().perform();
  }

  public VisualEditorHyperLinkDialog clickLinkButton() {
    wait.forElementClickable(linkButton);
    linkButton.click();
    return new VisualEditorHyperLinkDialog(driver);
  }

  public VisualEditorAddMediaDialog clickVideoButton() {
    wait.forElementClickable(videoButton);
    videoButton.click();
    return new VisualEditorAddMediaDialog(driver);
  }

  public VisualEditorInsertGalleryDialog clickGalleryButton() {
    wait.forElementClickable(galleryButton);
    galleryButton.click();
    return new VisualEditorInsertGalleryDialog(driver);
  }

  public VisualEditorAddMediaDialog clickImageButton() {
    wait.forElementClickable(imageButton);
    imageButton.click();
    return new VisualEditorAddMediaDialog(driver);
  }

  public void clickCodeButton() {
    codeButton.click();
  }

  public void clickClearButton() {
    clearButton.click();
  }

  public VisualEditorSaveChangesDialog clickPublishButton() {
    wait.forElementNotPresent(publishButtonDisabled);
    wait.forElementVisible(enabledPublishButton);
    WebElement publishButton = enabledPublishButton.findElement(labelBy);
    wait.forElementClickable(publishButton);
    publishButton.click();
    return new VisualEditorSaveChangesDialog(driver);
  }

  public void verifyVEToolBarPresent() {
    wait.forElementVisible(veMode);
    wait.forElementVisible(veToolMenu);
    PageObjectLogging.log("verifyVEToolBar", "VE toolbar is displayed", true);
  }

  public void verifyVEToolBarNotPresent() {
    waitForElementNotVisibleByElement(veMode);
    waitForElementNotVisibleByElement(veToolMenu);
    PageObjectLogging.log("verifyVEToolBarNotPresent", "VE toolbar is not visible", true);
  }
}
