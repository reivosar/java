package reivosar.common.lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConstructorUtilTest {
    
    @Nested
    class NewInstanceMethodTests {
        
        @Nested
        class NoArgsConstructorTests {
            
            @Test
            void shouldCreateNewInstance() {
                Constructor<?> constructor = NoArgsConstructor.class.getDeclaredConstructors()[0];
                assertNotNull(ConstructorUtil.newInstance(constructor));
            }
        }
        
        @Nested
        class PrimitiveArgsConstructorTests {
            
            @Test
            void shouldCreateNewInstance() {
                Constructor<?> constructor = PrimitiveArgsConstructor.class.getDeclaredConstructors()[0];
                assertNotNull(ConstructorUtil.newInstance(constructor, 1, true));
            }
        }
        
        @Nested
        class ObjectArgsConstructorTests {
            
            @Test
            void shouldCreateNewInstance() {
                Constructor<?> constructor = ObjectArgsConstructor.class.getDeclaredConstructors()[0];
                assertNotNull(ConstructorUtil.newInstance(constructor, "test", new Object()));
            }
            
            @Test
            void shouldThrowIllegalArgumentExceptionWhenParameterCountMismatch() {
                Constructor<?> constructor = ObjectArgsConstructor.class.getDeclaredConstructors()[0];
                assertThrows(IllegalStateException.class, () -> ConstructorUtil.newInstance(constructor, "test"));
            }
            
            @Test
            void shouldThrowIllegalArgumentExceptionWhenParameterTypeMismatch() {
                Constructor<?> constructor = ObjectArgsConstructor.class.getDeclaredConstructors()[0];
                assertThrows(IllegalStateException.class, () -> ConstructorUtil.newInstance(constructor, 123, new Object()));
            }
        }
        
        @Nested
        class ExceptionConstructorTests {
            
            @Test
            void shouldThrowException() {
                Constructor<?> constructor = ExceptionConstructor.class.getDeclaredConstructors()[0];
                assertThrows(IllegalStateException.class, () -> ConstructorUtil.newInstance(constructor, "test"));
            }
        }
    }
    
    static class NoArgsConstructor {
        public NoArgsConstructor() {
        }
    }
    
    static class PrimitiveArgsConstructor {
        public PrimitiveArgsConstructor(int i, boolean b) {
        }
    }
    
    static class ObjectArgsConstructor {
        public ObjectArgsConstructor(String s, Object o) {
        }
    }
    
    static class ExceptionConstructor {
        public ExceptionConstructor(String s) throws IllegalAccessException {
            throw new IllegalAccessException();
        }
    }
}