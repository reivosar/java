package reivosar.common.util.reflect.member;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassMemberConstructorAccessorTest {
    
    @Nested
    class NewInstanceTest {
        
        @Test
        void shouldCreateInstanceWithoutArgs() throws NoSuchMethodException, IllegalStateException {
            // given
            ClassMemberConstructorAccessor accessor = new ClassMemberConstructorAccessor(DummyClass.class.getDeclaredConstructor(), true);
            // when
            DummyClass instance = accessor.newInstance();
            // then
            assertNotNull(instance);
        }
        
        @Test
        void shouldCreateInstanceWithArgs() throws NoSuchMethodException, IllegalStateException {
            // given
            ClassMemberConstructorAccessor accessor = new ClassMemberConstructorAccessor(DummyClass.class.getDeclaredConstructor(String.class), true);
            // when
            DummyClass instance = accessor.newInstance("test");
            // then
            assertNotNull(instance);
            assertEquals("test", instance.getArg());
        }
        
        @Test
        void shouldThrowInstantiationExceptionWhenInvalidArgsGiven() throws NoSuchMethodException {
            // given
            ClassMemberConstructorAccessor accessor = new ClassMemberConstructorAccessor(DummyClass.class.getDeclaredConstructor(String.class), true);
            // when
            assertThrows(IllegalStateException.class, () -> accessor.newInstance(123));
        }
        
        @Test
        void shouldThrowIllegalStateExceptionWhenConstructorCallFailed() throws NoSuchMethodException {
            // given
            ClassMemberConstructorAccessor accessor = new ClassMemberConstructorAccessor(DummyClass.class.getDeclaredConstructor(String.class), true);
            // when
            assertThrows(IllegalStateException.class, () -> accessor.newInstance((String) null));
        }
    }
    
    static class DummyClass {
        private final String arg;
        
        public DummyClass() {
            this.arg = "";
        }
        
        public DummyClass(String arg) {
            if (arg == null) {
                throw new IllegalArgumentException("arg must not be null");
            }
            this.arg = arg;
        }
        
        public String getArg() {
            return arg;
        }
    }
}