package reivosar.common.util.io.pdf;

import reivosar.common.util.model.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a collection of pages within a PDF document, providing functionalities
 * to add page information, search text within pages, and retrieve specific page information.
 */
public class PdfPages extends Model {

    private final List<PdfPageInfo> pdfPageInfoList;

    PdfPages() {
        this.pdfPageInfoList = new ArrayList<>();
    }

    void addPageInfo(final PdfPageInfo pdfPageInfo) {
        this.pdfPageInfoList.add(pdfPageInfo);
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
        return pdfPageInfoList.stream()
                .filter(pageInfo -> pageInfo.pageNumber() == pageNumber)
                .findFirst();
    }

    /**
     * Gets the total number of pages in the collection.
     *
     * @return The total number of pages.
     */
    public int totalPages() {
        return pdfPageInfoList.size();
    }

    /**
     * Checks if any page in the document contains the specified text.
     *
     * @param searchText The text to search for across all pages.
     * @return {@code true} if the text is found in any page, {@code false} otherwise.
     */
    public boolean containsText(final String searchText) {
        return !findText(searchText).isEmpty();
    }

    /**
     * Searches for the specified text across all pages and returns a list of pages where the text is found.
     *
     * @param searchText The text to search for.
     * @return A collection of {@link PdfPageInfo} objects for pages that contain the specified text. The list is empty if the text is not found in any page.
     */
    public Collection<PdfPageInfo> findText(final String searchText) {
        return pdfPageInfoList.stream()
                .filter(pageInfo -> pageInfo.containsText(searchText))
                .collect(Collectors.toList());
    }
}
