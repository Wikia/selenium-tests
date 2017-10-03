package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.testcases.createawikitests.CreateWikiTests_lang;
import org.testng.annotations.Test;

@Test(groups = "discussions-creation")
public class DiscussionsCreation extends CreateWikiTests_lang {

  @Execute(asUser = User.USER_CNW)
  public void testNewCommunityDiscussionsCreation() {
    CreateNewWiki_lang_TC001("en");
    PageObjectLogging.logInfo(driver.getCurrentUrl());
  }
}
