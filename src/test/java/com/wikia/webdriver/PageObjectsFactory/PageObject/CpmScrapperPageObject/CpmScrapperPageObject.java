package com.wikia.webdriver.PageObjectsFactory.PageObject.CpmScrapperPageObject;

import com.wikia.webdriver.Common.DataProvider.CpmScrapperProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class CpmScrapperPageObject extends WikiBasePageObject {

    private Calendar twoDaysBefore;
    private SimpleDateFormat dateFormat;
    private String dateFormated;
    private String xPathDate = "//table/tbody/tr/td/div/b[contains(text(),'%date%')]";

    @FindBy(css="input[name='username']")
    private WebElement loginInput;
    @FindBy(css="input[name='passwordplain']")
    private WebElement passwordInput;
    @FindBy(css="input.home_submit")
    private WebElement submitStar;
    @FindBys(@FindBy(css="#sort_table > tbody tr:not(.summary)"))
    private List <WebElement> tableRows;
    @FindBys(@FindBy(css="#sort_table > thead tr th"))
    private List <WebElement> tableHeadersData;

    public CpmScrapperPageObject(WebDriver driver, String page) {
        super(driver, page);
        PageFactory.initElements(driver, this);
    }

    public void loginCpm(String page, String userName, String password) {
	getUrl(page);
	waitForElementByElement(loginInput);
	loginInput.sendKeys(userName);
	waitForElementByElement(passwordInput);
	passwordInput.sendKeys(password);
	clickAndWait(submitStar);

	PageObjectLogging.log(
	    "UserLoggedIn",
	    "User is logged in to cpm scrapper",
	    true, driver
	);
    }

    public void generateReportForYesterday() {
	getDateForReport();
	String urlWithDates = CpmScrapperProvider.dateUrl
	    .replaceAll("\\%startM\\%|\\%endM\\%", Integer.toString(twoDaysBefore.get(Calendar.MONTH) + 1))
	    .replaceAll("\\%startD\\%|\\%endD\\%", Integer.toString(twoDaysBefore.get(Calendar.DAY_OF_MONTH)))
	    .replaceAll("\\%startY\\%|\\%endY\\%", Integer.toString(twoDaysBefore.get(Calendar.YEAR)));
	getUrl(CpmScrapperProvider.publishReportsUrl + urlWithDates);
	waitForElementByXPath(xPathDate.replace("%date%", dateFormated));
	PageObjectLogging.log(
	    "ReportGenerated",
	    "Report for " + dateFormated + " generated",
	    true, driver
	);
    }

    public void scrapeData() {
	if (driver.findElements(By.cssSelector("#sort_table tbody")).size() < 1) {
	    PageObjectLogging.log(
		"NoData",
		"There is no report for " + dateFormated,
		true
	    );
	    return;
	} else {
	    String headers = "";
	    for (WebElement cell : tableHeadersData) {
		headers += cell.getText() + "\t";
	    }

	    String data = "";
	    for (WebElement row : tableRows) {
		List <WebElement> cells = row.findElements(By.cssSelector("td"));
		for (WebElement cell : cells) {
		    data += cell.getText() + "\t";
		}
		data += "\n";
	    }

	    PageObjectLogging.log(
		"DataScrapped",
		headers + data,
		true
	    );
	}
    }

    private void getDateForReport() {
	twoDaysBefore = Calendar.getInstance();
	dateFormat = new SimpleDateFormat("MMM dd, YYYY", Locale.ENGLISH);
	twoDaysBefore.add(Calendar.DATE, -2);
	dateFormated = dateFormat.format(twoDaysBefore.getTime());
    }
}
