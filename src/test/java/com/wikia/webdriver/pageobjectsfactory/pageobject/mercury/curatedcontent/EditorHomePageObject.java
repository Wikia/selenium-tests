package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.CategoryFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionFormPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by wikia on 2015-09-01.
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

  public SectionFormPageObject clickFeaturedContent(int featuredContentIndex) {
    waitAndClick(section.get(featuredContentIndex));
    return new SectionFormPageObject(driver);
  }

  public SectionItemListPageObject clickSection(int sectionIndex) {
    waitAndClick(section.get(sectionIndex));
    return new SectionItemListPageObject(driver);
  }

  public CategoryFormPageObject clickCategory(int categoryIndex) {
    waitAndClick(section.get(categoryIndex));
    return new CategoryFormPageObject(driver);
  }


}
