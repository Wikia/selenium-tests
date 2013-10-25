
package com.wikia.webdriver.Common.ContentPatterns;

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
			"{| border=\"2\" cellpadding=\"5\" cellspacing=\"3\" class=\"article-table\"" +
			" style=\"float: right; height: 50px; width: 500px;\"\n" +
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
