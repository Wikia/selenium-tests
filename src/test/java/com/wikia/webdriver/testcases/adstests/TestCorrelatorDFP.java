package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;

/**
 * Created by Ludwik on 2015-08-24.
 */
public class TestCorrelatorDFP extends TemplateNoFirstLoad {

  String correlatorDesktop = "_adtest,home,gpt";
  String correlatorMobile = "_adtest,home,mobile";

  @Test(groups = "DFPCorrelatorOasis")
  @NetworkTrafficDump
  @Execute(onWikia = "adtest")
  public void correlatorIDInQueryStringShouldBeTheSameOasis() {
    networkTrafficInterceptor.startIntercepting("DFPCorrelator");
    new HomePageObject(driver).open();
    networkTrafficInterceptor.logDFP(correlatorDesktop);
  }

  @Test(groups = "DFPCorrelatorMercury")
  @NetworkTrafficDump
  @Execute(onWikia = "adtest")
  public void correlatorIDInQueryStringShouldBeTheSameMercury() {
    networkTrafficInterceptor.startIntercepting("DFPCorrelator");
    new HomePageObject(driver).open();
    networkTrafficInterceptor.logDFP(correlatorMobile);
  }
}
