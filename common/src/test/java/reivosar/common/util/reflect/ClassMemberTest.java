package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.lang.FieldUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassMemberTest {
    
    @Nested
    class FieldDescriptorsTests {
        
        static class TestClass {
            private int field1;
            @MyAnnotation
            private String field2;
            private Object field3;
        }
        
        @Retention(RetentionPolicy.RUNTIME)
        @interface MyAnnotation {
        }
        
        @Nested
        class GetFieldNamesTest {
            
            @Test
            void shouldReturnFieldNamesAsCollection() {
                // given
                FieldDescriptors fieldDescriptors = new FieldDescriptors(TestClass.class.getDeclaredFields());
                ClassMember classMember = new ClassMember(fieldDescriptors, null, null);
                
                // when
                Collection<String> fieldNames = classMember.getFieldNames();
                
                // then
                assertEquals(fieldDescriptors.getMemberCount(), fieldNames.size());
                assertTrue(fieldNames.contains("field1"));
                assertTrue(fieldNames.contains("field2"));
                assertTrue(fieldNames.contains("field3"));
            }
        }
        
        private boolean containsFieldDescriptors(final Collection<FieldDescriptor> descriptors, final String... fields) {
            return descriptors.containsAll(
                    Arrays.stream(fields)
                            .map(s -> new FieldDescriptor(FieldUtil.getDeclaredField(TestClass.class, s))).toList()
            );
        }
        
        @Nested
        class GetFieldDescriptorsTest {
            
            @Test
            void shouldReturnFieldDescriptorCollection() {
                // given
                FieldDescriptors fieldDescriptors = new FieldDescriptors(TestClass.class.getDeclaredFields());
                ClassMember classMember = new ClassMember(fieldDescriptors, null, null);
                
                // when
                Collection<FieldDescriptor> descriptors = classMember.getFieldDescriptors();
                
                // then
                assertEquals(fieldDescriptors.getMemberCount(), descriptors.size());
                assertTrue(containsFieldDescriptors(descriptors, "field1", "field2", "field3"));
            }
        }
        
        @Nested
        class FilterFieldDescriptorTest {
            
            @Test
            void shouldFilterFieldDescriptorByName() {
                // given
                FieldDescriptors fieldDescriptors = new FieldDescriptors(FieldUtil.getAllFieldArray(TestClass.class));
                ClassMember classMember = new ClassMember(fieldDescriptors, null, null);
                
                // when
                Collection<FieldDescriptor> filteredFields = classMember.filterFieldDescriptor("field1");
                
                // then
                assertEquals(1, filteredFields.size());
                assertTrue(containsFieldDescriptors(filteredFields, "field1"));
            }
            
            @Test
            void shouldFilterFieldDescriptorByAnnotation() {
                // given
                FieldDescriptors fieldDescriptors = new FieldDescriptors(TestClass.class.getDeclaredFields());
                ClassMember classMember = new ClassMember(fieldDescriptors, null, null);
                
                // when
                Collection<FieldDescriptor> filteredFields = classMember.filterFieldDescriptor(MyAnnotation.class);
                
                // then
                assertEquals(1, filteredFields.size());
                assertTrue(containsFieldDescriptors(filteredFields, "field2"));
            }
        }
    }
    
    @Nested
    class ConstructorDescriptorsTests {
    
    }
    
    @Nested
    class MethodDescriptorsTests {
    
    }
}