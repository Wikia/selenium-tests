package com.wikia.webdriver.testcases.adstests;

import static java.util.stream.Collectors.toSet;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsVeles;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestAdsVelesTracking extends TemplateNoFirstLoad {

  public static final String VELES_TRACKING_REQUEST_PART = "adengadinfo";

  public static final String VELES_TRACKING_POSITION_PARAMETER = "kv_pos";

  public static final String VELES_TRACKING_PRICE_PARAMETER = "bidder_8";

  private void testVeles(String urlFragment, Map<String, String> positionsAndPrices) {
    AdsBaseObject pageObject = new AdsBaseObject(driver, urlBuilder.getUrlForPage(
        new Page("project43", urlFragment)
    ));

    Set<HarEntry> entries = findEntriesByUrlPart(
        pageObject,
        VELES_TRACKING_REQUEST_PART,
        positionsAndPrices
    );

    Assertion.assertTrue(
        wasVelesTrackedIn(entries, positionsAndPrices),
        "Veles should be tracked only in " + positionsAndPrices
    );
  }

  @NetworkTrafficDump()
  @Test(groups = {"AdsTrackingVeles", "AdsTrackingVelesTrackedForBothSlots"})
  public void adsTrackingVelesTrackedForBothSlots() {
    networkTrafficInterceptor.startIntercepting();
    testVeles(
        "SyntheticTests/RTB/Prebid.js/Veles?" + AdsVeles.TURN_ON_QUERY_PARAM,
        buildPositionsAndPrices("20.00", "20.00")
    );
  }

  @NetworkTrafficDump()
  @Test(groups = {"AdsTrackingVeles", "AdsTrackingVelesTrackedForIncontent"})
  public void adsTrackingVelesTrackedForIncontent() {
    networkTrafficInterceptor.startIntercepting();

    testVeles(
        "SyntheticTests/RTB/Prebid.js/Veles/Incontent?" + AdsVeles.TURN_ON_QUERY_PARAM,
        buildPositionsAndPrices("NOT_INVOLVED", "20.00")
    );
  }

  @NetworkTrafficDump()
  @Test(groups = {"AdsTrackingVeles", "AdsTrackingVelesTrackedForLeaderboard"})
  public void adsTrackingVelesTrackedForLeaderboard() {
    networkTrafficInterceptor.startIntercepting();

    testVeles(
        "SyntheticTests/RTB/Prebid.js/Veles/Leaderboard?" + AdsVeles.TURN_ON_QUERY_PARAM,
        buildPositionsAndPrices("20.00", "NOT_INVOLVED")
    );
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsTrackingVeles", "AdsTrackingVelesTimeoutErrorTracked"})
  public void adsTrackingVelesTimeoutErrorTracked() {
    Map<String, DefaultHttpResponse> mockRules = ImmutableMap.<String, DefaultHttpResponse>builder()
        .put(
            ".*output=vast.*",
            new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.REQUEST_TIMEOUT)
        )
        .build();
    mockResponses(mockRules);

    networkTrafficInterceptor.startIntercepting();

    testVeles(
        "SyntheticTests/RTB/Prebid.js/Veles/Both/Leaderboard?" + AdsVeles.TURN_ON_QUERY_PARAM,
        buildPositionsAndPrices("", "")
    );
  }

  @NetworkTrafficDump()
  @Test(groups = {"AdsTrackingVeles", "AdsTrackingVelesPageWithEmptyVastTracked"})
  public void adsTrackingVelesPageWithEmptyVastTracked() {
    Map<String, DefaultHttpResponse> mockRules = ImmutableMap.<String, DefaultHttpResponse>builder()
        .build();
    mockResponses(mockRules);

    networkTrafficInterceptor.startIntercepting();

    testVeles(
        "Project43_Wikia?" + AdsVeles.TURN_ON_QUERY_PARAM,
        buildPositionsAndPrices("0.00")
    );
  }

  private ImmutableMap<String, String> buildPositionsAndPrices(String leaderboardPrice) {
    return ImmutableMap.<String, String>builder()
        .put(AdsContent.TOP_LB, leaderboardPrice)
        .build();
  }

  private ImmutableMap<String, String> buildPositionsAndPrices(String leaderboardPrice,
                                                               String incontentPrice) {
      return ImmutableMap.<String, String>builder()
          .put(AdsContent.TOP_LB, leaderboardPrice)
          .put(AdsContent.INCONTENT_PLAYER, incontentPrice)
          .build();
  }

  private void mockResponses(Map<String, DefaultHttpResponse> mockRules) {
    if (mockRules != null && !mockRules.isEmpty()) {
      networkTrafficInterceptor.addRequestFilter((request, contents, messageInfo) -> {

        for (Map.Entry<String, DefaultHttpResponse> rule : mockRules.entrySet()) {
          if (request.getUri().matches(rule.getKey())) {
            return rule.getValue();
          }
        }

        return null;
      });
    }
  }

  private Set<HarEntry> findEntriesByUrlPart(AdsBaseObject pageObject, final String s, final Map<String, String> positionsAndPrices) {
    for(String slotName: positionsAndPrices.keySet()) {
      waitForSlotTrackingRequest(pageObject, slotName);
    }

    return networkTrafficInterceptor.getHar().getLog().getEntries().stream()
        .filter(entry -> entry.getRequest().getUrl().contains(s))
        .collect(toSet());
  }

  private void waitForSlotTrackingRequest(AdsBaseObject pageObject, String slotName) {
    final String pattern = String.format(".*%s.*%s=%s.*", VELES_TRACKING_REQUEST_PART, VELES_TRACKING_POSITION_PARAMETER, slotName);
    pageObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pattern);
  }

  private boolean wasVelesTrackedIn(final Set<HarEntry> entries, final Map<String, String> positionsAndPrices) {
    boolean result = false;

    if (!entries.isEmpty()) {

      result = entries.stream()
          .map(entry -> URLEncodedUtils.parse(entry.getRequest().getUrl(), StandardCharsets.UTF_8))
          .map(VelesTrackingInfo::fromParameters)
          .allMatch(trackingInfo -> validatePricesIn(trackingInfo, positionsAndPrices));
    }

    return result;
  }

  private boolean validatePricesIn(final VelesTrackingInfo trackingInfo, final Map<String, String> positionsAndPrices) {
    return positionsAndPrices.containsKey(trackingInfo.getPosition())
        ? positionsAndPrices.get(trackingInfo.getPosition()).equals(trackingInfo.getPrice())
        : "".equals(trackingInfo.getPrice());
  }

  private static class VelesTrackingInfo {
    private final String position;

    private final String price;

    private VelesTrackingInfo(String position, String price) {
      this.position = position;
      this.price = price;
    }

    String getPosition() {
      return position;
    }

    String getPrice() {
      return price;
    }

    @Override
    public String toString() {
      return "VelesTrackingInfo{" +
          "position='" + position + '\'' +
          ", price='" + price + '\'' +
          '}';
    }

    private static VelesTrackingInfo fromParameters(final List<NameValuePair> parameters) {
      return new VelesTrackingInfo(find(parameters, VELES_TRACKING_POSITION_PARAMETER), find(parameters, VELES_TRACKING_PRICE_PARAMETER));
    }

    private static String find(final List<NameValuePair> parameters, final String key) {
      return parameters.stream()
          .filter(parameter -> key.equals(parameter.getName()))
          .findFirst()
          .map(NameValuePair::getValue)
          .orElse("");
    }
  }
}
