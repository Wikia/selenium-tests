package com.wikia.webdriver.Common.Core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

public class Crawler extends WikiBasePageObject {
	
	private int currentNumberOfURLs = 0;
	private int wantedNumberOfURLs;
	private int currentPreparingIteration = 1;
	//TODO: prevent the currentPreparingIteration to exceed number 15. Searching for too long is not good 
	private List<String> ListOfURLsReadyForExport;
	private List<String> ImportedListOfURLs;
	List<String> listOfAllURLs;
	
	private static String crawlerFolderPath = "." + File.separator + "src"
			+ File.separator + "test"
			+ File.separator + "resources"
			+ File.separator + "crawler"
			+ File.separator;
	private static String wikiURLsFileName = "wikiURLs.txt";
	private static String wikiURLsFilePath = crawlerFolderPath + wikiURLsFileName;

	public Crawler(WebDriver driver, String Domain) {
		super(driver, Domain);	
		PageFactory.initElements(driver, this);
	}
		
	/**
	 * <br>
	 * Gather URLs for exploratory testing <br><br>
	 * The method prepares a text-file: src/resources/crawler/wikiURLs.txt <br>The file contains list of URL adresses that will be used for exploratory testing<br><br>
	 * @author Michal Nowierski
	 * <br>
	 * @param numberOfURLs
	 *            determines how many pages you want to gather for exploratory testing
	 * */
	public void prepareURLsForExploratoryTests(int numberOfURLs) {
		this.wantedNumberOfURLs = numberOfURLs;
		this.ListOfURLsReadyForExport = new ArrayList<String>(wantedNumberOfURLs);
		navigateToRandomPage();
		prepareExportList();
		exportTheExportList();			
	}

	/**
	 * <br>
	 * The method prepares list of wanted URL adresses in JAVA-memory, so that it is ready for beeing exported as a text-file<br><br>
	 * @author Michal Nowierski
	 * */
	private void prepareExportList() {
		this.listOfAllURLs = getURLs();
		extractWikiURLs(listOfAllURLs);
		if (currentNumberOfURLs < wantedNumberOfURLs) {
			listOfAllURLs.clear();
			navigateToRandomWikiUrl();
			prepareExportList();
		}		
	}
	
	/**
	 * <br>
	 * The method returns list of all URL adresses in current page<br><br>
	 * @author Michal Nowierski
	 * */	
	private List<String> getURLs() {
		List<String> listOfURLs = new ArrayList<String>();
		List<WebElement> list = driver.findElements(By.cssSelector("a"));
		for (int i = 0; i < list.size(); i++) {
			String href = list.get(i).getAttribute("href");
			listOfURLs.add(href);
		}
		return listOfURLs;
	}

	/**
	 * <br>
	 * The method from all URLs extracts only those URLs which contain special mark: <b>wiki/</b> after domain. <br><br>
	 * @author Michal Nowierski
	 * <br>
	 * @param urlList
	 *            list of URLs from which you want to extract only those which contain "wiki/" after domain.
	 * */
	private void extractWikiURLs(List<String> urlList) {
		int domainLength= this.Domain.length();
		for (int i = 0; i < urlList.size(); i++) {
			try {
				if (urlList.get(i)==null) {
					throw new NullPointerException();
				}				
				String without_domain = "thisTextWillChangeLater";
				int hrefLength = urlList.get(i).length();
				if (hrefLength > domainLength +5) {					
					without_domain = urlList.get(i).substring(domainLength, hrefLength);
				}				
				//prevent saving special:Random page. Reason: Two random pages will never be comparable because they are almost always different
				if ((currentNumberOfURLs < wantedNumberOfURLs) && without_domain.substring(0, 5).equals("wiki/") && (!without_domain.contains("Special:Random"))) {
					
					if (!this.ListOfURLsReadyForExport.contains(urlList.get(i))) {
						this.ListOfURLsReadyForExport.add(urlList.get(i));
						
						currentNumberOfURLs++;
					}								
				}
				if(currentNumberOfURLs >= wantedNumberOfURLs) {
					break;
				}
			} catch (NullPointerException e) {}
		}		
	}

	/**
	 * <br>
	 * Navigate to random wiki URL which is present in the list: readyForExportListOfURLs<br><br>
	 * @author Michal Nowierski
	 * */
	private void navigateToRandomWikiUrl() {
		int randomInteger = (int)(Math.random() * (ListOfURLsReadyForExport.size()));
		String randomURL = ListOfURLsReadyForExport.get(randomInteger);
		currentPreparingIteration++;
		try {			
			getUrl(randomURL);
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut","page loads for more than 30 seconds", true);
		}	
	}

