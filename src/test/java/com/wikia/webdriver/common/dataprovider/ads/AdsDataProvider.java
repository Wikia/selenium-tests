package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

public class AdsDataProvider {

  private static final String WIKI_SPECIAL = "project43";
  public static final Page UAP_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP");
  public static final Page UAP_HIVI_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP/HiVi");
  public static final Page UAP_CTP_HIVI_PAGE = new Page(WIKI_SPECIAL,
                                                        "SyntheticTests/UAP/HiVi/CTP"
  );
  public static final Page PAGE_FV = new Page(WIKI_SPECIAL, "SyntheticTests/Premium/FeaturedVideo");
  private static final String
      FV_JWPLAYER_PAGE_URI
      = "SyntheticTests/Premium/FeaturedVideo/JWPlayer";
  public static final Page PAGE_FV_JWPLAYER = new Page(WIKI_SPECIAL, FV_JWPLAYER_PAGE_URI);

  private AdsDataProvider() {}

  @DataProvider
  public static Object[][] adsSlotSizeOasis() {
    return new Object[][]{
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(300, 250))
             .put("lineItemId", "269679732").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage/300x600"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(300, 600))
             .put("lineItemId", "270230292").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapers"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(300, 250))
             .put("lineItemId", "269679732").build()}, {new Page(WIKI_SPECIAL,
                                                                 "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapersWithJumboMedrec"
    ), "", ImmutableMap.<String, Object>builder().put(
        "slotName",
        AdsContent.FLOATING_MEDREC
    )
                                                            .put("slotSize",
                                                                 new Dimension(300, 250)
                                                            )
                                                            .put("lineItemId",
                                                                 "269679732"
                                                            ).build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage/160x600"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(160, 600))
             .put("lineItemId", "270616092").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Slots/Size/120x600"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(120, 600))
             .put("lineItemId", "257673852").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Slots/Size/300x1050"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.TOP_BOXAD)
             .put("slotSize", new Dimension(300, 1050))
             .put("lineItemId", "255534972").build()}};
  }
}
