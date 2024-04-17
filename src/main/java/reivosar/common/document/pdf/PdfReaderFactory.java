package reivosar.common.document.pdf;

import java.nio.file.Path;

class PdfReaderFactory {

    static PdfReader getDefault(Path path) {
        return new PdfBoxPdfReader(path, new PdfPassword(""));
    }
    static PdfReader getDefault(Path path, PdfPassword pdfPassword) {
        return new PdfBoxPdfReader(path, pdfPassword);
    }
}
