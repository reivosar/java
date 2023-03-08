package reivosar.common.util.io.pdf.creator;

record PdfItem (PdfItemLocation pdfItemLocation, PdfItemSize pdfItemSize) {
    
    float xCoordination() {
        return pdfItemLocation.xCoordination();
    }
    
    float yCoordination() {
        return pdfItemLocation.yCoordination();
    }
    
    float width() {
        return pdfItemSize.width();
    }
    
    float height() {
        return pdfItemSize.height();
    }
}
