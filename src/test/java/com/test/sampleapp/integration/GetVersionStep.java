package com.test.sampleapp.integration;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by MRomeh.
 */
public class GetVersionStep extends CucumberRoot {
    private ResponseEntity<String> response; // output

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
        response = template.getForEntity("/application/version", String.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " +
                response.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        assertThat(response.getBody(), is(version));
    }
}
