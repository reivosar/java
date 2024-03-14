package reivosar.common.util.io.pdf;

import reivosar.common.util.lang.ObjectUtil;

import java.nio.file.Path;

/**
 * Interface for creating PDF documents. Provides methods to append content to a PDF and create the PDF file.
 * This interface uses a factory method pattern to obtain an instance of {@code PdfCreator}.
 */
public interface PdfCreator {

    /**
     * Obtains the default instance of {@code PdfCreator} using {@code PdfCreatorFactory}.
     *
     * @return The default {@code PdfCreator} instance.
     */
    static PdfCreator create() {
        return PdfCreatorFactory.gatDefault(null);
    }

    /**
     * Obtains an instance of {@code PdfCreator} configured with the specified {@code PdfTemplate}.
     *
     * @param pdfTemplate The {@code PdfTemplate} to configure the {@code PdfCreator} instance.
     * @return The configured {@code PdfCreator} instance.
     * @throws NullPointerException if the provided pdfTemplate is null.
     */
    static PdfCreator createWithTemplate(PdfTemplate pdfTemplate) {
        ObjectUtil.requireNonNull("pdfTemplate", pdfTemplate);
        return PdfCreatorFactory.gatDefault(pdfTemplate);
    }

    /**
     * Appends content to the PDF being created. This method allows for the addition of content
     * based on the {@code PdfCreateParameter} provided.
     *
     * @param pdfCreateParameter The parameters defining the content to be added to the PDF.
     * @return The {@code PdfCreator} instance for chaining method calls.
     */
    PdfCreator addContent(final PdfCreateParameter pdfCreateParameter);

    /**
     * Creates the PDF file at the specified path. This method finalizes the PDF creation process
     * and writes the generated PDF to the file system.
     *
     * @param path The path where the PDF file will be created.
     * @return {@code true} if the file was successfully created, {@code false} otherwise.
     */
    boolean saveAs(final Path path);
}

