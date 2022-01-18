Feature: Pet Store Application

  Scenario: user should be able to delete pet from store
    #Start
    Given the user adds pet
    And the user finds pet~name
    Then the user deletes pet
  #End
  
  Scenario: user should be able to update name of pet.
    #Start
    Given the user adds pet
    And the user finds pet~id
    Then the user updates pet
  #End
  
  Scenario: user should be able to find pet by Id
    #Start
    Given the user adds pet
    And the user finds pet~id
  #End
  
  Scenario: user should be able to update user.
    #Start
    Given create user list
    And get~user username
    Then updates user
  #End
  
  Scenario: user should be able to place the order for pet.
    #Start
    Given the user adds pet
    And the user finds pet~id
    And user places pet order
  #End
  
   Scenario: user should be able to find purchase the order for pet.
    #Start
    Given the user adds pet
    And the user finds pet~id
    And user places pet order
    Then user finds purchase order~id
  #End
  
   Scenario: user should be able to delete purchse order for pet.
    #Start
    Given the user adds pet
    And the user finds pet~id
    And user places pet order
    Then user delete purchase order~id
  #End
