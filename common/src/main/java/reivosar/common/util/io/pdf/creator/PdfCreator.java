package reivosar.common.util.io.pdf.creator;

import java.nio.file.Path;

/**
 * Generate PDF
 */
public final class PdfCreator {
    
    private final PdfTemplate pdfTemplate;
    private final PdfCreateParameters pdfCreateParameters;
    
    /**
     * Return new {@link PdfCreator} instance.
     *
     * @return {@link PdfCreator}
     */
    public static PdfCreator forCreatingNew() {
        return new PdfCreator(null);
    }
    
    private PdfCreator(final PdfTemplate pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
        this.pdfCreateParameters = new PdfCreateParameters();
    }
    
    /**
     * Specifies a text to embed in the PDF file.
     *
     * @param page     Number of pages to embed text
     * @param x        The x translation.
     * @param y        The y translation.
     * @param width    Vertical size of the target area for embedding text
     * @param height   Horizontal size of the target area for embedding text
     * @param fontName font name {@link FontType}
     * @param fontSize font size
     * @param align    {@link TextAlign}
     * @param text     Embedded text
     * @return this
     */
    public PdfCreator addText(final int page,
                              final float x,
                              final float y,
                              final float width,
                              final float height,
                              final String fontName,
                              final int fontSize,
                              final String align,
                              final Object text) {
        return append(new EmbedTextParameter.Builder()
                .page(page)
                .coordination(x, y)
                .areaSize(width, height)
                .font(fontName, fontSize)
                .align(align)
                .text(text)
                .build());
    }
    
    /**
     * Specifies object to embed in the PDF file.
     *
     * @param pdfCreateParameter parameter to embed in the PDF file
     * @return this
     */
    public PdfCreator append(final PdfCreateParameter pdfCreateParameter) {
        this.pdfCreateParameters.add(pdfCreateParameter);
        return this;
    }
    
    /**
     * Generates a PDF file in the specified path.
     *
     * @param path Path to generate PDF file
     * @return {@code true} If a PDF file is generated {@code false} otherwise
     */
    boolean createTo(final Path path) {
        final PdfCreateInvokerSelector selector = new PdfCreateInvokerSelector(pdfTemplate, pdfCreateParameters);
        final PdfCreateInvoker invoker = selector.selectInvoker();
        return invoker.invoke(path);
    }
}
