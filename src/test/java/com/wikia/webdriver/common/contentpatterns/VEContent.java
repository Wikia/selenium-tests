package com.wikia.webdriver.common.contentpatterns;

public class VEContent {

	public static String templateSearch1CharNoMatch = "a";
	public static String templateSearch2CharsNoMatch = "ab";
	public static String templateSearch3CharsPartialMatch = "per";
	public static String templateSearchMatchArticle = "testtemplate";
	public static String templateSearchExactMatch = "book";

	public static String templateParamLabel1 = "author";
	public static String templateParamLabel2 = "next";

	public static String templateParamValue1 = "contribution";
	public static String templateParamValue2 = "others";

	public static String templateAssignment = " = ";
	public static String paramSeparator = "|";

	public static String templateWikiText =
		paramSeparator + templateParamLabel1 + templateAssignment + templateParamValue1 +
		paramSeparator + templateParamLabel2 + templateAssignment + templateParamValue2;

	public static String boundingScript = "return jQuery.data( $(arguments[0])[arguments[1]] ).view.getBoundingRect();";
}
