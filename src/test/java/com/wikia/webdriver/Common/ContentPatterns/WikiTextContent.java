package com.wikia.webdriver.Common.ContentPatterns;

public class WikiTextContent {

	public static final String HEADING_PREFIX = "== ";
	public static final String headingPostfix = " ==";
	public static final String SUBHEADING1_PREFIX = "=== ";
	public static final String SUBHEADING1_POSTIX = " ===";
	public static final String SUBHEADING2_PREFIX = "==== ";
	public static final String SUBHEADING2_POSTIX = " ====";
	public static final String SUBHEADING3_PREFIX = "===== ";
	public static final String SUBHEADING3_POSTIX = " =====";
	public static final String SUBHEADING4_PREFIX = "====== ";
	public static final String SUBHEADING4_POSTIX = " ======";

	public static final String PREFORMATTED = "";
	public static final String BOLD = "'''";
	public static final String ITALIC = "''";

	public static final String STRIKETHROUGH_PREFIX = "<s>";
	public static final String STRIKETHROUGH_POSTFIX = "</s>";
	public static final String UNDERLINE_PREFIX = "<u>";
	public static final String UNDERLINE_POSTFIX = "</u>";
	public static final String SUBSCRIPT_PREFIX = "<sub>";
	public static final String SUBSCRIPT_POSTFIX = "</sub>";
	public static final String SUPERSCRIPT_PREFIX = "<sup>";
	public static final String SUPERSCRIPT_POSTFIX = "</sup>";

	public static final String BULLET_LIST = "* ";
	public static final String NUMMBERED_LIST = "# ";

	public static final String LINK_PREFIX = "[[";
	public static final String LINK_POSTFIX = "]]";

	public static final String TEXT = PageContent.ARTICLE_TEXT;

	public static final String PARAGRAPH_TEXT = TEXT;
	public static final String HEADING_TEXT = HEADING_PREFIX + TEXT + headingPostfix;
	public static final String SUBHEADING1_TEXT = SUBHEADING1_PREFIX + TEXT + SUBHEADING1_POSTIX;
	public static final String SUBHEADING2_TEXT = SUBHEADING2_PREFIX + TEXT + SUBHEADING2_POSTIX;
	public static final String SUBHEADING3_TEXT = SUBHEADING3_PREFIX + TEXT + SUBHEADING3_POSTIX;
	public static final String SUBHEADING4_TEXT = SUBHEADING4_PREFIX + TEXT + SUBHEADING4_POSTIX;
	public static final String PREFORMATTED_TEXT = PREFORMATTED + TEXT;
	public static final String BOLD_TEXT = BOLD + TEXT + BOLD;
	public static final String ITALIC_TEXT = ITALIC + TEXT + ITALIC;
	public static final String STRIKETROUGH_TEXT = STRIKETHROUGH_PREFIX + TEXT + STRIKETHROUGH_POSTFIX;
	public static final String UNDERLINE_TEXT = UNDERLINE_PREFIX + TEXT + UNDERLINE_POSTFIX;
	public static final String SUBSCRIPT_TEXT = SUBSCRIPT_PREFIX + TEXT + SUBSCRIPT_POSTFIX;
	public static final String SUPERSCRIPT_TEXT = SUPERSCRIPT_PREFIX + TEXT + SUPERSCRIPT_POSTFIX;
	public static final String BULLET_LIST_TEXT = BULLET_LIST + TEXT;
	public static final String NUMBERED_LIST_TEXT = NUMMBERED_LIST + TEXT;

	public static final String BLUELINK_TEXT = LINK_PREFIX + PageContent.INTERNAL_LINK + LINK_POSTFIX;
	public static final String EXTERNAL_LINK_TEXT = LINK_PREFIX + PageContent.EXTERNAL_LINK + LINK_POSTFIX;
	public static final String REDLINK_TEXT = LINK_PREFIX + PageContent.REDLINK + LINK_POSTFIX;
	public static final String REDIRECT_LINK_TEXT = LINK_PREFIX + PageContent.REDIRECT_LINK + LINK_POSTFIX;

}
