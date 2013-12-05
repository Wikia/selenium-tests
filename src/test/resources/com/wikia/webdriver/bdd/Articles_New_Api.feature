Feature: As a Mobile team member
  I'd like to API be sorted by creation date
  So that so I can use it in Co-Pilot

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/New"
    Then I should get list of no more than 20 most recent articles created on wiki
    And I see in each result following fields:
      | id            |
      | title         |
      | url           |
      | ns            |
      | abstract      |
      | creation_date |
    And I see in each result following fields not empty:
      | creation_date |
    And I see in each result "creator" object with following fields:
      | avatar |
      | name   |
    And I see in each result "creator" object with following fields not empty:
      | avatar |
      | name   |

  Scenario: I want to be able to filter results by namespace
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/New" with parameters:
      | key        | value |
      | namespaces | 8     |
    Then I should get list of no more than 20 most recent articles created on wiki
    And all results should have integer field "ns" equal to "8"


  Scenario: I want to be able to filter results by multiple namespaces
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/New" with parameters:
      | key        | value |
      | namespaces | 8,6   |
    Then I should get list of no more than 20 most recent articles created on wiki
    And all results should have integer field "ns" equal to "8" or "6"
