package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClassForAPI {
      static RequestSpecification reqSpec;
	public static Response response;

	public static void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);

	}

	public void pathParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
	}

	public void queryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}

	public void addBody(Object payLoad) {
		reqSpec = reqSpec.body(payLoad);
	}

	public void addBody(String payLoad) {

		reqSpec = reqSpec.body(payLoad);
	}

	public void addheaders(List<Header> h) {
		Headers headers = new Headers(h);
		reqSpec = RestAssured.given().headers(headers);
	}

	public static Response requestType(String reqType, String endPoint) {
		switch (reqType) {
		case "GET":
			response = reqSpec.log().all().get(endPoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endPoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endPoint);
			break;
		case "DELETE":
			response = reqSpec.delete(endPoint);
			break;

		default:
			break;
		}
		return response;
	}

	public void formData(String formDataKey,String filePath) throws IOException {
//		File file = new File(""+getPropertyFileValue(filePath)+"");
//		 reqSpec = reqSpec.multiPart(formDataKey, new File(""+getPropertyFileValue(filePath)+""));
		 reqSpec = reqSpec.multiPart(formDataKey, new File(filePath));
		 
	}

	public  static int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}

	public static ResponseBody getResBody(Response response) {
		ResponseBody body = response.getBody();
		return body;
	}

	public String resBodyAsString(Response response) {
		String asString = getResBody(response).asString();
		return asString;
	}

	public static String getResBodyAsPrettyString(Response response) {
		String asPrettyString = getResBody(response).asPrettyString();
		return asPrettyString;
	}

	 public static String getPropertyFileValue(String key) throws IOException {
	 FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
	 Properties properties = new Properties();
	 properties.load(stream);
	 String name = properties.getProperty(key);
	 return name;
	
	 }
//	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
//		Properties properties = new Properties();
//		properties.load(new FileInputStream(System.getProperty("user.dir") + "\\config.properties"));
//		Object object = properties.get(key);
//		String obj = (String) object;
//		return obj;
//	}
	


	public static void basicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);

	}

}
