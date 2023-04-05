package reivosar.common.util.collection;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionUtilTest {
    
    @Nested
    class IsEqualCollectionTests {
        
        @Nested
        class WhenComparingEmptyCollections {
            
            @Test
            void shouldReturnTrueForTwoEmptyCollections() {
                // given
                List<Object> emptyList1 = Collections.emptyList();
                List<Object> emptyList2 = Collections.emptyList();
                
                // when
                boolean result = CollectionUtil.isEqualCollection(emptyList1, emptyList2);
                
                // then
                assertTrue(result);
            }
            
            @Test
            void shouldReturnFalseForOneEmptyAndOneNonEmptyCollection() {
                // given
                List<Object> emptyList = Collections.emptyList();
                List<Object> nonEmptyList = Arrays.asList("a", "b", "c");
                
                // when
                boolean result1 = CollectionUtil.isEqualCollection(emptyList, nonEmptyList);
                boolean result2 = CollectionUtil.isEqualCollection(nonEmptyList, emptyList);
                
                // then
                assertFalse(result1);
                assertFalse(result2);
            }
        }
        
        @Nested
        class WhenComparingNonEmptyCollections {
            
            @Test
            void shouldReturnTrueForEqualCollections() {
                // given
                List<String> list1 = Arrays.asList("a", "b", "c");
                List<String> list2 = Arrays.asList("c", "a", "b");
                
                // when
                boolean result = CollectionUtil.isEqualCollection(list1, list2);
                
                // then
                assertTrue(result);
            }
            
            @Test
            void shouldReturnFalseForDifferentCollections() {
                // given
                List<String> list1 = Arrays.asList("a", "b", "c");
                List<String> list2 = Arrays.asList("a", "b", "d");
                
                // when
                boolean result = CollectionUtil.isEqualCollection(list1, list2);
                
                // then
                assertFalse(result);
            }
        }
    }
    
    @Nested
    class TestIsSameOrderCollection {
    
        @Test
        void shouldReturnTrueWhenArgumentsAreNull() {
            assertTrue(CollectionUtil.isSameOrderCollection(null, null));
        }
    
        @Test
        void shouldReturnFalseWhenOneArgumentIsNull() {
            assertFalse(CollectionUtil.isSameOrderCollection(Collections.emptyList(), null));
            assertFalse(CollectionUtil.isSameOrderCollection(null, Collections.emptyList()));
        }
    
        @Test
        void shouldReturnFalseWhenTheNumberOfElementsIsDifferent() {
            List<String> list1 = Arrays.asList("a", "b", "c");
            List<String> list2 = Arrays.asList("a", "b");
            assertFalse(CollectionUtil.isSameOrderCollection(list1, list2));
        }
    
        @Test
        void shouldReturnTrueWhenTheElementsAreContainedInTheSameOrder() {
            List<String> list1 = Arrays.asList("a", "b", "c");
            List<String> list2 = new ArrayList<>(list1);
            assertTrue(CollectionUtil.isSameOrderCollection(list1, list2));
        }
    
        @Test
        void shouldReturnFalseWhenTheElementsAreContainedInDifferentOrder() {
            List<String> list1 = Arrays.asList("a", "b", "c");
            List<String> list2 = Arrays.asList("c", "a", "b");
            assertFalse(CollectionUtil.isSameOrderCollection(list1, list2));
        }
    
        @Test
        void shouldReturnFalseWhenTheTypesOfElementsAreDifferent() {
            List<Object> list1 = Arrays.asList("a", 1, true);
            List<Object> list2 = Arrays.asList("a", 1, false);
            assertFalse(CollectionUtil.isSameOrderCollection(list1, list2));
        }
    
        @Test
        void shouldReturnFalseWhenTheValuesOfElementsAreDifferent() {
            List<String> list1 = Arrays.asList("a", "b", "c");
            List<String> list2 = Arrays.asList("a", "d", "c");
            assertFalse(CollectionUtil.isSameOrderCollection(list1, list2));
        }
    }
}