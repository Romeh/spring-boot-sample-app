Feature: the health can be retrieved
  Scenario: client makes call to GET /health
    When the client calls /health
    Then the client receives response status code of 200