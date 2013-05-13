package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * 
 * @author liz_lux
 *
 */
public class FilePagePageObject extends BasePageObject {

	public FilePagePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBys(@FindBy(css="ul.tabs li a"))
	private List<WebElement> tabList;

    @FindBy(css="section[data-listing-type='local'] h3.page-listing-title a")
    private WebElement appearsListing;

    @FindBy(css="section[data-listing-type='local'] div.page-list-pagination img.right")
    private WebElement localPageNext;

    @FindBy(css="section[data-listing-type='local'] div.page-list-pagination img.left")
    private WebElement localPagePrev;

	String selectedTab = ".tabBody.selected[data-tab-body='%name%']";

	public void selectTab(int tab) {
		WebElement currentTab = tabList.get(tab);
		currentTab.click();
		PageObjectLogging.log("selectTab", tab+" selected", true);
	}
	
	public void verifySelectedTab(String tabName) {
		driver.findElement(By.cssSelector(selectedTab.replace("%name%", tabName)));
		PageObjectLogging.log("verified selected tab", tabName+" selected", true);
	}
	
	public void openFilePage(String fileName) {
		getUrl(URLsContent.filePage + fileName);
		waitForElementByElement(tabList.get(0));
		PageObjectLogging.log("Open file page", "file page opened", true);
		
	}
	
	public void refreshAndVerifyTabs(int tab) {
		
		String tabName;
		
		if(tab == 0) {
			tabName = "about";
		} else if(tab == 1) {
			tabName = "history";
		} else {
			tabName = "metadata";
		}
		
		selectTab(tab);
		verifySelectedTab(tabName);
		refreshPage();
		verifySelectedTab(tabName);
	}

    // Page forward in the local "appears on" section
    public void localAppearsPageNext() {
        localPageNext.click();
    }

    // Page backward in the local "appears on" section
    public void localAppearsPagePrev() {
        localPagePrev.click();
    }

    // Verify that a specific video title is in the "Appears on these pages" list
    public void verifyAppearsOn(String articleName) {
        PageObjectLogging.log("Verify correct article title", "title correct", appearsListing.getText().equals(articleName));
    }
}
