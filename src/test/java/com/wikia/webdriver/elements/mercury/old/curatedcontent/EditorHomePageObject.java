package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.SectionFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.SectionItemListPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

/**
 * This class represents the main editor view on mercury. The editor home is responsible for adding
 * top level curated content items
 */
public class EditorHomePageObject extends BasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement publishButton;
  @FindBy(css = "section:nth-of-type(2) .curated-content-editor-add-item-btn")
  private WebElement addFeaturedContentButton;
  @FindBy(css = "section:nth-of-type(3) .curated-content-editor-add-item-btn")
  private WebElement addNewSectionButton;
  @FindBy(css = "section:nth-of-type(4) .curated-content-editor-add-item-btn")
  private WebElement addCategoryButton;

  @Getter(lazy = true)
  private final SectionItemListPageObject sectionItemList = new SectionItemListPageObject();

  public EditorHomePageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "/main/edit");

    return this;
  }

  public CuratedMainPagePageObject publish() {
    wait.forElementVisible(publishButton);
    publishButton.click();

    return new CuratedMainPagePageObject();
  }

  public ItemFormPageObject clickAddFeaturedContent() {
    wait.forElementVisible(addFeaturedContentButton);
    addFeaturedContentButton.click();

    return new ItemFormPageObject();
  }

  public SectionFormPageObject clickAddSection() {
    wait.forElementVisible(addNewSectionButton);
    addNewSectionButton.click();

    return new SectionFormPageObject();
  }

  public ItemFormPageObject clickAddCategory() {
    wait.forElementVisible(addCategoryButton);
    addCategoryButton.click();

    return new ItemFormPageObject();
  }

  public EditorHomePageObject waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);

    return this;
  }
}
