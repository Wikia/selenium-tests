package com.wikia.webdriver.Common.ContentPatterns;

/**
 *
 */
public class CssEditorContent {
	// this is valid css code
	public static String validCss = ".testStructure {display: none;}";
	public static String validCss2 = ".testStructure2 {display: none;}";
	// this css code gives an error
	public static String invalidCssError = ".testStructure {display: none;";
	// this css code gives a warning
	public static String invalidCssWarning = ".testStricture {display: none !important;}";
}

