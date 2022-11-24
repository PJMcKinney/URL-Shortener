Feature: URL Shortener API Tests
  
  Background: Set base url
    * url 'http://localhost:8080/url'

  Scenario: Test returnAllEntries endpoint
    Given path '/allEntries'
    When method GET
    Then status 200

  Scenario: Test saveURLRelationship endpoint
    Given path ''
    And request
    """
    {
      "longURL": "https://www.youtube.com/watch?v=9SGDpanrc8U&ab_channel=Amigoscode"
    }
    """
    When method POST
    Then status 201
    Then match response == "#object"
    Then match response.response.longURL == "https://www.youtube.com/watch?v=9SGDpanrc8U&ab_channel=Amigoscode"

  Scenario: Test redirectToLongURL endpoint
    Given path '5bf032c69'
    When method GET
    Then status 200
