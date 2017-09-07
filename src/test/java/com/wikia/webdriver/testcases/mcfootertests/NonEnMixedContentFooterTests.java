package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;

import org.testng.annotations.Test;

@Test(groups = {"NonEnMixedContentFooter"})
@Execute(onWikia = "es.serowiec")

public class NonEnMixedContentFooterTests extends NewTestTemplate{

  @Test
  public void isMCFooterPresentOnNonENwiki() {
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isMcfooterPresent());
  }

  @Test
  public void exploreWikisCardIsPresentOnNonENwiki(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isExploreWikisCardPresent());
  }

}
