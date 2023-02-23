package de.tab.json.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.imifou.jsonschema.module.addon.AddonModule;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationModule;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

// Doku https://github.com/victools/jsonschema-generator/tree/main/jsonschema-generator#completeminimal-example

public class JsonValidator {
    public static <T> JsonNode buildJsonSchemeStandard(Class<T> contractClass) {
        /*
            Standard
        */

        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(contractClass);

        return jsonSchema;
    }

    public static <T> JsonNode buildJsonSchemeJackson(Class<T> contractClass) {
        /*
            Java JSON Schema Generation – Module jackson

            Maven Central

            Module for the jsonschema-generator – deriving JSON Schema attributes from jackson annotations.
            Features

                Set a field/method's "description" as per @JsonPropertyDescription
                Set a type's "description" as per @JsonClassDescription.
                Override a field's/method's property name as per @JsonProperty annotations.
                Ignore fields/methods that are marked with a @JsonBackReference annotation.
                Ignore fields (and their associated getter methods) that are deemed to be ignored according to various other jackson-annotations (e.g. @JsonIgnore, @JsonIgnoreType, @JsonIgnoreProperties) or are otherwise supposed to be excluded.
                Optionally: set a field/method as "required" as per @JsonProperty annotations, if the JacksonOption.RESPECT_JSONPROPERTY_REQUIRED was provided (i.e. this is an "opt-in").
                Optionally: treat enum types as plain strings as per the @JsonValue annotated method, if there is one and the JacksonOption.FLATTENED_ENUMS_FROM_JSONVALUE was provided (i.e. this is an "opt-in").
                Optionally: treat enum types as plain strings, as per each enum constant's @JsonProperty annotation, if all values of an enum have such annotations and the JacksonOption.FLATTENED_ENUMS_FROM_JSONPROPERTY was provided (i.e. this is an "opt-in").
                Optionally: sort an object's properties according to its @JsonPropertyOrder annotation, if the JacksonOption.RESPECT_JSONPROPERTY_ORDER was provided (i.e. this is an "opt-in").
                Subtype resolution according to @JsonSubTypes on a supertype in general or directly on specific fields/methods as an override of the per-type behavior unless JacksonOption.SKIP_SUBTYPE_LOOKUP was provided (i.e. this is an "opt-out").
                Apply structural changes for subtypes according to @JsonTypeInfo on a supertype in general or directly on specific fields/methods as an override of the per-type behavior unless JacksonOption.IGNORE_TYPE_INFO_TRANSFORM was provided (i.e. this is an "opt-out").
                    Considering @JsonTypeInfo.include with values As.PROPERTY, As.EXISTING_PROPERTY, As.WRAPPER_ARRAY, As.WRAPPER_OBJECT
                    Considering @JsonTypeInfo.use with values Id.CLASS, Id.NAME
                Consider @JsonProperty.access for marking a field/method as readOnly or writeOnly
                Optionally: ignore all methods but those with a @JsonProperty annotation, if the JacksonOption.INCLUDE_ONLY_JSONPROPERTY_ANNOTATED_METHODS was provided (i.e. this is an "opt-in").
                Optionally: respect @JsonIdentityReference(alwaysAsId=true) annotation if there is a corresponding @JsonIdentityInfo annotation on the type and the JacksonOption.JSONIDENTITY_REFERENCE_ALWAYS_AS_ID as provided (i.e., this is an "opt-in")
        */

        JacksonModule module = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                .with(module);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(contractClass);

        return jsonSchema;
    }

    public static <T> JsonNode buildJsonSchemeJavax(Class<T> contractClass) {
        /*
            Java JSON Schema Generator – Module javax.validation

            Maven Central

            Module for the jsonschema-generator – deriving JSON Schema attributes from javax.validation.constraints annotations.
            Features

                Determine whether a member is not nullable, base assumption being that all fields and method return values are nullable if not annotated. Based on @NotNull/@Null/@NotEmpty/@NotBlank
                Populate list of "required" fields/methods for objects if JavaxValidationOption.NOT_NULLABLE_FIELD_IS_REQUIRED and/or JavaxValidationOption.NOT_NULLABLE_METHOD_IS_REQUIRED is/are being provided in constructor.
                Populate "minItems" and "maxItems" for containers (i.e. arrays and collections). Based on @Size/@NotEmpty
                Populate "minLength" and "maxLength" for strings. Based on @Size/@NotEmpty/@NotBlank
                Populate "format" for strings. Based on @Email, can be "email" or "idn-email" depending on whether JavaxValidationOption.PREFER_IDN_EMAIL_FORMAT is being provided in constructor.
                Populate "pattern" for strings. Based on @Pattern/@Email, when corresponding JavaxValidationOption.INCLUDE_PATTERN_EXPRESSIONS is being provided in constructor.
                Populate "minimum"/"exclusiveMinimum" for numbers. Based on @Min/@DecimalMin/@Positive/@PositiveOrZero
                Populate "maximum"/"exclusiveMaximum" for numbers. Based on @Max/@DecimalMax/@Negative/@NegativeOrZero

            Schema attributes derived from validation annotations on fields are also applied to their respective getter methods.
            Schema attributes derived from validation annotations on getter methods are also applied to their associated fields.
        */
        JavaxValidationModule module = new JavaxValidationModule();
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                .with(module);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(contractClass);

        return jsonSchema;
    }

    public static <T> JsonNode buildJsonSchemeCombine(Class<T> contractClass) {
        JacksonModule moduleJackson = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        JavaxValidationModule moduleJavax = new JavaxValidationModule();
        AddonModule moduleAddOn = new AddonModule();
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                .with(moduleJackson)
                .with(moduleJavax)
                .with(moduleAddOn);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(contractClass);

        return jsonSchema;
    }

    public static <T> JsonNode substituteScheme(JsonNode scheme, Class<T> subContractClass, JsonNode sub) {
        var className = subContractClass.getSimpleName();
        var parentClassName = subContractClass.getSuperclass().getSimpleName();
        if (!className.isEmpty() && !parentClassName.isEmpty()) {
            JsonNode substituteNode = scheme.findParent(parentClassName);
            if (substituteNode != null) {
                ObjectNode o = (ObjectNode) substituteNode;
                o.remove(parentClassName);  // remove place holder in json scheme
                o.put(className, sub);      // put new object in json scheme
                // update entries in required array?
            }
        }

        return scheme;
    }

    public static JsonValidationErrorResult validateJson(JsonNode scheme, JsonNode json) {
        JsonValidationErrorResult result = JsonValidationErrorResult.getInstance();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
        JsonSchema jsonSchema = jsonSchemaFactory.getSchema(scheme);
        Set<ValidationMessage> errors = jsonSchema.validate(json);
        int size = errors.size();
        if (size > 0) {
            result.setErrorCode(-1001);
            result.setDescription("(" + size + ") Validierungsfehler");
            for (ValidationMessage msg : errors)
                result.addValidationError(msg);
        }
        return result;
    }

}
