package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionFormPageObject;

/**
 * This class represents the main editor view on mercury. The editor home is responsible for adding
 * top level curated content items
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
  @FindBy(css = "section:nth-of-type(1) .curated-content-editor-row")
  private List<WebElement> featuredContentItem;

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

  public void waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);
  }

  public SectionFormPageObject clickOnItemByIndex(int itemIndex) {
    waitAndClick(featuredContentItem.get(itemIndex));
    return new SectionFormPageObject(driver);
  }
}
