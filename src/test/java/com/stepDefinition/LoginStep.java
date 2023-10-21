package com.stepDefinition;

import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClassForAPI;
import com.endpoints.Endpoints;
import com.pojo.Login_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LoginStep extends BaseClassForAPI {
	static String logtoken;
	 static Response response;

	@Given("User add header")
	public void userAddHeader() {
		addHeader("accept", "apploication/json");
	}
 
	@Given("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws IOException {
		basicAuth(getPropertyFileValue("userName"), getPropertyFileValue("password"));
	}

	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String post) {
	 response = requestType(post, Endpoints.POSTMANBASICAUTHLOGIN);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

	} 

	@Then("User verify the login response body firstName present in {string} and get the logtoken")
	public void userVerifyTheLoginResponseBodyFirstNamePresentInAndGetTheLogtoken(String firstName) {
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		logtoken = login_Output_Pojo.getData().getLogtoken();
		String first_name = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals("Verify firstName", firstName, first_name); 

	}

}
