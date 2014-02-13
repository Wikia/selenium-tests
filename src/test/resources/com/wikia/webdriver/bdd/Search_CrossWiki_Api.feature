Feature: As a Mobile Team
  I would like to search for any Wikia

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Search/CrossWiki" with parameters:
      | key        | value |
      | query      | lost  |
      | lang       | en    |
    Then I should get list of "items"
    And I see "items" array with each element having following fields not empty:
      | id         |
      | language   |

  Scenario: I want to see results in certain format
    Given non-corporate Wiki
    When I ask "v1" api for "Search/CrossWiki" with parameters:
      | key        | value   |
      | query      | muppet  |
      | lang       | en      |
      | expand     | 1       |
      | limit      | 1       |
    Then I should get list of "items"
    And I see "items" array with each element having following fields not empty:
      | id                    |
      | wordmark              |
      | title                 |
      | url                   |
      | stats                 |
      | topUsers              |
      | headline              |
      | lang                  |
      | flags                 |
      | desc                  |
      | image                 |
      | wam_score             |
      | original_dimensions   |
    And I see in each result "stats" object with following fields not empty:
      | edits         |
      | articles      |
      | pages         |
      | users         |
      | activeUsers   |
      | images        |
      | videos        |
      | admins        |
    And Together with "image" I see in each result "original_dimensions" object with following fields:
      | width   |
      | height  |
