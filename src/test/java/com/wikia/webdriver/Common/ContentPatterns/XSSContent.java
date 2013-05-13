package com.wikia.webdriver.Common.ContentPatterns;

public class XSSContent {

	public static String jsAlertCategories = "<script> alert(\"CategoriesTest\"); </script>";
	public static String jsAlertMessageCategories = "CategoriesTest";
	public static String noJQueryError = "ReferenceError: $ is not defined";
}
