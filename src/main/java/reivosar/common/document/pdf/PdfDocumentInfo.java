package reivosar.common.document.pdf;

import reivosar.common.data.model.Model;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents comprehensive information about a PDF document, including its metadata and pages.
 * This class serves as an aggregate of the document's metadata, provided by {@link PdfMetadata},
 * and the content of its pages, managed by {@link PdfPages}.
 */
public class PdfDocumentInfo extends Model {

    private final PdfMetadata pdfMetadata;
    private final PdfPages pdfPages;

    PdfDocumentInfo(final PdfMetadata pdfMetadata, final PdfPages pdfPages) {
        this.pdfMetadata = pdfMetadata;
        this.pdfPages = pdfPages;
    }

    /**
     * Retrieves the metadata of the PDF document.
     *
     * @return The metadata as a {@link PdfMetadata} instance.
     */
    public PdfMetadata metadata() {
        return pdfMetadata;
    }

    /**
     * Provides access to the pages of the PDF document.
     *
     * @return The pages as a {@link PdfPages} instance.
     */
    public PdfPages pages() {
        return pdfPages;
    }

    /**
     * Retrieves the information of a specific page by its page number.
     * <p>
     * This method searches through the collection of PdfPageInfo objects to find the one
     * with the matching page number. The search is not dependent on the order of pages in the collection.
     *
     * @param pageNumber The page number of the desired page information.
     * @return An {@link Optional} containing the {@link PdfPageInfo} of the specified page if it exists, or an empty {@link Optional} if there is no page with the specified number.
     */
    public Optional<PdfPageInfo> at(final int pageNumber) {
        return pages().at(pageNumber);
    }

    /**
     * Gets the total number of pages in the collection.
     *
     * @return The total number of pages.
     */
    public int totalPages() {
        return pages().totalPages();
    }

    /**
     * Checks if any page in the document contains the specified text.
     *
     * @param searchText The text to search for across all pages.
     * @return {@code true} if the text is found in any page, {@code false} otherwise.
     */
    public boolean containsText(final String searchText) {
        return pages().containsText(searchText);
    }

    /**
     * Searches for the specified text across all pages and returns a list of pages where the text is found.
     *
     * @param searchText The text to search for.
     * @return A collection of {@link PdfPageInfo} objects for pages that contain the specified text. The list is empty if the text is not found in any page.
     */
    public Collection<PdfPageInfo> findText(final String searchText) {
        return pages().findText(searchText);
    }

}
