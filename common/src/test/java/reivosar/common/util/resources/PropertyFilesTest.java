package reivosar.common.util.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFilesTest {
    
    @Nested
    class TestForGetLoadedPropertyFiles {
        
        @Test
        void test() {
            final Collection<ResourceFile> actual = PropertyFiles.loadedPropertyFiles();
            assertEquals(actual.size(), 2);
            assertEquals(Arrays.asList("test1.properties", "test2.properties"),
                    actual.stream().map(ResourceFile::fileName).sorted().collect(Collectors.toList()));
        }
    }
    
    @Nested
    class TestForGetProperty {
        
        @Test
        void test() {
            assertEquals(PropertyFiles.getProperty("test1_ng"), Optional.empty());
            assertEquals(PropertyFiles.getProperty("test1_string"), Optional.of("test1_string"));
            assertEquals(PropertyFiles.getProperty("test1_number"), Optional.of("1"));
            assertEquals(PropertyFiles.getProperty("test1_boolean"), Optional.of("true"));
            
            assertEquals(PropertyFiles.getProperty("test2_ng"), Optional.empty());
            assertEquals(PropertyFiles.getProperty("test2_string"), Optional.of("test2_string"));
            assertEquals(PropertyFiles.getProperty("test2_number"), Optional.of("2"));
            assertEquals(PropertyFiles.getProperty("test2_boolean"), Optional.of("false"));
        }
    }
    
    @Nested
    class TestForContainsKey {
        
        @Test
        void test() {
            assertFalse(PropertyFiles.containsKey("test1_ng"));
            assertTrue(PropertyFiles.containsKey("test1_string"));
            assertTrue(PropertyFiles.containsKey("test1_number"));
            assertTrue(PropertyFiles.containsKey("test1_boolean"));
            
            assertFalse(PropertyFiles.containsKey("test2_ng"));
            assertTrue(PropertyFiles.containsKey("test2_string"));
            assertTrue(PropertyFiles.containsKey("test2_number"));
            assertTrue(PropertyFiles.containsKey("test2_boolean"));
        }
    }
}