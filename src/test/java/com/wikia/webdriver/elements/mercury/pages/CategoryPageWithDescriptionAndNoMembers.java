package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPageWithDescriptionAndNoMembers extends WikiBasePageObject {

  private static final String DESCRIPTION_HANDLE = "description handle";
  private static final String NO_PAGES_MESSAGE = "This category currently contains no pages.";

  @FindBy(css = ".article-content")
  private WebElement description;

  @FindBy(css = ".article-content p:first-child")
  private WebElement descriptionHandle;

  @FindBy(css = ".category-sections")
  private WebElement noPagesMessageContainer;

  public CategoryPageWithDescriptionAndNoMembers() {
    super();
  }

  public CategoryPageWithDescriptionAndNoMembers open() {
    new Navigate(driver).toPage(MercurySubpages.CATEGORY_WITH_DESCRIPTION_AND_NO_MEMBERS);

    Assertion.assertTrue(description.isDisplayed(),
                         "There's no article container on the page.");
    PageObjectLogging.logInfo("Article container found: " + description);

    Assertion.assertEquals(descriptionHandle.getText(), DESCRIPTION_HANDLE,
                           "Unexpected description contents.");
    PageObjectLogging.logInfo("Description handle has expected content: " + descriptionHandle);

    Assertion.assertEquals(noPagesMessageContainer.getText(), NO_PAGES_MESSAGE,
                           "Info message about no pages in category is missing.");
    PageObjectLogging.logInfo("Info message about no pages in category was shown: " +
                              noPagesMessageContainer);

    wait.forElementNotPresent(By.cssSelector(".category-sections .category-section li"));
    PageObjectLogging.logInfo("There are no pages shown in this category, which is nice.");

    return this;
  }
}
