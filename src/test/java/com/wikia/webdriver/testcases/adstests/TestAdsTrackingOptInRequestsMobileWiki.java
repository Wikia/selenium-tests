package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import org.testng.annotations.Test;

import java.util.List;


@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TestAdsTrackingOptInRequestsMobileWiki extends NewTestTemplate {

  private static final Page ADS_HOME_PAGE = new Page("project43", "Project43_Wikia");
  private static final Page ADS_PORVATA_DIRECT = new Page("project43", "SyntheticTests/Video/Porvata/Direct");

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKikimoraDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                                                           networkTrafficInterceptor,
                                                           ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
          dataProviderClass = TrackingOptInDataProvider.class,
          dataProvider = "adsNpaSlotsDataProviderMobile",
          groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForSlotsAdSlots(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver,"EU", "PL");
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_PORVATA_DIRECT));

    modal.clickRejectButton();
    modal.isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKruxDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForKrux(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                                                           networkTrafficInterceptor,
                                                           ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNetzAthletenDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForNetzAthleten(String[] instantGlobals,
                                                 List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                                                           networkTrafficInterceptor,
                                                           ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsMoatDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForMoat(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                                                           networkTrafficInterceptor,
                                                           ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNordicsDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForNordics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");

    new TrackingOptInModal()
        .verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsPrebidDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                                                           networkTrafficInterceptor,
                                                           ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsA9DataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForA9(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                                                           networkTrafficInterceptor,
                                                           ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsComscoreQuantcastDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForComscoreAndQuantcast(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal()
        .verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);

  }

    @NetworkTrafficDump(useMITM = true)
    @Execute(trackingOptIn = false)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsGoogleAnalyticsDataProvider",
            groups = "AdsOptInRejectedMobileWiki"
    )
    public void adsTrackingRejectedForGoogleAnalytics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");  TrackingOptInModal.setGeoCookie(driver, "EU", "PL");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_HOME_PAGE));
        modal.clickRejectButton();

        modal.isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Execute(trackingOptIn = false)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsQualarooDataProvider",
            groups = "AdsOptInRejectedMobileWiki"
    )
    public void adsTrackingRejectedForQualaroo(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKikimoraDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void verifyAdsTrackingAcceptedForKikimora(String[] instantGlobals,
                                                   List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                                                        networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKruxDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForKrux(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                                                        networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNetzAthletenDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForNetzAthleten(String[] instantGlobals,
                                                 List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                                                        networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsMoatDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForMoat(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                                                        networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNordicsDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForNordics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");

    new TrackingOptInModal()
        .verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsPrebidDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                                                        networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsA9DataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForA9(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                                                        networkTrafficInterceptor, ADS_HOME_PAGE);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsComscoreQuantcastDataProvider",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForComscoreAndQuantcast(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

    new TrackingOptInModal()
        .verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
  }

    @NetworkTrafficDump(useMITM = true)
    @Execute(trackingOptIn = false)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsGoogleAnalyticsDataProvider",
            groups = "AdsOptInAcceptedMobileWiki"
    )
    public void adsTrackingAcceptedForGoogleAnalytics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_HOME_PAGE));
        modal.clickAcceptButton();

        modal.isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Execute(trackingOptIn = false)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsQualarooDataProvider",
            groups = "AdsOptInAcceptedMobileWiki"
    )
    public void adsTrackingAcceptedForQualaroo(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
          dataProviderClass = TrackingOptInDataProvider.class,
          dataProvider = "adsNpaSlotsDataProviderMobile",
          groups = "AdsOptInAcceptedMobileWiki"
  )
  public void adsTrackingAcceptedForAdSlots(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInModal.setGeoCookie(driver,"EU", "PL");
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_PORVATA_DIRECT));

    modal.clickAcceptButton();
    modal.isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }
}
