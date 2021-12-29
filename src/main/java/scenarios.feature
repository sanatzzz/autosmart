Feature: Pet Store Application

#Start
  Scenario: user should be able to login to pet store application.
    Given the user enters credentials
    When clicks on login
    Then the user should be able to login successfully.
#End
   
#Start 
  Scenario: user should be able to add pets to store
    Given the user selects the pet
    When clicks on Add to store
    Then the user should be able to add successfully.
#End

#Start 
  Scenario: user should be able to update name or ID of pet.
    Given the user selects the pet
    When User updates the name or ID of pet
    And clicks on save
    Then the user should be able to update successfully.
#End


  Scenario: user should be able to find pet by ID
    Given User navigates to shopping page
    When the user enters pet ID in search bar
    And clicks on search
    Then the user should be able to view pets according to ID's entered

  Scenario: user should be able to remove pets from store.
    Given the user selects the pet
    When clicks on Delete
    Then the user should be able to remove pets from store successfully

  Scenario: user should be able to place the order.
    Given the user selects billing address
    When User selects the payment mode
    And clicks on place order
    Then the user should be able to order successfully.

  Scenario: user should be able to view placed orders.
    Given User is logged in
    When the user view orders
    Then the user should be able to view orders successfully

  Scenario: user should be able to validate deleted ID.
     Given user is on shopping page
     When user selects deleted ID
     And if the deleted ID is not present
     Then user should be able to able to validate response.