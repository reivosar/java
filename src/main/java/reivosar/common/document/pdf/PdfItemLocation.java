package reivosar.common.document.pdf;

import reivosar.common.data.model.Model;

/**
 * Represents the location of an item within a PDF document, encapsulating the x and y coordinates.
 * This class provides a way to manage the positioning of items in PDF documents, with coordinates specified in millimeters.
 * Inherits from the {@code Model} class, potentially to leverage shared modeling features or behaviors.
 */
public class PdfItemLocation extends Model {
    
    private final UnitOfLength xCoordination;
    private final UnitOfLength yCoordination;

    /**
     * Constructs a {@code PdfItemLocation} object with specified x and y coordinates in millimeters.
     *
     * @param xCoordination The x-coordinate in millimeters.
     * @param yCoordination The y-coordinate in millimeters.
     */
    public PdfItemLocation(final float xCoordination, final float yCoordination) {
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
