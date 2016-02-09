package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.CreationTicket;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ModalTests extends NewTestTemplate {

  @CreationTicket(ticketID = "CONCF-621")
  @Test(groups = {"ModalTests_verifyScrollbarAppears_001", "ModalTests"},
      dataProvider = "DimensionDataProvider")
  @Execute(asUser = User.USER)
  public void ModalTests_verifyScrollbarAppears(Dimension dimension) {
    String errorMessage = "can not scroll window";

    ArticlePageObject article = new ArticlePageObject(driver).open();
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    GalleryBuilderComponentObject galleryBuilder = visualEditMode.clickGalleryButton();
    // resize window
    driver.manage().window().setSize(dimension);
    // make sure you can scroll to finish button and the finish button is visible
    Assertion.assertTrue(galleryBuilder.isFinishButtonVisibleOnPage(), errorMessage);
  }


  @DataProvider(name = "DimensionDataProvider")
  public final Dimension[][] DimensionProvider() {
    return new Dimension[][]{{new Dimension(1100, 570)}, {new Dimension(800, 570)}};
  }

}
