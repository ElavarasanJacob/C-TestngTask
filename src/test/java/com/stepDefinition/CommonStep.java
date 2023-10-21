package com.stepDefinition;

import org.junit.Assert;

import com.base.BaseClassForAPI;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;

/**
 * @author Elavarasan
 * @Description CommonStep for status code
 * @Date 18-07-2022
 * 
 */
public class CommonStep extends BaseClassForAPI {
	/**
	 * @Description Verifying response status code for all process in application
	 * @Date 18-07-2022
	 * @param expStatusCode
	 */
	@Then("User should verify the status code is {int}")
	public void userShouldVerifyTheStatusCodeIs(int expStatusCode) {
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals("Verify statusCode", expStatusCode, statusCode);

	} 
 
}
