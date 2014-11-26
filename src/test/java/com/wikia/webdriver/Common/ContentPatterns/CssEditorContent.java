package com.wikia.webdriver.Common.ContentPatterns;

/**
 *
 */
public class CssEditorContent {
	// this is valid css code
	public static final String VALID_CSS = ".testStructure {display: none;}";
	public static final String VALID_CSS2 = ".testStructure2 {display: none;}";
	// this css code gives an error
	public static final String INVALID_CSS_ERROR = ".testStructure {display: none;";
	// this css code gives a warning
	public static final String INVALID_CSS_WARNING = ".testStricture {display: none !important;}";
}

