Feature: Login Component
  Validate that Top 5 players lists shows as expected

  Scenario Outline: Login with valida credentials
    When Providing a valid "<username>" and "<password>"
    Then User features are visible
    Examples:
      | username | password |
      | francisco.moreloso@gmail.com | 170202!Fa |
