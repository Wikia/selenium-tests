package com.wikia.webdriver.common.testnglisteners;

import java.util.*;

public class TestFilters {

  private static final List<Set<String>> FILTERS = new ArrayList<>();

  private static void init(){
    for(String element : System.getProperty("groups").split(",")){
      HashSet<String> tagSet = new HashSet<String>();
      for(String tag : element.split("\\+")){
        tagSet.add(tag.trim());
      }
      FILTERS.add(tagSet);
    }
  }

  public static List<Set<String>> getFilters(){
    if(FILTERS.isEmpty())
      init();
    return FILTERS;
  }
}
