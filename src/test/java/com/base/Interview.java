package com.base;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.endpoints.Endpoints;
import com.github.dockerjava.api.model.Endpoint;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Interview extends BaseClassForAPI {
	
	
	
		String id;
		String customerId;

		@Test
		public  void getPoint() throws ParseException {
			
			addHeader("accept", "application/json");
//			queryParam("channel", "web");

			Response requestType = requestType("GET", Endpoints.CUSTOMERINTERVIEW);
			int statusCode = requestType.statusCode();
			System.out.println("statusCode : "+statusCode);
			ResponseBody body = requestType.getBody();

			String asPrettyString = body.asPrettyString();
			System.out.println(asPrettyString);

			JSONParser parser = new JSONParser();
			Object parse = parser.parse(asPrettyString);

			JSONObject object = (JSONObject) parse;

			Object object2 = object.get("result");

			JSONObject resultObj = (JSONObject) object2;
			Object sessionObject = resultObj.get("session");

			JSONObject session = (JSONObject) sessionObject;
			id = session.get("id").toString();
			System.out.println("id : " + id);
			customerId = session.get("customer_id").toString();
			System.out.println("customerId : " + customerId);
			
			

		}
		

}
