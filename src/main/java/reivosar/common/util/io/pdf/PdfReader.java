package reivosar.common.util.io.pdf;

import reivosar.common.util.lang.ObjectUtil;

import java.nio.file.Path;

/**
 * The PdfReader interface defines methods for opening and reading the contents
 * of PDF documents. It supports opening both password-protected and
 * non-protected PDF files.
 */
public interface PdfReader {

    /**
     * Opens a PDF document from the specified path without requiring a password.
     * This method should be used for non-password protected PDF documents.
     *
     * @param path The {@link Path} to the PDF document to be opened. Must not be null.
     * @return A {@link PdfReader} instance for the opened PDF document.
     * @throws NullPointerException if the provided path is null.
     */
    static PdfReader open(Path path) {
        ObjectUtil.requireNonNull("path", path);
        return PdfReaderFactory.getDefault(path);
    }

    /**
     * Opens a password-protected PDF document from the specified path using
     * the provided password. This method is intended for PDF documents that
     * are protected by a password.
     *
     * @param path        The {@link Path} to the PDF document to be opened. Must not be null.
     * @param pdfPassword The {@link PdfPassword} containing the password for the document. Must not be null.
     * @return A {@link PdfReader} instance capable of reading the opened PDF document.
     * @throws NullPointerException if either the path or pdfPassword is null.
     */
    static PdfReader open(Path path, PdfPassword pdfPassword) {
        ObjectUtil.requireNonNull("path", path);
        ObjectUtil.requireNonNull("pdfPassword", pdfPassword);
        return PdfReaderFactory.getDefault(path, pdfPassword);
    }

    /**
     * Reads the contents of the currently opened PDF document and returns
     * a {@link PdfDocumentInfo} object containing details such as text, images, and metadata.
     *
     * @return A {@link PdfDocumentInfo} instance containing information about the PDF document.
     */
    PdfDocumentInfo read();
}