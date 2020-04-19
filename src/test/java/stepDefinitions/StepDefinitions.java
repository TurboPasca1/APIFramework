package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {

    RequestSpecification reqSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string}  {string}  {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} HTTP request")
    public void userCallsWithHTTPRequest(String resource, String httpMethod) {
        APIResources resourceAPI = APIResources.valueOf(resource);   //constructor will be called with value of resource which you pass
        System.out.println(resourceAPI.getResource());
        System.out.println("before:" +response);
        System.out.println("reqSpec before:" + reqSpec);
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = reqSpec
                    .when()
                    .post(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")){
            response = reqSpec
                    .when()
                    .get(resourceAPI.getResource());
        }
        System.out.println("after:" +response);
    }

    @Then("the API call got successful with status code {int}")
    public void the_API_call_got_successful_with_status_code(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
        System.out.println("place_id: "+place_id);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String expectedValue) {
        assertEquals(getJsonPath(response,key), expectedValue);
        System.out.println("place_id: "+place_id);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        System.out.println("place_id"+place_id);
        reqSpec = given()
                .spec(requestSpecification())
                .queryParam("place_id", place_id);
        userCallsWithHTTPRequest(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(expectedName, actualName);
        System.out.println("---------------end---------------");
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
        System.out.println("Delete place payload start");
        reqSpec = given()
                .spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));
        System.out.println("place_id: "+place_id);
        System.out.println("Git demo");
        System.out.println("Develop branch");
        System.out.println("Delete place payload end");
    }
}
