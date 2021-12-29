Feature: Pet Store Application

  Scenario: user should be able to login to pet store application.
  #Start
    Given the user enters credentials
    When clicks on login
    Then the user should be able to login successfully.
   #End
    
  Scenario: user should be able to add pets to store
    #Start
    Given the user selects the pet
    When clicks on Add to store
    Then the user should be able to add successfully.
    #End

  Scenario: user should be able to update name or ID of pet.
   #Start
    Given the user selects the pet
    When User updates the name or ID of pet
    And clicks on save
    Then the user should be able to update successfully.
     #End

  Scenario: user should be able to find pet by ID
   #Start
    Given the user enters pet ID in search bar
    When clicks on search
    Then the user should be able to view pets according to ID's entered
     #End

  Scenario: user should be able to remove pets from store.
   #Start
    Given the user selects the pet
    When clicks on Delete
    Then the user should be able to remove pets from store successfully
     #End

  Scenario: user should be able to place the order.
   #Start
    Given the user selects billing address
    When User selects the payment mode
    And clicks on place order
    Then the user should be able to order successfully.
     #End

  Scenario: user should be able to view placed orders.
   #Start
    Given the user view orders
    Then the user should be able to view orders successfully
    #End

  Scenario: user should be able to validate deleted ID.
   #Start
     Given user selects deleted ID
     When if the deleted ID is not present
     Then user should be able to able to validate response.
    #End