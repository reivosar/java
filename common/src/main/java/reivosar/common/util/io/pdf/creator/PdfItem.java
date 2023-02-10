package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

class PdfItem extends Model {
    private final int page;
    private final PdfItemLocation pdfItemLocation;
    private final PdfItemSize pdfItemSize;
    
    PdfItem(final int page, final PdfItemLocation pdfItemLocation, final PdfItemSize pdfItemSize) {
        this.page = page;
        this.pdfItemLocation = pdfItemLocation;
        this.pdfItemSize = pdfItemSize;
    }
    
    int page() {
        return page;
    }
    
    float x() {
        return pdfItemLocation.x();
    }
    
    float y() {
        return pdfItemLocation.y();
    }
    
    float width() {
        return pdfItemSize.width();
    }
    
    float height() {
        return pdfItemSize.height();
    }
}
