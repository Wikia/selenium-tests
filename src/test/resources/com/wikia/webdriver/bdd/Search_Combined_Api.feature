Feature: As a Sony
  I would like to search all wikias for given phrase

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Search/Combined" with parameters:
      | key        | value |
      | query      | alien |
      | namespaces | 0     |
      | langs      | en    |
    Then I should get list of "wikias"
    And I see "wikias" array with each element having following fields not empty:
      | wikiId         |
      | name           |
      | url            |
      | lang           |
      | snippet        |
      | wordmark       |
      | topArticles    |
    And I see "wikias" array with "topArticles" array field with each element having following fields not empty:
      | wikiId         |
      | articleId      |
      | title          |
      | url            |
      | lang           |
      | snippet        |
      | image          |
    Then I should get list of "articles"
    And I see "articles" array with each element having following fields not empty:
      | wikiId         |
      | articleId      |
      | title          |
      | url            |
      | lang           |
      | snippet        |
      | image          |
      | articleQuality |
