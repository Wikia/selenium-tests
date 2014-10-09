package com.wikia.webdriver.Common.ContentPatterns;

public class VEContent {

	public static String templateSearchStr1 = "a";
	public static String templateSearchStr2 = "ab";
	public static String templateSearchStr3 = "per";
	public static String templateSearchStr4 = "ar";
	public static String templateSearchStr5 = "book";

	public static String templateParamLabel1 = "author";
	public static String templateParamLabel2 = "next";

	public static String templateParamValue1 = "contribution";
	public static String templateParamValue2 = "others";

	public static String templateAssignment = " = ";
	public static String paramSeparator = "|";

	public static String templateWikiText =
		paramSeparator + templateParamLabel1 + templateAssignment + templateParamValue1 +
		paramSeparator + templateParamLabel2 + templateAssignment + templateParamValue2;
}
