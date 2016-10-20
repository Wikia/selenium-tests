package com.wikia.webdriver.pageobjectsfactory.pageobject.category;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class CategoryPageObject extends WikiBasePageObject {

  @FindBy(css = "#WikiaPageHeader h1")
  private WebElement categoryHeader;
  @FindBy(css = ".category-gallery-item")
  private List<WebElement> categoryGalleryItems;

  public String getCategoryPageTitle() {
    wait.forElementVisible(categoryHeader);
    String title = categoryHeader.getText();
    PageObjectLogging.log("getCategoryname", "the name of the category is: " + title, true);

    return title;
  }

  /**
   * Get image URL for a specific article from gallery
   * 
   * @param articleName - name of the article
   * @return null if there is no such article name displayed
   */
  public String getImageURLFromGallery(String articleName) {
    for (WebElement page : categoryGalleryItems) {
      if (page.findElement(By.cssSelector("a")).getAttribute("title").equals(articleName)) {
        return page.findElement(By.cssSelector("img")).getAttribute("src");
      }
    }

    return null;
  }
}
