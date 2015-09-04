package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.CategoryFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionItemListPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * This class represents the main editor view on mercury.
 * The editor home is responsible for adding top level curated content items
 *
 * @ownership: Content X-Wing
 */
public class EditorHomePageObject extends BasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement publishButton;
  @FindBy(css = "section:nth-of-type(1) .curated-content-editor-add-item-btn")
  private WebElement addFeaturedContentButton;
  @FindBy(css = "section:nth-of-type(2) .curated-content-editor-add-item-btn")
  private WebElement addNewSectionButton;
  @FindBy(css = "section:nth-of-type(3) .curated-content-editor-add-item-btn")
  private WebElement addCategoryButton;
  @FindBys(@FindBy(css = "section:nth-of-type(1) .curated-content-editor-row"))
  private List<WebElement> featuredContent;
  @FindBys(@FindBy(css = "section:nth-of-type(2) .curated-content-editor-row"))
  private List<WebElement> section;
  @FindBys(@FindBy(css = "section:nth-of-type(3) .curated-content-editor-row"))
  private List<WebElement> category;

  private By sectionLabel = By.cssSelector(".title");


  public EditorHomePageObject(WebDriver driver) {
    super(driver);
  }

  public CuratedMainPagePageObject publish() {
    waitAndClick(publishButton);
    return new CuratedMainPagePageObject(driver);
  }

  public ItemFormPageObject clickAddFeaturedContent() {
    waitAndClick(addFeaturedContentButton);
    return new ItemFormPageObject(driver);
  }

  public SectionFormPageObject clickAddSection() {
    waitAndClick(addNewSectionButton);
    return new SectionFormPageObject(driver);
  }

  public ItemFormPageObject clickAddCategory() {
    waitAndClick(addCategoryButton);
    return new ItemFormPageObject(driver);
  }

  public SectionFormPageObject clickFeaturedContentByIndex(int featuredContentIndex) {
    waitAndClick(section.get(featuredContentIndex));
    return new SectionFormPageObject(driver);
  }

  public SectionItemListPageObject clickSectionByIndex(int sectionIndex) {
    waitAndClick(section.get(sectionIndex));
    return new SectionItemListPageObject(driver);
  }

  public CategoryFormPageObject clickCategoryByIndex(int categoryIndex) {
    waitAndClick(section.get(categoryIndex));
    return new CategoryFormPageObject(driver);
  }

  public void verifySection(String sectionName) {
    verifyTextInListElements(section, sectionLabel, sectionName);
  }

  public void waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);
  }
}
