package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewPages;

import org.testng.Assert;
import org.testng.annotations.Test;

@Execute(onWikia = "sustainingtest")
@Test(groups = {"newpages"})
public class NewPagesTests extends NewTestTemplate {
  @Test
  public void newArticleShowsUpOnSpecialNewPages() {
    ArticleContent articleContent = new ArticleContent();
    String articleTitle = articleContent.createUniqueArticle();

    SpecialNewPages specialNewPages = new SpecialNewPages().open();

    Assert.assertTrue(
        specialNewPages.containsLinkToArticle(articleTitle),
        "Special:NewPages does not contain link to article named " + articleTitle
    );
  }
}
