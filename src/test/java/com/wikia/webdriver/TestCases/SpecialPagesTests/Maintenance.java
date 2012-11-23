package com.wikia.webdriver.TestCases.SpecialPagesTests;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;

public class Maintenance extends TestTemplate{
	
	
	private static String[] specialAnonUrl()
	{
		return new String[] {
				"Special:BrokenRedirects",
				"Broken redirects",
				"Special:DeadendPages",
				"Dead-end pages",
				"Special:DoubleRedirects",
				"Double redirects",
				"Special:LongPages",
				"Long pages",
				"Special:AncientPages",
				"Oldest pages",
				"Special:LonelyPages",
				"Orphaned pages",
				"Special:FewestRevisions",
				"Pages with the fewest revisions",
				"Special:WithoutInterwiki",
				"Pages without language links",
				"Special:ProtectedPages",
				"Protected pages",
				"Special:ShortPages",
				"Short pages",
				"Special:TagsReport",
				"Tags report",
				"Special:UncategorizedCategories",
				"Uncategorized categories",
				"Special:UncategorizedPages",
				"Uncategorized pages",
				"Special:UncategorizedFiles",
				"Uncategorized photos",
				"Special:UncategorizedTemplates",
				"Uncategorized templates",
				"Special:UnusedCategories",
				"Unused categories",
				"Special:UnusedFiles",
				"Unused photos",
				"Special:UnusedTemplates",
				"Unused templates",
				"Special:WantedCategories",
				"Wanted categories",
				"Special:WantedPages",
				"Wanted pages",
				"Special:WantedFiles",
				"Wanted photos",
				"Special:WantedTemplates",
				"Wanted templates",
				"Special:AllPages",
				"All pages",
				"Special:PrefixIndex",
				"All pages with prefix",
				"Special:Categories",
				"Categories list",
				"Special:CategoryTree",
				"Category tree",
				"Special:Disambiguations",
				"Pages linking to disambiguation pages",
				"Special:ListRedirects",
				"Redirects list"
		};
	}

	
	@DataProvider
	private static final Iterator<Object[]> getSpecialUrl()
	{
		String[] urls = specialAnonUrl();
		ArrayList al = new ArrayList();
		al.add(new Object[] { Arrays.copyOfRange(urls, 0, urls.length) } );
		return al.iterator();
	}	
	
	@Test(dataProvider="getSpecialUrl", groups = {"SpecialPagesAnon","SpecialPages"})
	public  void maintenancePageAnon(String[] url)
	{
		BasePageObject base = new BasePageObject(driver);
		for (int i=0; i<url.length; i+=2)
		{
			base.getUrl(Global.DOMAIN + "wiki/"+url[i]);
			base.waitForElementByXPath("//h1[contains(text(), '"+url[i+1]+"')]");
		}
		
		
	}

}
