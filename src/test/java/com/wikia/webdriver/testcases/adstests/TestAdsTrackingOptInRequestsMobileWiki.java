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
      groups = "adsOptInRejectedMobileWiki"
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
          groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInRejectedMobileWiki"
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
            groups = "adsOptInRejectedMobileWiki"
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
            groups = "adsOptInRejectedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
      groups = "adsOptInAcceptedMobileWiki"
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
            groups = "adsOptInAcceptedMobileWiki"
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
            groups = "adsOptInAcceptedMobileWiki"
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
          groups = "adsOptInAcceptedMobileWiki"
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
