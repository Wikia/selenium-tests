package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Ludwik Kaźmierczak
 * @ownership Consumer
 */
public class CompareScreenshotsForHubsMenu extends NewTestTemplate {

  /**
   * Test is taking screenshots of of hubs menu in global navigation and comparing to the expected
   * designs from resources folder located in 'Baseline' folder
   */
  @Test(groups = {"HubsMenu_001", "GlobalNav"})
  public void HubsMenu_001_compareScreenshotForHubsMenu() {

    HomePageObject homePage = new HomePageObject(driver);
    homePage.openWikiPage(urlBuilder.getUrlForWiki("serowiec"));

    boolean failed = false;

    for (VenusGlobalNavPageObject.Hub hubName : VenusGlobalNavPageObject.Hub.values()) {
      homePage.getVenusGlobalNav().openHub(hubName);
      failed =
          takeScreenshotAndCompare(homePage.getVenusGlobalNav().getMenuScreenShotArea(),
                                   hubName.getLabelText());
    }

    //Throw exception if any test fails, just to mark whole test as failed
    if (failed) {
      throw new AssertionError();
    }
  }

  private boolean takeScreenshotAndCompare(WebElement element, String expectedFileName) {
    Shooter shooter = new Shooter();

    ImageComparison comparator = new ImageComparison();

    File currentFile = shooter.captureWebElement(element, driver);
    String expectedFilePath = ClassLoader.getSystemResource("Baseline/" + expectedFileName + ".png")
        .getPath();

    String
        currentFileCopyPath =
        ClassLoader.getSystemResource("Baseline/").getPath() + expectedFileName + "_current.png";

    String baselineDirectory = ClassLoader.getSystemResource("Baseline/").getPath();
    
    // rename screenshooted file - handles file already exists situation
    File newFile = new File(currentFile.getParent(), expectedFileName + "_current.png");
    try {
		Files.move(currentFile.toPath(), newFile.toPath());
	} catch (IOException e1) {
		e1.printStackTrace();
	}
    
    // assign current file for the new path
    currentFile = new File(currentFileCopyPath);
    
    // move file to baseline directory
    try {
        FileUtils.copyFileToDirectory(currentFile.getAbsolutePath(), baselineDirectory);
    } catch (IOException e) {
        e.printStackTrace();
    }

    File expectedFile = new File(expectedFilePath);
    try {
      Assertion
          .assertTrue(comparator.areFilesTheSame(currentFile, expectedFile));
      return false;
    } catch (AssertionError e) {
      PageObjectLogging
          .log("Design is not as expected for: " + expectedFileName,
               "Expected: " + expectedFilePath, false,
               driver);
      return true;
    }
  }
}
