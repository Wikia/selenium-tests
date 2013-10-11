/**
 *
 */
package com.wikia.webdriver.Common.DataProvider;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorDataProvider {

	public enum Formatting {
		PARAGRAPH (By.cssSelector("p")),
		HEADING (By.cssSelector("h2")),
		SUBHEADING1 (By.cssSelector("h3")),
		SUBHEADING2 (By.cssSelector("h4")),
		SUBHEADING3 (By.cssSelector("h5")),
		SUBHEADING4 (By.cssSelector("h6")),
		PREFORMATTED (By.cssSelector("pre")),
		TITLE (By.cssSelector("h1"));

		private By tag;

		private Formatting(By tag) {
			this.tag = tag;
		}

		public By getTag() {
			return tag;
		};
	}

	/**
	 * Data provider with text formatting
	 */
	@DataProvider
	public static final Object[][] getFormatting() {
		return new Object[][] {
			{Formatting.HEADING},
			{Formatting.PARAGRAPH},
			{Formatting.PREFORMATTED},
			{Formatting.SUBHEADING1},
			{Formatting.SUBHEADING2},
			{Formatting.SUBHEADING3},
			{Formatting.SUBHEADING4},
			{Formatting.TITLE},
		};
	}
}
