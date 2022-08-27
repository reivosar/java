package reivosar.common.util.io;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility Library for json
 */
public class JsonUtil {
    
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    static {
        JSON_MAPPER.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        JSON_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    
    private JsonUtil() {
    }
    
    /**
     * Deserialize JSON content from given JSON content String.
     *
     * @param json      JSON content String
     * @param valueType Value type when deserializing
     * @param <T>       Value Type
     * @return Deserialized JSON Object
     * @throws JsonHandlingException If an error occurs when reading or writing JSON
     */
    public static <T> T deserialize(final String json, final Class<T> valueType) throws JsonHandlingException {
        try {
            return JSON_MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
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
        try {
            return JSON_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonHandlingException(e);
        }
    }
    
    public static class JsonHandlingException extends RuntimeException {
        private JsonHandlingException(Throwable throwable) {
            super(throwable);
        }
    }
}