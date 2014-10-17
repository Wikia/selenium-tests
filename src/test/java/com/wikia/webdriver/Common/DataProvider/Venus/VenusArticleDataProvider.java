package com.wikia.webdriver.Common.DataProvider.Venus;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

public class VenusArticleDataProvider {

	@DataProvider
	public static Object[][] getArticleWithScrollableTable() {
		return new Object[][] {
				{"venustest", "Tables/ScrollableTable"}
		};
	}

	@DataProvider
	public static Object[][] getArticleWithScrollableTableOnSmallResoultion() {
		return new Object[][] {
				{"venustest", "Tables/ScrollableTableOnSmallRes", new Dimension(1920, 900), new Dimension(768, 1024)}
		};
	}

	@DataProvider
	public static Object[][] getArticleWithNotScrollableTable() {
		return new Object[][] {
				{"venustest", "Tables/NotScrollableTable"}
		};
	}


}
