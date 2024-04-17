package reivosar.common.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceFilesTest {
    
    @Nested
    class TestForFindByName {
        @Test
        void test() {
            Collection<ResourceFile> resourceFiles = ClassPathResourcesScanner
                    .scanBy(new FileExtension("properties"))
                    .filterByFileName("test1.properties");
            assertEquals(1, resourceFiles.size());
        }
    }
    
    @Nested
    class TestForFindByPath {
        @Test
        void test() {
            Collection<ResourceFile> resourceFiles = ClassPathResourcesScanner
                    .scanBy(new FileExtension("pdf"))
                    .filterByFilePath("/templates/pdf/template/test.pdf");
            assertEquals(1, resourceFiles.size());
        }
    }
}