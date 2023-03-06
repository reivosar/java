package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.model.Model;
import reivosar.common.util.reflect.member.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassDescriptorTest {
    
    @Nested
    class GetRawClassTest {
        @Test
        void shouldReturnClassInformationOfSpecifiedClassAsArgument() {
            assertEquals(ClassResources.of(MemberVariationClass.class).getRawClass(), MemberVariationClass.class);
        }
    }
    
    private static <T> void assertUnorderedCollection(final Collection<T> c1, final Collection<T> c2) {
        assertEquals(c1.stream().sorted().toList(), c2.stream().sorted().toList());
    }
    
    @Nested
    class GetPackageNameTest {
        @Test
        void shouldReturnPackageNameOfSpecifiedClassAsArgument() {
            assertEquals(ClassResources.of(MemberVariationClass.class).getPackageName(), "reivosar.common.util.reflect");
        }
    }
    
    @Nested
    class GetSimpleNameTest {
        @Test
        void shouldReturnClassNameOfSpecifiedClassAsArgument() {
            assertEquals(ClassResources.of(MemberVariationClass.class).getSimpleName(), "MemberVariationClass");
        }
    }
    
    @Nested
    class GetClassModifierTest {
        @Test
        void shouldReturnClassModifierOfSpecifiedClassAsArgument() {
            assertEquals(ClassResources.of(MemberVariationClass.class).getClassModifier(),
                    new ClassModifier(MemberVariationClass.class));
        }
    }
    
    @Nested
    class GetFieldDescriptorsTest {
        @Test
        void shouldReturnFieldDescriptorsOfSpecifiedClassAsArgument() {
            // given
            final FieldDescriptors expected = FieldDescriptorsFactory.createDescriptors(MemberVariationClass.class);
            // when
            final FieldDescriptors actual = ClassResources.of(MemberVariationClass.class).getFieldDescriptors();
            // then
            assertEquals(expected, actual);
            assertEquals(5, actual.getMemberCount());
            assertUnorderedCollection(Arrays.asList("privateFinalString", "publicInt", "packagePrivateFinalList", "protectedMap", "PRIVATE_STATIC_FINAL_STRING"),
                    actual.getNames());
            assertUnorderedCollection(Arrays.asList(
                            "private final java.lang.String reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.privateFinalString",
                            "public int reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.publicInt",
                            "final java.util.List reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.packagePrivateFinalList",
                            "protected java.util.Map reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.protectedMap",
                            "private static final java.lang.String reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.PRIVATE_STATIC_FINAL_STRING")
                    , actual.getDescribedMembers());
            assertUnorderedCollection(Collections.singletonList("publicInt"), actual.filter("publicInt").getNames());
            assertUnorderedCollection(Arrays.asList("packagePrivateFinalList", "PRIVATE_STATIC_FINAL_STRING"), actual.filter(TestMemberAllAnnotation.class).getNames());
        }
    }
    
    @Nested
    class GetConstructorDescriptorsTest {
        @Test
        void shouldReturnConstructorDescriptorsOfSpecifiedClassAsArgument() {
            // given
            final ConstructorDescriptors expected = ConstructorDescriptorsFactory.createDescriptors(MemberVariationClass.class);
            // when
            final ConstructorDescriptors actual = ClassResources.of(MemberVariationClass.class).getConstructorDescriptors();
            // then
            assertEquals(expected, actual);
            assertEquals(4, actual.getMemberCount());
            assertUnorderedCollection(Arrays.asList(
                            "reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass",
                            "reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass",
                            "reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass",
                            "reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass"),
                    actual.getNames());
            assertUnorderedCollection(Arrays.asList(
                            "private reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.lang.String,java.util.List)",
                            "protected reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.util.List)",
                            "reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.lang.String)",
                            "public reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass()"),
                    actual.getDescribedMembers());
            assertUnorderedCollection(Collections.singletonList("public reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass()"),
                    actual.filter().getDescribedMembers());
            assertUnorderedCollection(Collections.singletonList("protected reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.util.List)"),
                    actual.filter(List.class).getDescribedMembers());
            assertUnorderedCollection(Arrays.asList(
                            "private reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.lang.String,java.util.List)",
                            "reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.lang.String)"),
                    actual.filter(TestMemberAllAnnotation.class).getDescribedMembers());
            assertUnorderedCollection(List.of(
                            "private reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass(java.lang.String,java.util.List)"),
                    actual.filter(TestConstructorAnnotation.class, String.class, List.class).getDescribedMembers());
        }
    }
    
    @Nested
    class GetMethodDescriptorsTest {
        @Test
        void shouldReturnMethodDescriptorsOfSpecifiedClassAsArgument() {
            // given
            final MethodDescriptors expected = MethodDescriptorsFactory.createDescriptors(MemberVariationClass.class);
            // when
            final MethodDescriptors actual = ClassResources.of(MemberVariationClass.class).getMethodDescriptors();
            // then
            assertEquals(expected, actual);
            assertEquals(5, actual.getMemberCount());
            assertUnorderedCollection(Arrays.asList(
                            "add",
                            "put",
                            "sum",
                            "toStaticString",
                            "getPrivateFinalString"),
                    actual.getNames());
            assertUnorderedCollection(Arrays.asList(
                            "void reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.add(boolean)",
                            "private java.lang.Long reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.put(java.lang.String,java.lang.Long)",
                            "protected int reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.sum(int)",
                            "private static final java.lang.String reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.toStaticString()",
                            "public java.lang.String reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.getPrivateFinalString()"),
                    actual.getDescribedMembers());
            assertUnorderedCollection(List.of(
                            "void reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.add(boolean)"),
                    actual.filter("add").getDescribedMembers());
            assertUnorderedCollection(List.of(
                            "protected int reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.sum(int)"),
                    actual.filter("sum", int.class).getDescribedMembers());
            assertUnorderedCollection(Arrays.asList(
                            "void reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.add(boolean)",
                            "private java.lang.Long reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.put(java.lang.String,java.lang.Long)"),
                    actual.filter(TestMemberAllAnnotation.class).getDescribedMembers());
            assertUnorderedCollection(List.of(
                            "private java.lang.Long reivosar.common.util.reflect.ClassDescriptorTest$MemberVariationClass.put(java.lang.String,java.lang.Long)"),
                    actual.filter(TestMethodAnnotation.class, "key", 123L).getDescribedMembers());
        }
    }
    
    @TestMemberAllAnnotation
    private static class MemberVariationClass extends Model {
        
        private final String privateFinalString;
        @TestFieldAnnotation
        public int publicInt;
        @TestMemberAllAnnotation
        final List<Boolean> packagePrivateFinalList;
        protected Map<String, Long> protectedMap;
        
        @TestFieldAnnotation
        @TestMemberAllAnnotation
        @TestStaticMemberAllAnnotation
        private static final String PRIVATE_STATIC_FINAL_STRING = "PRIVATE_STATIC_FINAL_STRING";
        
        public MemberVariationClass() {
            this("privateFinalString", Arrays.asList(false, true, false));
        }
        
        @TestMemberAllAnnotation
        MemberVariationClass(final String privateFinalString) {
            this(privateFinalString, Arrays.asList(false, true, false));
        }
        
        @TestConstructorAnnotation
        protected MemberVariationClass(final List<Boolean> packagePrivateFinalList) {
            this("privateFinalString", packagePrivateFinalList);
        }
        
        @TestMemberAllAnnotation
        @TestConstructorAnnotation
        private MemberVariationClass(final String privateFinalString, final List<Boolean> packagePrivateFinalList) {
            this.privateFinalString = privateFinalString;
            this.packagePrivateFinalList = packagePrivateFinalList;
        }
        
        public String getPrivateFinalString() {
            return privateFinalString;
        }
        
        @TestMemberAllAnnotation
        void add(final boolean bool) {
            this.packagePrivateFinalList.add(bool);
        }
        
        @TestMethodAnnotation
        protected int sum(int num) {
            return publicInt + num;
        }
        
        @TestMemberAllAnnotation
        @TestMethodAnnotation
        private Long put(final String key, final Long value) {
            return this.protectedMap.put(key, value);
        }
        
        @TestStaticMemberAllAnnotation
        private static final String toStaticString() {
            return PRIVATE_STATIC_FINAL_STRING;
        }
    }
    
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestMemberAllAnnotation {
    }
    
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestStaticMemberAllAnnotation {
    }
    
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestFieldAnnotation {
    }
    
    @Target({ElementType.CONSTRUCTOR})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestConstructorAnnotation {
    }
    
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestMethodAnnotation {
    }
}