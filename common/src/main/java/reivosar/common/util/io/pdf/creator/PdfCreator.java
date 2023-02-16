package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.lang.ObjectUtil;

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
     * Specifies object to embed in the PDF file.
     *
     * @param pdfCreateParameter parameter to embed in the PDF file
     * @return this
     */
    public PdfCreator append(final PdfCreateParameter pdfCreateParameter) {
        ObjectUtil.requireNonNull("pdfCreateParameter", pdfCreateParameter);
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
        ObjectUtil.requireNonNull("path", path);
        final PdfCreateInvokerSelector selector = new PdfCreateInvokerSelector(pdfTemplate, pdfCreateParameters);
        final PdfCreateInvoker invoker = selector.selectInvoker();
        return invoker.invoke(path);
    }
}
