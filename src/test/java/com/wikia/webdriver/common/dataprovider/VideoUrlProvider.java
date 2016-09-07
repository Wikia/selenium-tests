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
            //ooyala
            "http://video.wikia.com/wiki/File:Mainpages_101_-_How_to_make_a_great_mainpage_for_your_wiki_10-19-12_3.05_PM.mp4",
            "Mainpages 101 - How to make a great mainpage for your wiki 10-19-12 3.05 PM.mp4"
        }, {
            //ign
            "http://video.wikia.com/wiki/File:Call_of_Duty_Black_Ops_2_Walkthrough_-_All_Cordis_Die_Intel_Locations",
            "Call of Duty Black Ops 2 Walkthrough - All Cordis Die Intel Locations"
        }, {
            //anyclip
            "http://video.wikia.com/wiki/File:Batman_-_Following",
            "Batman - Following"
        }, {
            //screenplay
            "http://video.wikia.com/wiki/File:Greek_Chapter_Four_(2010)_-_Clip_What_Would_You_Do%3F",
            "Greek Chapter Four (2010) - Clip What Would You Do?"
        },
        // Non-Premium Provider Links
        {
            "http://www.dailymotion.com/video/x101vdw_robert-pattison-y-kristen-stewart-se-dan-un-tiempo_people#.UZovsrWnqXw",
            "Robert Pattison y Kristen Stewart se dan un tiempo"
        }, {
            "http://www.hulu.com/watch/489169",
            "The Unnatural (Bob's Burgers)"
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
