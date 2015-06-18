package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ModalTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @CreationTicket(ticketID = "CONCF-621")
  @Test(groups = {"ModalTests_verifyScrollbarAppears_001", "ModalTests"},
      dataProvider = "DimensionDataProvider"
  )
  public void ModalTests_verifyScrollbarAppears(Dimension dimension) {
    String errorMessage = "can not scroll window";
    
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);

    ArticlePageObject article = base.openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    GalleryBuilderComponentObject galleryBuilder = visualEditMode.clickGalleryButton();
    //resize window
    driver.manage().window().setSize(dimension);
    //make sure you can scroll to finish button and the finish button is visible
    Assertion.assertTrue(galleryBuilder.isFinishButtonVisibleOnPage(), errorMessage);
  }


  @DataProvider(name = "DimensionDataProvider")
  public final Dimension[][] DimensionProvider() {
    return new Dimension[][]{
        {new Dimension(1100, 570)},
        {new Dimension(800, 570)}
    };
  }

}
