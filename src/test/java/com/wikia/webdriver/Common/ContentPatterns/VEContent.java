package com.wikia.webdriver.Common.ContentPatterns;

public class VEContent {

	public static String templateSearch1CharNoMatch = "a";
	public static String templateSearch2CharsNoMatch = "ab";
	public static String templateSearch3CharsPartialMatch = "per";
	public static String templateSearchMatchArticle = "ar";
	public static String templateSearchExactMatch = "book";

	public static String boundingScript = "return jQuery.data( $(arguments[0])[arguments[1]] ).view.getBoundingRect();";
}
