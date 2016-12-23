package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import java.util.List;
import java.util.function.Function;

public class CategoriesFieldset extends WikiBasePageObject {

  public static final int GENERAL_CATEGORY_POSITION = 0;

  @FindBy(className = "discussion-categories")
  private WebElement fieldset;

  @FindBy(css = ".discussion-categories > legend.mobile-hidden")
  private WebElement label;

  @FindBy(className = "discussion-categories-edit-link")
  private WebElement editLink;

  @FindBy(css = ".discussion-categories .discussion-categories-list > li")
  private List<WebElement> categories;

  @FindBy(className = "discussion-categories-edit")
  private WebElement categoriesEdit;

  @FindBy(className = "discussion-category-all")
  private WebElement editableCategoryAll;

  @FindBy(css = ".discussion-categories-list .sortable-item")
  private List<WebElement> editableCategories;

  public boolean canEdit() {
    WebElement svg = fieldset.findElement(By.tagName("use"));
    return svg.getAttribute("xlink:href").equals("#pencil");
  }

  @CheckForNull
  public CategoryPill clickCategory(final int position) {
    return withBoundryCheck(categories, position, webElement -> {
      final CategoryPill result = new CategoryPill(webElement, position);
      webElement.click();
      return result;
    });
  }

  private <T> T withBoundryCheck(final List<WebElement> elements, final int position, Function<WebElement, T> fun) {
    final int size = elements.size();

    if (size > position) {
      WebElement webElement = elements.get(position);
      return fun.apply(webElement);
    } else {
      throw new IllegalArgumentException("You wanted to click category on position " + position + " but there is only " + size + " categories.");
    }
  }

  public CategoriesFieldset clickEdit() {
    editLink.click();
    return this;
  }

  public boolean canEditAllCategory() {

    return isMobile() ? false
        // plesae remeber to change this line when SOC-3793 is done - "isCategoryEditable(editableCategoryAll)"
        : !editableCategoryAll.findElement(By.className("fancy-checkbox-span")).getAttribute("class").contains("disabled");
  }

  private boolean isMobile() {
    return !label.isDisplayed();
  }

  private boolean isCategoryEditable(WebElement webElement) {
    return webElement.findElement(By.tagName("input")).isEnabled();
  }

  public boolean canEditCategoryAt(final int position) {
    return withBoundryCheck(editableCategories, position, this::isCategoryEditable);
  }

  public boolean canEditGeneralCategory() {
    return canEditCategoryAt(GENERAL_CATEGORY_POSITION);
  }
}
