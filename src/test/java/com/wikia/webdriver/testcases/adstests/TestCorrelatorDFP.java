package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;

import org.testng.annotations.Test;

public class TestCorrelatorDFP extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String HOME_PAGE = "Project43_Wikia";

  String correlatorDesktop = "_adtest,home,gpt";
  String correlatorMobile = "_adtest,home,mobile";

  @Test(groups = "DFPCorrelatorOasis")
  @NetworkTrafficDump
  public void correlatorIDInQueryStringShouldBeTheSameOasis() {
    networkTrafficInterceptor.startIntercepting("DFPCorrelator");
    urlBuilder.getUrlForPage(WIKIA, HOME_PAGE);
    networkTrafficInterceptor.logDFP(correlatorDesktop);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(groups = "DFPCorrelatorMercury")
  @NetworkTrafficDump
  public void correlatorIDInQueryStringShouldBeTheSameMercury() {
    networkTrafficInterceptor.startIntercepting("DFPCorrelator");
    urlBuilder.getUrlForPage(WIKIA, HOME_PAGE);
    networkTrafficInterceptor.logDFP(correlatorMobile);
  }
}
