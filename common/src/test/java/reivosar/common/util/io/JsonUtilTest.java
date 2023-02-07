package reivosar.common.util.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

public class JsonUtilTest {
    
    private final static String TEST_JSON_NAME = "test.json";
    private final static URL TEST_JSON_URL = Thread.currentThread().getContextClassLoader().getResource(TEST_JSON_NAME);
    
    @Nested
    class TestForDeserialize {
        
        @Test
        void shouldBeThrownNullPointerException_when_argument_file_is_null() {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> {
                        JsonUtil.deserialize(null, String.class);
                    },
                    "file must not be null");
        }
        
        @Test
        void shouldBeThrownNullPointerException_when_argument_valueType_is_null() {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> {
                        JsonUtil.deserialize(new File(TEST_JSON_URL.getPath()), null);
                    },
                    "valueType must not be null");
        }
        
        @Test
        void test() {
        
        }
        
        
        
    }
    
    @Nested
    class TestForReadvalue {
    
    }
    
    @Nested
    class TestForSerialize {
    
    }
}
