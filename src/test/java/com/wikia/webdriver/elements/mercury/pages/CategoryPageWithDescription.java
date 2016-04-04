package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPageWithDescription extends WikiBasePageObject {

  private static final String DESCRIPTION_HANDLE = "description handle";

  @FindBy(css = ".article-content")
  private WebElement description;

  @FindBy(css = ".article-content p:first-child")
  private WebElement descriptionHandle;

  @FindBy(css = ".category-sections .category-section li")
  private WebElement firstSectionMembers;

  public CategoryPageWithDescription() {
    super();
  }

  public CategoryPageWithDescription checkHasArticleContainer() {
    Assertion.assertTrue(description.isDisplayed(), "There's no article container on the page.");
    PageObjectLogging.logInfo("Article container found: " + description);

    return this;
  }

  public CategoryPageWithDescription checkHasCorrectDescriptionHandle() {
    Assertion.assertEquals(descriptionHandle.getText(), DESCRIPTION_HANDLE,
                           "Unexpected description contents.");
    PageObjectLogging.logInfo("Description handle has expected content: " + descriptionHandle);

    return this;
  }

  public CategoryPageWithDescription checkFirstSectionHasMembers() {
    Assertion.assertTrue(firstSectionMembers.isDisplayed(), "First section is empty.");
    PageObjectLogging.logInfo("First section contains members: " + firstSectionMembers);

    return this;
  }

  public CategoryPageWithDescription open() {
    new Navigate(driver).toPage(MercurySubpages.CATEGORY_WITH_DESCRIPTION);

    return this
        .checkHasArticleContainer()
        .checkHasCorrectDescriptionHandle()
        .checkFirstSectionHasMembers();
  }
}
