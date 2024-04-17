package reivosar.common.document.pdf;

import reivosar.common.data.model.Model;

/**
 * Represents the size of an item in a PDF document, encapsulating its width and height dimensions.
 * This class extends {@code Model} to possibly leverage shared functionality or properties defined in the base class.
 * Dimensions are specified in millimeters and converted from and to internal representation as needed.
 */
public class PdfItemSize extends Model {

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
