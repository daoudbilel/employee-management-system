@GenerateApiMessageClass
Feature: employer test feature

  Scenario Outline: Ajouter des employees
    Given utilisateur avec <firstName> et <lastName> et <mail>
    And enregistrer l'employee
    Then reponse different de null
    Examples:
      | firstName | lastName | mail      |
      | "user-31" | "u31"    | "u30@u30" |
      | "user-21" | "u21"    | "u21@u21" |
      | "user-22" | "u22"    | "u22@u22" |
