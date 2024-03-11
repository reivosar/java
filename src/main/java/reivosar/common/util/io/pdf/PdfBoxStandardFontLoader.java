package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

class PdfBoxStandardFontLoader extends FontLoader {
    private final PDFont pdFont;
    
    PdfBoxStandardFontLoader(final PDFont pdFont) {
        this.pdFont = pdFont;
    }
    
    @Override
    PDFont load() {
        return pdFont;
    }
}
