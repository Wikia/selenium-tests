package com.wikia.webdriver.bdd.context;

import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestingContextImpl implements TestingContext {
    private WebDriver driver;
    private AbstractConfiguration config;
    private String wikiURL;
    private String wikiCorporateURL;
    private WikiBasePageObject page;

    public void init() {
        Properties.setProperties();
        config = ConfigurationFactory.getConfig();
        EventFiringWebDriver eventDriver = NewDriverProvider.getDriverInstanceForBrowser(
                config.getBrowser()
        );
        eventDriver.register(new PageObjectLogging());
        driver = eventDriver;
        UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
        wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
        wikiCorporateURL = urlBuilder.getUrlForWiki("wikia");
    }


    public void close() {
        driver.close();
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public AbstractConfiguration getConfig() {
        return config;
    }

    @Override
    public String getWikiURL() {
        return wikiURL;
    }

    @Override
    public String getWikiCorporateURL() {
        return wikiCorporateURL;
    }

    @Override
    public WikiBasePageObject getPage() {
        return page;
    }

    @Override
    public void setPage(WikiBasePageObject page) {
        this.page = page;
    }
}
