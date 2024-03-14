package reivosar.common.util.io.pdf;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.Collection;

/**
 * Represents information about a single page within a PDF document.
 * This includes the page's text content, images, font names used,
 * as well as its dimensions.
 */
public class PdfPageInfo extends Model {
    private final int pageNumber;
    private final String text;
    private final Collection<byte[]> images;
    private final Collection<String> fontNames;
    private final float width;
    private final float height;

    PdfPageInfo(final int pageNumber,
                final String text,
                final Collection<byte[]> images,
                final Collection<String> fontNames,
                final float width,
                final float height) {
        this.pageNumber = pageNumber;
        this.text = text;
        this.images = images;
        this.fontNames = fontNames;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the page number.
     *
     * @return The page number.
     */
    public int pageNumber() {
        return pageNumber;
    }

    /**
     * Returns the text content of the page.
     *
     * @return The text content.
     */
    public String text() {
        return text;
    }

    /**
     * Checks if the page's text contains the specified search text.
     *
     * @param searchText The text to search for.
     * @return true if the page contains the search text, false otherwise.
     */
    public boolean containsText(final String searchText) {
        ObjectUtil.requireNonNull("searchText", searchText);
        return text.contains(searchText);
    }

    /**
     * Returns the images on the page.
     *
     * @return A collection of images as byte arrays.
     */
    public Collection<byte[]> images() {
        return images;
    }

    /**
     * Returns the names of fonts used on the page.
     *
     * @return A collection of font names.
     */
    public Collection<String> fontNames() {
        return fontNames;
    }

    /**
     * Returns the width of the page.
     *
     * @return The width of the page in points.
     */
    public float width() {
        return width;
    }

    /**
     * Returns the height of the page.
     *
     * @return The height of the page in points.
     */
    public float height() {
        return height;
    }
}
