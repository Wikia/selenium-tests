package com.wikia.webdriver.Trash;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;

public class CreateAWiki_1 extends TestTemplate{

	private String wikiName;

	@Test
	public void CreateNewWiki()
	{
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
//		CommonFunctions.logIn();
		CreateNewWikiPageObjectStep1 createNewWikistep1 = home.startAWiki();
		String timeStamp = createNewWikistep1.getTimeStamp();
		wikiName = "QaTest"+timeStamp;

		createNewWikistep1.submit();
		//create new wiki step 1
		createNewWikistep1.waitForElementNotVisibleByCss("span.submit-error.error-msg");
		createNewWikistep1.typeInWikiName(wikiName);
		createNewWikistep1.typeInWikiDomain(wikiName);
		createNewWikistep1.waitForSuccessIcon();
		createNewWikistep1.waitForElementNotVisibleByCss("span.submit-error.error-msg");
		//create new wiki step 2
		CreateNewWikiPageObjectStep2 createNewWikistep2 = createNewWikistep1.submit();
		createNewWikistep2.describeYourTopic("Duis quam ante, fringilla at cursus tristique, laoreet vel elit. Nullam rhoncus, magna ut dictum ultrices, mauris lectus consectetur tellus, sed dignissim elit justo vel ante.");
		createNewWikistep2.selectCategory("Auto");
		//create new wiki step 3
		CreateNewWikiPageObjectStep3 createNewWikiStep3 = createNewWikistep2.submit();
		createNewWikiStep3.selectTheme(3);
		NewWikiaHomePage newWikia = createNewWikiStep3.submit();
		newWikia.waitForCongratulationsLightBox(wikiName);
		//logout



		//delete created wiki
//		CommonFunctions.logInAsStaff();
//		SpecialFactoryPageObject factory = new SpecialFactoryPageObject(driver);
//		factory.typeInDomainName(wikiName);
//		factory.getConfiguration();
//		factory.clickCloseWikiButton();
//		factory.deselectCreateDumpCheckBox();
//		factory.deselectImageArchiveCheckBox();
//		factory.confirmClose();
//		factory.confirmClose();
//		factory.clickClosedWikiaLink();
//		factory.verifyWikiaClosed();

	}



}
