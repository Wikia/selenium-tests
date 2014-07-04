package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * Author: Rodrigo Molinero Gomez
 * Date: 20.06.14
 * Time: 16:53
    - Special:Maps page
	IM01: Creating a custom new map based on new image upload
	IMXX: Create a custom new map based on existing template
	IMXX: Create a real map flow
	IM02: Click on a map and verify correct redirect and URL
	IM03: Click create a map button as anon and make sure log in modal is displayed
	- Special Map page
	IM04: Create a real map, add pin types and categories and then verify those elements appear along with bottom right hand
	      options: add a pin, zoom in/out and embed code
	IM05: Click on a PIN and verify following elements: title, description, article link, thumbnail, edit link, more link 
	      when description is long
	- Create PIN Types Modal      
	IM06: Create PIN Types modal: Check image error validation for small size, big size and non-image extension
	IM07: Verify clicking "Add another pin type" link will display a new line and fields for adding new information  
	-Edit PIN Types Modal 
	IM08: Verify saving any new data will actually update the data for that pin edited
	IM09: All pin types and their images appear with their associated name when opening Edit PIN Types modal
	- PIN Creation - Add a Pin modal
	IM10: Verify following elements appear: Pin name field, Description,  Associated article, Pin category drowdown,
          Submit button, Cancel button, Image upload button
    IM11: Verify form with values for pin name, description, associated article and pin type will display those results once 
          user clicks submit button
    - PIN Editing - Edit Pin modal
    IM12: Check existence of following elements in the modal: Title, Associated article, Select pin type, Description, 
          Add pin button, Cancel button, Close button, Delete PIN button
    IM13: Verify data is correctly updated after saving
    - Random Test Cases
    IM14: Verify possibility of embedding wikia map in other pages. Verify following elements in map: PIN description when
          clicking, zoom, no add/edit features, extra bar at the bottom with Wikia wordmark          
	
*/ 

public class InteractiveMapsTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	
	//move to other class:
	private String mapName = "RMG";
	private String templateName = "RMG";
	private String pinTypeName = "RMG";

	@Test(groups = {"InteractiveMaps_001", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_001_CreateCustomMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject map = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject newMap = map.clickCreateAMap();
		CreateACustomMapComponentObjectStep1 cm1 = newMap.clickCustomMap();
		CreateACustomMapComponentObjectStep2 cm2 = cm1.selectFileToUpload(PageContent.file);
		cm2.verifyTemplateImagePreview();
		cm2.typeMapName(mapName);
		cm2.typeTemplateName(templateName);
		CreatePinTypesComponentObject cm3 = cm2.clickNext();		
		cm3.typePinTypeTitle(pinTypeName);
		InteractiveMapsPageObject map2 = cm3.clickNext();
		map2.verifyMapIsBeingProcessedMessage();
	}	
	
	@Test(groups = {"InteractiveMaps_002", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_002_ClickMapAndCheckURL() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject map = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject newMap = map.clickCreateAMap();
		CreateACustomMapComponentObjectStep1 cm1 = newMap.clickExistingTemplateMap();
		
		
	}
}
		
		
		
		
		
