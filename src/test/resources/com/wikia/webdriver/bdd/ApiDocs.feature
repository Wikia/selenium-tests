Feature: As a user I want to have our Apis documented.

  Scenario: I want to see all Articles/AsSimpleJson arguments in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    Then I should see "/Articles"
    When I click "/Articles"
    Then I should see "/api/v1/Articles/AsSimpleJson"
    When I click "/api/v1/Articles/AsSimpleJson"
    Then I should see description for "id" property

  Scenario: I want to see all Articles/AsSimpleJson response fields in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    Then I should see "/Articles"
    When I click "/Articles"
    Then I should see "/api/v1/Articles/AsSimpleJson"
    When I click "/api/v1/Articles/AsSimpleJson"
    Then I should see "sections" in model description
    Then I should see "text" in model description
    Then I should see "level" in model description
    Then I should see "content" in model description
    Then I should see "type" in model description
    Then I should see "images" in model description
    Then I should see "elements" in model description

  Scenario: I should be able to test Articles/AsSimpleJson in api docs
    Given non-corporate Wiki
    When I go to API v1 documentation page
    Then I should see "/Articles"
    When I click "/Articles"
    Then I should see "/api/v1/Articles/AsSimpleJson"
    When I click "/api/v1/Articles/AsSimpleJson"
    And I put valid articleId on current wiki as "id"
    Then I should see successful response