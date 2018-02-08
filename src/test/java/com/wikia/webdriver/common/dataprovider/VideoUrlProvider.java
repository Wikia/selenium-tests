package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class VideoUrlProvider {

    private VideoUrlProvider() {
    }

  @DataProvider
  private static final Object[][] videoUrl() {
    return new Object[][]{
        // Premium Provider Links
        {
            //ign
            "http://video.wikia.com/wiki/File:Call_of_Duty_Black_Ops_2_Walkthrough_-_All_Cordis_Die_Intel_Locations",
            "Call of Duty Black Ops 2 Walkthrough - All Cordis Die Intel Locations"
        },
        // Non-Premium Provider Links
        {
            "http://www.dailymotion.com/video/x101vdw_robert-pattison-y-kristen-stewart-se-dan-un-tiempo_people#.UZovsrWnqXw",
            "Robert Pattison y Kristen Stewart se dan un tiempo"
        }, {
            "http://vimeo.com/channels/staffpicks/58024671",
            "Melt Yourself Down - Fix My Life"
        }, {
            "http://www.youtube.com/watch?v=d9r5_DDMMjY",
            "Top 5 Plays of the Night May 19th"
        },
    };
  }
}
