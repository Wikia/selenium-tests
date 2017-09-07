package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;

import org.testng.annotations.Test;

@Test (groups = {"EnMixedContentFooter"})
@Execute(onWikia = "serowiec")
public class EnMixedContentFooterTests extends NewTestTemplate{

  @Test
   public void mcFooterIsPresentOnENwiki() {
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isMcfooterPresent());
  }


  @Test
   public void exploreWikisCardIsPresentOnENwiki(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isExploreWikisCardPresent());
  }





}
