package com.wikia.webdriver.Common.Core.GeoEdge;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.codec.binary.Base64;
import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.http.BrowserMobHttpRequest;
import org.browsermob.proxy.http.RequestInterceptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class GeoEdgeProxyServer extends ProxyServer {

    private String country;
    private HashMap countriesConfiguration = new HashMap();

    public GeoEdgeProxyServer(String country, int port) throws Exception {
        super(port);
        this.country = country;
    }

    public void runGeoEdgeServer() throws Exception {
        start();
        String encoded = createBaseFromCredentials(
            Properties.geoEdgeUserName, Properties.geoEdgeUserPass
        );
        setGeoEdgeOptions();
        setHeadersWithCredentials(encoded);
    }

    private void setGeoEdgeOptions() {
        try {
            getIPForCountries();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> options = new HashMap<String,String>();
        options.put(
            "httpProxy", (String) countriesConfiguration.get(country)
        );
        setOptions(options);
    }

    private void setHeadersWithCredentials(final String credentials) {
        addRequestInterceptor(new RequestInterceptor() {
            @Override
            public void process(BrowserMobHttpRequest request) {
                request.getMethod().removeHeaders("Proxy-Authorization");
                try {
                    request.getMethod().addHeader(
                        "Proxy-Authorization",
                        "Basic " + credentials
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String createBaseFromCredentials(String userName, String password) {
        String credentials = userName + ":" + password;
        Base64 base = new Base64();
        byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
        final String encodedString = new String(encodedCredentials);
        return encodedString;
    }

    private void getIPForCountries() throws Exception{

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(Global.CONFIG_FILE);

        NodeList listOfCountries = doc.getElementsByTagName("Country");

        for (int s = 0; s < listOfCountries.getLength(); s++) {
            Element oneCountry = (Element) listOfCountries.item(s);
            countriesConfiguration.put(
                (String) oneCountry.getElementsByTagName("Name").item(0).getTextContent(),
                (String) oneCountry.getElementsByTagName("IP").item(0).getTextContent()
            );
        }
    }
}
