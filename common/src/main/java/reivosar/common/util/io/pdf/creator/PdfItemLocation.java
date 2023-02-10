package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

class PdfItemLocation extends Model {
    
    private final UnitOfLength x;
    private final UnitOfLength y;
    
    PdfItemLocation(final float x, final float y) {
        this.x = UnitOfLength.fromMmLength(x);
        this.y = UnitOfLength.fromMmLength(y);
    }
    
    float x() {
        return x.mmLength();
    }
    
    float y() {
        return y.mmLength();
    }
}
