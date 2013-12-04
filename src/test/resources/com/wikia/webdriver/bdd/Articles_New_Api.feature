Feature: As a Mobile team member
  I'd like to API be sorted by creation date
  So that so I can use it in Co-Pilot

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/New"
    Then I should get list of no more than 20 most recent articles created on wiki
    Then I see in each result following fields:
      | id       |
      | title    |
      | url      |
      | ns       |
      | abstract |


  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/New" with parameters:
      | paramName | value |
      | ns         | 0     |
    Then I should get list of no more than 20 most recent articles created on wiki
    And all results should have integer field "namespaces" equal to "0"


  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Articles/New" with parameters:
      | param_name | value |
      | ns         | 0,6   |
    Then I should get list of no more than 20 most recent articles created on wiki
    And all results should have integer field "namespaces" equal to "0" or "6"
