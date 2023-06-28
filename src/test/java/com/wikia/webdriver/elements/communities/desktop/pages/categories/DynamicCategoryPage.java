package com.wikia.webdriver.elements.communities.desktop.pages.categories;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.desktop.components.categories.CategoryLayoutSelector;
import com.wikia.webdriver.elements.communities.desktop.components.categories.PaginationControls;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicCategoryPage extends WikiBasePageObject {

  @FindBy(css=".article-content")
  private WebElement articleContent;


  private By categoryMembersContainer = By.cssSelector(".category-members-grouped");
  private By categoryMembers = By.cssSelector(".category-page__members li a");

  @Getter(lazy = true)
  private final CategoryLayoutSelector layoutSelector = new CategoryLayoutSelector();
  @Getter(lazy = true)
  private final PaginationControls paginationControls = new PaginationControls(this);

  public String getArticleContent() {
    return articleContent.getText();
  }

  public boolean categoryMembersContainerIsVisible() {
    wait.forElementVisible(categoryMembersContainer);
    Log.info("Category members container is visible.");

    return true;
  }

  public boolean hasCategoryMembers() {
    wait.forElementVisible(categoryMembers);
    Log.info("Category has members.");

    return driver.findElements(categoryMembers).size() > 0;
  }

  public List<WebElement> getMembers() {
    return driver.findElements(categoryMembers);
  }

  public String getNumberOfElementsInCategory() {
    Pattern pattern = Pattern.compile("(?:All items )\\((.d)\\)");
    Matcher matcher = pattern.matcher("test");
    if (matcher.group().isEmpty()) {
      throw new IllegalArgumentException("Unable to extract number of elements in the category");
    }
    return matcher.group(0);
   }


}
