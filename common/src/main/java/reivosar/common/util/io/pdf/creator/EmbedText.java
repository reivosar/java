package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.Closeable;
import java.io.IOException;

record EmbedText(TextContent textContent, TextFont textFont, TextAlign textAlign) implements Closeable {
    
    PDFont pdFont() {
        return textFont.pdFont();
    }
    
    int fontSize() {
        return textFont.size();
    }
    
    EmbedText rebuildWithFontSizeCalculation(final PdfItem pdfItem) {
        int result = fontSize();
        while (result > 0) {
            if (pdfItem.width() - calcTextWidth(result) < 1f) {
                result--;
                continue;
            }
            if (pdfItem.height() - calcTextHeight(result) < 1f) {
                result--;
                continue;
            }
            break;
        }
        if (result == fontSize()) {
            return this;
        }
        close();
        return new EmbedText(textContent(), new TextFont(result), textAlign());
    }
    
    float width() {
        return calcTextWidth(fontSize());
    }
    
    private float calcTextWidth(final int fontSize) {
        try {
            return UnitOfLength
                    .fromPtLength(pdFont().getStringWidth(textContent().asString()) * fontSize / 1000f)
                    .mmLength();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    float height() {
        return calcTextHeight(fontSize());
    }
    
    private float calcTextHeight(final int fontSize) {
        return UnitOfLength
                .fromPtLength(pdFont().getFontDescriptor().getFontBoundingBox().getHeight() * fontSize / 1000f)
                .inchLength();
    }
    
    @Override
    public void close() {
        this.textFont().close();
    }
}
