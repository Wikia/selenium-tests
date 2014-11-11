
package com.wikia.webdriver.common.contentpatterns;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class SourceModeContent {

	public static String placeholders =
			"[[File:Placeholder|video|right|300px]] " +
			"[[File:Placeholder|right|300px]]";

	// MessageWall source Content
	public static String unclosedDivComment =
			"{| border=\"1\"\n|<div style=\"border:1px solid blue;\">unclosed-div\n|}";

	public static String table =
			"{| border=\"%border%\" cellpadding=\"%cellpadding%\" cellspacing=\"%cellspacing%\" class=\"article-table\"" +
			" style=\"float: %float%; height: %height%px; width: %width%px;\"\n" +
			"|-\n" +
			"! scope=\"col\"|\n" +
			"! scope=\"col\"|\n" +
			"|-\n" +
			"|\n" +
			"|\n" +
			"|-\n" +
			"|\n" +
			"|\n" +
			"|}";
}
