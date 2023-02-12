package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class TrueTypeFontFileLoader extends FontLoader {
    
    private final PDFont pdFont;
    
    TrueTypeFontFileLoader(final String fontFilePath) {
        try (final PDDocument dummyDocument = new PDDocument();
             final InputStream inputStream =
                     Objects.requireNonNull(TrueTypeFontFileLoader.class.getResourceAsStream(fontFilePath))) {
            this.pdFont = PDTrueTypeFont.load(dummyDocument, inputStream, Encoding.getInstance(COSName.STANDARD_ENCODING));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    PDFont load() {
        return pdFont;
    }
}
