package com.convertJsonTOPojo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.NoopAnnotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class APIfunction {

	public static void createPOJOForJSON(URL path, String package1, String className) throws IOException {
		// TODO Auto-generated method stub
		JCodeModel codeModel = new JCodeModel();
		URL source = path;
		RuleFactory ruleFactory = new RuleFactory();
		ruleFactory.setAnnotator(new NoopAnnotator());
		ruleFactory.setGenerationConfig(new DefaultGenerationConfig() {

			@Override
			public boolean isIncludeConstructors() {
				return true;
			}

			@Override
			public boolean isIncludeAdditionalProperties() {
				return false;
			}

			@Override
			public boolean isGenerateBuilders() { // set config option by overriding method
				return false;
			}

			@Override
			public boolean isIncludeToString() {
				return false;
			}

			@Override
			public boolean isIncludeHashcodeAndEquals() {
				return false;
			}

			public SourceType getSourceType() {

				return SourceType.JSON;
			}
		});

		SchemaMapper mapper = new SchemaMapper(ruleFactory, new SchemaGenerator());
		mapper.generate(codeModel, className, package1, source);
		File outputPojoDirectory = new File("../MedApp-API/src/test/java");
		codeModel.build(outputPojoDirectory);

	}
}