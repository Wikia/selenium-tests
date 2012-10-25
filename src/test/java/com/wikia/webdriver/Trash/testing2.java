package com.wikia.webdriver.Trash;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;

public class testing2 extends TestTemplate{
	
	@Test
	public void CreateNewWiki()
	{
		driver.get(Global.DOMAIN);
		
		for (int i=0; i<100; i++)
		{
			System.out.println(i);
			CommonFunctions.logInCookie(Properties.userName, Properties.password);
		}
	}
	

}
