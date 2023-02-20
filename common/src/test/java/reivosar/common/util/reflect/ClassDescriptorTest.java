package reivosar.common.util.reflect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ClassDescriptorTest {
    
    @Nested
    class GetPackageNameTests {
        
        private ClassDescriptor classDescriptor;
        
        @BeforeEach
        void setUp() {
            classDescriptor = new ClassDescriptor(ClassDescriptorTest.class);
        }
        
        @Test
        void shouldReturnPackageName() {
            assertEquals("reivosar.common.util.reflect", classDescriptor.getPackageName());
        }
    }
    
    @Nested
    class GetClassNameTests {
        
        private ClassDescriptor classDescriptor;
        
        @BeforeEach
        void setUp() {
            classDescriptor = new ClassDescriptor(ClassDescriptorTest.class);
        }
        
        @Test
        void shouldReturnClassName() {
            assertEquals("ClassDescriptorTest", classDescriptor.getClassName());
        }
    }
    
    @Nested
    class GetAccessScopeTests {
        
        private ClassDescriptor classDescriptor;
        
        @BeforeEach
        void setUp() {
            classDescriptor = new ClassDescriptor(ClassDescriptorTest.class);
        }
        
        @Test
        void shouldReturnPackagePrivateAccessScope() {
            assertEquals(AccessScope.PACKAGE_PRIVATE, classDescriptor.getAccessScope());
        }
    }
    
    @Nested
    class GetFieldNamesTests {
        
        private ClassDescriptor classDescriptor;
        
        @BeforeEach
        void setUp() {
            classDescriptor = new ClassDescriptor(ClassDescriptorTest.class);
        }
        
        @Test
        void shouldReturnEmptyCollectionWhenNoFieldsExist() {
            assertTrue(classDescriptor.getFieldNames().isEmpty());
        }
        
        @Test
        void shouldReturnFieldNamesWhenFieldsExist() {
            classDescriptor = new ClassDescriptor(ClassProfile.class);
            Collection<String> fieldNames = classDescriptor.getFieldNames();
            assertFalse(fieldNames.isEmpty());
            assertTrue(fieldNames.contains("declaringClass"));
            assertTrue(fieldNames.contains("classModifier"));
        }
    }
    
    @Nested
    class GetConstructorNamesTests {
        
        private ClassDescriptor classDescriptor;
        
        @BeforeEach
        void setUp() {
            classDescriptor = new ClassDescriptor(String.class);
        }
        
        @Test
        void shouldReturnConstructorNames() {
            Collection<String> constructorNames = classDescriptor.getConstructorNames();
            assertFalse(constructorNames.isEmpty());
            assertTrue(constructorNames.contains("java.lang.String"));
        }
    }
    
    @Nested
    class GetMethodNamesTests {
        
        private ClassDescriptor classDescriptor;
        
        @BeforeEach
        void setUp() {
            classDescriptor = new ClassDescriptor(String.class);
        }
        
        @Test
        void shouldReturnMethodNames() {
            Collection<String> methodNames = classDescriptor.getMethodNames();
            assertFalse(methodNames.isEmpty());
            assertTrue(methodNames.contains("charAt"));
        }
    }
}