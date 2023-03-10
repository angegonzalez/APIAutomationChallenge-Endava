@Authentication
Feature: User wants create a session

  Scenario Outline: User successfully creates a session
    Given the user wants to do authentication with credentials "<username>" and "<password>"
    When the user creates the request token
    And the user validates the request token
    Then the user successfully creates a session

    Examples:
      | username     | password       |
      | angegonzalez | HKPBZ89p4apr7v |

  Scenario Outline: User can't create a session due to an unverified token request
    Given the user wants to do authentication with credentials "<username>" and "<password>"
    When the user creates the request token
    And the user does not validate the request token
    Then the user can't create a session

    Examples:
      | username     | password       |
      | angegonzalez | HKPBZ89p4apr7v |