package reivosar.common.util.io.pdf;

import java.util.Arrays;

/**
 * Defines the possible text alignment options within a PDF document or similar context.
 * The enum constants represent the standard text alignments: left-aligned, centered, and right-aligned text.
 */
public enum TextAlign {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right");
    
    private final String align;
    
    TextAlign(final String align) {
        this.align = align;
    }

    /**
     * Returns a {@code TextAlign} enum constant based on the given string alignment value.
     * This method allows for case-insensitive matching of alignment values, defaulting to {@code CENTER} if no match is found.
     *
     * @param align The string representation of the desired text alignment.
     * @return The corresponding {@code TextAlign} enum constant, or {@code CENTER} if no match is found.
     */
    public static TextAlign of(final String align) {
        return Arrays.stream(values()).
                filter(e -> e.align.equalsIgnoreCase(align))
                .findFirst()
                .orElse(CENTER);
    }
}
