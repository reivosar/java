package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.*;

class ClassAccessibleObjectTest {
    
    @Nested
    class GetAnnotations {
        
        @Test
        void shouldReturnEmptyCollectionWhenNoAnnotations() {
            // given
            ClassAccessibleObject object = new ClassAccessibleObject(NoAnnotatedMyClass.class);
            // when
            var annotations = object.getAnnotations();
            // then
            assertTrue(annotations.isEmpty());
        }
        
        @Test
        void shouldReturnCollectionOfAnnotations() {
            // given
            ClassAccessibleObject object = new ClassAccessibleObject(AnnotatedClass.class);
            // when
            var annotations = object.getAnnotations();
            // then
            assertEquals(1, annotations.size());
            assertTrue(object.hasAnnotation(EqualAnnotation.class));
        }
    }
    
    @Nested
    class HasAnnotation {
        
        @Test
        void shouldReturnFalseWhenAnnotationNotPresent() {
            // given
            ClassAccessibleObject object = new ClassAccessibleObject(AnnotatedClass.class);
            // when
            boolean result = object.hasAnnotation(NotEqualAnnotation.class);
            // then
            assertFalse(result);
        }
        
        @Test
        void shouldReturnTrueWhenAnnotationPresent() {
            // given
            ClassAccessibleObject object = new ClassAccessibleObject(AnnotatedClass.class);
            // when
            boolean result = object.hasAnnotation(EqualAnnotation.class);
            // then
            assertTrue(result);
        }
    }
    
    @EqualAnnotation
    private static class AnnotatedClass {
    }
    
    private static class NoAnnotatedMyClass {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    private @interface EqualAnnotation {
    
    }
    @Retention(RetentionPolicy.RUNTIME)
    private @interface NotEqualAnnotation {
    
    }
}