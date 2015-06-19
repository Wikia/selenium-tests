package com.wikia.webdriver.testcases.flagTests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mariusz on 2015-06-08.
 */
public class flagTest extends NewTestTemplate {
    Credentials credentials = config.getCredentials();


    @Test(groups = {"addFlag_01"})

    public void addFlag_01() {

        List list = new ArrayList();
        WikiBasePageObject base = new WikiBasePageObject(driver);

        base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);

        //  WikiBasePageObject base = new WikiBasePageObject(driver);
        wikiURL = "http://preview.runescape-test.wikia.com/wiki/Special:Random";
        base.openWikiPage(wikiURL);

        driver.findElement(
                By.cssSelector("#WikiaPageHeader > nav.wikia-menu-button > span.drop"))
                .click();
        base.waitForElementByCss("#WikiaPageHeader > nav.wikia-menu-button > span.drop");
        driver.findElement(By.id("ca-flags")).click();
        base.waitForElementById("ca-flags");

        List<WebElement> inputs =
                driver.findElements(By.cssSelector("#flagsEditForm ul input[type=checkbox]"));

        //take flags ID which are enabled on current wiki
        for (int i = 0; i < inputs.size(); i++) {
            if (!driver.findElement(By.id(inputs.get(i).getAttribute("id"))).isSelected()) {
                driver.findElement(By.id(inputs.get(i).getAttribute("id"))).click();
            }
            list.add(inputs.get(i).getAttribute("id"));
        }
        driver.findElement(By.xpath("//footer/div/button")).click();


        // change ID flags on numerical ID and check if it's enabled
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String rep;
            rep = (String) iterator.next();
            rep = rep.replaceFirst("\\D+", "");
            try {

                if (driver.findElement(By.cssSelector(
                        "[data-type-id=\"" + rep + "\"]")).isEnabled()) ;
            } catch (org.openqa.selenium.NoSuchElementException reason) {
                System.err.println(reason);         //print flag id which isn't enabled
            }
        }

    }
}
