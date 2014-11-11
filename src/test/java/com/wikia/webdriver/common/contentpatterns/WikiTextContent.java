package com.wikia.webdriver.common.contentpatterns;

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

	public static String preformatted = "";
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

	public static String linkPrefix = "[[";
	public static String linkPostfix = "]]";

	public static String text = PageContent.articleText;

	public static String paragraphText = text;
	public static String headingText = headingPrefix + text + headingPostfix;
	public static String subHeading1Text = subHeading1Prefix + text + subHeading1Postix;
	public static String subHeading2Text = subHeading2Prefix + text + subHeading2Postix;
	public static String subHeading3Text = subHeading3Prefix + text + subHeading3Postix;
	public static String subHeading4Text = subHeading4Prefix + text + subHeading4Postix;
	public static String preformattedText = preformatted + text;
	public static String boldText = bold + text + bold;
	public static String italicText = italic + text + italic;
	public static String striketroughText = strikethroughPrefix + text + strikethroughPostfix;
	public static String underlineText = underlinePrefix + text + underlinePostfix;
	public static String subscriptText = subscriptPrefix + text + subscriptPostfix;
	public static String superscriptText = superscriptPrefix + text + superscriptPostfix;
	public static String bulletListText = bulletList + text;
	public static String numberedListText = nummberedList + text;

	public static String blueLinkText = linkPrefix + PageContent.internalLink + linkPostfix;
	public static String externalLinkText = linkPrefix + PageContent.externalLink + linkPostfix;
	public static String redLinkText = linkPrefix + PageContent.redLink + linkPostfix;
	public static String redirectLinkText = linkPrefix + PageContent.redirectLink + linkPostfix;

}
