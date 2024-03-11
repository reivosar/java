package reivosar.common.util.io.pdf;

/**
 * A record that represents a specific page in a PDF document, identified by its page number.
 * This simple data structure is useful for referencing pages when constructing or modifying PDF documents.
 *
 * @param pageNumber The number of the page in the PDF document. Page numbers typically start from 1.
 */
public record PdfPage(int pageNumber) {
}
