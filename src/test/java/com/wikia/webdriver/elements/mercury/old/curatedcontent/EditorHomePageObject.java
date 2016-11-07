package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.SectionFormPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the main editor view on mercury.
 * The editor home is responsible for adding top level curated content items
 */
public class EditorHomePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement publishButton;
  @FindBy(css = "section:nth-of-type(2) .curated-content-editor-add-item-btn")
  private WebElement addFeaturedContentButton;
  @FindBy(css = "section:nth-of-type(3) .curated-content-editor-add-item-btn")
  private WebElement addNewSectionButton;
  @FindBy(css = "section:nth-of-type(4) .curated-content-editor-add-item-btn")
  private WebElement addCategoryButton;

  private WebDriver driver;
  private Wait wait;

  public EditorHomePageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public CuratedMainPagePageObject publish() {
    wait.forElementVisible(publishButton);
    publishButton.click();

    return new CuratedMainPagePageObject(driver);
  }

  public ItemFormPageObject clickAddFeaturedContent() {
    wait.forElementVisible(addFeaturedContentButton);
    addFeaturedContentButton.click();

    return new ItemFormPageObject(driver);
  }

  public SectionFormPageObject clickAddSection() {
    wait.forElementVisible(addNewSectionButton);
    addNewSectionButton.click();

    return new SectionFormPageObject(driver);
  }

  public ItemFormPageObject clickAddCategory() {
    wait.forElementVisible(addCategoryButton);
    addCategoryButton.click();

    return new ItemFormPageObject(driver);
  }

  public void waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);
  }
}
