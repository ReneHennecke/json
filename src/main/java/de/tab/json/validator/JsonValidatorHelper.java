package de.tab.json.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

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

    public static void writeJsonToFile(String fileName, String json) throws IOException
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
        fileWriter.write(json);
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

    public static JsonNode loadSchemeFromResource(String source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        File file = new File(source);
        JsonNode node = mapper.readTree(file);

        return node;
    }

    public static <T> String getResourcePath(Class<T> clazz) {
        String retval = "";

        final String RES_PATH = "scheme/";
        final String RES_EXT = ".json";

        try {
            retval = clazz.getClassLoader().getResource(RES_PATH + clazz.getSimpleName() + RES_EXT).getPath(); //.toExternalForm();
        }
        catch (Exception ex) {

        }

        return retval;
    }

    public static Boolean deleteSchemeFiles(final String prefix) {
        Boolean retval = false;

        try {
            final File dir = new File(JSON_SCHEME_PATH);
            final File[] contents = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(prefix);
                }
            });

            Arrays.asList(contents).stream().forEach(f -> {
                f.delete();
            });

            retval = true;
        }
        catch (Exception ex) {

        }

        return retval;
    }

    public static Integer countSchemeFiles(final String prefix) {
        Integer retval = 0;

        try {
            File dir = new File(JSON_SCHEME_PATH);
            File[] contents = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(prefix);
                }
            });

            retval = contents.length;
        }
        catch (Exception ex) {

        }

        return retval;
    }
}
