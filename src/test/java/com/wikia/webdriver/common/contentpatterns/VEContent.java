package com.wikia.webdriver.common.contentpatterns;

public class VEContent {

	public static final String TEMPLATE_SEARCH_1CHAR_NOMATCH = "a";
	public static final String TEMPLATE_SEARCH_2CHARS_NOMATCH = "ab";
	public static final String TEMPLATE_SEARCH_3CHARS_PARTIALMATCH = "per";
	public static final String TEMPLATE_SEARCH_MATCH_ARTICLE = "templatetest";
	public static final String TEMPLATE_SEARCH_EXACTMATCH = "book";

	public static final String TEMPLATE_PARAM_LABEL1 = "author";
	public static final String TEMPLATE_PARAM_LABEL2 = "next";

	public static final String TEMPLATE_PARAM_VALUE1 = "contribution";
	public static final String TEMPLATE_PARAM_VALUE2 = "others";

	public static final String TEMPLATE_ASSIGNMENT = " = ";
	public static final String PARAM_SEPARATOR = "|";

	public static final String TEMPLATE_WIKITEXT =
		PARAM_SEPARATOR + TEMPLATE_PARAM_LABEL1 + TEMPLATE_ASSIGNMENT + TEMPLATE_PARAM_VALUE1 +
		PARAM_SEPARATOR + TEMPLATE_PARAM_LABEL2 + TEMPLATE_ASSIGNMENT + TEMPLATE_PARAM_VALUE2;

	public static final String BOUNDING_SCRIPT = "return jQuery.data( $(arguments[0])[arguments[1]] ).view.getBoundingRect();";
}
