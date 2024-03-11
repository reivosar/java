package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

class PdfBoxStandardFontLoader extends FontLoader {
    private final PDFont pdFont;
    
    PdfBoxStandardFontLoader(final String fontName) {
        this.pdFont = new PDType1Font(Standard14Fonts.getMappedFontName(fontName));
    }
    
    @Override
    PDFont load() {
        return pdFont;
    }
}
