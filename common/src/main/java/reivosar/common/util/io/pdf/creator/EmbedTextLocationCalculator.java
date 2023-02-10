package reivosar.common.util.io.pdf.creator;

class EmbedTextLocationCalculator {
    
    private final PdfItem pdfItem;
    private final EmbedText embedText;
    
    EmbedTextLocationCalculator(final PdfItem pdfItem, final EmbedText embedText) {
        this.pdfItem = pdfItem;
        this.embedText = embedText;
    }
    
    float calcXPosition() {
        return UnitOfLength.fromMmLength(calcForEachAlignType()).ptLength();
    }
    
    private float calcForEachAlignType() {
        return switch (embedText.textAlign()) {
            case LEFT -> pdfItem.x() - 0.5f;
            case RIGHT -> pdfItem.x() * pdfItem.width() - embedText.width() - 0.5f;
            default -> ((pdfItem.width() - embedText.width()) / 2) * pdfItem.x();
        };
    }
    
    float calcYPosition() {
        float itemHeight = pdfItem.height();
        float textHeight = embedText.height();
        float margin = (itemHeight / textHeight) / 2;
        float result = pdfItem.y();
        if (margin > 0) {
            result += margin;
        }
        return result;
    }
}
