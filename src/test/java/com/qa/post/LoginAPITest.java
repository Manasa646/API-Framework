
package com.qa.post;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.util.ClassUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.convertJsonTOPojo.JsonToPojo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.httpclient.Restclient;
import com.qa.util.Testutil;
import com.responsestatus.GenerateExtentReport;
import com.responsestatus.Testbase;





public class LoginAPITest extends GenerateExtentReport {

	Testbase TB = new Testbase();
	String baseurl;
	String apiUrl;
	String url;
	Restclient conc;
	CloseableHttpResponse closeableHttpResponse;
	String className = "";
	IFolder project;

	@BeforeSuite
	public void pojodynamically() throws MalformedURLException, IOException, InterruptedException, CoreException {
		JsonToPojo jsonToPojo = new JsonToPojo();
		className = jsonToPojo.generatePojo("/Testjson/login.json", "generatepojo", "Loginclass");
		System.out.println(className);
		Thread.sleep(10000);
		/*
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot(); 
		IProject project = workspaceRoot.getProject("/home/cnt-dev/Documents/API-WP"); 
		project.open(null); 
		IFolder folder = project.getFolder("/home/cnt-dev/Documents/API-WP/MedApp-API"); 
		//IFolder folder = new IFolder(className);
		folder.refreshLocal(IResource.DEPTH_INFINITE,null); 
		System.out.println("Successfully refreshed."); 
*/
		
	}

	@BeforeMethod
	public void setup() throws Exception {

		TB = new Testbase();
		baseurl = TB.getAppProperty("URL");
		System.out.println(baseurl);
		apiUrl = TB.getAppProperty("serviceURL");
		System.out.println(apiUrl);

		url = baseurl + apiUrl;
		System.out.println(url);
		

	}

	@Test
	public void validLogin() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		test = extent.createTest("validLogin", "Check with Valid Details");
		Assert.assertTrue(true);

		conc = new Restclient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		String pojoJsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("TestCase");
		// Reading json data from file
		try {

			FileInputStream fileReader = new FileInputStream(
					"/home/cnt-dev/Documents/API-WP/MedApp-API/Testjson/login.json");
			//System.out.println("class :::::::::::::::::::::::" + className);
			/*File file = new File("../MedApp-API/src/test/java/generatedpojo/Loginclass.java");
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		    DiagnosticCollector<JavaFileObject> dc;
		    dc = new DiagnosticCollector<JavaFileObject>();

		    StandardJavaFileManager sjfm;
		    sjfm = compiler.getStandardFileManager(dc, null, null);

		    Iterable<? extends JavaFileObject> fileObjects;
		    
			fileObjects = sjfm.getJavaFileObjects(file);

		    compiler.getTask(null, sjfm, dc, null, null, fileObjects).call();

			sjfm.close();
			
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });

				Class<?> clazz = classLoader.loadClass(className);
			*/
			
			Class<?> clazz = ClassUtil.findClass(className);
			System.out.println("GeneratedClass" + clazz);
			Object p = mapper.readValue(fileReader, clazz);
			Field[] fields = p.getClass().getDeclaredFields();
			for (Field f : fields) {
				String fieldName = f.getName();
				PropertyDescriptor pd;

				pd = new PropertyDescriptor(fieldName, p.getClass());
				// Class<?> pojo = p.getClass();
				//System.out.println("Object :::::::::::::::::::::::::::::::::" + p.getClass());
				Field field = p.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(p, pd.getReadMethod().invoke(p));
				System.out.println(field.getName());
				System.out.println(pd.getReadMethod().invoke(p));

			}
			pojoJsonString = mapper.writeValueAsString(p);
			System.out.println(pojoJsonString);
		} catch (JsonParseException ex) {
			ex.printStackTrace();
		} catch (JsonMappingException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Object to JSON
		// mapper.writeValue(new
		// File("../MedApp-API/src/main/java/com/generatedpojo/resp.json"), pojoclass);

		// Java object to JSON in String:

		closeableHttpResponse = conc.post(url, pojoJsonString, headerMap); // call the API

		test.log(Status.INFO, "POST API passed within the request");
		test.log(Status.INFO, "Payload json added in the request");
		test.log(Status.INFO, "Headers added to the request");
		test.log(Status.INFO, "request sent successfully");

		// Validate response from API:
		// 1. Status code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println(statusCode);
		Assert.assertEquals(statusCode, TB.RESPONSE_STATUS_CODE_200);

		test.log(Status.PASS, "Response json received");
		test.log(Status.INFO, "status code :  " + statusCode + "");

		// 2. JsonString:
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		Object jsonObject = mapper.readValue(responseString, Object.class);
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		System.out.println(prettyJson);
		JSONObject responseJson = new JSONObject(prettyJson);
		test.log(Status.INFO, "response json : " + responseJson + "");

		// Validate Headers in Response
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		for (Header header : headersArray) {
			System.out.println(header);
			test.log(Status.INFO, "Display Response Headers" + header + "");
		}

		String message = Testutil.getValueByJPath(responseJson, "/status/message");
		System.out.println("value of message is : " + message);
		Assert.assertEquals(message, "User Found");

	}

////		@Test
////		public void invalidpassword() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
////			test = extent.createTest("invalidpassword", "check with Invalid Password");
////			Assert.assertTrue(true);
////			conc = new Restclient();
////			test.log(Status.INFO, "Login with invalid password testcase");
	////
////			HashMap<String, String> headerMap = new HashMap<String, String>();
////			headerMap.put("Content-Type", "application/json");
	////
////			ObjectMapper mapper = new ObjectMapper();
////			Loginclass Lc = new Loginclass("haripriya.p@cloudnowtech.com", "Hms@123123");
	////
////			// Object to JSON
////			mapper.writeValue(new File("../MedApp-API/src/main/java/com/generatedpojo/resp.json"), Lc);
	////
////			// Java object to JSON in String:
////			String pojoJsonString = mapper.writeValueAsString(Lc);
////			System.out.println(pojoJsonString);
	////
////			closeableHttpResponse = conc.post(url, pojoJsonString, headerMap); // call the API
	////
////			// validate response from API:
////			// 1. status code:
////			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
////			Assert.assertEquals(statusCode, TB.RESPONSE_STATUS_CODE_200);
	////
////			test.log(Status.PASS, "Response json received");
////			test.log(Status.INFO, "status code :  " + statusCode + "");
	////
////			// 2. JsonString:
////			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
////			Object jsonObject = mapper.readValue(responseString, Object.class);
////			String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
////			System.out.println(prettyJson);
////			JSONObject responseJson = new JSONObject(responseString);
////			System.out.println("The response from API is:" + responseJson);
////			test.log(Status.INFO, "response json : " + responseJson + "");
	////
////			String message = Testutil.getValueByJPath(responseJson, "/status/message");
////			System.out.println("value of message is : " + message);
////			Assert.assertEquals(message, "User Not Found");
	////
////		}
	// }
	//
}