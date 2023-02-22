package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClassMemberAccessorTest {
    
    static class SomeClass {
        private Boolean someField;
        public String publicField;
        protected int protectedField;
        private List<Integer> privateField;
        Map<String, String> packagePrivateField;
    }
    
    @Nested
    class GetName {
        
        @Test
        public void shouldReturnCorrectName() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("someField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            String name = accessor.getName();
            // then
            assertEquals("someField", name);
        }
    }
    
    @Nested
    class EqualsByName {
        
        @Test
        public void shouldReturnTrueWhenNamesAreEqual() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("someField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            boolean result = accessor.equalsByName("someField");
            // then
            assertTrue(result);
        }
        
        @Test
        public void shouldReturnFalseWhenNamesAreNotEqual() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("someField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            boolean result = accessor.equalsByName("anotherField");
            // then
            assertFalse(result);
        }
    }
    
    @Nested
    class GetAccessScope {
        
        @Test
        public void shouldReturnPublicAccessScope() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("publicField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            AccessScope accessScope = accessor.getAccessScope();
            // then
            assertEquals(AccessScope.PUBLIC, accessScope);
        }
        
        @Test
        public void shouldReturnPrivateAccessScope() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("privateField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            AccessScope accessScope = accessor.getAccessScope();
            // then
            assertEquals(AccessScope.PRIVATE, accessScope);
        }
        
        @Test
        public void shouldReturnProtectedAccessScope() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("protectedField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            AccessScope accessScope = accessor.getAccessScope();
            // then
            assertEquals(AccessScope.PROTECTED, accessScope);
        }
        
        @Test
        public void shouldReturnPackagePrivateAccessScope() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("packagePrivateField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            AccessScope accessScope = accessor.getAccessScope();
            // then
            assertEquals(AccessScope.PACKAGE_PRIVATE, accessScope);
        }
    }
    
    @Nested
    class EqualsByAccessScope {
    
        @Test
        public void shouldReturnTrueWhenAccessScopesAreEqual() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("packagePrivateField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            boolean result = accessor.equalsByAccessScope(AccessScope.PACKAGE_PRIVATE);
            // then
            assertTrue(result);
        }
    
        @Test
        public void shouldReturnTrueWhenAccessScopesAreNotEqual() throws NoSuchFieldException {
            // given
            Field field = SomeClass.class.getDeclaredField("packagePrivateField");
            ClassMemberAccessor accessor = new ClassMemberAccessor(field);
            // when
            boolean result = accessor.equalsByAccessScope(AccessScope.PUBLIC);
            // then
            assertFalse(result);
        }
    }
}