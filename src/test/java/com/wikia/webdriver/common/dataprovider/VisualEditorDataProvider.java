package com.wikia.webdriver.common.dataprovider;

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
		PREFORMATTED (By.tagName("pre"));

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

	public enum InsertDialog {
		MAP,
		MEDIA,
		TEMPLATE,
		REFERENCE,
		REFERENCE_LIST,
		PAGE_SETTINGS,
		CATEGORIES,
		KEYBOARD_SHORTCUTS,
		SOURCE_EDITOR;
	}

	public enum InsertList {
		NUMBERED_LIST,
		BULLET_LIST;
	}

	public enum Indentation {
		INCREASE,
		DECREASE;
	}

	public enum CategoryResultType {
		NEW,
		MATCHING;
	}

	public enum Alignment {
		LEFT,
		CENTER,
		RIGHT
	}

	public enum Setting {
		GENERAL,
		ADVANCED
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
				{"vetest"},
		};
	}

	/**
	 * Data provider with VE disabled wikis
	 */
	@DataProvider
	public static final Object[][] getNonVEWikis() {
		return new Object[][] {
				{"vedisabledtest"},
		};
	}
}
