package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.font.PDFont;
import reivosar.common.util.model.Model;

import java.io.Closeable;
import java.io.IOException;

class EmbedText extends Model implements Closeable {
    
    private final TextContent textContent;
    private final TextFont textFont;
    private final TextAlign textAlign;
    
    EmbedText(final TextContent textContent, final TextFont textFont, final TextAlign textAlign) {
        this.textContent = textContent;
        this.textFont = textFont;
        this.textAlign = textAlign;
    }
    
    PDFont pdFont() {
        return textFont.pdFont();
    }
    
    int fontSize() {
        return textFont.size();
    }
    
    TextContent text() {
        return textContent;
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
        return new EmbedText(text(), new TextFont(result), textAlign);
    }
    
    float width() {
        return calcTextWidth(fontSize());
    }
    
    private float calcTextWidth(final int fontSize) {
        try {
            return UnitOfLength
                    .fromPtLength(pdFont().getStringWidth(textContent.asString()) * fontSize / 1000f)
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
    
    TextAlign textAlign() {
        return textAlign;
    }
    
    @Override
    public void close() {
        this.textFont.close();
    }
}
