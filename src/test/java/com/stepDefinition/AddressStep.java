package com.stepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClassForAPI;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.GetAllAddress_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;

/**
 * @author Elavarasan
 * @Description AddressStep for application through API
 * @Date 18-07-2022
 */
public class AddressStep extends BaseClassForAPI {
	static String addressId;
	static Response response;

	/**
	 * @Description Add headers for authorization for address module
	 * @Date 18-07-2022
	 */
	@Given("User should add headers and bearer authorization for accessing address endpoints")
	public void userShouldAddHeadersAndBearerAuthorizationForAccessingAddressEndpoints() {
		List<Header> h = new ArrayList();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addheaders(h);
	} 

	/**
	 * @Description Add request body to add new address
	 * @Date 18-07-2022
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 */
	@When("User should add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userShouldAddRequestBodyForAddNewAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		Integer state1 = Integer.valueOf(state);
		Integer city1 = Integer.valueOf(city);
		Integer country1 = Integer.valueOf(country);

		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, state1, city1, country1, zipcode, address, address_type);
		addBody(addAddress_Input_Pojo);
	}

	/**
	 * @Description Send request for adding new address
	 * @Date 18-07-2022
	 * @param string
	 */
	@When("User send {string} request to add new address")
	public void userSendRequestToAddNewAddress(String post) {
		response = requestType(post, Endpoints.ADDADDRESS);

	}

	/**
	 * @Description Verifying response body message for added address
	 * @Date 18-07-2022
	 * @param expAddressAddedMsg
	 */
	@Then("User should verify the response body message for create address as {string} and get AddressId")
	public void userShouldVerifyTheResponseBodyMessageForCreateAddressAsAndGetAddressId(String expAddressAddedMsg) {
		AddAddress_output_Pojo addAddress_output_Pojo = response.as(AddAddress_output_Pojo.class);
		int address_id = addAddress_output_Pojo.getAddress_id();
		System.out.println(address_id);
		addressId = String.valueOf(address_id);
		String message = addAddress_output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Verify address added successfully", expAddressAddedMsg, message);
	}

	@When("User add request body for update address {string},{string},{string},{string},{string},{string},{string},{string},{string} and{string}")
	public void userAddRequestBodyForUpdateAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {

		Integer state1 = Integer.valueOf(state);
		Integer city1 = Integer.valueOf(city);
		Integer country1 = Integer.valueOf(country);
		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(addressId, first_name,
				last_name, mobile, apartment, state1, city1, country1, zipcode, address, address_type);
		addBody(updateAddress_Input_Pojo);
	}

	@When("User send {string} request to update existing address")
	public void userSendRequestToUpdateExistingAddress(String put) {
		response = requestType(put, Endpoints.UPDATEADDRESS);

	}

	@Then("User should verify the response body message for updated address as {string}")
	public void userShouldVerifyTheResponseBodyMessageForUpdatedAddressAs(String expUpdateMsg) {
		AddAddress_output_Pojo updateAddress_Output_Pojo = response.as(AddAddress_output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Verify address updated successfully", expUpdateMsg, message);
	}

	@When("User add request body for delete address")
	public void userAddRequestBodyForDeleteAddress() {
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(addressId);
		addBody(deleteAddress_Input_Pojo);
	}

	@When("User send {string} request to delete address")
	public void userSendRequestToDeleteAddress(String delete) {
	 response = requestType(delete, Endpoints.DELETEADDRESS);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	@Then("User should verify the delete address response body message as {string}")
	public void userShouldVerifyTheDeleteAddressResponseBodyMessageAs(String expDeleteMsg) {
		UpdateAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = deleteAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Verify address deleted successfully", expDeleteMsg, message);

	}

	@When("User send {string} request to get address")
	public void userSendRequestToGetAddress(String get) {
		response = requestType(get, Endpoints.GETADDRESS);
	}

	@Then("User should verify the get address response body message as {string}")
	public void userShouldVerifyTheGetAddressResponseBodyMessageAs(String expGetAddressScuccessMsg) {
		GetAllAddress_Output_Pojo getAllAddress_Output_Pojo = response.as(GetAllAddress_Output_Pojo.class);
		String message = getAllAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Verify OK", expGetAddressScuccessMsg, message);

	}

}
