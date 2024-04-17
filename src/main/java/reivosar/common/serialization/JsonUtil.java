package reivosar.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import reivosar.common.lang.ObjectUtil;

import java.io.File;

/**
 * Utility Library for json
 */
public final class JsonUtil {
    
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    static {
        JSON_MAPPER.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        JSON_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JSON_MAPPER.registerModule(new JavaTimeModule());
    }
    
    private JsonUtil() {
        // This constructor must be private
    }
    
    /**
     * Deserialize JSON content from given JSON content String.
     *
     * @param json      JSON file
     * @param valueType Value type when deserializing
     * @param <T>       Value Type
     * @return Deserialized JSON Object
     * @throws JsonHandlingException If an error occurs when reading or writing JSON
     */
    public static <T> T deserialize(final File json, final Class<T> valueType) throws JsonHandlingException {
        ObjectUtil.requireNonNull("json", json);
        ObjectUtil.requireNonNull("valueType", valueType);
        try {
            return JSON_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            throw new JsonHandlingException(e);
        }
    }
    
    /**
     * Obtains the value of a specified field name from a JSON string.
     * To obtain nested values, specify the field names in the following order: parent, child, grandchild.
     *
     * @param json       JSON content String
     * @param identifies fieldName or index in JSON
     * @return Value of the specified field name
     * @throws JsonHandlingException If an error occurs when reading or writing JSON
     */
    public static String read(final String json, final Object... identifies) throws JsonHandlingException {
        ObjectUtil.requireNonNull("json", json);
        ObjectUtil.requireNonNull("valueType", identifies);
        try {
            JsonNode jsonNode = JSON_MAPPER.readTree(json);
            for (final Object identify : identifies) {
                jsonNode = (identify instanceof Integer) ?
                        jsonNode.get(Integer.parseInt(identify.toString())) :
                        jsonNode.get(identify.toString());
                if (jsonNode == null) {
                    return "";
                }
            }
            return jsonNode.asText();
        } catch (Exception e) {
            throw new JsonHandlingException(e);
        }
    }
    
    /**
     * Serialize JSON content String from Object.
     *
     * @param object object
     * @return Serialized JSON content String
     * @throws JsonHandlingException If an error occurs when reading or writing JSON
     */
    public static String serialize(final Object object) throws JsonHandlingException {
        ObjectUtil.requireNonNull("object", object);
        try {
            return JSON_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonHandlingException(e);
        }
    }
    
    public static class JsonHandlingException extends RuntimeException {
        private JsonHandlingException(String message, Throwable throwable) {
            super(message, throwable);
        }
        
        private JsonHandlingException(Throwable throwable) {
            this("Failed to handle json file", throwable);
        }
    }
}
