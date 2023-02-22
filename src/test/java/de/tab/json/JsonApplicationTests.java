package de.tab.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.networknt.schema.ValidationMessage;
import de.tab.json.model.Animal;
import de.tab.json.model.Cat;
import de.tab.json.model.Dog;
import de.tab.json.model.Home;
import de.tab.json.validator.JsonValidationErrorResult;
import de.tab.json.validator.JsonValidator;
import de.tab.json.validator.JsonValidatorHelper;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

//import static org.reflections.ReflectionUtils.Resources;
import static org.reflections.scanners.Scanners.SubTypes;

@SpringBootTest
class JsonApplicationTests {
	final String JSON_SCHEME_PATH = "./json/scheme/";
	final String JSON_EXT = ".json";

	@Test
	void contextLoads() {
	}

	@Test
	void testDog() {
		try {
			JsonNode scheme = JsonValidator.buildJsonSchemeCombine(Home.class);            // create scheme based on standard contract class
			//System.out.println(scheme.toPrettyString());
			//JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + JSON_EXT,
			//		scheme);

			JsonNode dogScheme = JsonValidator.buildJsonSchemeCombine(Dog.class);        // substitute standard class with special class
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Dog.class.getSimpleName() + JSON_EXT,
					dogScheme);
			JsonValidator.substituteScheme(scheme, Dog.class, dogScheme);
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + "_" + Dog.class.getSimpleName() + JSON_EXT,
					scheme);
/*
			Dog dog = new Dog();

			Home home = new Home();
			home.setId(1234);
			home.setAnimal(dog);
			home.setDescription("AB");

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());
			mapper.registerModule(new JavaTimeModule());
			String json = mapper.writeValueAsString(home);

			JsonValidatorHelper.writeJsonToFile("./json/json/home_dog.json", json);
*/
			JsonValidationErrorResult result = JsonValidationErrorResult.getInstance();
			try {
				JsonNode scheme1 = JsonValidatorHelper.loadScheme("Home_Dog");    // load substitute json scheme
				JsonNode node = JsonValidatorHelper.loadJson("home_dog");         // load json file
				result = JsonValidator.validateJson(scheme1, node);                      // validate
			} catch (Exception ex) {
				result.setErrorCode(-1002);
				result.setDescription(ex.getMessage());
				result.setException(ex);
			}

			System.out.println(result.success());
			if (!result.success()) {
				for (ValidationMessage m : result.getErrorList())
					System.out.println(m);
			}
		} catch (Exception ex) {

		}
	}

	@Test
	void testCat() {
		try {
			JsonNode scheme = JsonValidator.buildJsonSchemeCombine(Home.class);            // create scheme based on standard contract class
			//System.out.println(scheme.toPrettyString());
			//JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + JSON_EXT,
			//		scheme);

			JsonNode catScheme = JsonValidator.buildJsonSchemeCombine(Cat.class);        // substitute standard class with special class
			JsonValidator.substituteScheme(scheme, Cat.class, catScheme);
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + "_" +
							                     Cat.class.getSimpleName() + JSON_EXT,
												 scheme);
/*
			Cat cat = new Cat();
			cat.setWeight(new BigDecimal("8.564"));

			Home home = new Home();
			home.setId(3456);
			home.setAnimal(cat);
			home.setDescription("XYZ");

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());
			mapper.registerModule(new JavaTimeModule());
			String json = mapper.writeValueAsString(home);

			JsonValidatorHelper.writeJsonToFile("./json/json/home_cat.json", json);
*/
			JsonValidationErrorResult result = JsonValidationErrorResult.getInstance();
			try {
				JsonNode schemeLoaded = JsonValidatorHelper.loadScheme("Home_Cat");    // load substitute json scheme
				JsonNode nodeLoaded = JsonValidatorHelper.loadJson("home_cat");         // load json file
				result = JsonValidator.validateJson(schemeLoaded, nodeLoaded);                      // validate
			} catch (Exception ex) {
				result.setErrorCode(-1002);
				result.setDescription(ex.getMessage());
				result.setException(ex);
			}

			System.out.println(result.success());
			if (!result.success()) {
				for (ValidationMessage m : result.getErrorList())
					System.out.println(m);
			}
		} catch (Exception ex) {

		}
	}

	@Test
	void restReflections() {
		//String path = "C:/dev/portal/json/target/classes/scheme/";
		final String path = "C:/dev/portal/json/src/main/resources/scheme/";

		Reflections reflections = new Reflections("de.tab.json.model");
		Set<Class<?>> subTypes =	reflections.get(SubTypes.of(Animal.class).asClass());

		subTypes.forEach(s -> {
			System.out.println(s.getSimpleName());

			try {
				Class<?> clazz = Class.forName(s.getName());

				//Constructor<?> ctor = clazz.getConstructor();
				//Object object = ctor.newInstance(); // args as string

				JsonNode scheme = JsonValidator.buildJsonSchemeCombine(clazz);
				JsonValidatorHelper.writeJsonToFile(path + clazz.getSimpleName() + ".json", scheme);

				int i = 0;
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		});
	}

	@Test
	void testLoad() {
		String path = ""; //JsonValidatorHelper.getResourcePath(Dog.class);
		if (!path.isEmpty()) {
			System.out.println(path);


//			try {
//
//				//JsonNode scheme = JsonValidatorHelper.loadSchemeFromResource(path);
//
//				int i = 0;
//			}
//			catch (IOException ex) {
//				System.out.println(ex.getMessage());
//			}
		}
	}

	@Test
	void testSave() {
		String path = "C:/dev/portal/json/target/classes/scheme/Dog.json";

		try {
			JsonNode scheme = JsonValidator.buildJsonSchemeCombine(Home.class);
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + JSON_EXT,
					scheme);

			JsonNode dogScheme = JsonValidator.buildJsonSchemeCombine(Dog.class);        // substitute standard class with special class
			JsonValidatorHelper.writeJsonToFile(path, dogScheme);

			JsonValidator.substituteScheme(scheme, Dog.class, dogScheme);
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Home.class.getSimpleName() + "_" + Dog.class.getSimpleName() + JSON_EXT,
					scheme);
		}
		catch (IOException ex) {

		}
	}

	@Test
	void testDog2() {
		try {
			JsonNode dogScheme = JsonValidator.buildJsonSchemeCombine(Dog.class);        // substitute standard class with special class
			JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Dog.class.getSimpleName() + JSON_EXT, dogScheme);

		}
		catch (IOException ex) {

		}
	}
}
