//package com.qa.post;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.HashMap;
//import org.apache.http.Header;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.testng.Assert;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import com.convertJsonTOPojo.JsonToPojo;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.generatedpojo.Roleclass;
//import com.httpclient.Restclient;
//import com.qa.util.Testutil;
//import com.responsestatus.Testbase;
//
//
//
//public class Role {
//	Testbase TB = new Testbase();
//	String baseurl;
//	String apiUrl;
//	String url;
//	Restclient conc;
//	CloseableHttpResponse closeableHttpResponse;
//
//	@BeforeSuite
//	public void pojodynamically() throws MalformedURLException, IOException {
//		JsonToPojo jsonToPojo = new JsonToPojo();
//		jsonToPojo.generatePojo("/Testjson/roleParams.json", "com.generatedpojo", "Roleclass");
//
//	}
//
//	@BeforeTest
//	public void setup() throws Exception {
//
//		TB = new Testbase();
//		baseurl = TB.getAppProperty("URL");
//		System.out.println(baseurl);
//		apiUrl = TB.getAppProperty("roleparamURL");
//		System.out.println(apiUrl);
//
//		url = baseurl + apiUrl;
//		System.out.println(url);
//
//	}
//
//	@Test
//	public void addRole() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
//		conc = new Restclient();
//
//		HashMap<String, String> headerMap = new HashMap<String, String>();
//		headerMap.put("Content-Type", "application/json");
//
//		ObjectMapper mapper = new ObjectMapper();
//		Roleclass RP = new Roleclass("Admin", true);
//		// Object to JSON
//		mapper.writeValue(new File("../MedApp-API/src/main/java/com/generatedpojo/resp.json"), RP);
//
//		// Java object to JSON in String:
//		String pojoJsonString = mapper.writeValueAsString(RP);
//		System.out.println(pojoJsonString);
//
//		closeableHttpResponse = conc.post(url, pojoJsonString, headerMap); // call the API
//
//		// Validate response from API:
//		// 1. Status code:
//		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
//		System.out.println(statusCode);
//		Assert.assertEquals(statusCode, TB.RESPONSE_STATUS_CODE_200);
//
//		// 2. JsonString:
//		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//		Object jsonObject = mapper.readValue(responseString, Object.class);
//		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
//		System.out.println(prettyJson);
//		JSONObject responseJson = new JSONObject(prettyJson);
//
//		String message = Testutil.getValueByJPath(responseJson, "/status/message");
//		System.out.println("value of message is : " + message);
//		Assert.assertEquals(message, "Role Found");
//
//		// All Headers
//		Header[] headersArray = closeableHttpResponse.getAllHeaders();
//		for (Header header : headersArray) {
//			System.out.println(header);
//		}
//
//	}
//}
