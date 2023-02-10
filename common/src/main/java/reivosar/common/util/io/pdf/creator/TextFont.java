package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import reivosar.common.util.model.Model;

import java.io.Closeable;
import java.io.IOException;

class TextFont extends Model implements Closeable {
    
    private final PDDocument dummyDocument;
    private final int size;
    
    TextFont(final int size) {
        this.dummyDocument = new PDDocument();
        this.size = size;
    }
    
    PDFont pdFont() {
        try {
            return PDType0Font.load(dummyDocument, TextFont.class.getResourceAsStream("/pdf/font/ipaexg.ttf"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    int size() {
        return size;
    }
    
    @Override
    public void close() {
        try {
            this.dummyDocument.close();
        } catch (IOException ignored) {
            // It is impossible for this process to generate an error.
        }
    }
}
