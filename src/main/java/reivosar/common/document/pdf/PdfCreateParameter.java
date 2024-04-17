package reivosar.common.document.pdf;

/**
 * Interface representing parameters for creating content in a PDF document.
 * It encapsulates the details necessary for appending content to a PDF, such as pages, items, and embedded text.
 */
public interface PdfCreateParameter {

    /**
     * Returns the {@code PdfPage} associated with the current parameters.
     * This method is intended to provide access to the page settings or content that will be added to the PDF.
     *
     * @return The {@code PdfPage} to be used or modified in the PDF document.
     */
    PdfPage pdfPage();

    /**
     * Returns the {@code PdfItem} associated with the current parameters.
     * {@code PdfItem} represents a specific content item, such as an image or a text block, that will be added to the PDF.
     *
     * @return The {@code PdfItem} to be added to the PDF document.
     */
    PdfItem pdfItem();

    /**
     * Returns the {@code EmbedText} instance associated with the current parameters.
     * This method is intended to provide access to text content that will be embedded in the PDF document.
     *
     * @return The {@code EmbedText} containing the text to be embedded in the PDF document.
     */
    EmbedText embedText();
}
