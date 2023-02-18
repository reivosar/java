package reivosar.common.util.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LocalCacheStoreTest {
    
    @Nested
    class GetTest {
        
        @Test
        void shouldReturnValuesAssociatedWithKeyWhenKeyExists() {
            // Given
            Map<String, Collection<String>> map = new HashMap<>();
            map.put("key", List.of("value1", "value2"));
            LocalCacheStore<String, String> cache = new LocalCacheStore<>(map);
            
            // When
            Collection<String> values = cache.get("key");
            
            // Then
            assertIterableEquals(List.of("value1", "value2"), values);
        }
        
        @Test
        void shouldReturnEmptyCollectionWhenKeyDoesNotExist() {
            // Given
            Map<String, Collection<String>> map = new HashMap<>();
            LocalCacheStore<String, String> cache = new LocalCacheStore<>(map);
            
            // When
            Collection<String> values = cache.get("key");
            
            // Then
            assertTrue(values.isEmpty());
        }
        
        @Test
        void shouldThrowNullPointerExceptionWhenKeyIsNull() {
            // Given
            LocalCacheStore<String, String> cache = new LocalCacheStore<>(new HashMap<>());
            
            // Then
            assertThrows(NullPointerException.class, () -> cache.get(null));
        }
    }
    
    @Nested
    class ContainsKeyTest {
        
        @Test
        void shouldReturnTrueWhenKeyExists() {
            // Given
            Map<String, Collection<String>> map = new HashMap<>();
            map.put("key", List.of("value1", "value2"));
            LocalCacheStore<String, String> cache = new LocalCacheStore<>(map);
            
            // When
            boolean result = cache.containsKey("key");
            
            // Then
            assertTrue(result);
        }
        
        @Test
        void shouldReturnFalseWhenKeyDoesNotExist() {
            // Given
            Map<String, Collection<String>> map = new HashMap<>();
            LocalCacheStore<String, String> cache = new LocalCacheStore<>(map);
            
            // When
            boolean result = cache.containsKey("key");
            
            // Then
            assertFalse(result);
        }
        
        @Test
        void shouldThrowNullPointerExceptionWhenKeyIsNull() {
            // Given
            LocalCacheStore<String, String> cache = new LocalCacheStore<>(new HashMap<>());
            
            // Then
            assertThrows(NullPointerException.class, () -> cache.containsKey(null));
        }
    }
    
    
    @Nested
    class ExistsMethod {
        LocalCacheStore<String, String> store;
        
        @BeforeEach
        void setUp() {
            Map<String, Collection<String>> cacheMap = new HashMap<>();
            cacheMap.put("key1", Arrays.asList("value1", "value2"));
            cacheMap.put("key2", Arrays.asList("value3", "value4", "value5"));
            cacheMap.put("key3", List.of("value6"));
            store = new LocalCacheStore<>(cacheMap);
        }
        
        @Test
        void shouldReturnTrueForExistingKeyWithNonEmptyValueCollection() {
            assertTrue(store.exists("key1"));
        }
        
        @Test
        void shouldReturnFalseForExistingKeyWithEmptyValueCollection() {
            assertTrue(store.exists("key3"));
        }
        
        @Test
        void shouldReturnFalseForNonExistingKey() {
            assertFalse(store.exists("key4"));
        }
        
        @Test
        void shouldThrowNullPointerExceptionForNullKey() {
            assertThrows(NullPointerException.class, () -> store.exists(null));
        }
    }
    
    @Nested
    class KeySetMethod {
        @Test
        void shouldReturnEmptySetWhenCacheMapIsEmpty() {
            // Given
            Map<String, Collection<Integer>> emptyMap = new HashMap<>();
            LocalCacheStore<String, Integer> cacheStore = new LocalCacheStore<>(emptyMap);
            
            // When
            Collection<String> result = cacheStore.keySet();
            
            // Then
            assertTrue(result.isEmpty());
        }
        
        @Test
        void shouldReturnSetOfAllKeysInCacheMap() {
            // Given
            Map<String, Collection<Integer>> map = new HashMap<>();
            map.put("A", Set.of(1, 2, 3));
            map.put("B", Set.of(4, 5));
            LocalCacheStore<String, Integer> cacheStore = new LocalCacheStore<>(map);
            
            // When
            Collection<String> result = cacheStore.keySet();
            
            // Then
            assertEquals(Set.of("A", "B"), new HashSet<>(result));
        }
    }
    
    @Nested
    class PutMethod {
        @Test
        void shouldThrowNullPointerExceptionWhenKeyIsNull() {
            // Given
            Map<String, Collection<Integer>> map = new HashMap<>();
            LocalCacheStore<String, Integer> cacheStore = new LocalCacheStore<>(map);
            
            // When, Then
            assertThrows(NullPointerException.class, () -> cacheStore.put(null, 1));
        }
        
        @Test
        void shouldThrowIllegalArgumentExceptionWhenValueIsNull() {
            // Given
            Map<String, Collection<Integer>> map = new HashMap<>();
            LocalCacheStore<String, Integer> cacheStore = new LocalCacheStore<>(map);
            
            // When, Then
            assertThrows(NullPointerException.class, () -> cacheStore.put("A", null));
        }
        
        @Test
        void shouldAddValueToCollectionForGivenKeyInCacheMap() {
            // Given
            Map<String, Collection<Integer>> map = new HashMap<>();
            map.put("A", new LinkedHashSet<>(List.of(1, 2, 3)));
            LocalCacheStore<String, Integer> cacheStore = new LocalCacheStore<>(map);
            
            // When
            cacheStore.put("A", 4);
            
            // Then
            assertEquals(Set.of(1, 2, 3, 4), new HashSet<>(cacheStore.get("A")));
        }
        
        @Test
        void shouldCreateNewCollectionForGivenKeyAndAddValueWhenKeyIsNotPresentInCacheMap() {
            // Given
            Map<String, Collection<Integer>> map = new HashMap<>();
            LocalCacheStore<String, Integer> cacheStore = new LocalCacheStore<>(map);
            
            // When
            cacheStore.put("A", 1);
            
            // Then
            assertEquals(Set.of(1), new HashSet<>(cacheStore.get("A")));
        }
    }
}