package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FileDataProvider {

	@DataProvider
	public static final Object[][] getFileNames() {
		return new Object[][] {
			{"文件名óśłżźćńę.jpg"},
			{"Image001.jpg"},
		};
	}
}
