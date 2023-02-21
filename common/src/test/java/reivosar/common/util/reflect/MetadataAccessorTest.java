package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class MetadataAccessorTest {
    
    @Nested
    class GetAnnotatedMetadataTests {
        
        @Test
        void shouldBeReturnsEmptyCollectionWhenAccessibleObjectIsNull() {
            // GIVEN
            ClassAccessibleObject mockAccessibleObject = null;
            MetadataAccessor metadataAccessor = new MetadataAccessor(mockAccessibleObject);
            // WHEN
            Collection<Annotation> actualAnnotations = metadataAccessor.getAnnotatedMetadata();
            // THEN
            assertTrue(actualAnnotations.isEmpty());
        }
        
        @Test
        void shouldBeReturnsAnnotationsWhenAccessibleObjectIsNotNull() {
            // GIVEN
            ClassAccessibleObject mockAccessibleObject = new ClassAccessibleObject(MyClass.class);
            MetadataAccessor metadataAccessor = new MetadataAccessor(mockAccessibleObject);
            // WHEN
            Collection<Annotation> actualAnnotations = metadataAccessor.getAnnotatedMetadata();
            // THEN
            assertEquals(1, actualAnnotations.size());
        }
        
        @Retention(RetentionPolicy.RUNTIME)
        @interface MyAnnotation {}
        
        @MyAnnotation
        class MyClass {
            @MyAnnotation
            public void myMethod() {}
        }
    }
    
    @Nested
    class HasAnnotatedMetadataTests {
        
        @Test
        void shouldBeReturnsFalseWhenAnnotationClassIsNotPresent() {
            // GIVEN
            ClassAccessibleObject mockAccessibleObject = new ClassAccessibleObject(MyClass.class);
            MetadataAccessor metadataAccessor = new MetadataAccessor(mockAccessibleObject);
            // WHEN
            boolean actualResult = metadataAccessor.hasAnnotatedMetadata(DummyAnnotation.class);
            // THEN
            assertFalse(actualResult);
        }
    
        @Test
        void shouldBeReturnsTrueWhenAnnotationClassIsPresent() {
            // GIVEN
            ClassAccessibleObject mockAccessibleObject = new ClassAccessibleObject(MyClass.class);
            MetadataAccessor metadataAccessor = new MetadataAccessor(mockAccessibleObject);
            // WHEN
            boolean actualResult = metadataAccessor.hasAnnotatedMetadata(MyAnnotation.class);
            // THEN
            assertTrue(actualResult);
        }
        
        @Retention(RetentionPolicy.RUNTIME)
        @interface MyAnnotation {}
    
        @Retention(RetentionPolicy.RUNTIME)
        @interface DummyAnnotation {}
        
        @MyAnnotation
        class MyClass {
            @MyAnnotation
            public void myMethod() {}
        }
    }
    
}
