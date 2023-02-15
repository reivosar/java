package reivosar.common.util.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilTest {
    
    @Test
    void testIsEmpty() {
        assertTrue(ObjectUtil.isEmpty(null));
        assertTrue(ObjectUtil.isEmpty(""));
        assertTrue(ObjectUtil.isEmpty(new Object[]{}));
        assertFalse(ObjectUtil.isEmpty(" "));
        assertFalse(ObjectUtil.isEmpty(new Object[]{new Object()}));
    }
    
    @Test
    void testIsNotEmpty() {
        assertFalse(ObjectUtil.isNotEmpty(null));
        assertFalse(ObjectUtil.isNotEmpty(""));
        assertFalse(ObjectUtil.isNotEmpty(new Object[]{}));
        assertTrue(ObjectUtil.isNotEmpty(" "));
        assertTrue(ObjectUtil.isNotEmpty(new Object[]{new Object()}));
    }
    
    @Test
    void testRequireNonNullWithNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> ObjectUtil.requireNonNull("testObject", null));
        assertEquals("testObject must not be null", exception.getMessage());
    }
    
    @Test
    void testRequireNonNullWithNonNull() {
        Object object = new Object();
        assertSame(object, ObjectUtil.requireNonNull("testObject", object));
    }
    
    @Test
    void testGetIfNullWithNull() {
        Object defaultObject = new Object();
        Object object = null;
        assertSame(defaultObject, ObjectUtil.getIfNull(object, defaultObject));
    }
    
    @Test
    void testGetIfNullWithNonNull() {
        Object defaultObject = new Object();
        Object object = new Object();
        assertSame(object, ObjectUtil.getIfNull(object, defaultObject));
    }
}