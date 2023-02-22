package reivosar.common.util.lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilTest {
    
    @Nested
    class GetPackageNameTests {
        @Test
        void shouldReturnCorrectPackageNameForClassInDefaultPackage() {
            Class<?> cls = ClassUtilTest.class;
            String packageName = ClassUtil.getPackageName(cls);
            assertEquals("reivosar.common.util.lang", packageName);
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
        
        @Test
        void shouldReturnCorrectSimpleNameForAnonymousClass() {
            Runnable r = () -> {
                // do nothing
            };
            Class<?> cls = r.getClass();
            String simpleName = ClassUtil.getSimpleName(cls);
            assertEquals("", simpleName);
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
}