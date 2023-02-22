package reivosar.common.util.reflect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorDescriptorTest {
    
    @Nested
    class IsMatchConstructorTest {
        
        @Nested
        class NoConstructorClassTests {
    
            private ConstructorDescriptor constructorDescriptor;
    
            @BeforeEach
            void setUp() throws ClassNotFoundException, NoSuchMethodException {
                Class<?> clazz = Class.forName(NoConstructorClass.class.getName());
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructorDescriptor = new ConstructorDescriptor(constructor);
            }
    
            @Test
            void shouldReturnTrueWhenMatched() {
                assertTrue(constructorDescriptor.isMatchParameters());
            }
    
            @Test
            void shouldReturnFalseWhenNotMatched() {
                assertFalse(constructorDescriptor.isMatchParameters("not a number"));
            }
    
            static class NoConstructorClass {}
        }
    
        @Nested
        class OneConstructorClassTests {
    
            private ConstructorDescriptor constructorDescriptor;
    
            @BeforeEach
            void setUp() throws Exception {
                Class<?> clazz = Class.forName(OneConstructorClass.class.getName());
                Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
                constructorDescriptor = new ConstructorDescriptor(constructor);
            }
    
            @Test
            void shouldReturnTrueWhenMatched() {
                assertTrue(constructorDescriptor.isMatchParameters("name"));
            }
    
            @Test
            void shouldReturnFalseWhenNotMatched() {
                assertFalse(constructorDescriptor.isMatchParameters(123));
            }
    
            static class OneConstructorClass {
                private final String str;
        
                public OneConstructorClass(final String str) {
                    this.str = str;
                }
            }
        }
        
        @Nested
        class TwoConstructorClassTests {
    
            private ConstructorDescriptor constructorDescriptor;
    
            @BeforeEach
            void setUp() throws Exception {
                Class<?> clazz = Class.forName(TwoConstructorClass.class.getName());
                Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
                constructorDescriptor = new ConstructorDescriptor(constructor);
            }
    
            @Test
            void shouldReturnTrueWhenMatched() {
                assertTrue(constructorDescriptor.isMatchParameters("name", 123));
            }
    
            @Test
            void shouldReturnFalseWhenNotMatched() {
                assertFalse(constructorDescriptor.isMatchParameters("not a number"));
            }
    
            private static class TwoConstructorClass {
                private final String name;
                private final int age;
        
                protected TwoConstructorClass(final String name) {
                    this(name, 999);
                }
        
                private TwoConstructorClass(final String name, final int age) {
                    this.name = name;
                    this.age = age;
                }
            }
        }
    }
    
    @Nested
    class NewInstanceTest {
        private ConstructorDescriptor constructorDescriptor;
        
        @BeforeEach
        void setUp() throws NoSuchMethodException {
            Constructor<ArrayList> constructor = ArrayList.class.getConstructor(int.class);
            constructorDescriptor = new ConstructorDescriptor(constructor);
        }
        
        @Test
        void shouldReturnNewInstance() {
            List<?> list = constructorDescriptor.newInstance(10);
            assertNotNull(list);
        }
        
        @Test
        void shouldThrowExceptionWhenFailsToCreateInstance() {
            assertThrows(IllegalStateException.class, () -> constructorDescriptor.newInstance("not a number"));
        }
    }
}