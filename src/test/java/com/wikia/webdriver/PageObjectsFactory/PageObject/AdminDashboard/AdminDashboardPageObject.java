package com.wikia.webdriver.PageObjectsFactory.PageObject.AdminDashboard;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPageObject extends BasePageObject {
	@FindBy(css=".controls .control a[data-tracking=\"general/specialcss\"]")
	private WebElement cssChromeLink;

	@FindBy(id="ca-viewsource")
	private WebElement viewsourceButton;

	@FindBy(id="ca-edit")
	private WebElement editButton;

    public AdminDashboardPageObject(WebDriver driver) {
        super(driver);
		PageFactory.initElements(driver, this);
    }

    public void openAdminDashboard() {
        getUrl(Global.DOMAIN + "wiki/Special:AdminDashboard");
        PageObjectLogging.log("openAdminDashboard", "admin dashboard opened", true);
    }

	public void openMediawikiWikiaCss() {
		getUrl(Global.DOMAIN + "wiki/Mediawiki:Wikia.css");
		PageObjectLogging.log("openMediawikiWikiaCss", "mediawiki:wikia.css opened", true);
	}

	public void clickCssChromeIcon() {
		cssChromeLink.click();
	}
}
