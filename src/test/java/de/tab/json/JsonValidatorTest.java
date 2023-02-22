package de.tab.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.tab.json.model.Antrag;
import de.tab.json.model.AusgabenBerechnungenPauschalbetrag;
import de.tab.json.model.AusgabenBerechnungenVKO;
import de.tab.json.model.Berechnungsmodell;
import de.tab.json.validator.JsonValidationErrorResult;
import de.tab.json.validator.JsonValidator;
import de.tab.json.validator.JsonValidatorHelper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@OpenAPIDefinition(info = @Info(title = "JsonValidator API", version = "1.0", description = "JsonValidator"))
public class JsonValidatorTest {
    final String JSON_SCHEME_PATH = "./json/scheme/";
    final String JSON_EXT = ".json";

    @Test
    void createSchemesAntrag() {
       assertTrue(JsonValidatorHelper.deleteSchemeFiles("Antrag"));

       try {
            JsonNode scheme = JsonValidator.buildJsonSchemeCombine(Antrag.class);            // create scheme based on standard contract class
            JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Antrag.class.getSimpleName() + JSON_EXT,
            		scheme);

            JsonNode ausgabenBerechnungenVKOScheme = JsonValidator.buildJsonSchemeCombine(AusgabenBerechnungenVKO.class);        // substitute standard class with special class
            JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + AusgabenBerechnungenVKO.class.getSimpleName() + JSON_EXT,
                    ausgabenBerechnungenVKOScheme);
            JsonValidator.substituteScheme(scheme, AusgabenBerechnungenVKO.class, ausgabenBerechnungenVKOScheme);
            JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Antrag.class.getSimpleName() + "_" + AusgabenBerechnungenVKO.class.getSimpleName() + JSON_EXT,
                    scheme);

           scheme = JsonValidator.buildJsonSchemeCombine(Antrag.class);            // create scheme based on standard contract class
           JsonNode ausgabenBerechnungenPauschalbetragscheme = JsonValidator.buildJsonSchemeCombine(AusgabenBerechnungenPauschalbetrag.class);        // substitute standard class with special class
           JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + AusgabenBerechnungenPauschalbetrag.class.getSimpleName() + JSON_EXT,
                   ausgabenBerechnungenPauschalbetragscheme);
           JsonValidator.substituteScheme(scheme, AusgabenBerechnungenPauschalbetrag.class, ausgabenBerechnungenPauschalbetragscheme);
           JsonValidatorHelper.writeJsonToFile(JSON_SCHEME_PATH + Antrag.class.getSimpleName() + "_" + AusgabenBerechnungenPauschalbetrag.class.getSimpleName() + JSON_EXT,
                   scheme);
       }
       catch (IOException ex) {

       }

        assertTrue(JsonValidatorHelper.countSchemeFiles("Antrag") == 3);
    }

    @Test
    void createJsonAntrag() {
        Antrag antrag = new Antrag();
        antrag.setId(new Long("6"));
        antrag.setFoerderprogramm("m-test");
        antrag.setFoerdergegenstand("fg-abr");
        antrag.setEmail("Rene.Hennecke@aufbaubank.de");

        AusgabenBerechnungenVKO vko = new AusgabenBerechnungenVKO();
        vko.setSummeAufteilungenGesamt(new BigDecimal("10700"));
        vko.setSummeAufteilungenGesamtBewilligt(new BigDecimal("10700"));
        vko.setSummeFinanzierungenGesamt(new BigDecimal("9630"));
        vko.setSummeFinanzierungenGesamtBewilligt(new BigDecimal("9630"));

        vko.setBerechnungsmodell(Berechnungsmodell.VKO);

        antrag.setAusgabenBerechnungen(vko);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new Jdk8Module());
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(antrag);

            JsonValidatorHelper.writeJsonToFile("./json/json/Antrag_AusgabenBerechnungenVKO.json", json);
        }
        catch (Exception ex) {

        }
    }

    @Test
    void validateAntrag() {
        JsonValidationErrorResult result = JsonValidationErrorResult.getInstance();

        // Schema holen
        JsonNode scheme = JsonValidator.buildJsonSchemeCombine(Antrag.class);
        JsonNode ausgabenBerechnungenVKOScheme = JsonValidator.buildJsonSchemeCombine(AusgabenBerechnungenVKO.class);
        scheme = JsonValidator.substituteScheme(scheme, AusgabenBerechnungenVKO.class, ausgabenBerechnungenVKOScheme);

        try {
            JsonNode json = JsonValidatorHelper.loadJson("Antrag_AusgabenBerechnungenVKO");
            result = JsonValidator.validateJson(scheme, json);
        }
        catch (Exception ex) {
            result.setErrorCode(-1002);
            result.setDescription(ex.getMessage());
            result.setException(ex);
        }


        assertTrue(result.success());


    }

    @Test
    void checkEMail() {
        List<String> emails = new ArrayList();
        emails.add("user@domain.com");
        emails.add("user@domain.co.in");
        emails.add("user1@domain.com");
        emails.add("user.name@domain.com");
        emails.add("user#@domain.co.in");
        emails.add("user@domaincom");
        emails.add("Rene.Hennecke@aufbaubank.de");
        emails.add("Rene.Henneckeaufbaubank.de");
//Invalid emails
        emails.add("user#domain.com");
        emails.add("@yahoo.com");

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        for(String email : emails){
            Matcher matcher = pattern.matcher(email);
            System.out.println(email +" : "+ matcher.matches());
        }
    }
}
