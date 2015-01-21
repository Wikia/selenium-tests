package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class ArticleDataProvider {

	private ArticleDataProvider() {

	}

	@DataProvider
	public static final Object[][] getArticleName() {
		return new Object[][]{
			{"QAarticle"},
			{"QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName"},
			{"QA/article"},
			{"QA_article"},
			{"123123123123"}
		};
	}

	@DataProvider
	public static final Object[][] articleTitles() {
		return new Object[][]{
			{"QAarticleĄŻŁ"},
			{"QAarticle國歷史"},
			{"QAarticle/"},
			{"QAarticle QAarticle"}
		};
	}

	@DataProvider
	public static final Object[][] getPageNames() {
		return new Object[][]{
			{"Muppets"},
			{"Gta"},
			{"Star_trek"},
			{"Harry_potter"},
			{"Star_wars"}
		};
	}
}
