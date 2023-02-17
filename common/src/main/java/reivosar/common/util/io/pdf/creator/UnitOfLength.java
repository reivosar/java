package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

class UnitOfLength extends Model {
    
    private static final float MM_PER_INCH = 25.4f;
    private static final float POINTS_PER_INCH = 72.0f;
    private static final float POINTS_PER_MM = 1 / MM_PER_INCH * POINTS_PER_INCH;
    
    private final float mm;
    
    private UnitOfLength(final float mm) {
        this.mm = mm;
    }
    
    static UnitOfLength fromMmLength(final float mm) {
        return new UnitOfLength(mm);
    }
    
    static UnitOfLength fromPtLength(final float pt) {
        return new UnitOfLength(pt / POINTS_PER_INCH * MM_PER_INCH);
    }
    
    float toMmLength() {
        return mm;
    }
    
    float toInchLength() {
        return mm * MM_PER_INCH / POINTS_PER_INCH;
    }
    
    float toPtLength() {
        return mm * POINTS_PER_MM;
    }
    
}
