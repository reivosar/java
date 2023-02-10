package reivosar.common.util.io.pdf.creator;

import java.util.Arrays;

enum TextAlign {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right");
    
    private final String align;
    
    TextAlign(final String align) {
        this.align = align;
    }
    
    static TextAlign of(final String align) {
        return Arrays.stream(values()).
                filter(e -> e.align.equalsIgnoreCase(align))
                .findFirst()
                .orElse(CENTER);
    }
}