	/**
	 * export list: readyForExportListOfURLs to text-file:<br>src/resources/crawler/wikiURLs.txt<br><br>
	 * @author Michal Nowierski
	 * */
	private void exportTheExportList() {
		
		CommonUtils.deleteDirectory("." + File.separator + "src"
				+ File.separator + "test"
				+ File.separator + "resources"
				+ File.separator + "crawler");
		CommonUtils.createDirectory("." + File.separator + "src"+ File.separator + "test"+ File.separator + "resources"+ File.separator + "crawler");
		CommonUtils.createDirectory("." + File.separator + "src"+ File.separator + "test"+ File.separator + "resources"+ File.separator + "crawler"+ File.separator + "resultImages");
		for (int i = 0; i < ListOfURLsReadyForExport.size(); i++) {			
			CommonUtils.appendTextToFile(wikiURLsFilePath, ListOfURLsReadyForExport.get(i));
		}		
	}

	/**
	 * Explore the prepared URLs<br><br>
	 * If file src/resources/crawler/wikiURLs.txt is prepared, crawler visits all URLs on that list and take screenshots.<br>
	 * Saves the screenshoots to folder specified by the method's parameter<br><br>
	 * @author Michal Nowierski
	 * <br>
	 * @param explorationName
	 * 			  name of the exploration <br><br>
	 *            It saves all screenshoots to folder: <br> src\test\resources\crawler\<b>explorationName</b>         
	 * */
	public void explore(String explorationName) {
		ImportedListOfURLs = CommonUtils.getLinesInFile(wikiURLsFilePath);
				
		String screenDirPath = crawlerFolderPath + "screenshots"+explorationName
				+ File.separator;
		String screenPath = screenDirPath + "screenshot";
		for (int i = 0; i < ImportedListOfURLs.size(); i++) {
			driver.get(ImportedListOfURLs.get(i));
			hideAds();
			CommonUtils.captureScreenshot(screenPath + (i), driver);			
		}
	}

	private void hideAds() {
		String command = "css('opacity', '0')";
		executeScript("$('#TOP_BUTTON')."+command);
		executeScript("$('#HOME_TOP_RIGHT_BOXAD')."+command);
		executeScript("$('#TOP_RIGHT_BOXAD img')."+command);		
		executeScript("$('#WIKIA_BAR_BOXAD_1')."+command);
		executeScript("$('#PREFOOTER_LEFT_BOXAD')."+command);
		executeScript("$('#PREFOOTER_RIGHT_BOXAD')."+command);
		executeScript("$('#SPOTLIGHT_FOOTER img')."+command);		
		executeScript("$('#div.RVBody .item a.video-thumbnail')."+command);	
		//TODO: continue adding elements that should be hidden
		executeScript("$('#div.RVBody .item a.video-thumbnail')."+command);		
	}

	/**
	 * <br>
	 * Compare the results of two explorations. Exploration is done by using "explore(...)" method
	 * @author Michal Nowierski
	 * 
	 * @param firstExploration
	 *            name of the firts exploration to be compared
	 * @param secondExploration
	 *            name of the second exploration to be compared               
	 * */
	public void compareExplorationResults(String firstExploration, String secondExploration) {
		File crawlerFolder = new File(crawlerFolderPath);
		File[] listOfFiles = crawlerFolder.listFiles(); 
		int numberOfFiles = 0;

		for (File file : listOfFiles) {
			String name = file.getName();
			if (name.contains(firstExploration)) {				
				File[] screenshoots = file.listFiles(); 
				numberOfFiles = screenshoots.length;			
			}			
		}		
		for (int i = 0; i < numberOfFiles; i++) {
			
			File preview = new File("./src/test/resources/crawler/screenshots"+firstExploration+"/screenshot"+i+".png");
			
			File live = new File("./src/test/resources/crawler/screenshots"+secondExploration+"/screenshot"+i+".png");
			File output = new File("./src/test/resources/crawler/resultImages/output"+i+".png");
			try {
				ImageComparisonHelper.compareImages(preview.getCanonicalPath(),live.getCanonicalPath(),output.getCanonicalPath(), "compare.exe");
				//"D:/workspace/selenium-tests/src/test/resources/crawler/output.png"
			} catch (IOException e) {e.printStackTrace();
			}
		}				
	}		
}
