package com.test.sampleapp.integration;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by MRomeh.
 */
public class GetHealthStep extends CucumberRoot {
    private ResponseEntity<String> response; // output

    @When("^the client calls /health$")
    public void the_client_issues_GET_health() throws Throwable {
        response = template.getForEntity("/actuator/health", String.class);
    }

    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " +
                response.getBody(), currentStatusCode.value(), is(statusCode));
    }

}
