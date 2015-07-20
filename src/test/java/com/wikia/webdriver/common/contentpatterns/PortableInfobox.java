package com.wikia.webdriver.common.contentpatterns;

/**
 * Created by nikodem on 20.07.15.
 */
public class PortableInfobox {

    public static final String
        INFOBOX_TEMPLATE = "<infobox>\n" +
            " <data source=\"orderedList\"><label>Ordered list</label></data>\n" +
            " <data source=\"unorderedList\"><label>Unordered list</label></data>\n" +
            "</infobox>";

    public static final String
        INFOBOX_INVOCATION = "{{Infobox_Template " +
            "|orderedList = <ul> <li>One</li> <li>Two</li> </ul> " +
            "|unorderedList = <ol> <li>One</li> <li>Two</li> </ol>" +
            "}}";

}
