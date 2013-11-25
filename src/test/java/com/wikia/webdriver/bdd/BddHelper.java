package com.wikia.webdriver.bdd;

import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Global;

public class BddHelper {

    private AbstractConfiguration config;

    public BddHelper(AbstractConfiguration config) {
        this.config = config;
    }

    public String getWikiUrl( String wikiDomain ) {
        String env = config.getEnv();
        if ( env.equals("preview") ) {
            return "http://preview." + wikiDomain + ".wikia.com/";
        }

        if ( env.contains("wikia-dev.com") ) {
            return "http://" + wikiDomain +"."+ env + "/";
        }

        return "http://" + wikiDomain + ".wikia.com/";
    }

    public String getWikiSubdomainByName( String wikiName ) {

        if ( wikiName.equals("Muppet Wiki") ) {
            return "muppet";
        }

        return "www";
    }
}
