package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Lukasz Jedrzejczak
 * @author Lukasz Nowak (Dyktus)
 */

public class CreateACustomMapComponentObject extends BasePageObject {

  @FindBy(css = "#intMapUpload")
  private WebElement browseForFileInput;
  @FindBy(css = "#intMapTileSetSearch")
  private WebElement searchField;
  @FindBy(css = "#intMapBack")
  private WebElement backButton;
  @FindBy(css = ".modalEvent>img")
  private List<WebElement> templateList;
  @FindBy(css = "#intMapError")
  private WebElement errorField;
  @FindBy(css = ".close")
  private WebElement closeButton;

  private String beforeImageName = "116x116-";

  public CreateACustomMapComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clearSearchTitle() {
    wait.forElementVisible(searchField);
    searchField.clear();
  }

  public InteractiveMapsPageObject clickCloseButton() {
    wait.forElementVisible(closeButton);
    closeButton.click();
    return new InteractiveMapsPageObject(driver);
  }

  public CreateAMapComponentObject clickBack() {
    wait.forElementVisible(backButton);
    backButton.click();
    LOG.logResult("clickCustomMap", "custom map link clicked", true, driver);
    return new CreateAMapComponentObject(driver);
  }

  public String getSelectedTemplateImageName(int selectedImageIndex) {
    int imageNameIndex =
        templateList.get(selectedImageIndex).getAttribute("src").indexOf(beforeImageName);
    String selectedTemplateImageName =
        templateList.get(selectedImageIndex).getAttribute("src")
            .substring(imageNameIndex + beforeImageName.length());
    return selectedTemplateImageName;
  }

  public TemplateComponentObject selectFileToUpload(String file) {
    browseForFileInput.sendKeys(CommonUtils
        .getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file));
    LOG.success("typeInFileToUploadPath", "type file " + file + " to upload it");
    return new TemplateComponentObject(driver);
  }

  public TemplateComponentObject selectTemplate(int templateId) {
    wait.forElementVisible(templateList.get(templateId));
    templateList.get(templateId).click();
    return new TemplateComponentObject(driver);
  }

  public void typeSearchTile(String templateName) {
    wait.forElementVisible(searchField);
    searchField.sendKeys(templateName);
    LOG.success("typeTilesetName", "title (" + templateName
                                   + ") for template is typed in");
  }

  public void verifyErrorExists() {
    wait.forElementVisible(errorField);
    Assertion.assertEquals(isElementOnPage(errorField), true);
  }

  public void verifyTemplateListElementVisible(int element) {
    wait.forElementVisible(templateList.get(element));
    LOG.success("verifyTemplateListElementVisible", "Template element is visible ");
  }
}
