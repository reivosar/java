package reivosar.common.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFileTest {

    @TempDir
    File tempDir;

    @Nested
    class ConstructorTests {

        @Test
        void shouldLoadPropertiesFromResourceFile() throws IOException {
            String content = "key1=value1\nkey2=value2";
            File file = createTempFileWithContent(content);
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertEquals("value1", propertyFile.getProperty("key1").orElse(null));
            assertEquals("value2", propertyFile.getProperty("key2").orElse(null));
        }

        @Test
        void shouldNotThrowExceptionWhenPropertiesFileCannotBeLoaded() {
            File nonExistentFile = new File("nonExistentFile.properties");
            ResourceFile resourceFile = new ResourceFile(nonExistentFile);
            assertDoesNotThrow(() -> new PropertyFile(resourceFile));
        }
    }

    @Nested
    class FileNameMethodTests {

        @Test
        void shouldReturnFileName() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertEquals(file.getName(), propertyFile.fileName());
        }
    }

    @Nested
    class BaseNameMethodTests {

        @Test
        void shouldReturnBaseName() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertEquals("test", propertyFile.baseName());
        }
    }

    @Nested
    class GetPropertyMethodTests {

        @Test
        void shouldReturnPropertyValueWhenKeyExists() throws IOException {
            String content = "key1=value1";
            File file = createTempFileWithContent(content);
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            Optional<String> value = propertyFile.getProperty("key1");
            assertTrue(value.isPresent());
            assertEquals("value1", value.get());
        }

        @Test
        void shouldReturnEmptyOptionalWhenKeyDoesNotExist() throws IOException {
            String content = "key1=value1";
            File file = createTempFileWithContent(content);
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            Optional<String> value = propertyFile.getProperty("nonexistentKey");
            assertFalse(value.isPresent());
        }

        @Test
        void shouldThrowNullPointerExceptionWhenKeyIsNull() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertThrows(NullPointerException.class, () -> propertyFile.getProperty(null));
        }
    }

    @Nested
    class GetPropertyWithDefaultMethodTests {

        @Test
        void shouldReturnPropertyValueWhenKeyExists() throws IOException {
            String content = "key1=value1";
            File file = createTempFileWithContent(content);
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            String value = propertyFile.getProperty("key1", "defaultValue");
            assertEquals("value1", value);
        }

        @Test
        void shouldReturnDefaultValueWhenKeyDoesNotExist() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            String value = propertyFile.getProperty("nonexistentKey", "defaultValue");
            assertEquals("defaultValue", value);
        }

        @Test
        void shouldThrowNullPointerExceptionWhenKeyIsNull() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertThrows(NullPointerException.class, () -> propertyFile.getProperty(null, "defaultValue"));
        }
    }

    @Nested
    class ContainsKeyMethodTests {

        @Test
        void shouldReturnTrueWhenKeyExists() throws IOException {
            String content = "key1=value1";
            File file = createTempFileWithContent(content);
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertTrue(propertyFile.containsKey("key1"));
        }

        @Test
        void shouldReturnFalseWhenKeyDoesNotExist() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertFalse(propertyFile.containsKey("nonexistentKey"));
        }

        @Test
        void shouldThrowNullPointerExceptionWhenKeyIsNull() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            PropertyFile propertyFile = new PropertyFile(resourceFile);

            assertThrows(NullPointerException.class, () -> propertyFile.containsKey(null));
        }
    }

    private File createTempFile() throws IOException {
        File tempFile = new File(tempDir, "test.properties");
        if (tempFile.createNewFile()) {
            return tempFile;
        }
        throw new IOException("Failed to create temp file.");
    }

    private File createTempFileWithContent(String content) throws IOException {
        File tempFile = createTempFile();
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        }
        return tempFile;
    }
}
