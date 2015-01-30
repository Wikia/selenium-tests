package com.wikia.webdriver.common.contentpatterns;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class WikiFactoryVariablesProvider {

  public enum WikiFactoryVariables {
    WG_WIKI_DIRECTED_AT_CHILDREN_BY_FOUNDER("wgWikiDirectedAtChildrenByFounder"),
    WG_HIGH_VALUE_COUNTRIES("wgHighValueCountries");

    private String displayName;

    WikiFactoryVariables(String displayName) {
      this.displayName = displayName;
    }

    public String toString() {
      return displayName;
    }
  }
}
