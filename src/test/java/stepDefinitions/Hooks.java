package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinitions m = new StepDefinitions();
        if (StepDefinitions.place_id == null) {
            m.addPlacePayloadWith("Pista", "Magyar", "Hungary");
            m.userCallsWithHTTPRequest("addPlaceAPI", "POST");
            m.verifyPlace_IdCreatedMapsToUsing("Pista", "getPlaceAPI");
        }
    }
}
