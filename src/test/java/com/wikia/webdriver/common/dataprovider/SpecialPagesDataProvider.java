package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Bogna 'bognix' Knychala
 */
public class SpecialPagesDataProvider {

  private SpecialPagesDataProvider() {

  }

  @DataProvider
  public static final Object[][] getSpecialPagesForAnons() {
    return new Object[][]{
        {"Special:BrokenRedirects", "Broken redirects"},
        {"Special:DeadendPages", "Dead-end pages"},
        {"Special:DoubleRedirects", "Double redirects"},
        {"Special:LongPages", "Long pages"},
        {"Special:AncientPages", "Oldest pages"},
        {"Special:LonelyPages", "Orphaned pages"},
        {"Special:FewestRevisions", "Pages with the fewest revisions"},
        {"Special:WithoutInterwiki", "Pages without language links"},
        {"Special:ProtectedPages", "Protected pages"},
        {"Special:ShortPages", "Short pages"},
        {"Special:TagsReport", "Tags report"},
        {"Special:UncategorizedCategories", "Uncategorized categories"},
        {"Special:UncategorizedPages", "Uncategorized pages"},
        {"Special:UncategorizedFiles", "Uncategorized photos"},
        {"Special:UncategorizedTemplates", "Uncategorized templates"},
        {"Special:UnusedCategories", "Unused categories"},
        {"Special:UnusedFiles", "Unused photos"},
        {"Special:UnusedTemplates", "Unused templates"},
        {"Special:WantedCategories", "Wanted categories"},
        {"Special:WantedPages", "Wanted pages"},
        {"Special:WantedFiles", "Wanted photos"},
        {"Special:WantedTemplates", "Wanted templates"},
        {"Special:AllPages", "All pages"},
        {"Special:PrefixIndex", "All pages with prefix"},
        {"Special:Categories", "Categories list"},
        {"Special:CategoryTree", "Category tree"},
        {"Special:Disambiguations", "Pages linking to disambiguation pages"},
        {"Special:ListRedirects", "Redirects list"}
    };
  }
}
