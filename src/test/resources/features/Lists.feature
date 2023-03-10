@Lists
Feature: User wants to manage lists

  @details
  Scenario: User successfully gets the details of a list
    Given the user has a list id: "50941077760ee35e1500000c"
    When the user tries to get the list details
    Then the user successfully gets the details of that list

  @details
  Scenario: User can't get the details of a list
    Given the user has a list id: "50941077760ee35e1500000x"
    When the user tries to get the list details
    Then the user can't get the details of that list
    And the status message is "The resource you requested could not be found."

  @creation
  Scenario: User successfully creates a list
    Given the user has the list information
      | name                    | description          | language |
      | This is my awesome list | Just an awesome list | en       |
    When the user tries to create the list
    Then the list is created
    And the status message is "The item/record was created successfully."

  @creation
  Scenario: User cannot create a list due to an invalid information
    Given the user does not have any list information
    When the user tries to create the list
    Then the list is not created
    And the status message is "Invalid parameters: Your request parameters are incorrect."

  @add @ignore
  Scenario: User successfully adds an item to a list
    Given the user has a list id: "8243820"
    When the user tries to add an item with id : "10" to the list
    Then the item is added to the list
    And the status message is "The item/record was updated successfully."

  @add
  Scenario: User cannot add a duplicated item to a list
    Given the user has a list id: "8243820"
    When the user tries to add an item with id : "10" to the list
    Then the item is not added to the list
    And the status message is "Duplicate entry: The data you tried to submit already exists."

  @clear @ignore
  Scenario: User successfully clear a list
    Given the user has a list id: "8243820"
    When the user tries to clear the list
    Then the list is cleared
    And the status message is "The item/record was updated successfully."

  @clear
  Scenario: User cannot clear a list
    Given the user has a list id: "82438201"
    When the user tries to clear the list
    Then the list is not cleared
    And the status message is "The resource you requested could not be found."

  @delete @ignore
  Scenario: User successfully deletes a list
    Given the user has a list id: "82438201"
    When the user tries to delete the list
    Then the list is deleted
    And the status message is "The item/record was updated successfully."

  @delete
  Scenario: User can't deletes a list because it doesn't exists
    Given the user has a list id: "824382019"
    When the user tries to delete the list
    Then the list is not deleted
    And the status message is "The resource you requested could not be found."