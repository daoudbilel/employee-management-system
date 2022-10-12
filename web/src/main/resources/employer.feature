@GenerateApiMessageClass
Feature: employer feature

  Scenario: Afficher la listes des employees
    Given list des employees
    When Utilisateur demande list employees
    Then List afficher
    And Affichage message
