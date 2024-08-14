package reivosar.common.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ResourceFileTest {

    @TempDir
    File tempDir;

    @Nested
    class FileNameMethodTests {

        @Test
        void shouldReturnFileName() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            assertEquals(file.getName(), resourceFile.fileName());
        }
    }

    @Nested
    class ToFileMethodTests {
        @Test
        void shouldReturnFile() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            assertEquals(file, resourceFile.toFile());
        }
    }

    @Nested
    class BaseNameMethodTests {

        @Test
        void shouldReturnBaseName() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            assertEquals("testFile", resourceFile.baseName());
        }
    }

    @Nested
    class MatchFilenameMethodTests {

        @Test
        void shouldMatchFilename() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            assertTrue(resourceFile.matchFilename("testFile.txt"));
        }

        @Test
        void shouldNotMatchIncorrectFilename() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            assertFalse(resourceFile.matchFilename("wrongName.txt"));
        }
    }

    @Nested
    class MatchUnixFilePathMethodTests {

        @Test
        void shouldMatchUnixFilePath() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            String unixFilePath = file.getAbsolutePath().replace('\\', '/');
            assertTrue(resourceFile.matchUnixFilePath(unixFilePath));
        }

        @Test
        void shouldNotMatchIncorrectUnixFilePath() throws IOException {
            File file = createTempFile();
            ResourceFile resourceFile = new ResourceFile(file);
            assertFalse(resourceFile.matchUnixFilePath("/wrong/path"));
        }
    }

    @Nested
    class UseInputStreamMethodTests {

        @Test
        void shouldUseInputStreamSuccessfully() throws IOException {
            String content = "Sample content";
            File file = createTempFileWithContent(content);
            ResourceFile resourceFile = new ResourceFile(file);

            resourceFile.useInputStream(inputStream -> {
                byte[] buffer = new byte[content.length()];
                int bytesRead;
                try {
                    bytesRead = inputStream.read(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                assertEquals(content, new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
            });
        }

        @Test
        void shouldNotThrowExceptionWhenUseInputStreamFails() {
            File nonExistentFile = new File("nonExistentFile.txt");
            ResourceFile resourceFile = new ResourceFile(nonExistentFile);

            resourceFile.useInputStream(inputStream -> {
                // No operation as the file doesn't exist
            });

            // No assertion needed, the test passes if no exception is thrown
        }
    }

    @Nested
    class ConstructorTests {

        @Test
        void shouldThrowNullPointerExceptionWhenFileIsNull() {
            assertThrows(NullPointerException.class, () -> new ResourceFile(null));
        }
    }

    private File createTempFile() throws IOException {
        File tempFile = new File(tempDir, "testFile.txt");
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
