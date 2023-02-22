package reivosar.common.util.collection;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}