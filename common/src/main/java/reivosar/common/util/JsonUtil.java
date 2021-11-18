package reivosar.common.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility Library for json
 */
public class JsonUtil {
    
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final JsonFactory JSON_FACTORY = new JsonFactory(JSON_MAPPER);
    
    static {
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
    }
    
    private JsonUtil() {
    }
    
    /**
     * Method to deserialize JSON content from given JSON content String.
     *
     * @param json      JSON content String
     * @param valueType Value type when deserializing
     * @param <T>       Value Type
     * @return Deserialized JSON Object
     * @throws JsonHandlingException If an error occurs when reading or writing JSON
     */
    public static <T> T fromJsonString(final String json, final Class<T> valueType) throws JsonHandlingException {
        try {
            return JSON_MAPPER.readValue(json, valueType);
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
