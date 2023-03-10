@Movies
Feature: User wants to manage movies

  @details
  Scenario: User successfully gets a movie details
    Given the user has a movie id : "12"
    When the user tries to get the movie details
    Then the user successfully gets the details of that movie
    And the title of the movie is "Finding Nemo"

  @details
  Scenario: User can't get details of a movie doesn't exist
    Given the user has a movie id : "1200"
    When the user tries to get the movie details
    Then the user can't get the details of that movie
    And the status message is : "The resource you requested could not be found."

  @rating @ignore
  Scenario: User successfully rates a movie
    Given the user has a movie id : "10"
    When the user tries to rate the movie with value : 8.5
    Then the user successfully rates the movie
    And the status message is : "Success."

  @rating
  Scenario: User rate a movie with a value grater than 10
    Given the user has a movie id : "5"
    When the user tries to rate the movie with value : 11
    Then the user can't rate the movie
    And the status message is : "Value too high: Value must be less than, or equal to 10.0."