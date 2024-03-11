package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * A record that encapsulates the font settings for text in a PDF document, including the font name and size.
 * This record simplifies the management of font information for text rendering in PDFs.
 *
 * @param fontName The name of the font.
 * @param fontSize The size of the font.
 */
public record TextFont(String fontName, int fontSize) {
    
    PDFont pdFont() {
        return FontType.of(fontName).loadFont();
    }
}
