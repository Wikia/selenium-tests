Feature: As a user I want to have our Apis documented.

  Scenario: I want to see all Articles/AsSimpleJson arguments in api docs
    Given I am on "Muppet Wiki"
    When I go to API v1 documentation page
    Then I want to see "/Articles"
    When I click "/Articles"
    Then I want to see "/api/v1/Articles/AsSimpleJson"
    When I click "/api/v1/Articles/AsSimpleJson"
    Then I want to see description for "id" property

  Scenario: I want to see all Articles/AsSimpleJson response fields in api docs
    Given I am on "Muppet Wiki"
    When I go to API v1 documentation page
    Then I want to see "/Articles"
    When I click "/Articles"
    Then I want to see "/api/v1/Articles/AsSimpleJson"
    When I click "/api/v1/Articles/AsSimpleJson"
    Then I want to see "sections" in model description
    Then I want to see "text" in model description
    Then I want to see "level" in model description
    Then I want to see "content" in model description
    Then I want to see "type" in model description
    Then I want to see "images" in model description
    Then I want to see "elements" in model description

  Scenario: I want to be able to test Articles/AsSimpleJson in api docs
    Given I am on "Muppet Wiki"
    When I go to API v1 documentation page
    Then I want to see "/Articles"
    When I click "/Articles"
    Then I want to see "/api/v1/Articles/AsSimpleJson"
    When I click "/api/v1/Articles/AsSimpleJson"
    And I put valid articleId on current wiki as "id"
    Then I want to see successful response