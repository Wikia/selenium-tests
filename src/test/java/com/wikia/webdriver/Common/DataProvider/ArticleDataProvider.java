package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

public class ArticleDataProvider {

	@DataProvider
	public static final Object[][] getArticleName() {
		return new Object[][] {
			{"QAarticle"},
			{"QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName"},
			{"QA/article"},
			{"QA_article"},
			{"123123123123"}
		};
	}

	@DataProvider
	public static final Object[][] getPopularPagenames() {
		return new Object[][] {
			{"Muppets"},
			{"Gta"},
			{"Star_trek"},
			{"Harry_potter"},
			{"Star_wars"},
		};
	}

}
