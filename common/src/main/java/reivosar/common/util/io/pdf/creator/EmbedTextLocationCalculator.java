package reivosar.common.util.io.pdf.creator;

record EmbedTextLocationCalculator(PdfItem pdfItem, EmbedText embedText) {
    
    float calcXPosition() {
        return UnitOfLength.fromMmLength(calcForEachAlignType()).ptLength();
    }
    
    private float calcForEachAlignType() {
        return switch (embedText.textAlign()) {
            case LEFT -> pdfItem.xCoordination() - 0.5f;
            case RIGHT -> pdfItem.xCoordination() * pdfItem.width() - embedText.width() - 0.5f;
            default -> ((pdfItem.width() - embedText.width()) / 2) * pdfItem.xCoordination();
        };
    }
    
    float calcYPosition() {
        float itemHeight = pdfItem.height();
        float textHeight = embedText.height();
        float margin = (itemHeight / textHeight) / 2;
        float result = pdfItem.yCoordination();
        if (margin > 0) {
            result += margin;
        }
        return result;
    }
}
