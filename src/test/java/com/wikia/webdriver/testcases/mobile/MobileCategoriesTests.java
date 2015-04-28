package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileCategoryPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author PMG <p/> Below test cases are executed against mobileregressiontesting wikiName with
 *         CHROMEMOBILE browser 1. Verify that user is able to see category exhibition buttons grid
 *         2. Verify that category with more than 25 entries for one letter have pagination of
 *         entries
 */
public class MobileCategoriesTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void logIn() {
    new MobileBasePageObject(driver).loginDropDown(credentials.userName, credentials.password);
  }

  @Test(groups = {"MobileCategory_001", "MobileCategories", "Mobile"})
  public void MobileCategory_001_checkCategoryExhibitionButtons() {
    MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
    mobile.openCategory(wikiURL);
    mobile.verifyCategoryExhibition();
  }

  @Test(groups = {"MobileCategory_002", "MobileCategories", "Mobile"})
  public void MobileCategory_002_checkPagination() {
    MobileCategoryPageObject mobile = new MobileCategoryPageObject(driver);
    mobile.openCategory(wikiURL);
    mobile.verifyArticlesCount("P", 25);
    String firstArticle = mobile.getFirstArticleName("P");
    String lastArticle = mobile.getLastArticleName("P");
    mobile.showNextArticles("P");
    String firstArticle2 = mobile.getFirstArticleName("P");
    mobile.verifyArticlesNotEquals(firstArticle, firstArticle2);
    mobile.showPreviousArticles("P");
    mobile.verifyArticlesCount("P", 25);
    String firstArticle3 = mobile.getFirstArticleName("P");
    String lastArticle3 = mobile.getLastArticleName("P");
    mobile.verifyArticlesEquals(firstArticle, firstArticle3);
    mobile.verifyArticlesEquals(lastArticle, lastArticle3);
  }

}
