package com.convertJsonTOPojo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.BeforeMethod;

public class JsonToPojo {

	@BeforeMethod
	public String generatePojo(String path, String packageName, String className)
			throws MalformedURLException, IOException {
		String jsonPath = System.getProperty("user.dir") + path;
		File jsonFile = new File(jsonPath);
		APIfunction.createPOJOForJSON(jsonFile.toURL(), packageName, className);
		return packageName + "." + className;
	}

}
