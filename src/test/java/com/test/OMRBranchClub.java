package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClassForAPI;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_output_Pojo;
import com.pojo.ChangeProfilePic_Output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.GetAllAddress_Output_Pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OMRBranchClub extends BaseClassForAPI {
	String logtoken;
	String addressId;

	@Test 
	public void login() throws FileNotFoundException, IOException {
		addHeader("accept", "apploication/json");
		basicAuth(getPropertyFileValue("userName"), getPropertyFileValue("password"));
		Response response = requestType("POST", Endpoints.POSTMANBASICAUTHLOGIN);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		logtoken = login_Output_Pojo.getData().getLogtoken();
		String first_name = login_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, "Elavarasan", "Verify firstName");
		Assert.assertEquals(statusCode, 200, "statusCode");
	} 

	@Test(priority = 2)
	public void addAddress() {
		List<Header> h = new ArrayList();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addheaders(h);
		AddAddress_Input_Pojo address = new AddAddress_Input_Pojo("Jacobss", "S", "8144222243", "apartment", 35, 3659,
				101, "600012", "Chennai", "Office");
		addBody(address);
		Response response = requestType("POST", Endpoints.ADDADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		AddAddress_output_Pojo addAddress_output_Pojo = response.as(AddAddress_output_Pojo.class);
		int address_id = addAddress_output_Pojo.getAddress_id();
		System.out.println(address_id);
		addressId = String.valueOf(address_id);
		String message = addAddress_output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(statusCode, 200, "statusCode");
		Assert.assertEquals(message, "Address added successfully", "Verify address added successfully");
	}

	@Test(priority = 3)
	public void updateAddress() {
		List<Header> h = new ArrayList();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addheaders(h); 

		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(addressId, "Jacob", "S",
				"8144222243", "apartment", 35, 3659, 101, "600012", "Chennai", "Home");
		addBody(updateAddress_Input_Pojo);
		Response response = requestType("PUT", Endpoints.UPDATEADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		AddAddress_output_Pojo updateAddress_Output_Pojo = response.as(AddAddress_output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(statusCode, 200, "statusCode");
		Assert.assertEquals(message, "Address updated successfully", "Verify address updated successfully");
	}

	@Test(priority = 4)
	public void deleteAddress() {
		List<Header> h = new ArrayList();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addheaders(h);

		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(addressId);
		addBody(deleteAddress_Input_Pojo);
		Response response = requestType("DELETE", Endpoints.DELETEADDRESS);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		UpdateAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = deleteAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(statusCode, 200, "statusCode");
		Assert.assertEquals(message, "Address deleted successfully", "Verify address deleted successfully");
	}

	// (priority = 5)
	@Test(priority = 5)
	public void getUserAddress() {
		List<Header> h = new ArrayList();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		addheaders(h);
		Response response = requestType("GET", Endpoints.GETADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		GetAllAddress_Output_Pojo getAllAddress_Output_Pojo = response.as(GetAllAddress_Output_Pojo.class);
		String message = getAllAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(statusCode, 200, "statusCode");
		Assert.assertEquals(message, "OK", "Verify OK");
	}

	@Test(priority = 6)
	public void changeProfilePic() throws FileNotFoundException, IOException {
		List<Header> h = new ArrayList();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addheaders(h);

		formData("profile_picture", getPropertyFileValue("picture"));
		Response response = requestType("POST", Endpoints.CHANGEPROFILEPIC);

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "statusCode");

		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);

		String message = changeProfilePic_Output_Pojo.getMessage();
		System.out.println(message);

		Assert.assertEquals(message, "Profile updated Successfully", "Verify Profile updated Successfully");

	}

}
