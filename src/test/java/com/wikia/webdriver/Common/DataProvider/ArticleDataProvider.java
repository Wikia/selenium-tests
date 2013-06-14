package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

public class ArticleDataProvider {

	@DataProvider
	private static final Object[][] getArticleName() {
		return new Object[][] {
				{ "QAarticle" },
				{ "QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName" },
				{ "QA/article" },
				{ "QA_article" },
				{ "123123123123" } };
	}

}
