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

  @Test(groups = "DFPCorrelator")
  @NetworkTrafficDump
  @Execute(onWikia = "adtest")
  public void correlatorIDInQueryStringShouldBeTheSame() {
    networkTrafficInterceptor.startIntercepting("DFPCorrelator");
    new HomePageObject(driver).open();
    networkTrafficInterceptor.logDFP();
  }
}
