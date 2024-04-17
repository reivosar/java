package reivosar.common.document.pdf;

/**
 * A record that encapsulates the content of text, designed to handle various object types as text values.
 * This approach allows for a flexible representation of text content that can be converted to a string format
 * for rendering or processing purposes.
 *
 * @param value The text content value, which can be any object. The object's {@code toString()} method
 *              will be used to convert the value to a String.
 */
public record TextContent(Object value) {
    
    String asString() {
        return (value() != null) ? value().toString() : "";
    }
    
}
