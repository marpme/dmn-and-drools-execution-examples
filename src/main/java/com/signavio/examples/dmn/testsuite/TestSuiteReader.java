package com.signavio.examples.dmn.testsuite;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.signavio.bdm.testlab.exchange.TestSuite;
import org.json.JSONObject;

public class TestSuiteReader {
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	
	// TODO: TestSuite.class is part of the bdm-test-suite-api, clarify if Signavio is publishing it
	public TestSuite parse(InputStream testSuite) {
		try {
			JsonReader reader = createTestSuiteReader(testSuite);
			JsonObject testSuiteJson = GSON.fromJson(reader, JsonObject.class);
			
			// TODO: just for this we need a whole bunch of other dependencies
			return TestSuite.fromJson(new JSONObject(testSuiteJson.toString()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private JsonReader createTestSuiteReader(InputStream testSuite) {
		return new JsonReader(new InputStreamReader(testSuite));
	}
	
}
