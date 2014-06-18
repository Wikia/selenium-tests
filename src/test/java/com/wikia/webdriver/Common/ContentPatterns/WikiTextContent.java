package com.wikia.webdriver.Common.ContentPatterns;

public class WikiTextContent {

	public static String headingPrefix = "== ";
	public static String headingPostfix = " ==";
	public static String subHeading1Prefix = "=== ";
	public static String subHeading1Postix = " ===";
	public static String subHeading2Prefix = "==== ";
	public static String subHeading2Postix = " ====";
	public static String subHeading3Prefix = "===== ";
	public static String subHeading3Postix = " =====";
	public static String subHeading4Prefix = "====== ";
	public static String subHeading4Postix = " ======";

	public static String preformatted = " ";
	public static String bold = "'''";
	public static String italic = "''";

	public static String strikethroughPrefix = "<s>";
	public static String strikethroughPostfix = "</s>";
	public static String underlinePrefix = "<u>";
	public static String underlinePostfix = "</u>";
	public static String subscriptPrefix = "<sub>";
	public static String subscriptPostfix = "</sub>";
	public static String superscriptPrefix = "<sup>";
	public static String superscriptPostfix = "</sup>";

	public static String bulletList = "* ";
	public static String nummberedList = "# ";

	public static String text = PageContent.articleText;

	public static String paragraphText = text;
	public static String headingText = WikiTextContent.headingPrefix + text + WikiTextContent.headingPostfix;
	public static String subHeading1Text = WikiTextContent.subHeading1Prefix + text + WikiTextContent.subHeading1Postix;
	public static String subHeading2Text = WikiTextContent.subHeading2Prefix + text + WikiTextContent.subHeading2Postix;
	public static String subHeading3Text = WikiTextContent.subHeading3Prefix + text + WikiTextContent.subHeading3Postix;
	public static String subHeading4Text = WikiTextContent.subHeading4Prefix + text + WikiTextContent.subHeading4Postix;
	public static String preformattedText = WikiTextContent.preformatted + text;
	public static String boldText = WikiTextContent.bold + text + WikiTextContent.bold;
	public static String italicText = WikiTextContent.italic + text + WikiTextContent.italic;
	public static String striketroughText = WikiTextContent.strikethroughPrefix + text + WikiTextContent.strikethroughPostfix;
	public static String underlineText = WikiTextContent.underlinePrefix + text + WikiTextContent.underlinePostfix;
	public static String subscriptText = WikiTextContent.subscriptPrefix + text + WikiTextContent.subscriptPostfix;
	public static String superscriptText = WikiTextContent.superscriptPrefix + text + WikiTextContent.superscriptPostfix;

}
