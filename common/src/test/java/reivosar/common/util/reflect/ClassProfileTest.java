package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClassProfileTest {
    
    @Nested
    class GetPackageName {
        
        @Test
        void shouldReturnCorrectPackageName() {
            ClassProfile classProfile = new ClassProfile(String.class);
            assertEquals("java.lang", classProfile.getPackageName());
        }
        
        @Test
        void shouldReturnDefaultPackageNameWhenNull() {
            ClassProfile classProfile = new ClassProfile(getClass());
            assertEquals(getClass().getPackageName(), classProfile.getPackageName());
        }
    }
    
    @Nested
    class GetClassName {
        
        @Test
        void shouldReturnCorrectClassName() {
            ClassProfile classProfile = new ClassProfile(String.class);
            assertEquals("String", classProfile.getClassName());
        }
        
        @Test
        void shouldReturnDefaultClassNameWhenNull() {
            ClassProfile classProfile = new ClassProfile(getClass());
            assertEquals(getClass().getSimpleName(), classProfile.getClassName());
        }
    }
    
    @Nested
    class GetAccessScope {
        
        @Test
        void shouldReturnPublic() {
            ClassProfile classProfile = new ClassProfile(String.class);
            assertEquals(AccessScope.PUBLIC, classProfile.getAccessScope());
        }
        
        @Test
        void shouldReturnProtected() {
            ClassProfile classProfile = new ClassProfile(ProtectedClass.class);
            assertEquals(AccessScope.PROTECTED, classProfile.getAccessScope());
        }
        
        @Test
        void shouldReturnPackagePrivate() {
            ClassProfile classProfile = new ClassProfile(getClass());
            assertEquals(AccessScope.PACKAGE_PRIVATE, classProfile.getAccessScope());
        }
        
        @Test
        void shouldReturnPrivate() {
            ClassProfile classProfile = new ClassProfile(PrivateClass.class);
            assertEquals(AccessScope.PRIVATE, classProfile.getAccessScope());
        }
    }
    
    @Nested
    class IsStatic {
        
        @Test
        void shouldReturnTrue() {
            ClassProfile classProfile = new ClassProfile(ProtectedClass.class);
            assertTrue(classProfile.isStatic());
        }
        
        @Test
        void shouldReturnFalse() {
            ClassProfile classProfile = new ClassProfile(ArrayList.class);
            assertFalse(classProfile.isStatic());
        }
    }
    
    @Nested
    class IsFinal {
        
        @Test
        void shouldReturnTrue() {
            ClassProfile classProfile = new ClassProfile(String.class);
            assertTrue(classProfile.isFinal());
        }
        
        @Test
        void shouldReturnFalse() {
            ClassProfile classProfile = new ClassProfile(ArrayList.class);
            assertFalse(classProfile.isFinal());
        }
    }
    
    protected static class ProtectedClass {
    }
    
    private static class PrivateClass {
    }
}
