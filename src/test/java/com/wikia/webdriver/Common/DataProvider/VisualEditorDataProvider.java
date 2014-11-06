package com.wikia.webdriver.Common.DataProvider;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;

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

	public enum Editor {
		VE,
		CK,
		SRC
	}

	public enum EditorPref {
		VE,
		CK,
		SRC
	}

	/**
	 * Data provider with category main edit entry point
	 */
	@DataProvider
	public static final Object[][] categoryEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.CK},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.CK},
		};
	}

	/**
	 * Data provider with create a page entry point
	 */
	@DataProvider
	public static final Object[][] createAPageEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.VE},
		};
	}

	/**
	 * Data provider with list entry point
	 */
	@DataProvider
	public static final Object[][] listEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.VE},
		};
	}

	/**
	 * Data provider with main article edit entry point
	 */
	@DataProvider
	public static final Object[][] mainEditEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.VE},
		};
	}

	/**
	 * Data provider with article red link edit entry point
	 */
	@DataProvider
	public static final Object[][] redLinkEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.VE},
		};
	}

	/**
	 * Data provider with article section edit entry point
	 */
	@DataProvider
	public static final Object[][] sectionEditEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.VE},
		};
	}

	/**
	 * Data provider with template edit entry point
	 */
	@DataProvider
	public static final Object[][] templateEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.SRC},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.SRC},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.SRC},
			{true, false, EditorPref.CK, Editor.SRC},
			{true, true, EditorPref.CK, Editor.SRC},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.SRC},
		};
	}

	/**
	 * Data provider with url param action=edit entry point
	 */
	@DataProvider
	public static final Object[][] urlActionEditEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.SRC},
			{false, true, EditorPref.SRC, Editor.SRC},
			{false, true, EditorPref.VE, Editor.SRC},
			{false, false, EditorPref.CK, Editor.SRC},
			{false, false, EditorPref.SRC, Editor.SRC},
			{false, false, EditorPref.VE, Editor.SRC},
			{true, false, EditorPref.SRC, Editor.SRC},
			{true, false, EditorPref.VE, Editor.SRC},
			{true, false, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.CK, Editor.CK},
			{true, true, EditorPref.SRC, Editor.SRC},
			{true, true, EditorPref.VE, Editor.CK},
		};
	}

	/**
	 * Data provider with url param veaction=edit entry point
	 */
	@DataProvider
	public static final Object[][] urlVEActionEditEntryPoints() {
		return new Object[][] {
			//wgEnableRTEExt, wgVisualEditorExt, user editor preference, expected editor
			{false, true, EditorPref.CK, Editor.VE},
			{false, true, EditorPref.SRC, Editor.VE},
			{false, true, EditorPref.VE, Editor.VE},
			{false, false, EditorPref.CK, Editor.VE},
			{false, false, EditorPref.SRC, Editor.VE},
			{false, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.SRC, Editor.VE},
			{true, false, EditorPref.VE, Editor.VE},
			{true, false, EditorPref.CK, Editor.VE},
			{true, true, EditorPref.CK, Editor.VE},
			{true, true, EditorPref.SRC, Editor.VE},
			{true, true, EditorPref.VE, Editor.VE},
		};
	}

	public static String getTestWiki(boolean isRTEext, boolean isVEext) {
		if (isRTEext) {
			if (isVEext) {
				return URLsContent.veEnabledTestMainPage;
			} else {
				return URLsContent.veDisabledTestMainPage;
			}
		} else {
			if (isVEext) {
				return URLsContent.rteDisabledTestMainPage;
			} else {
				return URLsContent.veAndrteDisabledTestMainPage;
			}
		}
	}
}
