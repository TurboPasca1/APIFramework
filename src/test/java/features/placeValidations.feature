Feature: Validating Place API's

@AddPlace @Regression
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>"  "<language>"  "<address>"
    When user calls "addPlaceAPI" with "POST" HTTP request
    Then the API call got successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

  Examples:
    | name    | language  |   address           |
    |AAhouse  | English   | World cross center  |
#    |BBhouse  | Spanish   | Sea cross center    |

@DeletePlace @Regression
  Scenario: Verify if delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" HTTP request
    Then the API call got successful with status code 200
    And "status" in response body is "OK"