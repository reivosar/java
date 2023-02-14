package reivosar.common.util.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ResourceFilesTest {
    
    @Nested
    class TestForFindByName {
        @Test
        void test() {
            Collection<ResourceFile> resourceFiles = ClassPathResourcesScanner
                    .scanBy(new FileExtension("properties"))
                    .findByName("test1.properties");
            assertEquals(1, resourceFiles.size());
        }
    }
    
    @Nested
    class TestForFindByPath {
        @Test
        void test() {
            Collection<ResourceFile> resourceFiles = ClassPathResourcesScanner
                    .scanBy(new FileExtension("pdf"))
                    .findByPath("/templates/pdf/template/test.pdf");
            assertEquals(1, resourceFiles.size());
        }
    }
}