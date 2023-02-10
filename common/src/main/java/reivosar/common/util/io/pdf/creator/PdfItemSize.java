package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

class PdfItemSize extends Model {
    
    private final UnitOfLength width;
    private final UnitOfLength height;
    
    PdfItemSize(final float width, final float height) {
        this.width = UnitOfLength.fromMmLength(width);
        this.height = UnitOfLength.fromMmLength(height);
    }
    
    float width() {
        return width.mmLength();
    }
    
    float height() {
        return height.mmLength();
    }
}
