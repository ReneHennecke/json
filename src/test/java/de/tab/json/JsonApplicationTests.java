package de.tab.json;

import com.fasterxml.jackson.databind.JsonNode;
import de.tab.json.model.Animal;
import de.tab.json.model.Dog;
import de.tab.json.model.Home;
import de.tab.json.validator.JsonValidator;
import de.tab.json.validator.JsonValidatorHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class JsonApplicationTests {
	final String JSON_SCHEME_PATH = "./json/scheme/";
	final String JSON_EXT = ".json";

	@Test
	void contextLoads() {
	}

	@Test
	void testScheme() {
		try {

			Home home = new Home();
			home.setId(1234);
			home.setAnimal(new Dog());

			JsonNode scheme = JsonValidator.buildJsonSchemeCombine(Home.class);
			//System.out.println(scheme.toPrettyString());

			JsonNode dogScheme = JsonValidator.buildJsonSchemeStandard(Dog.class);
			JsonValidator.substituteScheme(scheme, Dog.class, dogScheme);
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + JSON_EXT,
												scheme);
		}
		catch (IOException ex) {

		}

	}

}
