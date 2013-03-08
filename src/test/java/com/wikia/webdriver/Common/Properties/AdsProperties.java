package com.wikia.webdriver.Common.Properties;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.XMLFunctions;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AdsProperties {

    public static String geoEdgeUserName;
    public static String geoEdgeUserPass;

    public static HashMap setAdsConfiguration() throws Exception {
        geoEdgeUserName = XMLFunctions.getXMLConfiguration(
                Global.CONFIG_FILE, "ci.AdsConfig.GeoEdgeCredentials.userName");
        geoEdgeUserPass = XMLFunctions.getXMLConfiguration(
                Global.CONFIG_FILE, "ci.AdsConfig.GeoEdgeCredentials.password");

        HashMap countiresConf = new HashMap();

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(Global.CONFIG_FILE);

        NodeList listOfCountries = doc.getElementsByTagName("Country");

        for (int s = 0; s < listOfCountries.getLength(); s++) {
            Element country = (Element) listOfCountries.item(s);
            countiresConf.put(
                (String) country.getElementsByTagName("Name").item(0).getTextContent(),
                (String) country.getElementsByTagName("IP").item(0).getTextContent()
            );
        }

        return countiresConf;
    }
}


