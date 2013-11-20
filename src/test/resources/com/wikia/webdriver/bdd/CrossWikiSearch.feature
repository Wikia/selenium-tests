@CrossWikiSearch
Feature: As a user I want to have cross wikia search functionality
  In order to easily find wikias about topic I'm interested in

  Scenario Outline: Cross-Wikia search exact match
    Given I am on wikia global
    When I search for "<phrase>"
    Then I see following search results first:
      | Muppet Wiki |
    When I click "Muppet Wiki"
    Then I should see "Muppet Wiki"
    And I should see "Home"
  Examples:
    | phrase    |
    | mupPet      |
    | Muppet Wiki |
    | Muppets     |
    | Mu#$%$ppets |

