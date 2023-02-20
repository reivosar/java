package reivosar.common.util.lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.model.Model;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilTest {
    
    @Nested
    class IsEmptyTest {
        @Test
        void shouldReturnTrueForNullObject() {
            assertTrue(ObjectUtil.isEmpty(null));
        }
        
        @Test
        void shouldReturnTrueForEmptyString() {
            assertTrue(ObjectUtil.isEmpty(""));
        }
        
        @Test
        void shouldReturnFalseForNonEmptyString() {
            assertFalse(ObjectUtil.isEmpty("not empty"));
        }
        
        @Test
        void shouldReturnFalseForNonNullObject() {
            assertFalse(ObjectUtil.isEmpty(new Object()));
        }
    }
    
    @Nested
    class IsNotEmptyTest {
        @Test
        void shouldReturnFalseForNullObject() {
            assertFalse(ObjectUtil.isNotEmpty(null));
        }
        
        @Test
        void shouldReturnFalseForEmptyString() {
            assertFalse(ObjectUtil.isNotEmpty(""));
        }
        
        @Test
        void shouldReturnTrueForNonEmptyString() {
            assertTrue(ObjectUtil.isNotEmpty("not empty"));
        }
        
        @Test
        void shouldReturnTrueForNonNullObject() {
            assertTrue(ObjectUtil.isNotEmpty(new Object()));
        }
    }
    
    @Nested
    class RequireNonNullTest {
        @Test
        void shouldReturnNonNullObject() {
            assertNotNull(ObjectUtil.requireNonNull("name", new Object()));
        }
        
        @Test
        void shouldThrowNullPointerExceptionForNullObject() {
            assertThrows(NullPointerException.class, () -> ObjectUtil.requireNonNull("name", null));
        }
    }
    
    @Nested
    class RequireNonNullAndEmptyTest {
        @Test
        void shouldReturnNonEmptyString() {
            assertEquals("not empty", ObjectUtil.requireNonNullAndEmpty("name", "not empty"));
        }
        
        @Test
        void shouldThrowNullPointerExceptionForNullObject() {
            assertThrows(NullPointerException.class, () -> ObjectUtil.requireNonNullAndEmpty("name", null));
        }
        
        @Test
        void shouldThrowIllegalArgumentExceptionForEmptyString() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtil.requireNonNullAndEmpty("name", ""));
        }
    }
    
    @Nested
    class GetIfNullTest {
        @Test
        void shouldReturnSpecifiedObject() {
            Object obj = new Object();
            assertSame(obj, ObjectUtil.defaultIfNull(obj, new Object()));
        }
        
        @Test
        void shouldReturnDefaultObjectForNullObject() {
            Object obj = new Object();
            assertSame(obj, ObjectUtil.defaultIfNull(null, obj));
        }
        
        @Test
        void shouldReturnDefaultObjectForNullDefaultObject() {
            assertSame(null, ObjectUtil.defaultIfNull(null, null));
        }
    }
    
    
    @Nested
    class CloneTest {
        
        @Test
        public void shouldThrowExceptionWhenCloningNullObject() {
            assertThrows(NullPointerException.class, () -> ObjectUtil.clone(null));
        }
        
        @Test
        public void shouldCloneNonPrimitiveObject() {
            // prepare
            Person original = new Person("Alice", 25);
            
            // execute
            Person clone = ObjectUtil.clone(original);
            
            // verify
            assertSame(original, clone);
            assertEquals(original.getName(), clone.getName());
            assertEquals(original.getAge(), clone.getAge());
        }
        
        @Test
        public void shouldReturnSamePrimitiveObject() {
            int value = 10;
            
            // execute
            int result = ObjectUtil.clone(value);
            // verify
            assertSame(value, result);
        }
        
        static class Person {
            private final String name;
            private final int age;
            
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
            
            public String getName() {
                return name;
            }
            
            public int getAge() {
                return age;
            }
        }
    }
}