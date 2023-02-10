package reivosar.common.util.io.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.io.json.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class JsonUtilTest {
    
    private static File getJsonFile(final String jsonName) {
        final URL fileURL = Thread.currentThread().getContextClassLoader().getResource(jsonName);
        return new File(Objects.requireNonNull(fileURL).getPath());
    }
    
    record Artists(String group, List<Member> members, Favorite favorite) {
    }
    
    record Member(String name, String birthday) {
    }
    
    record Favorite(String album, String single) {
    }
    
    @Nested
    class TestForDeserializeMethod {
        
        @Test
        void shouldBeThrownNullPointerException_when_argumentJsonIsNull() {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> JsonUtil.deserialize((File) null, String.class),
                    "json must not be null");
        }
        
        @Test
        void shouldBeThrownNullPointerException_when_argumentValueTypeIsNull() {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> JsonUtil.deserialize(getJsonFile("test.json"), null),
                    "valueType must not be null");
        }
        
        @Test
        void shouldBeThrownJsonHandlingException_when_jsonFileIsNotExists() {
            Assertions.assertThrows(
                    JsonUtil.JsonHandlingException.class,
                    () -> JsonUtil.deserialize(new File("not-exists.json"), Artists.class),
                    "Failed to handle json file");
        }
        
        @Test
        void shouldBeMappedOfJsonToMappingClass_when_jsonLoadingIsSuccess() {
            // GIVEN
            final File jsonFile = getJsonFile("test.json");
            final Class<Artists> mappingClass = Artists.class;
            // WHEN
            Artists actual = JsonUtil.deserialize(jsonFile, mappingClass);
            // THEN
            final Artists expected = new Artists(
                    "The Beatles",
                    Arrays.asList(
                            new Member("John Lennon", "1940/10/9"),
                            new Member("Paul McCartney", "1942/6/18"),
                            new Member("George Harrison", "1943/2/25"),
                            new Member("Ringo Starr", "1940/7/7")),
                    new Favorite("Revolver", "Hello, Goodbye / I Am the Walrus"));
            Assertions.assertEquals(expected, actual);
        }
    }
    
    @Nested
    class TestForReadMethod {
        
        @Test
        void shouldBeThrownNullPointerException_when_argumentFileIsNull() {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> JsonUtil.read(null),
                    "json must not be null");
        }
        
        @Test
        void shouldBeReturnedEmptyValue_when_fieldNamesIsEmpty() throws IOException {
            // GIVEN
            final String json = Files.readString(getJsonFile("test.json").toPath());
            // WHEN
            final String actual = JsonUtil.read(json);
            // THEN
            Assertions.assertEquals("", actual);
        }
        
        @Test
        void shouldBeReturnedEmptyValue_when_fieldNamesIsNotExists() throws IOException {
            // GIVEN
            final String json = Files.readString(getJsonFile("test.json").toPath());
            // WHEN
            final String actual = JsonUtil.read(json, "not-exists");
            // THEN
            Assertions.assertEquals("", actual);
        }
        
        @Test
        void shouldBeReturnedExpectedValue_when_fieldNamesIsOne() throws IOException {
            // GIVEN
            final String json = Files.readString(getJsonFile("test.json").toPath());
            // WHEN
            final String actual = JsonUtil.read(json, "group");
            // THEN
            Assertions.assertEquals("The Beatles", actual);
        }
        
        @Test
        void shouldBeReturnedExpectedValue_when_fieldNamesAndIndex() throws IOException {
            // GIVEN
            final String json = Files.readString(getJsonFile("test.json").toPath());
            // WHEN
            final String actual = JsonUtil.read(json, "members", 0, "name");
            // THEN
            Assertions.assertEquals("John Lennon", actual);
        }
        
        @Test
        void shouldBeReturnedExpectedValue_when_fieldNamesIsTwo() throws IOException {
            // GIVEN
            final String json = Files.readString(getJsonFile("test.json").toPath());
            // WHEN
            final String actual = JsonUtil.read(json, "favorite", "single");
            // THEN
            Assertions.assertEquals("Hello, Goodbye / I Am the Walrus", actual);
        }
    }
    
    @Nested
    class TestForSerializeMethod {
        
        @Test
        void shouldBeThrownNullPointerException_when_argumentObjectIsNull() {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> JsonUtil.serialize(null),
                    "object must not be null");
        }
        
        @Test
        void shouldBeMappedOfClassToJson_when_jsonSerializingIsSuccess() {
            // GIVEN
            final Artists artists = new Artists(
                    "The Beatles",
                    Arrays.asList(
                            new Member("John Lennon", "1940/10/9"),
                            new Member("Paul McCartney", "1942/6/18"),
                            new Member("George Harrison", "1943/2/25"),
                            new Member("Ringo Starr", "1940/7/7")),
                    new Favorite("Revolver", "Hello, Goodbye / I Am the Walrus"));
            // WHEN
            String actual = JsonUtil.serialize(artists);
            // THEN
            Assertions.assertEquals(
                    "{" +
                            "\"group\":\"The Beatles\"," +
                            "\"members\":[" +
                            "{\"name\":\"John Lennon\",\"birthday\":\"1940/10/9\"}," +
                            "{\"name\":\"Paul McCartney\",\"birthday\":\"1942/6/18\"}," +
                            "{\"name\":\"George Harrison\",\"birthday\":\"1943/2/25\"}," +
                            "{\"name\":\"Ringo Starr\",\"birthday\":\"1940/7/7\"}" +
                            "]," +
                            "\"favorite\":{" +
                            "\"album\":\"Revolver\"," +
                            "\"single\":\"Hello, Goodbye / I Am the Walrus\"" +
                            "}" +
                            "}",
                    actual);
        }
    }
}
