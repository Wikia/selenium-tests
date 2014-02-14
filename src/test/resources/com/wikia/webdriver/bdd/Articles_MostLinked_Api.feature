Feature: As a Sony
  I'd like to API return most linked articles
  So that so I can use it in my app

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/MostLinked"
    Then I see in each result following fields:
      | id            |
      | title         |
      | url           |
      | backlink_cnt  |
    And I see in each result following fields not empty:
      | id            |
      | title         |
      | url           |
      | backlink_cnt  |
    And I should get list of "backlink_cnt" in descending or equal order

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/MostLinked?expand=1"
    Then I see in each result following fields:
      | id            |
      | title         |
      | url           |
      | backlink_cnt  |
      | revision      |
      | type          |
      | abstract      |
      | ns            |
    And I see in each result following fields not empty:
      | id            |
      | title         |
      | url           |
      | backlink_cnt  |