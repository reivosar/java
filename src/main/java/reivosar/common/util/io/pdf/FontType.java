package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.util.Arrays;

enum FontType {
    TIMES_ROMAN("Times-Roman"),
    TIMES_BOLD("Times-Bold"),
    TIMES_ITALIC("Times-Italic"),
    TIMES_BOLD_ITALIC("Times-BoldItalic"),
    HELVETICA("Helvetica"),
    HELVETICA_BOLD("Helvetica-Bold"),
    HELVETICA_OBLIQUE("Helvetica-Oblique"),
    HELVETICA_BOLD_OBLIQUE("Helvetica-BoldOblique"),
    COURIER("Courier"),
    COURIER_BOLD("Courier-Bold"),
    COURIER_OBLIQUE("Courier-Oblique"),
    COURIER_BOLD_OBLIQUE("Courier-BoldOblique"),
    SYMBOL("Symbol"),
    ZAPF_DINGBATS("ZapfDingbats"),
    JAPANESE("Japanese", new IpaFontFileLoader()),
    ;
    
    private final String fontName;
    private final FontLoader fontLoader;
    
    FontType(final String fontName) {
        this(fontName, new PdfBoxStandardFontLoader(fontName));
    }

    FontType(final String fontName, final FontLoader fontLoader) {
        this.fontName = fontName;
        this.fontLoader = fontLoader;
    }
    
    static FontType of(final String fontName) {
        return Arrays.stream(values())
                .filter(fontType -> fontType.fontName.equalsIgnoreCase(fontName))
                .findFirst()
                .orElse(JAPANESE);
    }
    
    PDFont loadFont() {
        return fontLoader.load();
    }
}
