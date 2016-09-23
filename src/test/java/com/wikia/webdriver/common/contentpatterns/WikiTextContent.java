package com.wikia.webdriver.common.contentpatterns;

public class WikiTextContent {

  public static final String HEADING_OPENING = "== ";
  public static final String HEADING_CLOSING = " ==";
  public static final String SUBHEADING1_OPENING = "=== ";
  public static final String SUBHEADING1_CLOSING = " ===";
  public static final String SUBHEADING2_OPENING = "==== ";
  public static final String SUBHEADING2_CLOSING = " ====";
  public static final String SUBHEADING3_OPENING = "===== ";
  public static final String SUBHEADING3_CLOSING = " =====";
  public static final String SUBHEADING4_OPENING = "====== ";
  public static final String SUBHEADING4_CLOSING = " ======";

  public static final String PREFORMATTED = " ";
  public static final String BOLD = "'''";
  public static final String ITALIC = "''";

  public static final String STRIKETHROUGH_OPENING = "<s>";
  public static final String STRIKETHROUGH_CLOSING = "</s>";
  public static final String UNDERLINE_OPENING = "<u>";
  public static final String UNDERLINE_CLOSING = "</u>";
  public static final String SUBSCRIPT_OPENING = "<sub>";
  public static final String SUBSCRIPT_CLOSING = "</sub>";
  public static final String SUPERSCRIPT_OPENING = "<sup>";
  public static final String SUPERSCRIPT_CLOSING = "</sup>";

  public static final String BULLET_LIST = "* ";
  public static final String NUMBERED_LIST = "# ";

  public static final String INTERNAL_LINK_OPENING = "[[";
  public static final String INTERNAL_LINK_CLOSING = "]]";

  public static final String EXT_LINK_OPENING = "[";
  public static final String EXT_LINK_CLOSING = "]";

  public static final String REDIRECT = "#redirect";

  public static final String TEXT = PageContent.ARTICLE_TEXT;

  public static final String PARAGRAPH_TEXT = TEXT;
  public static final String HEADING_TEXT = HEADING_OPENING + TEXT + HEADING_CLOSING;
  public static final String SUBHEADING1_TEXT = SUBHEADING1_OPENING + TEXT + SUBHEADING1_CLOSING;
  public static final String SUBHEADING2_TEXT = SUBHEADING2_OPENING + TEXT + SUBHEADING2_CLOSING;
  public static final String SUBHEADING3_TEXT = SUBHEADING3_OPENING + TEXT + SUBHEADING3_CLOSING;
  public static final String SUBHEADING4_TEXT = SUBHEADING4_OPENING + TEXT + SUBHEADING4_CLOSING;
  public static final String PREFORMATTED_TEXT = PREFORMATTED + TEXT;
  public static final String BOLD_TEXT = BOLD + TEXT + BOLD;
  public static final String ITALIC_TEXT = ITALIC + TEXT + ITALIC;
  public static final String
      STRIKETROUGH_TEXT =
      STRIKETHROUGH_OPENING + TEXT + STRIKETHROUGH_CLOSING;
  public static final String UNDERLINE_TEXT = UNDERLINE_OPENING + TEXT + UNDERLINE_CLOSING;
  public static final String SUBSCRIPT_TEXT = SUBSCRIPT_OPENING + TEXT + SUBSCRIPT_CLOSING;
  public static final String SUPERSCRIPT_TEXT = SUPERSCRIPT_OPENING + TEXT + SUPERSCRIPT_CLOSING;
  public static final String BULLET_LIST_TEXT = BULLET_LIST + TEXT;
  public static final String NUMBERED_LIST_TEXT = NUMBERED_LIST + TEXT;

  public static final String
      BLUELINK_TEXT =
      INTERNAL_LINK_OPENING + PageContent.INTERNAL_LINK + INTERNAL_LINK_CLOSING;
  public static final String
      EXTERNAL_LINK_TEXT =
      EXT_LINK_OPENING + PageContent.EXTERNAL_LINK + EXT_LINK_CLOSING;
  public static final String
      REDLINK_TEXT =
      INTERNAL_LINK_OPENING + PageContent.REDLINK + INTERNAL_LINK_CLOSING;
  public static final String
      REDIRECT_LINK_TEXT =
      INTERNAL_LINK_OPENING + PageContent.REDIRECT_LINK + INTERNAL_LINK_CLOSING;

  private WikiTextContent() {

  }
}
