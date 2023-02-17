package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

class PdfItemSize extends Model {
    
    private final UnitOfLength width;
    private final UnitOfLength height;
    
    PdfItemSize(final float widthAsMm, final float heightAsMm) {
        this.width = UnitOfLength.fromMmLength(widthAsMm);
        this.height = UnitOfLength.fromMmLength(heightAsMm);
    }
    
    float width() {
        return width.toMmLength();
    }
    
    float height() {
        return height.toMmLength();
    }
}
