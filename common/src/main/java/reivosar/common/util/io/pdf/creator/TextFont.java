package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.font.PDFont;

record TextFont(String fontName, int fontSize) {
    
    PDFont pdFont() {
        return FontType.of(fontName).loadFont();
    }
}
