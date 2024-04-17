package reivosar.common.document.pdf;

/**
 * A record that represents an item in a PDF document, encompassing its location and size.
 * This record provides a concise way to package an item's dimensional properties and its position on the page.
 *
 * @param pdfItemLocation The location of the item within the PDF document, including x and y coordinates.
 * @param pdfItemSize The size of the item, including width and height.
 */
public record PdfItem (PdfItemLocation pdfItemLocation, PdfItemSize pdfItemSize) {
    
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
