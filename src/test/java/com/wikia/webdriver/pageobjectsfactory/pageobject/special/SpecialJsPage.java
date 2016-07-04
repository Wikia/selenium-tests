package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ContentReviewModule;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Created by Ludwik on 2015-09-25.
 */
public class SpecialJsPage extends WikiBasePageObject {

    @FindBy(css = ".source-javascript")
    private WebElement scriptArea;

    private ContentReviewModule contentReviewModule;

    public SpecialJsPage() {
        super();
    }

    /**
     * Open article with name that is the following combination: TEST CLASS NAME + TEST METHOD NAME
     */
    public SpecialJsPage open() {
        getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
                + String.format("mediawiki:%s.js", TestContext.getCurrentMethodName()));

        return this;
    }

    public SpecialJsPage open(String articleTitle) {
        getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
                + String.format("mediawiki:%s.js", articleTitle));
        return this;
    }

    public String getScriptContent() {
        wait.forElementVisible(scriptArea);

        return scriptArea.getText();
    }

    public ContentReviewModule getReviewModule() {
        if (contentReviewModule == null) {
            contentReviewModule = new ContentReviewModule();
        }
        return contentReviewModule;
    }
}
