#@testEtablissementFeature
@GenerateApiMessageClass

Feature: Etablissemet feature

  Scenario: Ajouter des etablissements
    Given cree un etablissement
    When Enregistrer etablissement
    Then Retourner status 200 ok


