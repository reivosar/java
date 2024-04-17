package reivosar.common.document.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

/**
 * A record representing text to be embedded in a PDF document, including content, font, and alignment details.
 * It provides functionality to access font details, calculate appropriate font size based on PDF item dimensions,
 * and compute the width and height of the text.
 *
 * @param textContent The content of the text to be embedded.
 * @param textFont The font of the text, including font name and size.
 * @param textAlign The alignment of the text.
 */
public record EmbedText(TextContent textContent, TextFont textFont, TextAlign textAlign) {
    
    PDFont pdFont() {
        return textFont.pdFont();
    }
    
    int fontSize() {
        return textFont.fontSize();
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
        return new EmbedText(textContent(), new TextFont(textFont.fontName(), result), textAlign());
    }
    
    float width() {
        return calcTextWidth(fontSize());
    }
    
    private float calcTextWidth(final int fontSize) {
        try {
            return UnitOfLength
                    .fromPtLength(pdFont().getStringWidth(textContent().asString()) * fontSize / 1000f)
                    .toMmLength();
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
                .toInchLength();
    }
}
