package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.Arrays;

enum FontType {
    TIMES_ROMAN("times_roman", new PdfBoxStandardFontLoader(PDType1Font.TIMES_ROMAN)),
    HELVETICA("helvetica", new PdfBoxStandardFontLoader(PDType1Font.HELVETICA)),
    COURIER("courier", new PdfBoxStandardFontLoader(PDType1Font.COURIER)),
    SYMBOL("symbol", new PdfBoxStandardFontLoader(PDType1Font.SYMBOL)),
    JAPANESE("japanese", new IpaFontFileLoader()),
    ;
    
    private final String fontName;
    private final FontLoader fontLoader;
    
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
