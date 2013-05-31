/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.net.URI;
import java.net.URISyntaxException;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.WikiArticleMonoBookPageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class WikiBaseMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy(css = "#n-randompage a")
    private WebElement randomArticleLink;
    @FindBy(css = "#pt-userpage>a")
    private WebElement userPageLink;
    @FindBy(css = "#pt-userpage>a")
    private WebElement closeDisclaimer;

    public WikiBaseMonoBookPageObject(WebDriver driver, String Domain) {
        super(driver);
        this.Domain = Domain;
        PageFactory.initElements(driver, this);
    }

    public void openWikiWithMonobook() {
        String baseUrl = Domain;
        getUrl(baseUrl + URLsContent.noexternals);
        PageObjectLogging.log(
            "openWikiBasePageError",
            "open wiki base page took more then 30 seconds",
            true
        );
        changeToMonoBook();
        PageObjectLogging.log(
            "changeBaseWikiSkin",
            "changing skin to monobook took more then 30 seconds",
            true, driver
        );
    }

    public void openRandomArticleMonobook() {
        waitForElementByElement(randomArticleLink);
        randomArticleLink.click();
    }

    public String addArticleByUrl(String articleName) {
        String url  = URLsContent.addArticle;
        articleName = articleName+getTimeStamp();
        url = url.replace("%title%", articleName);
        getUrl(Domain + url);
        PageObjectLogging.log(
            "addArticleUsingMonobook",
            "adding article by url",
            true, driver
        );
        return articleName;
    }

    public void openUserProfile() {
        waitForElementByElement(userPageLink);
        clickAndWait(userPageLink);
        changeToMonoBook();
        PageObjectLogging.log(
            "enterUserProfile",
            "enter user profile with monobook skin",
            true, driver
        );
    }

	public WikiArticleMonoBookPageObject openArticle(String articleName) {
		URI uri;
		try {
			uri = new URI(Global.DOMAIN + "wiki/" + articleName);
			String url = uri.toASCIIString();
			getUrl(url);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		catch (TimeoutException e) {
			PageObjectLogging.log("OpenArticle",
					"page loads for more than 30 seconds", true);
		}
		PageObjectLogging.log("openArticle", "article " + articleName
				+ " opened", true);
		return new WikiArticleMonoBookPageObject(driver);
	}
}
