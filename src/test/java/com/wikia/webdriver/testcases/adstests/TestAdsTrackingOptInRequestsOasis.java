package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.List;

public class TestAdsTrackingOptInRequestsOasis extends NewTestTemplate {

  private static final Page ADS_HOME_PAGE = new Page("project43", "Project43_Wikia");
  private static final Page ADS_HIVI_PAGE = new Page("project43", "SyntheticTests/UAP/HiVi");
  private static final String POLAND = "PL";
  private static final String DENMARK = "DK";
  private static final String GERMANY = "DE";

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKikimoraRejectedDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKruxDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForKrux(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNetzAthletenDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForNetzAthleten(String[] instantGlobals,
                                                 List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, GERMANY, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsMoatDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForMoat(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HIVI_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNordicsDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForNordics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, DENMARK, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsPrebidDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsA9DataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForA9(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsQuantcastDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForQuantcast(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);

  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsComscoreDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForComscore(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);

  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsGoogleAnalyticsDataProvider",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForGoogleAnalytics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNpaHiViDataProviderOasis",
      groups = "AdsOptInRejectedOasis"
  )
  public void adsTrackingRejectedForHiViAdSlots(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.rejectOptInModal(driver, POLAND, ADS_HIVI_PAGE);
    modal.scrollTo(driver.findElement(By.className("wds-global-footer__header")));

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKikimoraAcceptedDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void verifyAdsTrackingAcceptedForKikimora(String[] instantGlobals,
                                                   List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKruxDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForKrux(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNetzAthletenDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForNetzAthleten(String[] instantGlobals,
                                                 List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, GERMANY, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNordicsDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForNordics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, DENMARK, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsMoatDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForMoat(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HIVI_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsPrebidDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsA9DataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForA9(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsQuantcastDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForQuantcast(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsComscoreDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForComscore(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNpaHiViDataProviderOasis",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForHiViAdSlots(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HIVI_PAGE);
    modal.scrollTo(driver.findElement(By.className("wds-global-footer__header")));

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsGoogleAnalyticsDataProvider",
      groups = "AdsOptInAcceptedOasis"
  )
  public void adsTrackingAcceptedForGoogleAnalytics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.acceptOptInModal(driver, POLAND, ADS_HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }
}
