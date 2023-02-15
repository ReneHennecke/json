package de.tab.json.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonValidatorHelper {
    private static final String JSON_PATH = "./json/json/";
    private static final String JSON_SCHEME_PATH = "./json/scheme/";
    private static final String JSON_EXT = ".json";

    public static void writeJsonToFile(String fileName, JsonNode node) throws IOException
    {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        else {
            file.delete();
        }

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(node.toPrettyString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static JsonNode loadJson(String source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());            // for optional values, if not used, then only "present" flag available
        mapper.registerModule(new JavaTimeModule());        // for date, time, datetime values
        File file = new File(JSON_PATH +  source + JSON_EXT);
        JsonNode node = mapper.readTree(file);

        return node;
    }

    public static JsonNode loadScheme(String source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        File file = new File(JSON_SCHEME_PATH +  source + JSON_EXT);
        JsonNode node = mapper.readTree(file);

        return node;
    }
}
