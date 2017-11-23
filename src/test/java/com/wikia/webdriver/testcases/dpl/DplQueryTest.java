package com.wikia.webdriver.testcases.dpl;

import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.dpl.DplQueryTestPageObject;
import org.testng.annotations.Test;

@Execute(onWikia = "sustainingtest")
@Test(groups = {"dpl"})
public class DplQueryTest extends NewTestTemplate {
  @Test
  public void verifySimpleDplQueriesProduceExpectedResultSet() {
    DplQueryTestPageObject dplQueryTestPageObject = new DplQueryTestPageObject();
    dplQueryTestPageObject.openDplQueryTestPage(wikiURL);

    assertTrue(dplQueryTestPageObject.dplCategoryQueryContainsExpectedPageSet());
    assertTrue(dplQueryTestPageObject.dplCreatedByQueryContainsExpectedPageSet());
  }
}
