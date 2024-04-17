package reivosar.common.lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilTest {
    
    @Nested
    class GetPackageNameTests {
        @Test
        void shouldReturnCorrectPackageNameForClassInDefaultPackage() {
            Class<?> cls = ClassUtilTest.class;
            String packageName = ClassUtil.getPackageName(cls);
            assertEquals("reivosar.common.lang", packageName);
        }
        
        @Test
        void shouldReturnCorrectPackageNameForClassInNamedPackage() {
            Class<?> cls = org.junit.jupiter.api.Test.class;
            String packageName = ClassUtil.getPackageName(cls);
            assertEquals("org.junit.jupiter.api", packageName);
        }
    }
    
    @Nested
    class GetSimpleNameTests {
        @Test
        void shouldReturnCorrectSimpleNameForClassInDefaultPackage() {
            Class<?> cls = ClassUtilTest.class;
            String simpleName = ClassUtil.getSimpleName(cls);
            assertEquals("ClassUtilTest", simpleName);
        }
        
        @Test
        void shouldReturnCorrectSimpleNameForNestedClass() {
            Class<?> cls = GetSimpleNameTests.class;
            String simpleName = ClassUtil.getSimpleName(cls);
            assertEquals("GetSimpleNameTests", simpleName);
        }
    }
    
    @Nested
    class ToClassTests {
        
        @Test
        void shouldReturnEmptyArrayWhenInputIsNull() {
            Class<?>[] classes = ClassUtil.toClass((Object[]) null);
            assertNotNull(classes);
            assertEquals(0, classes.length);
        }
        
        @Test
        void shouldReturnArrayOfClasses() {
            Class<?>[] classes = ClassUtil.toClass(1, "2", 3L);
            assertNotNull(classes);
            assertEquals(3, classes.length);
            assertArrayEquals(new Class<?>[]{Integer.class, String.class, Long.class}, classes);
        }
    }
    
    @Nested
    class PrimitiveToWrapperTests {
        
        @Test
        void shouldReturnWrapperClassForPrimitiveBoolean() {
            assertEquals(Boolean.class, ClassUtil.primitiveToWrapper(boolean.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveByte() {
            assertEquals(Byte.class, ClassUtil.primitiveToWrapper(byte.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveChar() {
            assertEquals(Character.class, ClassUtil.primitiveToWrapper(char.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveDouble() {
            assertEquals(Double.class, ClassUtil.primitiveToWrapper(double.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveFloat() {
            assertEquals(Float.class, ClassUtil.primitiveToWrapper(float.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveInt() {
            assertEquals(Integer.class, ClassUtil.primitiveToWrapper(int.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveLong() {
            assertEquals(Long.class, ClassUtil.primitiveToWrapper(long.class));
        }
        
        @Test
        void shouldReturnWrapperClassForPrimitiveShort() {
            assertEquals(Short.class, ClassUtil.primitiveToWrapper(short.class));
        }
        
        @Test
        void shouldReturnSameClassForNonPrimitiveClass() {
            assertEquals(String.class, ClassUtil.primitiveToWrapper(String.class));
        }
        
        @Test
        void shouldReturnNullForNullInput() {
            assertNull(ClassUtil.primitiveToWrapper(null));
        }
    }
    
    @Nested
    class IsAssignableTest {
        
        @ParameterizedTest
        @MethodSource("strictComparisonOnSource")
        void shouldReturnExpectedValue(final Class<?> source, final Class<?> target, final boolean expected) {
            assertEquals(expected, ClassUtil.isAssignable(source, target, true));
        }
        
        @ParameterizedTest
        @MethodSource("nonStrictComparisonOnSource")
        void shouldReturnExpectedValueNonStrict(final Class<?> source, final Class<?> target, final boolean expected) {
            assertEquals(expected, ClassUtil.isAssignable(source, target, false));
        }
        
        static Stream<Arguments> strictComparisonOnSource() {
            return Stream.of(
                    Arguments.of(Integer.TYPE, Integer.TYPE, true),
                    Arguments.of(String.class, String.class, true),
                    Arguments.of(String.class, Object.class, false),
                    Arguments.of(Object.class, String.class, false),
                    Arguments.of(String.class, Integer.class, false),
                    Arguments.of(Integer.class, String.class, false),
                    Arguments.of(ArrayList.class, List.class, false),
                    Arguments.of(List.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Collection.class, false),
                    Arguments.of(Collection.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Object.class, false),
                    Arguments.of(Object.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, List.class, false),
                    Arguments.of(List.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Collection.class, false),
                    Arguments.of(Collection.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Iterable.class, false),
                    Arguments.of(Iterable.class, ArrayList.class, false),
                    Arguments.of(Integer.class, Number.class, false),
                    Arguments.of(Number.class, Integer.class, false),
                    Arguments.of(Double.class, Number.class, false),
                    Arguments.of(Number.class, Double.class, false)
            );
        }
        
        static Stream<Arguments> nonStrictComparisonOnSource() {
            return Stream.of(
                    Arguments.of(Integer.TYPE, Integer.TYPE, true),
                    Arguments.of(String.class, String.class, true),
                    Arguments.of(String.class, Object.class, false),
                    Arguments.of(Object.class, String.class, false),
                    Arguments.of(String.class, Integer.class, false),
                    Arguments.of(Integer.class, String.class, false),
                    Arguments.of(ArrayList.class, List.class, true),
                    Arguments.of(List.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Collection.class, true),
                    Arguments.of(Collection.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Object.class, false),
                    Arguments.of(Object.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, List.class, true),
                    Arguments.of(List.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Collection.class, true),
                    Arguments.of(Collection.class, ArrayList.class, false),
                    Arguments.of(ArrayList.class, Iterable.class, true),
                    Arguments.of(Iterable.class, ArrayList.class, false),
                    Arguments.of(Integer.class, Number.class, true),
                    Arguments.of(Number.class, Integer.class, false),
                    Arguments.of(Double.class, Number.class, true),
                    Arguments.of(Number.class, Double.class, false)
            );
        }
    }
}