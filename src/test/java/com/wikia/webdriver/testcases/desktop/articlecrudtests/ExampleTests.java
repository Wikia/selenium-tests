package com.wikia.webdriver.testcases.desktop.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = "ExampleTests")
public class ExampleTests extends NewTestTemplate {

  @Test
  public void articlePushedWithAPIHasItsContent() {
    // push special article through API
    String specialText = "SPECIAL_TEXT";
    String articleContentSent = PageContent.ARTICLE_TEXT + specialText;
    new ArticleContent().push(articleContentSent);

    // get article
    ArticlePageObject article = new ArticlePageObject().open();
    String articleContentReceived = article.getContent();

    // check if received content contains special text
    Assertion.assertStringContains(articleContentReceived, specialText);
  }
}
