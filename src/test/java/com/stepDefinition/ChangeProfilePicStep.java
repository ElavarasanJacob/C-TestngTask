package com.stepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClassForAPI;
import com.endpoints.Endpoints;
import com.pojo.ChangeProfilePic_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class ChangeProfilePicStep extends BaseClassForAPI {
	Response response;
	
@Given("User should add headers and bearer authorization and multipart\\/form-data for accessing change profile picture endpoints")
public void userShouldAddHeadersAndBearerAuthorizationAndMultipartFormDataForAccessingChangeProfilePictureEndpoints() throws IOException {
	List<Header> h = new ArrayList();
	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Authorization", "Bearer " + LoginStep.logtoken);
	Header h3 = new Header("Content-Type", "multipart/form-data");
	h.add(h1);
	h.add(h2);
	h.add(h3);
	addheaders(h);
	formData("profile_picture", getPropertyFileValue("picture"));

}

@When("User send {string} request to change profile Picture")
public void userSendRequestToChangeProfilePicture(String post) {
	response = requestType(post, Endpoints.CHANGEPROFILEPIC);
}

@Then("User should verify the response body message for change profile picture as {string}")
public void userShouldVerifyTheResponseBodyMessageForChangeProfilePictureAs(String expProfileChangedMSg) {
	ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
	String message = changeProfilePic_Output_Pojo.getMessage();
	System.out.println(message);
	Assert.assertEquals("Verify Profile updated Successfully",expProfileChangedMSg,message);



}

	
}
