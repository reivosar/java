package reivosar.common.util.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class ClassPathResourcesTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldThrowExceptionWhenGivenNull() {
            Assertions.assertThrows(NullPointerException.class, () -> new ClassPathResources(null));
        }
        
        @Test
        void shouldCreateClassPathResourcesObjectWhenGivenEmptyCollection() {
            ClassPathResources resources = new ClassPathResources(new FileExtension("class"));
            Assertions.assertNotNull(resources);
        }
    }
    
    @Nested
    class FilterByFilenameTests {
        
        private final ClassPathResources testClass = new ClassPathResources(new FileExtension("json"));
        
        @Test
        void shouldThrowExceptionWhenGivenNull() {
            Assertions.assertThrows(NullPointerException.class, () -> this.testClass.filterByFilename(null));
        }
        
        @Test
        void shouldBeReturnEmptyCollectionWhenCacheIsEmpty() {
            // GIVEN
            String fileName = "not-exists.json";
            // WHEN
            Collection<ResourceFile> resourceFiles = this.testClass.filterByFilename(fileName);
            // THEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(0, resourceFiles.size());
        }
        
        @Test
        void shouldBeReturnEmptyCollectionWhenCacheIsNotEmpty() {
            // GIVEN
            String fileName = "test.json";
            // WHEN
            Collection<ResourceFile> resourceFiles = this.testClass.filterByFilename(fileName);
            // THEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(2, resourceFiles.size());
        }
    }
    
    @Nested
    class FilterByFilePathTests {
        
        private final ClassPathResources testClass = new ClassPathResources(new FileExtension("json"));
        
        @Test
        void shouldThrowNullPointerExceptionWhenGivenNull() {
            Assertions.assertThrows(NullPointerException.class, () -> this.testClass.filterByFilePath(null));
        }
        
        @Test
        void shouldBeReturnEmptyCollectionWhenCacheIsEmpty() {
            // GIVEN
            String fileName = "est.json";
            // WHEN
            Collection<ResourceFile> resourceFiles = this.testClass.filterByFilePath(fileName);
            // THEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(0, resourceFiles.size());
        }
        
        @Test
        void shouldBeReturnNotEmptyCollectionWhenGivenFileNameOnly() {
            // GIVEN
            String fileName = "test.json";
            // WHEN
            Collection<ResourceFile> resourceFiles = this.testClass.filterByFilePath(fileName);
            // THEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(2, resourceFiles.size());
        }
        
        @Test
        void shouldBeReturnNotEmptyCollectionWhenCacheIsNotEmpty() {
            // GIVEN
            String fileName = "/templates/pdf/definition/test.json";
            // WHEN
            Collection<ResourceFile> resourceFiles = this.testClass.filterByFilePath(fileName);
            // THEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(1, resourceFiles.size());
        }
    }
    
    @Nested
    class ResourcesFilesTests {
        
        @Test
        void shouldReturnEmptyCollectionWhenCacheIsEmpty() {
            // WHEN
            ClassPathResources resources = new ClassPathResources(new FileExtension("not-exists-file-extension"));
            // THEN
            Collection<ResourceFile> resourceFiles = resources.resourceFiles();
            // GIVEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(0, resourceFiles.size());
        }
        
        @Test
        void shouldReturnNotEmptyCollectionWhenCacheNotEmpty() {
            // WHEN
            ClassPathResources resources = new ClassPathResources(new FileExtension("json"));
            // THEN
            Collection<ResourceFile> resourceFiles = resources.resourceFiles();
            // GIVEN
            Assertions.assertNotNull(resourceFiles);
            Assertions.assertEquals(2, resourceFiles.size());
        }
    }
}