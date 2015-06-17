package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ModalTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @CreationTicket(ticketID = "CONCF-621")
  @Test(groups = {"ArticleFeaturesCRUDUser_001", "ArticleFeaturesCRUDUser", "Smoke"},
      dataProvider = "DimensionDataProvider"
  )
  public void ArticleFeaturesCRUDUser_001_addModifyGallery(Dimension dimension) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    GalleryBuilderComponentObject galleryBuilder = visualEditMode.clickGalleryButton();
    WebElement finishButton = galleryBuilder.getFinishButton();
    //verify Finish button is visible
    galleryBuilder.waitForElementByElement(finishButton);
    //resize window to the size that makes Finish button not visible
    driver.manage().window().setSize(dimension);
    //verify Finish button is not visible
    galleryBuilder.waitForElementNotVisibleByElement(finishButton);
    //scroll to Finish button
    galleryBuilder.scrollToElement(finishButton);
    //verify Finish button is visible
    galleryBuilder.waitForElementByElement(finishButton);
    galleryBuilder.clickFinish();
  }


  @DataProvider(name = "DimensionDataProvider")
  public final Dimension[][] DimensionProvider() {
    return new Dimension[][]{
        {new Dimension(1100, 570)},
        {new Dimension(800, 570)}
    };
  }

}
