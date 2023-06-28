package com.wikia.webdriver.elements.communities.desktop.pages.categories;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryExhibitionPage extends WikiBasePageObject {

  @FindBy(css = ".category-gallery-item")
  private List<WebElement> categoryGalleryItems;

  public String getCategoryPageTitle() {
    wait.forElementVisible(this.articleTitle);
    String title = articleTitle.getText();
    Log.log("getCategoryname", "the name of the category is: " + title, true);

    return title;
  }

  /**
   * Get image URL for a specific article from gallery
   *
   * @param articleName - name of the article
   */
  public String getImageURLFromGallery(String articleName) {
    return categoryGalleryItems.stream()
        .filter(page -> page.findElement(By.cssSelector("a"))
            .getAttribute("title")
            .equals(articleName))
        .findFirst()
        .map(page -> page.findElement(By.cssSelector("img")).getAttribute("src"))
        .orElseThrow(() -> new NoSuchElementException(String.format(
            "Could not find article with name %s",
            articleName
        )));
  }
}
