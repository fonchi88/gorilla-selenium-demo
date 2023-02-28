Feature: Players Top Five Stats
  Validate that Top 5 players lists shows as expected

  Scenario: Yesterdays Top Scorers are listed
    When Hit NBA stats page on my computer
    Then Yesterdays Leaders is selected
    And Top Five scorers are listed
    And Names are not empty