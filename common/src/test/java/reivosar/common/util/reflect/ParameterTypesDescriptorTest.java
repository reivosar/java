package reivosar.common.util.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParameterTypesDescriptorTest {
    
    @Nested
    class GetParameterCountTest {
        
        @Test
        void shouldReturnZeroWhenNoArgs() {
            // given
            ParameterTypesDescriptor descriptor = new ParameterTypesDescriptor();
            
            // when
            int result = descriptor.getParameterCount();
            
            // then
            assertEquals(0, result);
        }
        
        @Test
        void shouldReturnArgCountWhenArgsExist() {
            // given
            ParameterTypesDescriptor descriptor = new ParameterTypesDescriptor(Integer.class, String.class);
            
            // when
            int result = descriptor.getParameterCount();
            
            // then
            assertEquals(2, result);
        }
    }
    @Nested
    @DisplayName("isEqualParameterTypeメソッドのテスト")
    class IsEqualParameterTypeTest {
    
        @Test
        void shouldReturnTrueWhenArgsSameType() {
            // given
            ParameterTypesDescriptor descriptor1 = new ParameterTypesDescriptor(Integer.class, String.class);
            ParameterTypesDescriptor descriptor2 = new ParameterTypesDescriptor(int.class, String.class);
        
            // when
            boolean result = descriptor1.isEqualParameterType(descriptor2);
        
            // then
            assertTrue(result);
        }
    
        @Test
        void shouldReturnTrueWhenArgsDifferentType() {
            // given
            ParameterTypesDescriptor descriptor1 = new ParameterTypesDescriptor(Integer.class, String.class);
            ParameterTypesDescriptor descriptor2 = new ParameterTypesDescriptor(Long.class, String.class);
        
            // when
            boolean result = descriptor1.isEqualParameterType(descriptor2);
        
            // then
            assertTrue(result);
        }
    
        @Test
        void shouldReturnFalseWhenArgsSameTypeDifferentOrder() {
            // given
            ParameterTypesDescriptor descriptor1 = new ParameterTypesDescriptor(123, "test");
            ParameterTypesDescriptor descriptor2 = new ParameterTypesDescriptor("test", 123);
        
            // when
            boolean result = descriptor1.isEqualParameterType(descriptor2);
        
            // then
            assertFalse(result);
        }
    
        @Test
        void shouldReturnFalseWhenArgsDifferentCount() {
            // given
            ParameterTypesDescriptor descriptor1 = new ParameterTypesDescriptor(Integer.class, String.class);
            ParameterTypesDescriptor descriptor2 = new ParameterTypesDescriptor(Integer.class);
        
            // when
            boolean result = descriptor1.isEqualParameterType(descriptor2);
        
            // then
            assertFalse(result);
        }
    }
}