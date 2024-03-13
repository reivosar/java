package reivosar.common.util.io.pdf;

import java.io.File;
import java.nio.file.Path;

final class PdfBoxPdfReader implements PdfReader {

    private final File file;
    private final String password;

    PdfBoxPdfReader(final Path path, final PdfPassword pdfPassword) {
        this.file = path.toFile();
        this.password = pdfPassword.value;
    }

    @Override
    public PdfDocumentInfo read() {
        return null;
    }
}
