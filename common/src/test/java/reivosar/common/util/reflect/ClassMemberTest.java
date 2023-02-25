package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.lang.FieldUtil;
import reivosar.common.util.reflect.member.ConstructorDescriptor;
import reivosar.common.util.reflect.member.FieldDescriptor;
import reivosar.common.util.reflect.member.FieldDescriptorFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

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
                FieldDescriptors fieldDescriptors = new FieldDescriptors(FieldUtil.getAllFieldArray(TestClass.class));
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
                            .map(s -> FieldDescriptorFactory.create(FieldUtil.getDeclaredField(TestClass.class, s))).toList()
            );
        }
        
        @Nested
        class GetFieldDescriptorsTest {
            
            @Test
            void shouldReturnFieldDescriptorCollection() {
                // given
                FieldDescriptors fieldDescriptors = new FieldDescriptors(FieldUtil.getAllFieldArray(TestClass.class));
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
                FieldDescriptors fieldDescriptors = new FieldDescriptors(FieldUtil.getAllFieldArray(TestClass.class));
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
        
        @Nested
        class GetConstructorNamesMethod {
            @Test
            void shouldReturnAllConstructorNames() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                // when
                final Collection<String> constructorNames = classMember.getConstructorNames();
                // then
                assertAll(
                        () -> assertTrue(constructorNames.contains("java.util.ArrayList"))
                );
            }
        }
    
        @Nested
        class GetConstructorDetailsMethod {
            @Test
            void shouldReturnAllConstructorNames() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                // when
                final Collection<String> constructorNames = classMember.getConstructorDetails();
                // then
                assertAll(
                        () -> assertTrue(constructorNames.contains("public java.util.ArrayList(java.util.Collection)")),
                        () -> assertTrue(constructorNames.contains("public java.util.ArrayList()")),
                        () -> assertTrue(constructorNames.contains("public java.util.ArrayList(int)"))
                );
            }
        }
        
        @Nested
        class GetConstructorDescriptorsMethod {
            @Test
            void shouldReturnAllConstructorDescriptors() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                
                // when
                final Collection<ConstructorDescriptor> constructorDescriptors = classMember.getConstructorDescriptors();
                
                // then
                assertEquals(clazz.getDeclaredConstructors().length, constructorDescriptors.size());
            }
        }
    
        @Nested
        class FilterConstructorDescriptorByNameMethod {
            
            @Test
            void shouldReturnConstructorsWithTheGivenName() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                final String constructorName = "java.util.ArrayList";
            
                // when
                final Collection<ConstructorDescriptor> constructorDescriptors = classMember.filterConstructorDescriptor(constructorName);
            
                // then
                assertAll(
                        () -> assertEquals(3, constructorDescriptors.size()),
                        () -> assertEquals(constructorName, constructorDescriptors.iterator().next().getName())
                );
            }
    
            @Test
            void shouldReturnConstructorsWithTheGivenNameAndParameter() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                final String constructorName = "java.util.ArrayList";
                final Object[] parameters = new Object[]{100};
        
                // when
                final Collection<ConstructorDescriptor> constructorDescriptors = classMember.filterConstructorDescriptor(constructorName, parameters);
        
                // then
                assertAll(
                        () -> assertEquals(1, constructorDescriptors.size()),
                        () -> assertEquals(constructorName, constructorDescriptors.iterator().next().getName())
                );
            }
        
            @Test
            void shouldReturnAnEmptyCollectionIfNoConstructorsWithTheGivenName() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                final String constructorName = "notExist";
            
                // when
                final Collection<ConstructorDescriptor> constructorDescriptors = classMember.filterConstructorDescriptor(constructorName);
            
                // then
                assertTrue(constructorDescriptors.isEmpty());
            }
    
            @Test
            void shouldReturnAnEmptyCollectionIfNoConstructorsWithTheGivenNameAndParameters() {
                // given
                final Class<?> clazz = ArrayList.class;
                final ClassMember classMember = new ClassMember(
                        null,
                        new ConstructorDescriptors(clazz.getDeclaredConstructors()),
                        null
                );
                final String constructorName = "java.util.ArrayList";
                final Object[] parameters = new Object[]{"100"};
                
                // when
                final Collection<ConstructorDescriptor> constructorDescriptors = classMember.filterConstructorDescriptor(constructorName, parameters);
        
                // then
                assertTrue(constructorDescriptors.isEmpty());
            }
        }
    }
    
    @Nested
    class MethodDescriptorsTests {
    
    }
}