package com.wikia.webdriver.pageobjectsfactory.pageobject.special.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownshership: Content West-Wing
 */
public class SpecialInfoboxBuilderPageObject extends SpecialPageObject {

  private int defaultTitleCount = 1;
  private int defaultImageCount = 1;
  private int defaultDataLabelsCount = 2;

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;
  @FindBy(css = ".sub-head--done")
  private WebElement saveButton;
  @FindBy(css = ".edit-header--delete")
  private WebElement deleteButton;
  @FindBy(css = ".infobox-builder-button")
  private List<WebElement> componentsButtons;
  @FindBy(css = ".portable-infobox .pi-data")
  private List<WebElement> rows;
  @FindBy(css = ".portable-infobox .pi-title")
  private List<WebElement> titles;
  @FindBy(css = ".portable-infobox .pi-image")
  private List<WebElement> images;

  public SpecialInfoboxBuilderPageObject(WebDriver driver) {
    super(driver);
  }

  public SpecialInfoboxBuilderPageObject open(String templateName) {
    String url = urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_INFOBOX_BUILDER + templateName;
    getUrl(url);
    return this;
  }

  public SpecialInfoboxBuilderPageObject switchToIFrame() {
    driver.switchTo().frame(builderIFrame);
    return this;
  }

  public SpecialInfoboxBuilderPageObject addRowComponent() {
    wait.forElementVisible(componentsButtons.get(0));
    componentsButtons.get(0).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject addTitleComponent() {
    wait.forElementVisible(componentsButtons.get(1));
    componentsButtons.get(1).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject addImageComponent() {
    wait.forElementVisible(componentsButtons.get(2));
    componentsButtons.get(2).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject deleteTitleComponentWithIndex(List<WebElement> componentsList, int index) {
    wait.forElementVisible(componentsList.get(index));
    componentsList.get(index).click();
    wait.forElementVisible(deleteButton);
    deleteButton.click();
    return this;
  }

  /* Verifies default rendered template structure, which should contain:
     1 title component, 1 image component and 2 data labels components
  */
  public SpecialInfoboxBuilderPageObject verifyDefaultTemplateStructure() {
    Assertion.assertEquals(this.titles.size(), defaultTitleCount);
    Assertion.assertEquals(this.images.size(), defaultImageCount);
    Assertion.assertEquals(this.rows.size(), defaultDataLabelsCount);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyRowAdded() {
    int dataLabelsCount = rows.size();
    Assertion.assertEquals(dataLabelsCount + 1, this.addRowComponent().rows.size());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyTitleAdded() {
    int titlesCount = titles.size();
    Assertion.assertEquals(titlesCount + 1, this.addTitleComponent().titles.size());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyImageAdded() {
    int imageCount = images.size();
    Assertion.assertEquals(imageCount + 1, this.addImageComponent().images.size());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyDeletingTitleWithIndex(int index) {
    int titlesCount = titles.size();
    this.deleteTitleComponentWithIndex(titles, index);
    Assertion.assertEquals(titlesCount, titles.size() + 1);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyDeletingRowWithIndex(int index) {
    int rowsCount = rows.size();
    this.deleteTitleComponentWithIndex(rows, index);
    Assertion.assertEquals(rowsCount, rows.size() + 1);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyDeletingImageWithIndex(int index) {
    int imagesCount = images.size();
    this.deleteTitleComponentWithIndex(images, index);
    Assertion.assertEquals(imagesCount, images.size() + 1);
    return this;
  }

  public TemplatePage save() {
    wait.forElementClickable(saveButton);
    saveButton.click();
    return new TemplatePage(driver);
  }




}
