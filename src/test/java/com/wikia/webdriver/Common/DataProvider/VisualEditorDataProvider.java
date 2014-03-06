package com.wikia.webdriver.Common.DataProvider;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorDataProvider {

	public enum Formatting {
		PARAGRAPH (By.tagName("p")),
		HEADING (By.tagName("h2")),
		SUBHEADING1 (By.tagName("h3")),
		SUBHEADING2 (By.tagName("h4")),
		SUBHEADING3 (By.tagName("h5")),
		SUBHEADING4 (By.tagName("h6")),
		PREFORMATTED (By.tagName("pre")),
		TITLE (By.tagName("h1"));

		private By tag;

		private Formatting(By tag) {
			this.tag = tag;
		}

		public By getTag() {
			return tag;
		};
	}

	public enum Style {
		BOLD (By.tagName("b")),
		ITALIC (By.tagName("i")),
		STRIKETHROUGH (By.tagName("s")),
		UNDERLINE (By.tagName("u")),
		SUBSCRIPT (By.tagName("sub")),
		SUPERSCRIPT (By.tagName("sup"));

		private By tag;

		private Style(By tag) {
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

	/**
	 * Data provider with text formatting
	 */
	@DataProvider
	public static final Object[][] getStyles() {
		return new Object[][] {
				{Style.BOLD},
				{Style.ITALIC},
				{Style.STRIKETHROUGH},
				{Style.SUBSCRIPT},
				{Style.SUPERSCRIPT},
				{Style.UNDERLINE},
		};
	}

	/**
	 * Data provider with VE enabled wikis
	 */
	@DataProvider
	public static final Object[][] getVEWikis() {
		return new Object[][] {
				{ "leagueoflegends"},
				{ "bbc-online"},
		};
	}

	/**
	 * Data provider with VE disabled wikis
	 */
	@DataProvider
	public static final Object[][] getNonVEWikis() {
		return new Object[][] {
				{ "runescape"},
				{ "disney"},
		};
	}
}
