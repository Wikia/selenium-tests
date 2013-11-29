Feature: As a user I want to have our Apis documented.

  Scenario: I want to see all Articles/AsSimpleJson arguments in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    And I click "/Articles"
    And I click "/api/v1/Articles/AsSimpleJson"
    Then I should see description for "id" parameter

  Scenario: I want to see all Articles/AsSimpleJson response fields in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    And I click "/Articles"
    And I click "/api/v1/Articles/AsSimpleJson"
    Then I should see following fields in model description:
      | sections |
      | text     |
      | level    |
      | content  |
      | type     |
      | images   |
      | elements |

  Scenario: I should be able to test Articles/AsSimpleJson in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    And I click "/Articles"
    And I click "/api/v1/Articles/AsSimpleJson"
    And I put valid articleId on current wiki as "id"
    And I click try it out
    Then I should see successful response


  Scenario: I want to see all Articles/New arguments in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    And I click "/Articles"
    And I click "/api/v1/Articles/New"
    Then I should see following fields in model description:
      | id            |
      | title         |
      | url           |
      | ns            |
      | abstract      |
      | creation_date |
      | avatar        |
      | name          |
      | width         |
      | height        |

  Scenario: I should be able to test Articles/New in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    And I click "/Articles"
    And I click "/api/v1/Articles/New"
    And I click try it out
    Then I should see successful response