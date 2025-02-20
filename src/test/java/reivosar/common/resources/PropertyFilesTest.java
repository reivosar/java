package reivosar.common.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PropertyFilesTest {

    @Nested
    class TestForGetProperty {

        @ParameterizedTest
        @MethodSource("testParameters")
        void test(final String key, final Optional<String> expected) {
            assertEquals(PropertyFiles.getProperty(key), expected);
        }

        public static Stream<Arguments> testParameters() {
            return Stream.of(
                    arguments("test1_ng", Optional.empty()),
                    arguments("test1_string", Optional.of("test1_string")),
                    arguments("test1_number", Optional.of("1")),
                    arguments("test1_boolean", Optional.of("true")),
                    arguments("test2_ng", Optional.empty()),
                    arguments("test2_string", Optional.of("test2_string")),
                    arguments("test2_number", Optional.of("2")),
                    arguments("test2_boolean", Optional.of("false"))
            );
        }
    }

    @Nested
    class FindFileMethodTests {

        @Test
        void shouldFindPropertyFileByFullName() throws Exception {
            PropertyFile foundFile = PropertyFiles.findFile("test1.properties");
            assertNotNull(foundFile);
            assertEquals("test1.properties", foundFile.fileName());
        }

        @Test
        void shouldFindPropertyFileByBaseName() throws Exception {
            PropertyFile foundFile = PropertyFiles.findFile("test2");
            assertNotNull(foundFile);
            assertEquals("test2.properties", foundFile.fileName());
        }

        @Test
        void shouldThrowFileNotFoundExceptionWhenFileNotFound() {
            assertThrows(FileNotFoundException.class, () -> PropertyFiles.findFile("nonexistent.properties"));
        }

        @Test
        void shouldThrowNullPointerExceptionWhenNameIsNull() {
            assertThrows(NullPointerException.class, () -> PropertyFiles.findFile(null));
        }
    }

    @Nested
    class TestForGetPropertyWithDefaultValue {

        @ParameterizedTest
        @MethodSource("testParameters")
        void test(final String key, final String defaultValue, final String expected) {
            assertEquals(PropertyFiles.getProperty(key, defaultValue), expected);
        }

        public static Stream<Arguments> testParameters() {
            return Stream.of(
                    arguments("test1_ng", "default", "default"),
                    arguments("test1_string", "default", "test1_string"),
                    arguments("test1_number", "default", "1"),
                    arguments("test1_boolean", "default", "true"),
                    arguments("test2_ng", "default", "default"),
                    arguments("test2_string", "default", "test2_string"),
                    arguments("test2_number", "default", "2"),
                    arguments("test2_boolean", "default", "false")
            );
        }
    }

    @Nested
    class TestForContainsKey {

        @ParameterizedTest
        @MethodSource("testParameters")
        void test(final String key, final boolean expected) {
            assertEquals(PropertyFiles.containsKey(key), expected);
        }

        public static Stream<Arguments> testParameters() {
            return Stream.of(
                    arguments("test1_ng", false),
                    arguments("test1_string", true),
                    arguments("test1_number", true),
                    arguments("test1_boolean", true),
                    arguments("test2_ng", false),
                    arguments("test2_string", true),
                    arguments("test2_number", true),
                    arguments("test2_boolean", true)
            );
        }
    }
}