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
    Then I should see "sections" in model description
    And I should see "text" in model description
    And I should see "level" in model description
    And I should see "content" in model description
    And I should see "type" in model description
    And I should see "images" in model description
    And I should see "elements" in model description

  Scenario: I should be able to test Articles/AsSimpleJson in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    And I click "/Articles"
    And I click "/api/v1/Articles/AsSimpleJson"
    And I put valid articleId on current wiki as "id"
    And I click try it out
    Then I should see successful response