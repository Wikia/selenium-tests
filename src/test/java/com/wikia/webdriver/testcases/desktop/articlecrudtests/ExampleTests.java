package com.wikia.webdriver.testcases.desktop.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = "ExampleTests")
public class ExampleTests extends NewTestTemplate {

  @Test
  public void articlePushedWithAPIHasItsContent() {
    // push special article through API
    String specialText = "!!!</html>";
    String articleContentSent = PageContent.ARTICLE_TEXT + specialText;
    new ArticleContent().push(articleContentSent);

    // get article
    ArticlePageObject article = new ArticlePageObject().open();
    String articleContentReceived = article.getContent();

    // check if received content contains special text
    Assertion.assertStringContains(articleContentReceived, specialText);
  }

  @Test
  @Execute(asUser = User.USER)
  public void articleAddedContentClassicEditor() {
    // create a new article
    new ArticleContent().push("");

    // open the article and enter the classic editor
    ArticlePageObject article = new ArticlePageObject().open();
    VisualEditModePageObject classicVisualEditMode = article.openCKModeWithMainEditButtonDropdown();

    // add text and photo content
    classicVisualEditMode.addContent(PageContent.ARTICLE_TEXT);
    PhotoAddComponentObject photoAddPhoto = classicVisualEditMode.clickPhotoButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    classicVisualEditMode.clickPublishButton();

    // verify photo and text
    article.refreshPage();
    Assertion.assertStringContains(article.getAtricleTextRaw(), PageContent.ARTICLE_TEXT);
    article.verifyPhoto();
  }

  @Test
  @Execute(asUser = User.USER)
  public void articlesLinkingBackForth() {
    // create a new article
    new ArticleContent().push();
    new ArticleContent().push(PageContent.ARTICLE_TEXT, PageContent.);
    // open the article and enter the classic editor
    ArticlePageObject article = new ArticlePageObject().open();
    VisualEditModePageObject classicVisualEditMode = article.openCKModeWithMainEditButtonDropdown();

    // add text and photo content
    classicVisualEditMode.addContent(PageContent.ARTICLE_TEXT);
    PhotoAddComponentObject photoAddPhoto = classicVisualEditMode.clickPhotoButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    classicVisualEditMode.clickPublishButton();

    // verify photo and text
    article.refreshPage();
    Assertion.assertStringContains(article.getAtricleTextRaw(), PageContent.ARTICLE_TEXT);
    article.verifyPhoto();
  }



}
