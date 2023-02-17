package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

class PdfItemLocation extends Model {
    
    private final UnitOfLength xCoordination;
    private final UnitOfLength yCoordination;
    
    PdfItemLocation(final float xCoordination, final float yCoordination) {
        this.xCoordination = UnitOfLength.fromMmLength(xCoordination);
        this.yCoordination = UnitOfLength.fromMmLength(yCoordination);
    }
    
    float xCoordination() {
        return xCoordination.toMmLength();
    }
    
    float yCoordination() {
        return yCoordination.toMmLength();
    }
}
