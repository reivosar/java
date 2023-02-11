package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.Closeable;
import java.io.IOException;

class PdfCreateInvoker implements Closeable {
    
    private final PdfItem pdfItem;
    private final EmbedText embedText;
    
    PdfCreateInvoker(final PdfItem pdfItem, final EmbedText embedText) {
        this.pdfItem = pdfItem;
        this.embedText = embedText.rebuildWithFontSizeCalculation(pdfItem);
    }
    
    void invoke(final PDPageContentStream stream) throws IOException {
        try {
            stream.beginText();
            stream.setFont(embedText.pdFont(), embedText.fontSize());
            final EmbedTextLocationCalculator calculator = new EmbedTextLocationCalculator(pdfItem, embedText);
            stream.newLineAtOffset(calculator.calcXPosition(), calculator.calcYPosition());
            stream.showText(embedText.textContent().asString());
        } finally {
            stream.endText();
        }
    }
    
    @Override
    public void close() {
        this.embedText.close();
    }
}
