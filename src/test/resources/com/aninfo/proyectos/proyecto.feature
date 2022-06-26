Feature: mantequita
  Scenario: There's no mantequita
    Given The mantequita package is empty
    When I want mantequita
    Then I ask Krachitos to buy it