package reivosar.common.document.pdf;

import reivosar.common.data.model.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents metadata for a PDF document. This class provides a structured way
 * to access metadata such as title, author, subject, and other relevant information
 * associated with a PDF document.
 */
public class PdfMetadata extends Model {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static final String TITLE = "Title";
    static final String AUTHOR = "Author";
    static final String SUBJECT = "Subject";
    static final String KEYWORDS = "Keywords";
    static final String CREATION_DATE = "CreationDate";
    static final String MOD_DATE = "ModDate";

    private final Map<String, String> metadata;

    PdfMetadata() {
        this.metadata = new HashMap<>();
    }

    void addMetadata(String key, String value) {
        metadata.put(key, value);
    }

    private Optional<String> getMetadata(String key) {
        return Optional.ofNullable(metadata.get(key));
    }

    /**
     * Gets the title of the PDF document.
     *
     * @return The title, or an empty string if not available.
     */
    public Optional<String> title() {
        return getMetadata(TITLE);
    }

    /**
     * Gets the author of the PDF document.
     *
     * @return The author, or an empty string if not available.
     */
    public Optional<String> author() {
        return getMetadata(AUTHOR);
    }

    /**
     * Gets the subject of the PDF document.
     *
     * @return The subject, or an empty string if not available.
     */
    public Optional<String> subject() {
        return getMetadata(SUBJECT);
    }

    /**
     * Retrieves the keywords associated with the PDF document as a collection.
     * Each keyword is trimmed and empty keywords are omitted. The keywords are
     * assumed to be separated by commas in the metadata.
     *
     * @return A collection of keywords. Returns an empty collection if no keywords are available or if the metadata does not contain keywords.
     */
    public Collection<String> keywords() {
        final Optional<String> keywords = getMetadata(KEYWORDS);
        return keywords.<Collection<String>>map(s -> Arrays.stream(s.split(","))
                .map(String::trim)
                .filter(keyword -> !keyword.isEmpty())
                .collect(Collectors.toList())).orElseGet(List::of);
    }

    /**
     * Gets the creation date of the PDF document.
     *
     * @return The creation date, or an empty string if not available.
     */
    public Optional<LocalDateTime> creationDate() {
        final Optional<String> dateString = getMetadata(CREATION_DATE);
        return dateString.map(d -> LocalDateTime.parse(d, DATE_TIME_FORMATTER));
    }

    /**
     * Gets the last modification date of the PDF document.
     *
     * @return The modification date, or an empty string if not available.
     */
    public Optional<LocalDateTime> modDate() {
        final Optional<String> dateString = getMetadata(MOD_DATE);
        return dateString.map(d -> LocalDateTime.parse(d, DATE_TIME_FORMATTER));
    }
}
